package com.example.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailConstraintValidator implements ConstraintValidator<EmailAnnotation, String> {

	@Override
	public void initialize(EmailAnnotation string) {

	}

	@Override
	public boolean isValid(String customField, ConstraintValidatorContext ctx) {

		if (customField == null) {
			return false;
		}
		return customField.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
	}

}
