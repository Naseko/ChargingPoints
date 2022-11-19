package org.agafvic.chargepoints.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.agafvic.chargepoints.config.Constants.VALIDATION_MSG_UUID;

@Target(ElementType.FIELD)
@Constraint(validatedBy={})
@Retention(RUNTIME)
@Pattern(regexp="^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$")
@ReportAsSingleViolation
public @interface UUID {
    String message() default VALIDATION_MSG_UUID;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}