package com.lrsilva.projetospring.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.lrsilva.projetospring.domain.enums.PaymentState;

@Entity
@JsonTypeName("paymentWithTicket")
public class PaymentWithTicket extends Payment {
	private static final long serialVersionUID = 1L;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date dueDate;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date paymentDate;

	public PaymentWithTicket() {
		super();
	}

	public PaymentWithTicket(Integer id, PaymentState state, OrderT order, Date dueDate, Date paymentDate) {
		super(id, state, order);
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

}
