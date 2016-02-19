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

public class RecMessage extends ActionSupport {
	public void receive() throws ClassNotFoundException, SQLException, IOException{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/cpsystem";
		String usernameMysql = "root";
		String passwordMysql = "root";
		Connection conn = DriverManager.getConnection(url,usernameMysql,passwordMysql);
		Statement stmt=conn.createStatement();
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject dt = JSONObject.parseObject(request.getParameter("dt"));
		String id = (String) dt.get("id");
		String sql = "select * from message where to_id="+id;
		ResultSet messages =stmt.executeQuery(sql);
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		while(messages.next()){
			JSONObject mes = new JSONObject();
			mes.put("id",messages.getInt("id"));
			mes.put("title",messages.getString("title"));
			mes.put("status",messages.getInt("status"));
			mes.put("detail",messages.getString("detail"));
			mes.put("createtime",messages.getString("createtime"));
			sql = "select * from user where id="+messages.getInt("from_id");
			if(messages.getInt("from_id")==0){
				mes.put("from","系统");
			}else{
				stmt = conn.createStatement();
				ResultSet user = stmt.executeQuery(sql);
				if(user.next()){
					if(user.getString("name").equals("0")){
						mes.put("from","用户"+user.getString("phone"));
					}else{
						mes.put("from",user.getString("name"));
					}
				}
			}
			array.add(mes);
		}
		json.put("messages",array);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
	}
}
