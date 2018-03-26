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
	private String FirstName;
	private String LastName;
	private String Hobbies;

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

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String LastName) {
		this.LastName = LastName;
	}

	public String getHobbies() {
		return Hobbies;
	}

	public void setHobbies(String Hobbies) {
		this.Hobbies = Hobbies;
	}


	@Override
	public String toString() {
		return "User [ username=" + username + ", password="
				+ password + ", email=" + email +
				", FirstName=" + FirstName + ", LastName="
				+ LastName + ", Hobbies=" + Hobbies + "]";
	}
}
