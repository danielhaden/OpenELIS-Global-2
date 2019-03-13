package spring.mine.common.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;

import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.util.validator.GenericValidator;

public class ValidationHelper {

	// prevents people constructing a helper object
	private ValidationHelper() {

	}

	private static final String DEFAULT_PREFIX = "Field ";

	/*
	 * STRING METHODS
	 */

	public static void validateFieldRequired(String value, String name, String displayName, Errors errors) {
		if (GenericValidator.isBlankOrNull(value)) {
			errors.rejectValue(name, "error.field.required", new Object[] { displayName },
					DEFAULT_PREFIX + displayName + " is required");
		}
	}

	public static void validateFieldLengthMax(String value, String name, String displayName, Errors errors, int max) {
		int length = 0;
		if (value != null) {
			length = value.length();
		}
		if (length > max) {
			errors.rejectValue(name, "error.field.length.long", new Object[] { displayName, Integer.toString(max) },
					DEFAULT_PREFIX + displayName + " has too many characters");
		}
	}

	public static void validateFieldLengthMin(String value, String name, String displayName, Errors errors, int min) {
		int length = 0;
		if (value != null) {
			length = value.length();
		}
		if (length < min) {
			errors.rejectValue(name, "error.field.length.short", new Object[] { displayName, Integer.toString(min) },
					DEFAULT_PREFIX + displayName + " has too few characters");
		}
	}

	public static void validateFieldMatchRegex(String value, String name, String displayName, Errors errors,
			String regex) {
		if (!Pattern.matches(regex, value)) {
			errors.rejectValue(name, "error.field.format.invalid", new Object[] { displayName },
					DEFAULT_PREFIX + displayName + " is an invalid format");
		}
	}

	public static void validateFieldCharSet(String value, String name, String displayName, Errors errors,
			String charSet) {
		if (!Pattern.matches("[" + charSet + "]*", value)) {
			errors.rejectValue(name, "error.field.charset.invalid", new Object[] { displayName },
					DEFAULT_PREFIX + displayName + " has an invalid character");
		}
	}

	public static void validateFieldLength(String value, String name, String displayName, Errors errors, int min,
			int max) {
		validateFieldLengthMax(value, name, displayName, errors, max);
		validateFieldLengthMin(value, name, displayName, errors, min);
	}

	public static void validateField(String value, String name, String displayName, Errors errors, boolean required,
			int maxLength) {
		if (required) {
			validateFieldRequired(value, name, displayName, errors);
		}
		validateFieldLengthMax(value, name, displayName, errors, maxLength);
	}

	public static void validateField(String value, String name, String displayName, Errors errors, boolean required,
			int maxLength, String regex) {
		validateField(value, name, displayName, errors, required, maxLength);
		validateFieldMatchRegex(value, name, displayName, errors, regex);
	}

	public static void validateFieldAndCharset(String value, String name, String displayName, Errors errors,
			boolean required, int maxLength, String charSetRegex) {
		validateField(value, name, displayName, errors, required, maxLength);
		validateFieldCharSet(value, name, displayName, errors, charSetRegex);
	}

	public static void validateIdField(String value, String name, String displayName, Errors errors, boolean required) {
		validateField(value, name, displayName, errors, required, 10, "[0-9]+");
	}

	public static void validateYNField(String value, String name, String displayName, Errors errors) {
		validateOptionField(value, name, displayName, errors,
				new String[] { IActionConstants.YES, IActionConstants.NO });
	}

	// methods for using name = displayName

	public static void validateFieldRequired(String value, String name, Errors errors) {
		validateFieldRequired(value, name, name, errors);
	}

	public static void validateFieldLengthMax(String value, String name, Errors errors, int max) {
		validateFieldLengthMax(value, name, name, errors, max);
	}

	public static void validateFieldLengthMin(String value, String name, Errors errors, int min) {
		validateFieldLengthMin(value, name, name, errors, min);
	}

	public static void validateFieldMatchRegex(String value, String name, Errors errors, String regex) {
		validateFieldMatchRegex(value, name, name, errors, regex);
	}

	public static void validateFieldCharSet(String value, String name, Errors errors, String charSet) {
		validateFieldCharSet(value, name, name, errors, charSet);
	}

	public static void validateFieldLength(String value, String name, Errors errors, int min, int max) {
		validateFieldLength(value, name, name, errors, min, max);
	}

	public static void validateField(String value, String name, Errors errors, boolean required, int maxLength) {
		validateField(value, name, name, errors, required, maxLength);
	}

	public static void validateField(String value, String name, Errors errors, boolean required, int maxLength,
			String regex) {
		validateField(value, name, name, errors, required, maxLength, regex);
	}

