package com.ssh.model;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private String phone;
	private String email;
	private String name;
	private Integer type;
	private Integer driverInfo;
	private Integer passengerInfo;
	private String password;
	private String idcardcode;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String phone, String email, String name, Integer type,
			Integer driverInfo, Integer passengerInfo, String password,
			String idcardcode) {
		this.phone = phone;
		this.email = email;
		this.name = name;
		this.type = type;
		this.driverInfo = driverInfo;
		this.passengerInfo = passengerInfo;
		this.password = password;
		this.idcardcode = idcardcode;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getDriverInfo() {
		return this.driverInfo;
	}

	public void setDriverInfo(Integer driverInfo) {
		this.driverInfo = driverInfo;
	}

	public Integer getPassengerInfo() {
		return this.passengerInfo;
	}

	public void setPassengerInfo(Integer passengerInfo) {
		this.passengerInfo = passengerInfo;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdcardcode() {
		return this.idcardcode;
	}

	public void setIdcardcode(String idcardcode) {
		this.idcardcode = idcardcode;
	}

}