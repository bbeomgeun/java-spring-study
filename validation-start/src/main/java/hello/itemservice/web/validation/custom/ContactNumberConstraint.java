package hello.itemservice.web.validation.custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ContactNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ContactNumberConstraint {
    String message() default "유효하지 않은 전화번호 입력입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
