package ua.com.hav.pb.validator;

import org.junit.Before;
import org.junit.Test;
import ua.com.hav.pb.domain.Contact;
import ua.com.hav.pb.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by sunny on 23.07.2017.
 */
public class ContactValidatorTest {

    List<Contact> contacts;
    int[] results;
    List<List<String>> names;

    @Before
    public void setUp() {
        contacts = new ArrayList<Contact>(){{
            add(new Contact(null, "name", "lastname", "fathers", "+38(066)1234567", "", "", ""));
            add(new Contact(null, "name", "lastname1111", "fathers", "+38(066)1234567", "", "", ""));
            add(new Contact(null, "name", "lastname1111", "fathers", "+38(056)1234567", "", "", ""));
            add(new Contact(null, "name", "lastname", "fathers", "+38(066)1234567", "fff", "", ""));
            add(new Contact(null, "ne", "lastname", "fathers", "+38(066)1234567", "", "", ""));
            add(new Contact(null, "name", "lastname", "fathers", "+38(066)1234567", "+38(050)0000000",
                    "jhbhbhbhjhvhvhvibdcebdiucbhebufhbcuhebuchbeuhbcwbchbeihcbwhbdhcuvcuevduhcbehdbcueduceducvuhe", "hhh"));
        }};
        results = new int[] {0, 1, 2, 1, 1, 3};
        names = new ArrayList<List<String>>(){{
                add(asList(""));
                add(asList("firstName"));
                add(asList("firstName", "mobile"));
                add(asList("home"));
                add(asList("lastName"));
                add(asList("email", "address", "home"));
            }};

    }

    @Test
    public void validator_test() {
        int i = 0;
        for (Contact c : contacts) {
            Map<String, String> map = ReflectionValidator.validate(c);
            assertThat(map.size(), is(results[i]));
            for (String s : names.get(i++)) {
                if (!s.equals("")) {
                    assertThat(map.get(s), is(notNullValue()));
                }
            }
        }
    }

}
