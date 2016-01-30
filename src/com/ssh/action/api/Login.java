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

public class Login extends ActionSupport {
	public void login() throws SQLException, ClassNotFoundException, IOException{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/cpsystem";
		String usernameMysql = "root";
		String passwordMysql = "root";
		Connection conn = DriverManager.getConnection(url,usernameMysql,passwordMysql);
		Statement stmt=conn.createStatement();
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject dt = JSONObject.parseObject(request.getParameter("dt"));
		String phone = (String) dt.get("phone");
		String password = (String)dt.get("password");
		String sql = "select * from user where phone='"+phone+"' && password='"+password+"';";
		ResultSet rs = stmt.executeQuery(sql);
		JSONObject json = new JSONObject();
		if(rs.next()){
			json.put("result","success");
			json.put("id",rs.getInt("id"));
			json.put("account",rs.getString("phone"));
			json.put("type",rs.getInt("id"));
			stmt.close();
			conn.close();
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(json.toString());
		}else{
			json.put("result","fail");
			stmt.close();
			conn.close();
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(json.toString());
		}
	}
	
}
