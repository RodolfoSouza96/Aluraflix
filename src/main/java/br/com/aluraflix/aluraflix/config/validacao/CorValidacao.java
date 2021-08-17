package br.com.aluraflix.aluraflix.config.validacao;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CorValidacao implements ConstraintValidator<Cor, String>{

	public void initialize(Cor cor){	
		cor.message();
	}
	@Override
	public boolean isValid(String cor, ConstraintValidatorContext context) {
		Boolean Hexa = true;
		if(cor != null && !cor.trim().isEmpty()) {
			Hexa = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$").matcher(cor).matches();
		}
		return Hexa;
	}

}

