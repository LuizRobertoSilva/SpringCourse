package com.lrsilva.projetospring.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lrsilva.projetospring.domain.Client;
import com.lrsilva.projetospring.domain.OrderItem;
import com.lrsilva.projetospring.domain.OrderT;
import com.lrsilva.projetospring.domain.PaymentWithTicket;
import com.lrsilva.projetospring.domain.enums.PaymentState;
import com.lrsilva.projetospring.repositories.OrderItemRepository;
import com.lrsilva.projetospring.repositories.OrderRepository;
import com.lrsilva.projetospring.repositories.PaymentRepository;
import com.lrsilva.projetospring.security.UserSS;
import com.lrsilva.projetospring.services.exceptions.AuthorizationException;
import com.lrsilva.projetospring.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;

	@Autowired
	private TicketService ticketService;

	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private ClientService clientService;
	@Autowired
	private EmailService emailService;

	public OrderT find(Integer id) {
		Optional<OrderT> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type:" + OrderT.class.getName()));
	}

	public List<OrderT> findAll() {
		List<OrderT> obj = repo.findAll();
		return obj;
	}

	@Transactional
	public OrderT insert(OrderT obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.setClient(clientService.find(obj.getClient().getId()));
		obj.getPayment().setState(PaymentState.PENDING);
		obj.getPayment().setOrder(obj);
		if (obj.getPayment() instanceof PaymentWithTicket) {
			PaymentWithTicket payment = (PaymentWithTicket) obj.getPayment();
			ticketService.fillPaymentWithTicket(payment, obj.getInstant());

		}
		obj = repo.save(obj);
		paymentRepository.save(obj.getPayment());
		for (OrderItem oi : obj.getItems()) {
			oi.setDiscount(0.00);
			oi.setProduct(productService.find(oi.getProduct().getId()));
			oi.setPrice(oi.getProduct().getPrice());
			oi.setOrderT(obj);
		}
		orderItemRepository.saveAll(obj.getItems());

		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}
	
	public Page<OrderT> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Access denied");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Client client =  clientService.find(user.getId());
		return repo.findByClient(client, pageRequest);
	}

}