	public static void validateFieldAndCharset(String value, String name, Errors errors, boolean required,
			int maxLength, String charSetRegex) {
		validateFieldAndCharset(value, name, name, errors, required, maxLength, charSetRegex);
	}

	public static void validateIdField(String value, String name, Errors errors, boolean required) {
		validateIdField(value, name, name, errors, required);
	}

	public static void validateYNField(String value, String name, Errors errors) {
		validateYNField(value, name, name, errors);
	}

	/*
	 * STRING[] METHODS
	 */

	public static void validateFieldRequired(String[] value, String name, String displayName, Errors errors) {
		if (value == null || value.length == 0) {
			errors.rejectValue(name, "error.field.required", new Object[] { displayName },
					DEFAULT_PREFIX + displayName + " is required");
		}
	}

	// methods for using name = displayName

	public static void validateFieldRequired(String[] value, String name, Errors errors) {
		validateFieldRequired(value, name, name, errors);
	}

	/*
	 * INTEGER METHODS
	 */

	public static void validateFieldMax(Integer value, String name, String displayName, Errors errors, int max) {
		if (value > max) {
			errors.rejectValue(name, "error.field.max", new String[] { displayName }, "Field too large an integer");
		}
	}

	public static void validateFieldMin(Integer value, String name, String displayName, Errors errors, int min) {
		if (value < min) {
			errors.rejectValue(name, "error.field.min", new String[] { displayName }, "Field too small an integer");
		}
	}

	public static void validateFieldMinMax(Integer value, String name, String displayName, Errors errors, int min,
			int max) {
		validateFieldMin(value, name, displayName, errors, min);
		validateFieldMax(value, name, displayName, errors, max);
	}

	// methods for using name = displayName

	public static void validateFieldMax(Integer value, String name, Errors errors, int max) {
		validateFieldMax(value, name, name, errors, max);
	}

	public static void validateFieldMin(Integer value, String name, Errors errors, int min) {
		validateFieldMin(value, name, name, errors, min);
	}

	public static void validateFieldMinMax(Integer value, String name, Errors errors, int min, int max) {
		validateFieldMinMax(value, name, name, errors, min, max);
	}

	/*
	 * FLOAT METHODS
	 */

	public static void validateFieldMax(Float value, String name, String displayName, Errors errors, float max) {
		if (value > max) {
			errors.rejectValue(name, "error.field.max", new String[] { displayName }, "Field too large a number");
		}
	}

	public static void validateFieldMin(Float value, String name, String displayName, Errors errors, float min) {
		if (value < min) {
			errors.rejectValue(name, "error.field.min", new String[] { displayName }, "Field too small a number");
		}
	}

	public static void validateFieldMinMax(Float value, String name, String displayName, Errors errors, float min,
			float max) {
		validateFieldMin(value, name, displayName, errors, min);
		validateFieldMax(value, name, displayName, errors, max);
	}

	// methods for using name = displayName

	public static void validateFieldMax(Float value, String name, Errors errors, float max) {
		validateFieldMax(value, name, name, errors, max);
	}

	public static void validateFieldMin(Float value, String name, Errors errors, float min) {
		validateFieldMin(value, name, name, errors, min);
	}

	public static void validateFieldMinMax(Float value, String name, Errors errors, float min, float max) {
		validateFieldMinMax(value, name, name, errors, min, max);
	}

	/*
	 * BOOLEAN METHODS
	 */

	public static void validateTFField(Boolean value, String name, String displayName, Errors errors,
			boolean required) {
		Boolean[] options;
		if (required) {
			options = new Boolean[] { true, false };
		} else {
			options = new Boolean[] { true, false, null };
		}
		validateOptionField(value, name, displayName, errors, options);
	}

	// methods for using name = displayName

	public static void validateTFField(Boolean value, String name, Errors errors, boolean required) {
		validateTFField(value, name, name, errors, required);
	}

	/*
	 * OBJECT METHODS
	 */

	public static void validateOptionField(Object value, String name, String displayName, Errors errors,
			Object[] possibleValues) {
		boolean match = false;
		for (Object possibleValue : possibleValues) {
			// null safety
			if (possibleValue == null) {
				if (value == null) {
					match = true;
				}
			} else {
				if (possibleValue.equals(value)) {
					match = true;
				}
			}
			if (match) {
				break;
			}
		}
		if (!match) {
			errors.rejectValue(name, "error.field.option.invalid", new Object[] { displayName },
					DEFAULT_PREFIX + displayName + " is not a valid option");
		}
	}

	// methods for using name = displayName

	public static void validateOptionField(Object value, String name, Errors errors, Object[] possibleValues) {
		validateOptionField(value, name, name, errors, possibleValues);
	}
}