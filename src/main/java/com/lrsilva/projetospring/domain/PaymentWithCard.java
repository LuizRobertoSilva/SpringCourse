package com.lrsilva.projetospring.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.lrsilva.projetospring.domain.enums.PaymentState;

@Entity
@JsonTypeName("paymentWithCard")
public class PaymentWithCard extends Payment {
	private static final long serialVersionUID = 1L;
	private Integer installmentsNumber;

	public PaymentWithCard() {
		super();

	}

	public PaymentWithCard(Integer id, PaymentState state, OrderT order, Integer installmentsNumber) {
		super(id, state, order);
		this.installmentsNumber = installmentsNumber;
	}

	public Integer getInstallmentsNumber() {
		return installmentsNumber;
	}

	public void setInstallmentsNumber(Integer installmentsNumber) {
		this.installmentsNumber = installmentsNumber;
	}

}
