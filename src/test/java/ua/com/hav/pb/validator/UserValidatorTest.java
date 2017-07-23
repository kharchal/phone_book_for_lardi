package ua.com.hav.pb.validator;

import org.junit.Before;
import org.junit.Test;
import ua.com.hav.pb.domain.User;
import ua.com.hav.pb.validator.ReflectionValidator;

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
public class UserValidatorTest {

    List<User> users;
    int[] results;
    List<List<String>> names;

    @Before
    public void setUp() {
        users = new ArrayList<User>(){{
            add(new User("23345", "ghhhhh", "hbbhh"));
            add(new User("abcdjkkjhkkihih", "ghhhhh", "hbbhh"));
            add(new User("abyrv", "3434", "d"));
            add(new User("Log", "ghhhhh", "hbbhh"));
        }};
        results = new int[] {1, 1, 2, 0};
        names = new ArrayList<List<String>>(){{
                add(asList("login"));
                add(asList("login"));
                add(asList("password", "name"));
                add(asList(""));
            }};

    }

    @Test
    public void validator_test() {
        int i = 0;
        for (User u : users) {
            Map<String, String> map = ReflectionValidator.validate(u);
            assertThat(map.size(), is(results[i]));
            for (String s : names.get(i++)) {
                if (!s.equals("")) {
                    assertThat(map.get(s), is(notNullValue()));
                }
            }
        }
    }

}
