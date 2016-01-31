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

public class GetAllPost extends ActionSupport {
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
		JSONArray record =new JSONArray();
		JSONObject dt = JSONObject.parseObject(request.getParameter("dt"));
		int type = Integer.parseInt((String) dt.get("type"));
		if(type==1){
			String city = (String) dt.get("city");
			String sql = "select * from local";
			ResultSet trips = stmt.executeQuery(sql);
			while(trips.next()){
				JSONObject trip = new JSONObject();
				sql = "select * from localrouter where id="+trips.getInt("router_id");
				stmt = conn.createStatement();
				ResultSet router = stmt.executeQuery(sql);
				if(router.next()){
					if(router.getString("city").equals(city)){
						trip.put("id",trips.getInt("id"));
						trip.put("startdate",trips.getString("startdate"));
						trip.put("starttime",trips.getString("starttime"));
						trip.put("price",trips.getString("price"));
						trip.put("in_count",trips.getInt("in_count"));
						trip.put("need_count",trips.getInt("need_count"));
						trip.put("status",trips.getInt("status"));
						trip.put("start",router.getString("start"));
						trip.put("dest",router.getString("dest"));
						record.add(trip);
					}
				}
			}
		}else if(type==2){
			String sql = "select * from nation";
			ResultSet trips = stmt.executeQuery(sql);
			while(trips.next()){
				JSONObject trip = new JSONObject();
				sql = "select * from nationrouter where id="+trips.getInt("router_id");
				stmt = conn.createStatement();
				ResultSet router = stmt.executeQuery(sql);
				if(router.next()){
					trip.put("id",trips.getInt("id"));
					trip.put("type",trips.getInt("type"));
					trip.put("startdate",trips.getString("startdate"));
					trip.put("starttime",trips.getString("starttime"));
					trip.put("cartype",trips.getString("cartype"));
					trip.put("price",trips.getString("price"));
					trip.put("in_count",trips.getInt("in_count"));
					trip.put("need_count",trips.getInt("need_count"));
					trip.put("status",trips.getInt("status"));
					trip.put("start",router.getString("start"));
					trip.put("dest",router.getString("dest"));
					record.add(trip);
				}
			}
		}
		json.put("detail",record);
		response.getWriter().write(json.toString());
	}
}
