package com.ssh.action.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

public class Getinfo extends ActionSupport {
	public void get() throws ClassNotFoundException, SQLException, IOException{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/cpsystem";
		String usernameMysql = "root";
		String passwordMysql = "root";
		Connection conn = DriverManager.getConnection(url,usernameMysql,passwordMysql);
		Statement stmt=conn.createStatement();
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject dt = JSONObject.parseObject(request.getParameter("dt"));
		String account = (String) dt.get("account");
		String sql = "select * from user where phone='"+account+"'";
		ResultSet userinfo = stmt.executeQuery(sql);
		JSONObject json = new JSONObject();
		JSONObject info = new JSONObject();
		if(userinfo.next()){
			if(userinfo.getInt("type")==1){//driver
				info.put("id",userinfo.getInt("id"));
				info.put("phone",userinfo.getString("phone"));
				info.put("email",userinfo.getString("email"));
				info.put("name",userinfo.getString("name"));
				info.put("type",userinfo.getInt("type"));
				sql = "select * from driver_info where id="+userinfo.getInt("id");
				stmt = conn.createStatement();
				ResultSet driverinfo = stmt.executeQuery(sql);
				if(driverinfo.next()){
					info.put("success_count",driverinfo.getInt("success_count"));
					info.put("success_thismonth",driverinfo.getInt("success_thismonth"));
					info.put("send_count",driverinfo.getInt("send_count"));
					info.put("send_thismonth",driverinfo.getInt("send_thismonth"));
				}
				json.put("info",info);
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				response.getWriter().write(json.toString());
			}else if(userinfo.getInt("type")==2){//passenger
				info.put("id",userinfo.getInt("id"));
				info.put("phone",userinfo.getString("phone"));
				info.put("email",userinfo.getString("email"));
				info.put("type",userinfo.getString("type"));
				sql = "select * from passenger_info where id="+userinfo.getInt("id");
				stmt = conn.createStatement();
				ResultSet passengerinfo = stmt.executeQuery(sql);
				if(passengerinfo.next()){
					info.put("take_count",passengerinfo.getInt("take_count"));
					info.put("take_thismonth",passengerinfo.getInt("take_thismonth"));
				}
				json.put("info",info);
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				response.getWriter().write(json.toString());
			}
			
		}
	}
}
