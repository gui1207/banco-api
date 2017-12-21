package br.com.banco.validator;

import org.apache.commons.lang3.StringUtils;
import org.mule.api.MuleEvent;
import org.mule.extension.validation.api.ValidationResult;
import org.mule.extension.validation.api.Validator;
import org.mule.extension.validation.internal.ImmutableValidationResult;
import org.mule.extension.validation.internal.ValidationContext;
import org.mule.extension.validation.internal.ValidationMessages;
import org.mule.extension.validation.internal.ValidationOptions;
import org.mule.extension.validation.internal.validator.EmptyValidator;

public class CustomSelectValidator implements Validator {
	
	@Override
	public ValidationResult validate(MuleEvent event) {
		Object payload = event.getMessage().getPayload();
		
		ValidationResult emptyValidationResult = new EmptyValidator(payload, new ValidationContext(new ValidationMessages(), new ValidationOptions(), event)).validate(event);
		String whereCpf = event.getMessage().getInvocationProperty("whereCpf", new String(""));
		String whereId = event.getMessage().getInvocationProperty("whereId", new String(""));
		String whereIdConta = event.getMessage().getInvocationProperty("whereIdConta", new String(""));
				
		if ((StringUtils.isNotBlank(whereCpf) || StringUtils.isNotBlank(whereId) || StringUtils.isNotBlank(whereIdConta))
				&& !emptyValidationResult.isError()) {
			return ImmutableValidationResult.error("Nenhum registro encontrado");
		} else {
			return ImmutableValidationResult.ok();
		}
		
	}

}
