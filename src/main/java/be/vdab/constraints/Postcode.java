package be.vdab.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;

import org.hibernate.validator.constraints.Range;

@Retention(RUNTIME)
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Constraint(validatedBy = {})
@Range(min = 1000, max=9999)
public @interface Postcode {
	@OverridesAttribute(constraint = Range.class, name= "message")
	String message() default "{be.vdab.constraints.Postcode}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
