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

public class GetCarpool extends ActionSupport {
	public void get() throws ClassNotFoundException, SQLException, IOException{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/cpsystem";
		String usernameMysql = "root";
		String passwordMysql = "root";
		Connection conn = DriverManager.getConnection(url,usernameMysql,passwordMysql);
		Statement stmt=conn.createStatement();
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject dt = JSONObject.parseObject(request.getParameter("dt"));
		int type = Integer.parseInt((String) dt.get("type"));
		String trip_id = (String) dt.get("trip_id");
		JSONObject json = new JSONObject();
		JSONObject poster = new JSONObject();
		JSONObject detail = new JSONObject();
		if(type==1){//local
			String sql = "update local set viewtime=viewtime+1 where id"+trip_id;
			stmt.executeUpdate(sql);
			stmt = conn.createStatement();
			sql = "select * from local where id="+trip_id;
			ResultSet info = stmt.executeQuery(sql);
			if(info.next()){
				detail.put("driver_id",info.getInt("driver_id"));
				detail.put("need_count",info.getInt("need_count"));
				detail.put("in_count",info.getInt("in_count"));
				detail.put("apply_count",info.getInt("apply_count"));
				detail.put("price",info.getString("price"));
				detail.put("cartype",info.getString("cartype"));
				detail.put("status",info.getInt("status"));
				detail.put("startdate",info.getString("startdate"));
				detail.put("starttime",info.getString("starttime"));
				detail.put("viewtime",info.getInt("viewtime"));
				detail.put("message",info.getString("message"));
				sql = "select * from localrouter where id="+info.getInt("router_id");
				stmt = conn.createStatement();
				ResultSet router = stmt.executeQuery(sql);
				if(router.next()){
					detail.put("start",router.getString("start"));
					detail.put("dest",router.getString("dest"));
					detail.put("city",router.getString("city"));
					detail.put("passby1",router.getString("passby1"));
					detail.put("passby2",router.getString("passby2"));
					detail.put("passby2",router.getString("passby2"));
				}
				sql = "select * from user where id="+info.getInt("driver_id");
				stmt = conn.createStatement();
				ResultSet userinfo = stmt.executeQuery(sql);
				if(userinfo.next()){
					poster.put("id",userinfo.getInt("id"));
					poster.put("phone",userinfo.getString("phone"));
					poster.put("name",userinfo.getString("name"));
				}
				sql = "select * from driver_info where id="+userinfo.getInt("id");
				stmt = conn.createStatement();
				ResultSet driverinfo = stmt.executeQuery(sql);
				if(driverinfo.next()){
					poster.put("seccess_count",driverinfo.getInt("success_count"));
					poster.put("seccess_thismonth",driverinfo.getInt("success_thismonth"));
					poster.put("send_count",driverinfo.getInt("send_thismonth"));
					poster.put("send_thismonth",driverinfo.getInt("send_thismonth"));
					poster.put("support",driverinfo.getInt("support"));
					poster.put("unsupport",driverinfo.getInt("unsupport"));
				}
				json.put("poster",poster);
				json.put("detail",detail);
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				response.getWriter().write(json.toString());
			}
		}else if(type==2){//nation
			String sql = "update nation set viewtime=viewtime+1 where id"+trip_id;
			stmt.executeUpdate(sql);
			stmt = conn.createStatement();
			sql = "select * from nation where id="+trip_id;
			ResultSet info = stmt.executeQuery(sql);
			if(info.next()){
				detail.put("type",info.getInt("type"));
				detail.put("apply_id",info.getInt("apply_id"));
				detail.put("need_count",info.getInt("need_count"));
				detail.put("in_count",info.getInt("in_count"));
				detail.put("apply_count",info.getInt("apply_count"));
				detail.put("price",info.getString("price"));
				detail.put("cartype",info.getString("cartype"));
				detail.put("status",info.getInt("status"));
				detail.put("startdate",info.getString("startdate"));
				detail.put("starttime",info.getString("starttime"));
				detail.put("viewtime",info.getInt("viewtime"));
				detail.put("message",info.getString("message"));
			}
			sql = "select * from nationrouter where id="+info.getInt("router_id");
			stmt = conn.createStatement();
			ResultSet router = stmt.executeQuery(sql);
			if(router.next()){
				detail.put("start",router.getString("start"));
				detail.put("dest",router.getString("dest"));
			}
			sql = "select * from user where id="+info.getInt("apply_id");
			stmt = conn.createStatement();
			ResultSet userinfo = stmt.executeQuery(sql);
			if(userinfo.next()){
				poster.put("id",userinfo.getInt("id"));
				poster.put("phone",userinfo.getString("phone"));
				poster.put("name",userinfo.getString("name"));
			}
			System.out.print(info.getInt("type"));
			if(info.getInt("type")==1){
				sql = "select * from driver_info where id="+userinfo.getInt("id");
				stmt = conn.createStatement();
				ResultSet driverinfo = stmt.executeQuery(sql);
				if(driverinfo.next()){
					poster.put("seccess_count",driverinfo.getInt("success_count"));
					poster.put("seccess_thismonth",driverinfo.getInt("success_thismonth"));
					poster.put("send_count",driverinfo.getInt("send_thismonth"));
					poster.put("send_thismonth",driverinfo.getInt("send_thismonth"));
					poster.put("support",driverinfo.getInt("support"));
					poster.put("unsupport",driverinfo.getInt("unsupport"));
				}
				json.put("poster",poster);
				json.put("detail",detail);
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				response.getWriter().write(json.toString());
			}else if(info.getInt("type")==2){
				sql = "select * from passenger_info where id="+userinfo.getInt("id");
				stmt = conn.createStatement();
				ResultSet passengerinfo = stmt.executeQuery(sql);
				if(passengerinfo.next()){
					poster.put("take_count",passengerinfo.getInt("take_count"));
					poster.put("take_thismonth",passengerinfo.getInt("take_thismonth"));
					poster.put("approve",passengerinfo.getInt("approve"));
					poster.put("disapprove",passengerinfo.getInt("disapprove"));
				}
				json.put("poster",poster);
				json.put("detail",detail);
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				response.getWriter().write(json.toString());
			}
		}
	}
}
