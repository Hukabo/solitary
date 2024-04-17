package solo.Project.Solitary.response;

import jakarta.validation.ConstraintViolation;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public class ConstraintViolationError implements Error {
    private String field;
    private String rejectedValue;
    private String reason;

    private ConstraintViolationError(String propertyPath, String rejectedValue, String reason) {
        this.field = propertyPath;
        this.rejectedValue = rejectedValue;
        this.reason = reason;
    }

    public static List<ConstraintViolationError> of(Set<ConstraintViolation<?>> constraintViolations) {
        return Error.from(constraintViolations,
                constraintViolation -> new ConstraintViolationError(
                        constraintViolation.getPropertyPath().toString(),
                        constraintViolation.getInvalidValue().toString(),
                        constraintViolation.getMessage()
                ));
    }
}
