package com.lrsilva.projetospring.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lrsilva.projetospring.domain.Address;
import com.lrsilva.projetospring.domain.Category;
import com.lrsilva.projetospring.domain.City;
import com.lrsilva.projetospring.domain.Client;
import com.lrsilva.projetospring.domain.OrderItem;
import com.lrsilva.projetospring.domain.OrderT;
import com.lrsilva.projetospring.domain.Payment;
import com.lrsilva.projetospring.domain.PaymentWithCard;
import com.lrsilva.projetospring.domain.PaymentWithTicket;
import com.lrsilva.projetospring.domain.Product;
import com.lrsilva.projetospring.domain.State;
import com.lrsilva.projetospring.domain.enums.ClientType;
import com.lrsilva.projetospring.domain.enums.PaymentState;
import com.lrsilva.projetospring.repositories.AddressRepository;
import com.lrsilva.projetospring.repositories.CategoryRepository;
import com.lrsilva.projetospring.repositories.CityRepository;
import com.lrsilva.projetospring.repositories.ClientRepository;
import com.lrsilva.projetospring.repositories.OrderItemRepository;
import com.lrsilva.projetospring.repositories.OrderRepository;
import com.lrsilva.projetospring.repositories.PaymentRepository;
import com.lrsilva.projetospring.repositories.ProductRepository;
import com.lrsilva.projetospring.repositories.StateRepository;

@Service
public class DBService {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;

	public void instantiateDataBase() throws ParseException {
		Category cat1 = new Category(null, "IT");
		Category cat2 = new Category(null, "Office");

		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		State est1 = new State(null, "Minas Gerais");
		State est2 = new State(null, "São Paulo");

		City c1 = new City(null, "Ubelândia", est1);
		City c2 = new City(null, "São Paulo", est2);
		City c3 = new City(null, "Campinas", est2);

		est1.getCities().addAll(Arrays.asList(c1));
		est2.getCities().addAll(Arrays.asList(c2, c3));

		stateRepository.saveAll(Arrays.asList(est1, est2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

		Client cli1 = new Client(null, "Maria Silva", "robertolg.luiz@gmail.com", "31245685423", ClientType.PHYSICALPERSON);

		cli1.getTelephones().addAll(Arrays.asList("2723562353", "938445016"));

		Address ad1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "382208834", cli1, c1);
		Address ad2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "326541522", cli1, c2);

		cli1.getAddresses().addAll(Arrays.asList(ad1, ad2));

		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(ad1, ad2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		OrderT or1 = new OrderT(null, sdf.parse("30/09/2017 12:44"), cli1, ad1);
		OrderT or2 = new OrderT(null, sdf.parse("10/10/2017 19:44"), cli1, ad2);

		Payment pay1 = new PaymentWithCard(null, PaymentState.SETTLED, or1, 6);

		or1.setPayment(pay1);

		Payment pay2 = new PaymentWithTicket(null, PaymentState.PENDING, or2, sdf.parse("20/10/2017 00:00"), null);

		or2.setPayment(pay2);

		cli1.getOrders().addAll(Arrays.asList(or1, or2));

		orderRepository.saveAll(Arrays.asList(or1, or2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));

		OrderItem oi1 = new OrderItem(or1, p1, 0.00, 1, 2000.00);
		OrderItem oi2 = new OrderItem(or1, p3, 0.00, 2, 80.00);
		OrderItem oi3 = new OrderItem(or2, p2, 1000.00, 1, 800.00);

		or1.getItems().addAll(Arrays.asList(oi1, oi2));
		or2.getItems().addAll(Arrays.asList(oi3));

		p1.getItems().addAll(Arrays.asList(oi1));
		p2.getItems().addAll(Arrays.asList(oi3));
		p3.getItems().addAll(Arrays.asList(oi2));
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));

	}
}
