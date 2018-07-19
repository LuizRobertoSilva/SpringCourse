package com.lrsilva.projetospring.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.lrsilva.projetospring.services.validation.ClientInsert;

@ClientInsert
public class ClientNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Required field")
	@Length(min = 5, message = "Length must be between 5 and 120 characters")
	private String name;

	@NotEmpty(message = "Required field")
	@Email(message = "Invalid e-mail")
	private String email;
	@NotEmpty(message = "Required field")
	private String password;

	@NotEmpty(message = "Required field")
	private String cpfOrCnpj;

	private Integer type;

	private String street;
	private String number;
	private String complement;
	private String neighbourhood;
	private String zipCode;
	@NotEmpty(message = "Required field")
	private String telphone1;
	private String telphone2;
	private String telphone3;

	private Integer cityId;

	public ClientNewDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}

	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTelphone1() {
		return telphone1;
	}

	public void setTelphone1(String telphone1) {
		this.telphone1 = telphone1;
	}

	public String getTelphone2() {
		return telphone2;
	}

	public void setTelphone2(String telphone2) {
		this.telphone2 = telphone2;
	}

	public String getTelphone3() {
		return telphone3;
	}

	public void setTelphone3(String telphone3) {
		this.telphone3 = telphone3;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
