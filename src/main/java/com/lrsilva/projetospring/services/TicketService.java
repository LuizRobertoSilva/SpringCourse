package com.lrsilva.projetospring.services;

import java.util.Date;
import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.lrsilva.projetospring.domain.PaymentWithTicket;

@Service
public class TicketService {

	public void fillPaymentWithTicket(PaymentWithTicket pay, Date instant) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pay.setDueDate(cal.getTime());
	}
}
