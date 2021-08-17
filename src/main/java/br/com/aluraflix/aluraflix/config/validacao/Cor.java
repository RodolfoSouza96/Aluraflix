package br.com.aluraflix.aluraflix.config.validacao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CorValidacao.class)
public @interface Cor {
		String message() default "Favor digitar um código em HexaDecimal Ex:#1C1C1C";

	    Class<?>[] groups() default {};

	    Class<? extends Payload>[] payload() default {};
	
	
}
