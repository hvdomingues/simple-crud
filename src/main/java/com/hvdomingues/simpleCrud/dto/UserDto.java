package com.hvdomingues.simpleCrud.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String login;
	private String fullName;
	private String birthday;
	private String zipCode;

	public UserDto(String login, String fullName, String birthday, String zipCode) {

		this.login = login;
		this.fullName = fullName;
		this.birthday = birthday;
		this.zipCode = zipCode;

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {

		this.birthday = birthday;

	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@JsonIgnore
	public Boolean isFullFilled() {

		if (isBlankOrNull(this.login) || isBlankOrNull(this.birthday) || isBlankOrNull(this.fullName)|| isBlankOrNull(this.zipCode)) {
			return false;
		} else {
			return true;
		}

	}

	private Boolean isBlankOrNull(String toTest) {

		if (toTest != null && !toTest.isBlank()) {

			return false;

		}

		return true;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserDto [login=" + login + ", fullName=" + fullName + ", birthDay=" + birthday + ", zipCode=" + zipCode
				+ "]";
	}

}
