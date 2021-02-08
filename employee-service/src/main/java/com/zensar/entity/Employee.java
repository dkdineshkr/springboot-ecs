package com.zensar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @Size(min = 3, max = 20)
    @Column(name = "employeename", nullable = false, unique = true)
    private String employeename;

    @NotNull
    @Size(min = 1)
    @Column(name = "email", nullable = false)
    private String email;

    @Size(min = 1, max = 20)
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Size(max = 20)
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Transient
    private String port;
    
    @Transient
    private String hostAddress;
    
    @Transient
    private String hostName;
    
	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getHostAddress() {
		return hostAddress;
	}

	public void setHostAddress(String hostAddress) {
		this.hostAddress = hostAddress;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
    
	
}
