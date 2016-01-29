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

public class GetOwnApply extends ActionSupport {
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
		int type = Integer.parseInt((String)dt.get("type"));
		String id = (String) dt.get("id");
		if(type==1){
			String sql = "select * from apply where apply_id="+id+" && status=1 && type=1";
			ResultSet trips = stmt.executeQuery(sql);
			while(trips.next()){
				sql = "select * from local where id="+trips.getInt("trip_id");
				ResultSet trip = stmt.executeQuery(sql);
				JSONObject detail = new JSONObject();
				if(trip.next()){
					detail.put("id",trip.getInt("id"));
					detail.put("startdate",trip.getString("startdate"));
					detail.put("need_count",trip.getInt("need_count"));
					detail.put("in_count",trip.getInt("in_count"));
					detail.put("status",trip.getInt("status"));
					sql = "select * from localrouter where id="+trip.getInt("router_id");
					ResultSet router = stmt.executeQuery(sql);
					if(router.next()){
						detail.put("start",router.getString("start"));
						detail.put("dest",router.getString("dest"));
					}
				}
				record.add(detail);
			}
		}else if(type==2){
			String sql = "select * from apply where apply_id="+id+" && status!=1 && type=1";
			ResultSet trips = stmt.executeQuery(sql);
			while(trips.next()){
				sql = "select * from local where id="+trips.getInt("trip_id");
				ResultSet trip = stmt.executeQuery(sql);
				JSONObject detail = new JSONObject();
				if(trip.next()){
					detail.put("id",trip.getInt("id"));
					detail.put("startdate",trip.getString("startdate"));
					detail.put("need_count",trip.getInt("need_count"));
					detail.put("in_count",trip.getInt("in_count"));
					detail.put("status",trip.getInt("status"));
					sql = "select * from localrouter where id="+trip.getInt("router_id");
					ResultSet router = stmt.executeQuery(sql);
					if(router.next()){
						detail.put("start",router.getString("start"));
						detail.put("dest",router.getString("dest"));
					}
				}
				record.add(detail);
			}
		}else if(type==3){
			String sql = "select * from apply where apply_id="+id+" && status=1 && type=2";
			ResultSet trips = stmt.executeQuery(sql);
			while(trips.next()){
				sql = "select * from nation where id="+trips.getInt("trip_id");
				ResultSet trip = stmt.executeQuery(sql);
				JSONObject detail = new JSONObject();
				if(trip.next()){
					detail.put("id",trip.getInt("id"));
					detail.put("startdate",trip.getString("startdate"));
					detail.put("need_count",trip.getInt("need_count"));
					detail.put("in_count",trip.getInt("in_count"));
					detail.put("status",trip.getInt("status"));
					sql = "select * from nationrouter where id="+trip.getInt("router_id");
					ResultSet router = stmt.executeQuery(sql);
					if(router.next()){
						detail.put("start",router.getString("start"));
						detail.put("dest",router.getString("dest"));
					}
				}
				record.add(detail);
			}
		}else if(type==4){
			String sql = "select * from apply where apply_id="+id+" && status!=1 && type=2";
			ResultSet trips = stmt.executeQuery(sql);
			while(trips.next()){
				sql = "select * from nation where id="+trips.getInt("trip_id");
				ResultSet trip = stmt.executeQuery(sql);
				JSONObject detail = new JSONObject();
				if(trip.next()){
					detail.put("id",trip.getInt("id"));
					detail.put("startdate",trip.getString("startdate"));
					detail.put("need_count",trip.getInt("need_count"));
					detail.put("in_count",trip.getInt("in_count"));
					detail.put("status",trip.getInt("status"));
					sql = "select * from nationrouter where id="+trip.getInt("router_id");
					ResultSet router = stmt.executeQuery(sql);
					if(router.next()){
						detail.put("start",router.getString("start"));
						detail.put("dest",router.getString("dest"));
					}
				}
				record.add(detail);
			}
		}
		json.put("detail",record);
		response.getWriter().write(json.toString());
	}
}
