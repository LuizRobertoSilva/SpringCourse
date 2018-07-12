package com.lrsilva.projetospring.domain.enums;

public enum Profile {

	ADMIN(1, "ROLE_ADMIN"), CLIENT(2, "ROLE_CLIENT");

	private int id;
	private String description;

	private Profile(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static Profile toEnum(Integer id) {
		if (id == null)
			return null;

		for (Profile x : Profile.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID: " + id);

	};

}
