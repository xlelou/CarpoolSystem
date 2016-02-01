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

public class CheckApply extends ActionSupport {
		public void check() throws ClassNotFoundException, SQLException, IOException{
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
			JSONObject dt = JSONObject.parseObject(request.getParameter("dt"));
			String id = (String) dt.get("apply_id");
			String trip_id = (String) dt.get("trip_id");
			int type = Integer.parseInt((String) dt.get("type"));
			if(type==1){
				String sql = "select * from apply where trip_id=+"+trip_id+" &&apply_id="+id+" && type=1";
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()){
					json.put("result","applied");
				}else{
					json.put("result","notapplied");
				}
			}else if(type==2){
				String sql = "select * from apply where trip_id=+"+trip_id+" &&apply_id="+id+" && type=2";
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()){
					json.put("result","applied");
				}else{
					json.put("result","notapplied");
				}
			}
			response.getWriter().write(json.toString());
		}
}
