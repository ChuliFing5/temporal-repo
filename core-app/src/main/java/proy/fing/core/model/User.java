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
@Table(name = "USER")
public class User extends AbstractEntity<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String userName;

	private String email;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String password;
	private UserType userType;
	private boolean active;

	@Column(name = "USER_NAME", nullable = false)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "EMAIL", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "FIRSTNAME", nullable = false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LASTNAME", nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "MOBILE_NUMBER", nullable = false)
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Column(name = "PASSWORD", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public boolean isActive() {
		return active;
	}

	@Column(name = "ACTIVE", nullable = false)
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String getIdentifier() {
		return this.userName;
	}

}
