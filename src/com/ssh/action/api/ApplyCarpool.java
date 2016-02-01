package com.ssh.action.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

public class ApplyCarpool extends ActionSupport {
	public void apply() throws ClassNotFoundException, SQLException, IOException{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/cpsystem";
		String usernameMysql = "root";
		String passwordMysql = "root";
		Connection conn = DriverManager.getConnection(url,usernameMysql,passwordMysql);
		Statement stmt=conn.createStatement();
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject dt = JSONObject.parseObject(request.getParameter("dt"));
		JSONObject json = new JSONObject();
		String apply_id = (String) dt.get("apply_id");//获取申请者的id
		String trip_id =(String)dt.get("trip_id");//获取要加入的id
		int type = Integer.parseInt((String) dt.get("type"));
		String sql = "insert into record(trip_id,apply_id,status,type) values("+trip_id+","+apply_id+",1,"+type+")";
		stmt.executeUpdate(sql);
		if(type==1){//本地加入
			 sql = "update local set apply_count=apply_count+1 where id="+trip_id;
			 stmt=conn.createStatement();
			 stmt.executeUpdate(sql);
			 sql = "insert into apply(trip_id,apply_id,status,type) values("+trip_id+","+apply_id+",1,1)";	
			 stmt=conn.createStatement();
			 stmt.executeUpdate(sql);
			 json.put("result","success");
		}else if(type==2){//全国加入
			sql = "update nation set apply_count=apply_count+1 where id="+trip_id;
			stmt=conn.createStatement();
			stmt.executeUpdate(sql);
			sql = "insert into apply(trip_id,apply_id,status,type) values("+trip_id+","+apply_id+",1,2)";
			stmt=conn.createStatement();
			stmt.executeUpdate(sql);
			json.put("result","success");
		}else if(type==3){//本地取消加入
			sql = "update local set apply_count=apply_count-1 where id="+trip_id;
			stmt=conn.createStatement();
			stmt.executeUpdate(sql);
			sql ="delete from apply where trip_id="+trip_id+" &&apply_id="+apply_id+" &&type=1";
			stmt=conn.createStatement();
			stmt.executeUpdate(sql);
			json.put("result","success");
		}else if(type==4){//全国取消加入 
			sql = "update nation set apply_count=apply_count-1 where id="+trip_id;
			stmt=conn.createStatement();
			stmt.executeUpdate(sql);
			sql ="delete from apply where trip_id="+trip_id+" &&apply_id="+apply_id+" &&type=2";
			stmt=conn.createStatement();
			stmt.executeUpdate(sql);
			json.put("result","success");
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
	} 
}
