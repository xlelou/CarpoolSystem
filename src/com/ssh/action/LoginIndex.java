package com.ssh.action;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.service.impl.GetConn;

public class LoginIndex extends ActionSupport implements ServletRequestAware{

	private String aid;
	private String password;
	private HttpServletRequest request;
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request=request;
	}
	public String execute(){
		HttpServletRequest request=ServletActionContext.getRequest(); 
		Connection conn=(Connection)GetConn.returnConn();
		String sql="select * from account";
		Boolean flag=false;
		System.out.print(aid);
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				if((aid.equals(rs.getString("aid")))&&(password.equals(rs.getString("password")))){
					flag=true;
					}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag){
			HttpSession session=request.getSession(); 
			session.setAttribute("aid",this.aid);
			return "success";
		}else{
			request.setAttribute("message","用户名或密码有误，请重新输入!");
			return "err";
		}
		
		
	}
	}


