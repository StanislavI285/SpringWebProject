package softuni.unisports.model.validators.multipartFile;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.File;

public class MultipartNotNullValidator implements ConstraintValidator<MultiPartNotNull, MultipartFile> {

    private MultipartFile multipartFile;

    @Override
    public void initialize(MultiPartNotNull constraintAnnotation) {

    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        return !multipartFile.isEmpty();
    }
}
