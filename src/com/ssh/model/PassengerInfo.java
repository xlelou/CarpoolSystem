package com.ssh.model;

/**
 * PassengerInfo entity. @author MyEclipse Persistence Tools
 */

public class PassengerInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer takeCount;
	private Integer takeThismonth;
	private Integer approve;
	private Long disapprove;

	// Constructors

	/** default constructor */
	public PassengerInfo() {
	}

	/** full constructor */
	public PassengerInfo(Integer takeCount, Integer takeThismonth,
			Integer approve, Long disapprove) {
		this.takeCount = takeCount;
		this.takeThismonth = takeThismonth;
		this.approve = approve;
		this.disapprove = disapprove;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTakeCount() {
		return this.takeCount;
	}

	public void setTakeCount(Integer takeCount) {
		this.takeCount = takeCount;
	}

	public Integer getTakeThismonth() {
		return this.takeThismonth;
	}

	public void setTakeThismonth(Integer takeThismonth) {
		this.takeThismonth = takeThismonth;
	}

	public Integer getApprove() {
		return this.approve;
	}

	public void setApprove(Integer approve) {
		this.approve = approve;
	}

	public Long getDisapprove() {
		return this.disapprove;
	}

	public void setDisapprove(Long disapprove) {
		this.disapprove = disapprove;
	}

}