package ua.com.hav.pb.dao.sql;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.hav.pb.dao.LoginException;
import ua.com.hav.pb.dao.UserDao;
import ua.com.hav.pb.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoSqlImpl implements UserDao {

	public static final String LOGIN = "SELECT id, name FROM user WHERE"
		+ " login = ? AND pass = ? LIMIT 1;";
	public static final String SAVE = "INSERT INTO user (login, pass, name) VALUES (?, ?, ?);";
	public static final String LOGIN_CHECK = "SELECT id FROM user WHERE login = ? LIMIT 1;";

	@Autowired
	DataSource dataSource;

	public User login(String login, String password) {
		if (login == null || password == null || login.equals("") || password.equals("")) {
			throw new LoginException();
		}
		User user = null;
		try (Connection con = dataSource.getConnection()) {
			PreparedStatement st = con.prepareStatement(LOGIN);
			st.setString(1, login);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setPassword("<-SECURED->");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean save(User user) {
		if (user == null) {
			throw new IllegalArgumentException();
		}
		boolean ok = false;
		try (Connection con = dataSource.getConnection()) {
			PreparedStatement st = con.prepareStatement(LOGIN_CHECK);
			st.setString(1, user.getLogin());
			ResultSet rs = st.executeQuery();
			if (!rs.next()) {
				PreparedStatement stt = con.prepareStatement(SAVE);
				stt.setString(1, user.getLogin());
				stt.setString(2, user.getPassword());
				stt.setString(3, user.getName());
				stt.executeUpdate();
				ok = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}

	@Override
	public boolean checkLogin(String login) {
		if (login == null) {
			throw new IllegalArgumentException();
		}
		try (Connection con = dataSource.getConnection()) {
			PreparedStatement st = con.prepareStatement(LOGIN_CHECK);
			st.setString(1, login);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}