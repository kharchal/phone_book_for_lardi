package ua.com.hav.pb.domain;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by yulia on 12.07.2017.
 */
//@Entity
public class Contact {

//    @Id
//    @GeneratedValue
    private Long id;

    @Size(min = 4, max = 10, message = "size is wrong (4-10)")
    private String lastName;

    @Size(min = 4, max = 10, message = "size is wrong (4-10)")
    private String firstName;

    @Size(min = 4, max = 10, message = "size is wrong (4-10)")
    private String middleName;

    @Pattern(regexp = "^\\+38\\(0[0-9]{2}\\)[0-9]{7}$", message = "+38(XXX)XXXXXXX")
    @NotNull(message = "must not be empty")
    private String mobile;

    @Null(message = "?..")
    @Pattern(regexp = "^\\+38\\(0[0-9]{2}\\)[0-9]{7}$", message = "+38(XXX)XXXXXXX")
    private String home;

    @Size(max = 35, message = "size is too big")
    private String address;

    @Null(message = "?...")
    @Pattern(regexp = "^.+@.+\\..+$", message = "xxx@yyy.zzz")
    @Size(max = 20, message = "size is too big")
    private String email;
    private Long userId;

    public Contact() {
    }

    public Contact(String lastName, String firstName, String middleName, String mobile, String home, String address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.mobile = mobile;
        this.home = home;
        this.address = address;
    }

//    public Contact(String[] array, User user) {
//        firstName = array[0];
//        lastName = array[1];
//        middleName = array[2];
//        mobile = array[3];
//        home = array[4];
//        address = array[5];
//        email = array[6];
//        this.user = user;
//    }

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
