package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

class ValidationTest {

    @Test
    void testValidate() {
        Address address1 = new Address("Russia", "Ufa", "Lenina", "54", null);
        List<String> result1 = Validator.validate(address1);
        List<String> expected1 = List.of();
        assertThat(result1).isEqualTo(expected1);

        Address address2 = new Address(null, "London", "1-st street", "5", "1");
        List<String> result2 = Validator.validate(address2);
        List<String> expected2 = List.of("country");
        assertThat(result2).isEqualTo(expected2);

        Address address3 = new Address("USA", null, null, null, "1");
        List<String> result3 = Validator.validate(address3);
        List<String> expected3 = List.of("city", "street", "houseNumber");
        assertThat(result3).isEqualTo(expected3);
    }

    // BEGIN
    @Test
    void testAdvancedValidate() {
        Address validAddress = new Address("Germany", "Vancouver", "Main St", "25", "301");
        Map<String, String> errors = Validator.advancedValidate(validAddress);
        assertFalse(errors.containsKey("country"), "Карта ошибок не должна содержать ошибку для 'country'");
    }

    @Test
    void testAdvancedValidateWithErrors() {
        Address addressWithShortCountry = new Address("Ab", "Vancouver", null, "25", "301");
        Map<String, String> errors = Validator.advancedValidate(addressWithShortCountry);

        assertTrue(errors.containsKey("country"), "Карта ошибок должна содержать ошибку для 'country'");
        assertEquals("length less than 3", errors.get("country"), "Сообщение об ошибке для 'country' должно указывать на нарушение минимальной длины 3");
        assertTrue(errors.containsKey("street"), "Карта ошибок должна содержать ошибку для 'street'");
        assertEquals("can not be null", errors.get("street"), "Сообщение об ошибке для 'street' должно быть 'can not be null'");
        assertEquals(2, errors.size(), "Карта ошибок должна содержать только две ошибки");
    }
    // END
}
