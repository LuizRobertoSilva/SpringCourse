package com.lrsilva.projetospring.domain.enums;

public enum ClientType {

	PHYSICALPERSON(1, "Physical Person"), LEGALPERSON(2, "Legal Person");

	private int id;
	private String description;

	private ClientType(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static ClientType toEnum(Integer id) {
		if (id == null)
			return null;

		for (ClientType x : ClientType.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID: " + id);

	};

}
