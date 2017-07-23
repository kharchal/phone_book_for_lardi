package ua.com.hav.pb.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by sunny on 12.07.2017.
 */
public class Contact {

    private Long id;

    @Size(min = 4, max = 10, message = "wrong length (4-10)")
    private String lastName;

    @Size(min = 4, max = 10, message = "wrong length (4-10)")
    private String firstName;

    @Size(min = 4, max = 10, message = "wrong length (4-10)")
    private String middleName;

    @Pattern(regexp = "^\\+38\\(0(39|50|6[36-8]|73|9[1-9])\\)[0-9]{7}$",
            message = "+38(0XX)XXXXXXX or wrong mobile number")
    @NotNull(message = "must not be empty")
    private String mobile;

    @Null(message = "?..")
    @Pattern(regexp = "^\\+38\\(0(3[1-8]|4[1346-8]|5[1-7]|6[12459]|[7-9]0)\\)[0-9]{7}$",
            message = "+38(0XX)XXXXXXX or wrong phone number")
    private String home;

    @Size(max = 35, message = "too long")
    private String address;

    @Null(message = "?...")
    @Pattern(regexp = "^.+@.+\\..+$", message = "use the pattern: xxx@yyy.zzz")
    @Size(max = 20, message = "too long")
    private String email;
    private Long userId;

    public Contact() {
    }

    public Contact(Long id, String lastName, String firstName, String middleName, String mobile, String home, String address, String email) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.mobile = mobile;
        this.home = home;
        this.address = address;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return id != null ? id.equals(contact.id) : contact.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", home='" + home + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", userId=" + userId +
                '}';
    }
}
