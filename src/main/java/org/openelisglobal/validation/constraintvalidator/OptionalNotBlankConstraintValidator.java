package org.openelisglobal.validation.constraintvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.openelisglobal.common.formfields.FormFields;
import org.openelisglobal.common.util.validator.GenericValidator;
import org.openelisglobal.validation.annotations.OptionalNotBlank;

public class OptionalNotBlankConstraintValidator implements ConstraintValidator<OptionalNotBlank, String> {

    private FormFields.Field[] fields;

    @Override
    public void initialize(OptionalNotBlank constraint) {
        fields = constraint.formFields();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean valid = true;
        for (FormFields.Field field : fields) {
            if (FormFields.getInstance().useField(field)) {
                valid &= !GenericValidator.isBlankOrNull(value);
            }
        }
        return valid;
    }
}
