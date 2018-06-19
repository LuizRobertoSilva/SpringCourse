package com.lrsilva.projetospring.domain.enums;

public enum PaymentState {

	PENDING(1, "Pending"), SETTLED(2, "Settled"), CANCELED(3, "Canceled");

	private int id;
	private String description;

	private PaymentState(int id, String description) {
		this.id = id;
		this.description = description;

	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static PaymentState toEnum(Integer id) {
		if (id == null)
			return null;

		for (PaymentState x : PaymentState.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID: " + id);

	};

}
