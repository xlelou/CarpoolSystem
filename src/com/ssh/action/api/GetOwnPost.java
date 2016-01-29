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

public class GetOwnPost extends ActionSupport {
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
			String sql = "select * from local where driver_id="+id+"&& status=1";
			ResultSet localposting = stmt.executeQuery(sql);
			while(localposting.next()){
				JSONObject detail = new JSONObject();
				detail.put("id",localposting.getInt("id"));
				detail.put("need_count",localposting.getInt("need_count"));
				detail.put("in_count",localposting.getInt("in_count"));
				detail.put("price",localposting.getString("price"));
				detail.put("startdate",localposting.getString("startdate"));
				sql = "select * from localrouter where id="+localposting.getInt("router_id");
				ResultSet router = stmt.executeQuery(sql);
				if(router.next()){
				detail.put("start",router.getString("start"));
				detail.put("dest",router.getString("dest"));
				}
				record.add(detail);
			}
		}else if(type==2){
			String sql = "select * from local where driver_id="+id+" && status!=1";
			ResultSet localposted = stmt.executeQuery(sql);
			while(localposted.next()){
				JSONObject detail = new JSONObject();
				detail.put("id",localposted.getInt("id"));
				detail.put("need_count",localposted.getInt("need_count"));
				detail.put("in_count",localposted.getInt("in_count"));
				detail.put("price",localposted.getString("price"));
				detail.put("startdate",localposted.getString("startdate"));
				detail.put("status",localposted.getInt("status"));
				sql = "select * from localrouter where id="+localposted.getInt("router_id");
				ResultSet router = stmt.executeQuery(sql);
				if(router.next()){
				detail.put("start",router.getString("start"));
				detail.put("dest",router.getString("dest"));
				}
				record.add(detail);
			}
		}else if(type==3){
			String sql = "select * from nation where apply_id="+id+" &&status=1";
			ResultSet nationposting = stmt.executeQuery(sql);
			while(nationposting.next()){
				JSONObject detail = new JSONObject();
				detail.put("id",nationposting.getInt("id"));
				detail.put("type",nationposting.getInt("type"));
				detail.put("need_count",nationposting.getInt("need_count"));
				detail.put("in_count",nationposting.getInt("in_count"));
				detail.put("startdate",nationposting.getString("startdate"));
				sql = "select * from nationrouter where id="+nationposting.getInt("router_id");
				ResultSet router = stmt.executeQuery(sql);
				if(router.next()){
					detail.put("start",router.getString("start"));
					detail.put("dest",router.getString("dest"));
				}
				record.add(detail);
			}
		}else if(type==4){
			String sql = "select * from nation where apply_id+"+id+" &&status!=1";
			ResultSet nationposted = stmt.executeQuery(sql);
			while(nationposted.next()){
				JSONObject detail = new JSONObject();
				detail.put("id",nationposted.getInt("id"));
				detail.put("type",nationposted.getInt("type"));
				detail.put("startdate",nationposted.getString("startdate"));
				sql = "select * from nationrouter where id="+nationposted.getInt("router_id");
				ResultSet router = stmt.executeQuery(sql);
				if(router.next()){
					detail.put("start",router.getString("start"));
					detail.put("dest",router.getString("dest"));
				}
				record.add(detail);
			}
		}
		json.put("detail",record);
		response.getWriter().write(json.toString());
	}
}
