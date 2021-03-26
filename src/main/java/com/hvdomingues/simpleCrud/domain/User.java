package com.hvdomingues.simpleCrud.domain;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	
	@Column(name = "user_login")
	private String login;
	
	@Column(name = "user_full_name")
	private String fullName;
	
	@Column(name = "user_birthday")
	private Date birthday;
	
	@Column(name = "user_zip_code")
	private String zipCode;
	
	@Column(name = "user_is_deleted")
	Boolean isDeleted;

	
	public User() {
		
		if(isDeleted == null) {
			isDeleted = false;
		}
		
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public void setBirthday(String birthdayString) {
		
		if(birthdayString != null) {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			
			dateFormat.setLenient(false);
			
			try {
				
				this.birthday = dateFormat.parse(birthdayString);
				
			}catch(ParseException e) {
				throw new IllegalArgumentException("A data está no formato errado, utilizar o formato 'dd-MM-yyyy'");
			}
			
		}
		
		
	}
	
	public String getBirthdayAsString() {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		
		
		return dateFormat.format(birthday);
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", fullName=" + fullName + ", birthday=" + birthday
				+ ", zipCode=" + zipCode + "]";
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
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}



	
	
	
	
	
	
	
	
	

}
