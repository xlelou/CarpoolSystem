package com.ssh.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;

public  class GetConn {
	public static Object returnConn(){
		Connection conn=null;
	try{
		Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/cpsystem";
		String usernameMysql="root";
		String passwordMysql="root";
		conn=DriverManager.getConnection(url,usernameMysql,passwordMysql);
	}catch (Exception e){
		e.printStackTrace();
	}
	return conn;
	}
}
