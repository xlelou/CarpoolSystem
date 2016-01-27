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

public class Saveinfo extends ActionSupport {
	public void save() throws ClassNotFoundException, SQLException, IOException{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/cpsystem";
		String usernameMysql = "root";
		String passwordMysql = "root";
		Connection conn = DriverManager.getConnection(url,usernameMysql,passwordMysql);
		Statement stmt=conn.createStatement();
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject dt = JSONObject.parseObject(request.getParameter("dt"));
		int type = Integer.parseInt((String) dt.get("type"));
		String id = (String) dt.get("id");
		String password = (String) dt.get("password");
		String sql = "select * from user where id="+id+" && password='"+password+"'";
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()){
			if(type==1){//修改邮箱
				String email = (String) dt.get("email");
				sql = "update user set email='"+email+"' where id="+id;
				stmt.executeUpdate(sql);
			}else if(type==2){//修改密码
				String newPassword = (String) dt.get("newPassword");
				sql = "update user set password='"+newPassword+"' where id="+id;
				stmt.executeUpdate(sql);
			}else if(type==3){//修改邮箱及密码
				String email = (String) dt.get("email");
				String newPassword = (String) dt.get("newPassword");
				sql = "update user set email='"+email+"',password='"+newPassword+"' where id="+id;
			}
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("success");
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("error");
		}
	}
}
