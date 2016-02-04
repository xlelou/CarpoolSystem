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

public class GetSomeoneApply extends ActionSupport {
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
		String sql = "select * from apply where apply_id="+id;
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			if(rs.getInt("type")==1){
				JSONObject info = new JSONObject();
				info.put("id",rs.getInt("trip_id"));
				info.put("status",rs.getInt("status"));
				sql = "select * from local where id="+rs.getInt("trip_id");
				stmt = conn.createStatement();
				ResultSet rs1 = stmt.executeQuery(sql);
				if(rs1.next()){
					info.put("startdate",rs1.getString("startdate"));
					sql = "select * from localrouter where id="+rs1.getInt("router_id");
					stmt = conn.createStatement();
					ResultSet rs2 = stmt.executeQuery(sql);
					if(rs2.next()){
						info.put("start",rs2.getString("start"));
						info.put("dest",rs2.getString("dest"));
					}
				}
				local.add(info);
			}else if(rs.getInt("type")==2){
				JSONObject info = new JSONObject();
				info.put("id",rs.getInt("trip_id"));
				info.put("status",rs.getInt("status"));
				sql = "select * from nation where id="+rs.getInt("trip_id");
				stmt = conn.createStatement();
				ResultSet rs1 = stmt.executeQuery(sql);
				if(rs1.next()){
					info.put("startdate",rs1.getString("startdate"));
					sql = "select * from nationrouter where id="+rs1.getInt("router_id");
					stmt = conn.createStatement();
					ResultSet rs2 = stmt.executeQuery(sql);
					if(rs2.next()){
						info.put("start",rs2.getString("start"));
						info.put("dest",rs2.getString("dest"));
					}
				}
				nation.add(info);
			}
		}
		json.put("local",local);
		json.put("nation",nation);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
	}
}
