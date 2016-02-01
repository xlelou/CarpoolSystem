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

public class GetApply extends ActionSupport {
	public void get() throws ClassNotFoundException, SQLException, IOException{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/cpsystem";
		String usernameMysql = "root";
		String passwordMysql = "root";
		Connection conn = DriverManager.getConnection(url,usernameMysql,passwordMysql);
		Statement stmt=conn.createStatement();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		JSONArray applied =new JSONArray();
		JSONArray applying =new JSONArray();
		JSONObject dt = JSONObject.parseObject(request.getParameter("dt"));
		String trip_id = (String) dt.get("trip_id");
		int type = Integer.parseInt((String) dt.get("type"));
		if(type==1){
			String sql = "select * from apply where trip_id="+trip_id+" &&status=1 &&type=1";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				applying.add(rs.getInt("apply_id"));
			}
			sql = "select * from apply where trip_id="+trip_id+" &&status=2 &&type=1";
			stmt = conn.createStatement();
			ResultSet rs1 = stmt.executeQuery(sql);
			while(rs1.next()){
				applied.add(rs1.getInt("apply_id"));
			}
			json.put("applying",applying);
			json.put("applied",applied);
		}else if(type==2){
			String sql = "select * from apply where trip_id="+trip_id+" &&status=1 &&type=2";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				applying.add(rs.getInt("apply_id"));
			}
			sql = "select * from apply where trip_id="+trip_id+" &&status=2 &&type=2";
			stmt = conn.createStatement();
			ResultSet rs1 = stmt.executeQuery(sql);
			while(rs1.next()){
				applied.add(rs1.getInt("apply_id"));
			}
			json.put("applying",applying);
			json.put("applied",applied);
		}
		response.getWriter().write(json.toString());
	}
}
