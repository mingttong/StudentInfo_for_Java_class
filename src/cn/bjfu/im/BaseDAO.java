package cn.bjfu.im;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDAO {
	
	/*
	 * 关闭数据库连接
	 */
	public void close(Connection conn) {
		if (conn == null) {
			return;
		}
		
		try {
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 获取数据库连接
	 */
	public Connection getConn() {
		Connection conn = null;
		
		try {
			// 1. 加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2. 获取指定数据库的连接对象
			String url = "jdbc:mysql://127.0.0.1:3306/zwn";
			conn = (Connection) DriverManager.getConnection(url, "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}

}
