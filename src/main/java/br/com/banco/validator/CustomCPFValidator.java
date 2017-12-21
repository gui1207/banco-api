package br.com.banco.validator;

import java.util.Map;

import org.mule.api.MuleEvent;
import org.mule.extension.validation.api.ValidationResult;
import org.mule.extension.validation.api.Validator;
import org.mule.extension.validation.internal.ImmutableValidationResult;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class CustomCPFValidator implements Validator {

	@SuppressWarnings("unchecked")
	@Override
	public ValidationResult validate(MuleEvent event) {
		Object payload = event.getMessage().getPayload();
		String cpf = null;
		if (payload instanceof Map<?, ?>) {
			Map<Object, Object> map = (Map<Object, Object>) payload;
			cpf = (String) map.get("cpf");
		} else if (payload instanceof String) {
			cpf = (String) payload;
		}
		
		if (cpf == null) {
			return ImmutableValidationResult.error("CPF não encontrado para validação!");
		}
		
		try {
			new CPFValidator().assertValid(cpf);
		} catch (InvalidStateException e) {
			return ImmutableValidationResult.error("CPF inválido!");
		}
		
		
		return ImmutableValidationResult.ok();
	}

}
