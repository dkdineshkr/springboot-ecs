package com.zensar.entity;

public class Employee {


    private String employeename;
    private String email;
    private String firstname;
    private String lastname;
    private String port;
    private String hostAddress;
    private String hostName;


	public Employee(String employeename, String email, String firstname, String lastname, String port,
			String hostAddress, String hostName) {
		super();
		this.employeename = employeename;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.port = port;
		this.hostAddress = hostAddress;
		this.hostName = hostName;
	}

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
