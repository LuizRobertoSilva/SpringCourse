package com.lrsilva.projetospring.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.lrsilva.projetospring.domain.Client;
import com.lrsilva.projetospring.domain.enums.ClientType;
import com.lrsilva.projetospring.dto.ClientNewDTO;
import com.lrsilva.projetospring.repositories.ClientRepository;
import com.lrsilva.projetospring.resources.exceptions.FieldMessage;
import com.lrsilva.projetospring.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {

	@Autowired
	private ClientRepository repo;
	
	@Override
	public void initialize(ClientInsert ann) {

	}

	@Override
	public boolean isValid(ClientNewDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (value.getType().equals(ClientType.PHYSICALPERSON.getId()) && !BR.isValidCPF(value.getCpfOrCnpj())) {
			list.add(new FieldMessage("CpfOrCnpj", "Invalid CPF"));
		}
		if (value.getType().equals(ClientType.LEGALPERSON.getId()) && !BR.isValidCNPJ(value.getCpfOrCnpj())) {
			list.add(new FieldMessage("CpfOrCnpj", "Invalid CNPJ"));
		}
		
		Client aux = repo.findByEmail(value.getEmail());
		if(aux != null)
		{
			list.add(new FieldMessage("Email", "Email already exist"));
		}
		

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
