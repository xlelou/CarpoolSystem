package com.ssh.model;

/**
 * Local entity. @author MyEclipse Persistence Tools
 */

public class Local implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer driverId;
	private Integer routerId;
	private Integer needCount;
	private Integer inCount;
	private String price;
	private String cartype;
	private Integer status;
	private String startdate;
	private String starttime;
	private Integer viewtime;
	private String message;

	// Constructors

	/** default constructor */
	public Local() {
	}

	/** full constructor */
	public Local(Integer driverId, Integer routerId, Integer needCount,
			Integer inCount, String price, String cartype, Integer status,
			String startdate, String starttime, Integer viewtime, String message) {
		this.driverId = driverId;
		this.routerId = routerId;
		this.needCount = needCount;
		this.inCount = inCount;
		this.price = price;
		this.cartype = cartype;
		this.status = status;
		this.startdate = startdate;
		this.starttime = starttime;
		this.viewtime = viewtime;
		this.message = message;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDriverId() {
		return this.driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public Integer getRouterId() {
		return this.routerId;
	}

	public void setRouterId(Integer routerId) {
		this.routerId = routerId;
	}

	public Integer getNeedCount() {
		return this.needCount;
	}

	public void setNeedCount(Integer needCount) {
		this.needCount = needCount;
	}

	public Integer getInCount() {
		return this.inCount;
	}

	public void setInCount(Integer inCount) {
		this.inCount = inCount;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCartype() {
		return this.cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStartdate() {
		return this.startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getStarttime() {
		return this.starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public Integer getViewtime() {
		return this.viewtime;
	}

	public void setViewtime(Integer viewtime) {
		this.viewtime = viewtime;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}