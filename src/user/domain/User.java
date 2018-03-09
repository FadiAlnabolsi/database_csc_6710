package user.domain;

/**
 * User object
 * 
 * @author changxin bai
 * 
 */
public class User {
	/*
	 * Correspond to the user table
	 */
	
	private String username;
	private String password; 
	private String email;
	private String first_name;
	private String last_name;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}
	
	public String getFirstName() {
		return first_name;
	}
	
	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public String getLastName() {
		return last_name;
	}
	
	@Override
	public String toString() {
		return "User [ username=" + username + ", password="
				+ password + ", email=" + email +"]";
	}
}
