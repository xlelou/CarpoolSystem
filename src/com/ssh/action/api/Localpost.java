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

public class Localpost extends ActionSupport {
	public void post() throws ClassNotFoundException, SQLException, IOException{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/cpsystem";
		String usernameMysql = "root";
		String passwordMysql = "root";
		Connection conn = DriverManager.getConnection(url,usernameMysql,passwordMysql);
		Statement stmt=conn.createStatement();
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject dt = JSONObject.parseObject(request.getParameter("dt"));
		String city = (String)dt.get("city");
		String start = (String)dt.get("start");
		String dest = (String)dt.get("dest");
		String driverid = (String)dt.get("id");
		String need_count = (String)dt.get("need_count");
		String price = (String)dt.get("price");
		String cartype = (String)dt.get("cartype");
		String startdate = (String)dt.get("startdate");
		String starttime = (String)dt.get("starttime");
		String message = (String)dt.get("message");
		String passby1 = (String)dt.get("passby1");
		String passby2 = (String)dt.get("passby2");
		String passby3 = (String)dt.get("passby3");
		String sql ="insert into local(driver_id,router_id,need_count,in_count,apply_count,price,cartype,status,startdate,starttime,viewtime,message) values("+driverid+",0,"+need_count+",0,0,'"+price+"','"+cartype+"',1,'"+startdate+"','"+starttime+"',0,'"+message+"')";
		stmt.executeUpdate(sql);
		stmt = conn.createStatement();
		sql = "select * from local where router_id=0";
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()){
			sql = "insert into localrouter values("+rs.getInt("id")+",'"+start+"','"+dest+"','"+city+"','"+passby1+"','"+passby2+"','"+passby3+"')";
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			sql = "update local set router_id="+rs.getInt("id")+" where router_id=0";
			stmt.executeUpdate(sql);
		}
		JSONObject json = new JSONObject();
		json.put("result","success");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
	}
}
