package softuni.unisports.model.validators.multipartFile;


import org.springframework.web.multipart.MultipartFile;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MultipartNotNullValidator.class)
public @interface MultiPartNotNull {

    String message() default "No file selected to upload";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
