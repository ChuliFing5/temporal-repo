package proy.fing.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import proy.fing.core.dao.db.AbstractEntity;
import proy.fing.core.model.enums.UserType;

@Entity
@Table(name = "users")
public class User extends AbstractEntity<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_name", nullable = false)
	private String userName;

	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "firstname", nullable = false)
	private String firstName;
	
	@Column(name = "lastname", nullable = false)
	private String lastName;
	
	@Column(name = "mobile_number", nullable = false)
	private String mobileNumber;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
	private UserType userType;
	
	@Column(name = "active", nullable = false)
	private boolean active;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String getIdentifier() {
		return this.userName;
	}

}
