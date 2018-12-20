package dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
	
	/*
	 * 获取对数据库的连接
	 */
	public static Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Class.forName("com.mysql.jdbc.Driver")
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/youjidatabase?"
					+ "serverTimezone=GMT"
					+ "&useUnicode=true"
					+ "&characterEncoding=utf-8"
					+ "&useSSL=false","root","");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}

}
