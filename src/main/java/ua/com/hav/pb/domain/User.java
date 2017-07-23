package ua.com.hav.pb.domain;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {

	private Long id;

	@Size(min = 3, max = 10, message = "wrong length (3-10)")
	@Pattern(regexp = "[A-Za-z]+", message = "letters only")
	private String login;

	@Size(min = 5, max = 10, message = "wrong length (5-10)")
	@Pattern(regexp = "[A-Za-z]{1}[A-Za-z0-9]+", message = "letters or digits, starts with a letter")
	private String password;

	@Size(min = 5, max = 10, message = "wrong length (5-10)")
	private String name;

	public User() {
	}

	public User(String login, String password, String name) {
		this.login = login;
		this.password = password;
		this.name = name;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o instanceof User) {
			return getId().equals(((User) o).getId());
		}
		return false;
	}

	public int hashCode() {
		return getId().hashCode();
	}

	public String toString() {
		return "User{id= " + getId() + ", login= '" + getLogin() + "', password= '<-SECURED->', name= '" + getName() + "'}";
	}

}