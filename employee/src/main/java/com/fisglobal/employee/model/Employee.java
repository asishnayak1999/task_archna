package com.fisglobal.employee.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name = "empl")
public class Employee {
	
	@Id
	private String UserID;
	private String FirstName;
	private String LastName;
	private String LoginName;
	private String Address;
	private String Email;
	private String MobNumber;
	private String linkedInURL;
	
	public String getUserId() {
		
		return UserID;
	}
	
}
