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

public class Register extends ActionSupport {
	public void register() throws ClassNotFoundException, SQLException, IOException{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/cpsystem";
		String usernameMysql = "root";
		String passwordMysql = "root";
		Connection conn = DriverManager.getConnection(url,usernameMysql,passwordMysql);
		Statement stmt=conn.createStatement();
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject dt = JSONObject.parseObject(request.getParameter("dt"));
		int type = Integer.parseInt((String) dt.get("type"));
		if(type==1){//司机注册
			String account = (String) dt.get("account");
			String password = (String) dt.get("password");
			String name = (String) dt.get("username");
			String idcode = (String) dt.get("id");
			String email = (String) dt.get("email");
			String sql ="insert into user(phone,email,name,type,driver_info,passenger_info,password,idcardcode) values('"+account+"','"+email+"','"+name+"',1,0,0,'"+password+"','"+idcode+"')";
			System.out.print(sql);
			stmt.executeUpdate(sql);
			String query = "select * from user where phone='"+account+"';";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				int id = rs.getInt("id");
				sql = "insert into driver_info values("+id+",0,0,0,0,0,0)";
				stmt.executeUpdate(sql);
				sql = "update user set driver_info='"+id+"' where phone='"+account+"';";
				stmt.executeUpdate(sql);
			}
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("success");
		}else if(type==2){
			String account = (String) dt.get("account");
			String password = (String) dt.get("password");
			String email = (String) dt.get("email");
			String sql ="insert into user(phone,email,name,type,driver_info,passenger_info,password,idcardcode) values('"+account+"','"+email+"','0',2,0,0,'"+password+"','0')";
			stmt.executeUpdate(sql);
			String query = "select * from user where phone='"+account+"';";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				int id = rs.getInt("id");
				sql = "insert into passenger_info values("+id+",0,0,0,0)";
				stmt.executeUpdate(sql);
				sql = "update user set passenger_info='"+id+"' where phone='"+account+"';";
				stmt.executeUpdate(sql);
			}
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("success");
		}
		
	}
}
