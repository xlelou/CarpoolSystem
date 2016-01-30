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

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

public class Nationpost extends ActionSupport {
	public void post() throws ClassNotFoundException, SQLException, IOException{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/cpsystem";
		String usernameMysql = "root";
		String passwordMysql = "root";
		Connection conn = DriverManager.getConnection(url,usernameMysql,passwordMysql);
		Statement stmt=conn.createStatement();
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject dt = JSONObject.parseObject(request.getParameter("dt"));
		int type = Integer.parseInt((String)dt.get("type"));
		String apply_id = (String) dt.get("id");
		String start = (String) dt.get("start");
		String dest = (String) dt.get("dest");
		String startdate = (String) dt.get("startdate");
		String starttime = (String) dt.get("starttime");
		String count = (String) dt.get("count");
		String price = (String) dt.get("price");
		String message = (String) dt.get("message");
		if(type==1){//司机发布
			String cartype = (String) dt.get("cartype");
			String sql = "insert into nation(type,apply_id,router_id,need_count,in_count,apply_count,price,cartype,status,startdate,starttime,viewtime,message) values(1,"+apply_id+",0,"+count+",0,0,'"+price+"','"+cartype+"',1,'"+startdate+"','"+starttime+"',0,'"+message+"')";
			stmt.executeUpdate(sql);
			stmt = conn.createStatement();
			sql = "select * from nation where router_id=0";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				sql = "insert into nationrouter values("+rs.getInt("id")+",'"+start+"','"+dest+"')";
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				stmt = conn.createStatement();
				sql = "update nation set router_id="+rs.getInt("id")+" where router_id=0";
				stmt.executeUpdate(sql);
			}
		}else if(type==2){//乘客发布
			String sql = "insert into nation(type,apply_id,router_id,need_count,in_count,apply_count,price,cartype,status,startdate,starttime,viewtime,message) values(2,"+apply_id+",0,"+count+",0,0,'"+price+"','0',1,'"+startdate+"','"+starttime+"',0,'"+message+"')";
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			sql = "select * from nation where router_id=0";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				sql = "insert into nationrouter values("+rs.getInt("id")+",'"+start+"','"+dest+"')";
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				stmt = conn.createStatement();
				sql = "update nation set router_id="+rs.getInt("id")+" where router_id=0";
				stmt.executeUpdate(sql);
			}
		}
		JSONObject json = new JSONObject();
		json.put("result","success");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
	}
}