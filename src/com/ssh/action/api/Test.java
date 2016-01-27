package com.ssh.action.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

public class Test extends ActionSupport {
	public void test() throws ClassNotFoundException, SQLException, IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		String str = request.getParameter("dt");
		JSONObject a = JSONObject.parseObject(str);
		System.out.print(a.get("name"));
		JSONObject json=new JSONObject();
		JSONArray jsonMembers = new JSONArray();
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/cpsystem";
		String usernameMysql = "root";
		String passwordMysql = "root";
		Connection conn = DriverManager.getConnection(url,usernameMysql,passwordMysql);
		Statement stmt=conn.createStatement();
		String sql = "select * from message";
		ResultSet rs = stmt.executeQuery(sql);
		String res="";
		while(rs.next()){
			res = rs.getString("detail");
			JSONObject member1 = new JSONObject();
			member1.put("id",rs.getInt("id"));
			member1.put("from_id",rs.getInt("from_id"));
			member1.put("to_id",rs.getInt("to_id"));
			member1.put("status",rs.getInt("status"));
			member1.put("createtime",rs.getString("createtime"));
			member1.put("detail",rs.getString("detail"));
			jsonMembers.add(member1);
		}
		json.put("message",jsonMembers);
		stmt.close();
		conn.close();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
	}
}
