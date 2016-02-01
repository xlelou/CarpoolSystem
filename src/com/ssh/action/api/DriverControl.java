package com.ssh.action.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

public class DriverControl extends ActionSupport {
	public void control() throws ClassNotFoundException, SQLException, IOException{
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
		JSONArray record =new JSONArray();
		JSONObject dt = JSONObject.parseObject(request.getParameter("dt"));
		int type = Integer.parseInt((String)dt.get("type"));
		int trip_type = Integer.parseInt((String)dt.get("trip_type"));
		String trip_id = (String) dt.get("trip_id");
		if(type==1){//加入
			String id = (String) dt.get("id");
			if(trip_type==1){
				String sql = "update apply set status=2 where trip_id="+trip_id+" &&apply_id="+id+" && type=1";
				stmt.executeUpdate(sql);
				stmt = conn.createStatement();
				sql = "update local set in_count=in_count+1,apply_count=apply_count-1 where id="+trip_id;
				stmt.executeUpdate(sql);
				json.put("result","success");
			}else if(trip_type==2){
				String sql = "update apply set status=2 where trip_id="+trip_id+" &&apply_id="+id+" && type=2";
				stmt.executeUpdate(sql);
				stmt = conn.createStatement();
				sql = "update nation set in_count=in_count+1,apply_count=apply_count-1 where id="+trip_id;
				stmt.executeUpdate(sql);
				json.put("result","success");
			}
		}else if(type==2){//踢出
			String id = (String) dt.get("id");
			if(trip_type==1){
				String sql = "update apply set status=1 where trip_id="+trip_id+" &&apply_id="+id+" && type=1";
				stmt.executeUpdate(sql);
				stmt = conn.createStatement();
				sql = "update local set in_count=in_count-1,apply_count=apply_count+1 where id="+trip_id;
				stmt.executeUpdate(sql);
				json.put("result","success");
			}else if(trip_type==2){
				String sql = "update apply set status=1 where trip_id="+trip_id+" &&apply_id="+id+" && type=2";
				stmt.executeUpdate(sql);
				stmt = conn.createStatement();
				sql = "update nation set in_count=in_count-1,apply_count=apply_count+1 where id="+trip_id;
				stmt.executeUpdate(sql);
				json.put("result","success");
			}
		}else if(type==3){//发起
			if(trip_type==1){
				String sql = "update local set status=2 where id="+trip_id;
				stmt.executeUpdate(sql);
				json.put("result","success");
			}else if(trip_type==2){
				String sql = "update nation set status=2 where id="+trip_id;
				stmt.executeUpdate(sql);
				json.put("result","success");
			}
		}
		response.getWriter().write(json.toString());
	}
}
