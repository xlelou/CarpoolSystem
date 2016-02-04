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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

public class GetSomeonePost extends ActionSupport {
	public void get() throws ClassNotFoundException, SQLException, IOException{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/cpsystem";
		String usernameMysql = "root";
		String passwordMysql = "root";
		Connection conn = DriverManager.getConnection(url,usernameMysql,passwordMysql);
		Statement stmt=conn.createStatement();
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject dt = JSONObject.parseObject(request.getParameter("dt"));
		JSONObject json = new JSONObject();
		JSONArray local = new JSONArray();
		JSONArray nation = new JSONArray();
		String id = (String) dt.get("id");
		String sql = "select * from local where driver_id="+id;
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			JSONObject info = new JSONObject();
			info.put("id",rs.getInt("id"));
			info.put("startdate",rs.getString("startdate"));
			info.put("status",rs.getString("status"));
			sql = "select * from localrouter where id="+rs.getInt("id");
			stmt = conn.createStatement();
			ResultSet rs1 = stmt.executeQuery(sql);
			if(rs1.next()){
				info.put("start",rs1.getString("start"));
				info.put("dest",rs1.getString("dest"));
			}
			local.add(info);
		}
		sql = "select * from nation where apply_id="+id;
		stmt = conn.createStatement();
		ResultSet rs2 = stmt.executeQuery(sql);
		while(rs2.next()){
			JSONObject info = new JSONObject();
			info.put("id",rs2.getInt("id"));
			info.put("startdate",rs2.getString("startdate"));
			info.put("status",rs2.getString("status"));
			sql = "select * from nationrouter where id="+rs2.getInt("id");
			stmt = conn.createStatement();
			ResultSet rs3 = stmt.executeQuery(sql);
			if(rs3.next()){
				info.put("start",rs3.getString("start"));
				info.put("dest",rs3.getString("dest"));
			}
			nation.add(info);
		}
		json.put("local",local);
		json.put("nation",nation);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
	}
}
