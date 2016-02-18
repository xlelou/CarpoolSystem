package com.ssh.action.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

public class DriInvite extends ActionSupport {
	public void invite() throws ClassNotFoundException, SQLException, IOException{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/cpsystem";
		String usernameMysql = "root";
		String passwordMysql = "root";
		Connection conn = DriverManager.getConnection(url,usernameMysql,passwordMysql);
		Statement stmt = conn.createStatement();
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject dt = JSONObject.parseObject(request.getParameter("dt"));
		String from_id = (String) dt.get("from_id");
		String to_id = (String) dt.get("to_id");
		SimpleDateFormat dfsample = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String df = dfsample.format(new Date());
		String sql = "select * from user where id="+from_id;
		ResultSet rs = stmt.executeQuery(sql);
		String number=null;
		String name=null;
		if(rs.next()){
			number = rs.getString("phone");
			name = rs.getString("name");
		}
		sql = "insert into message(from_id,to_id,status,createtime,title,detail) values("+from_id+","+to_id+",1,'"+df+"','邀请拼车','"+"司机"+name+"邀请您加入他的拼车！联系电话："+number+"')";
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write("success");
	}
}
