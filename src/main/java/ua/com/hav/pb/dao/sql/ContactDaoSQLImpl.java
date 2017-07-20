package ua.com.hav.pb.dao.sql;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.hav.pb.dao.ContactDao;
import ua.com.hav.pb.domain.Contact;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yulia on 15.07.2017.
 */
public class ContactDaoSQLImpl implements ContactDao {

    @Autowired
    DataSource dataSource;

    public static final String FIND = "SELECT * FROM contact WHERE id = ? AND user_id =? LIMIT 1;";
    public static final String FIND_FOR_USER = "SELECT * FROM contact WHERE user_id = ?";
    public static final String FIND_STRING = "SELECT * FROM contact WHERE (firstname LIKE ? OR lastname LIKE ?"
            + " OR mobile LIKE ? OR home LIKE ?) AND user_id =?;";
    public static final String INSERT = "INSERT INTO contact "
            + "(firstname, lastname, middlename, mobile, home, address, email, user_id) VALUES (?,?,?,?,?,?,?,?);";
    public static final String UPDATE = "UPDATE contact SET firstname =?, lastname =?, middlename = ?,"
            + " mobile =?, home =?, address =?, email =? WHERE user_id = ? AND id =?";
    public static final String DELETE = "DELETE FROM contact WHERE id = ? AND user_id =?;";

    public List<Contact> findAll() {
        List<Contact> list = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM contact");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(assemble(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Contact> findForUser(Long userId) {
        List<Contact> contacts = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement st = con.prepareStatement(FIND_FOR_USER);
            st.setLong(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                contacts.add(assemble(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public List<Contact> find(String str, Long userId) {
        if (str == null ) {//|| str.equals("")) {
            throw new IllegalArgumentException();
        }

        String s = "%" + str + "%";
        List<Contact> contacts = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement st = con.prepareStatement(FIND_STRING);
            st.setString(1, s);
            st.setString(2, s);
            st.setString(3, s);
            st.setString(4, s);
            st.setLong(5, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                contacts.add(assemble(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public Contact find(Long id, Long userId) {
        Contact contact = null;
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement st = con.prepareStatement(FIND);
            st.setLong(1, id);
            st.setLong(2, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                contact = assemble(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contact;
    }

    public void save(Contact contact, Long userId) {
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement st = null;
            if (contact.getId() == null) {
                st = con.prepareStatement(INSERT);
            } else {
                st = con.prepareStatement(UPDATE);
            }
            st.setString(1, contact.getFirstName());
            st.setString(2, contact.getLastName());
            st.setString(3, contact.getMiddleName());
            st.setString(4, contact.getMobile());
            st.setString(5, contact.getHome());
            st.setString(6, contact.getAddress());
            st.setString(7, contact.getEmail());
            st.setLong(8, userId);
            if (contact.getId() != null) {
                st.setLong(9, contact.getId());
            }
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id, Long userId) {
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement st = con.prepareStatement(DELETE);
            st.setLong(1, id);
            st.setLong(2, userId);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Contact assemble(ResultSet rs) throws SQLException {
        Contact c = new Contact();
        if (rs != null) {
            c.setId(rs.getLong("id"));
            c.setFirstName(rs.getString("firstname"));
            c.setLastName(rs.getString("lastname"));
            c.setMiddleName(rs.getString("middlename"));
            c.setMobile(rs.getString("mobile"));
            c.setHome(rs.getString("home"));
            c.setAddress(rs.getString("address"));
            c.setEmail(rs.getString("email"));
        }
        return c;
    }

}
