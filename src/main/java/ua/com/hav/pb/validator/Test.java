package ua.com.hav.pb.validator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by yulia on 18.07.2017.
 */
public class Test {

    private String name;

    @Size(min = 4, max = 7, message = "Size must be between 4 and 7")
    private String phone;

    @Pattern(regexp = "^.+@.+\\..+$", message = "Must match: ^.+@.+\\..+$")
    private String email;

    @NotNull(message = "Must not be null")
    private String address;

    public Test(String name, String phone, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
