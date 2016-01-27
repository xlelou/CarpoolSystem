package com.ssh.model;

/**
 * DriverInfo entity. @author MyEclipse Persistence Tools
 */

public class DriverInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer successCount;
	private Integer sendCount;
	private Integer sendThismonth;
	private Integer support;
	private Integer unsupport;

	// Constructors

	/** default constructor */
	public DriverInfo() {
	}

	/** full constructor */
	public DriverInfo(Integer successCount, Integer sendCount,
			Integer sendThismonth, Integer support, Integer unsupport) {
		this.successCount = successCount;
		this.sendCount = sendCount;
		this.sendThismonth = sendThismonth;
		this.support = support;
		this.unsupport = unsupport;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSuccessCount() {
		return this.successCount;
	}

	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}

	public Integer getSendCount() {
		return this.sendCount;
	}

	public void setSendCount(Integer sendCount) {
		this.sendCount = sendCount;
	}

	public Integer getSendThismonth() {
		return this.sendThismonth;
	}

	public void setSendThismonth(Integer sendThismonth) {
		this.sendThismonth = sendThismonth;
	}

	public Integer getSupport() {
		return this.support;
	}

	public void setSupport(Integer support) {
		this.support = support;
	}

	public Integer getUnsupport() {
		return this.unsupport;
	}

	public void setUnsupport(Integer unsupport) {
		this.unsupport = unsupport;
	}

}