package com.ssh.model;

/**
 * Router entity. @author MyEclipse Persistence Tools
 */

public class Router implements java.io.Serializable {

	// Fields

	private Integer id;
	private String startpoint;
	private String destpoint;
	private String startcity;
	private String destcity;
	private String passby;
	private Integer type;

	// Constructors

	/** default constructor */
	public Router() {
	}

	/** full constructor */
	public Router(String startpoint, String destpoint, String startcity,
			String destcity, String passby, Integer type) {
		this.startpoint = startpoint;
		this.destpoint = destpoint;
		this.startcity = startcity;
		this.destcity = destcity;
		this.passby = passby;
		this.type = type;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStartpoint() {
		return this.startpoint;
	}

	public void setStartpoint(String startpoint) {
		this.startpoint = startpoint;
	}

	public String getDestpoint() {
		return this.destpoint;
	}

	public void setDestpoint(String destpoint) {
		this.destpoint = destpoint;
	}

	public String getStartcity() {
		return this.startcity;
	}

	public void setStartcity(String startcity) {
		this.startcity = startcity;
	}

	public String getDestcity() {
		return this.destcity;
	}

	public void setDestcity(String destcity) {
		this.destcity = destcity;
	}

	public String getPassby() {
		return this.passby;
	}

	public void setPassby(String passby) {
		this.passby = passby;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}