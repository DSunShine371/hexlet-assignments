package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        List<String> errors = new ArrayList<>();
        Class<?> adClass = address.getClass();
        Field[] fields = adClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(address);

                    if (value == null) {
                        errors.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    System.err.println("Error accessing field " + field.getName() + ": " + e.getMessage());
                } finally {
                    field.setAccessible(false);
                }
            }
        }
        return errors;
    }

    public static Map<String, String> advancedValidate(Address address) {
        Map<String, String> errors = new HashMap<>();
        Class<?> adClass = address.getClass();
        Field[] fields = adClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            try {
                Object value = field.get(address);

                if (field.isAnnotationPresent(NotNull.class)) {
                    if (value == null) {
                        errors.put(field.getName(), "can not be null");
                        continue;
                    }
                }
                if (field.isAnnotationPresent(MinLength.class)) {
                    if (value != null) {
                        MinLength minLengthAnnotation = field.getAnnotation(MinLength.class);
                        String stringValue = (String) value;
                        if (stringValue.length() < minLengthAnnotation.minLength()) {
                            errors.put(field.getName(), "length less than " + minLengthAnnotation.minLength());
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                System.err.println("Error accessing field " + field.getName() + ": " + e.getMessage());
            } finally {
                field.setAccessible(false);
            }
        }
        return errors;
    }
}
// END
