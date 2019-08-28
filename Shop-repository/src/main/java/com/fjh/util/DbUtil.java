package com.fjh.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.DataSourceConnectionFactory;


/**
 * 
 * @ClassName: DbUtil
 * @Description: TODO(数据库连接工具类)
 * @author 冯佳豪
 * @date 2019年7月9日
 *
 */
public class DbUtil {

	//获取数据库连接
	/**
	 * 
	 * @return 获取数据库连接
	 */
	static String url = Constant.URL;
	static String user = Constant.USER;
	static String pwd = Constant.PASSWORD;
	static String driver = Constant.DRIVER;
	static final ThreadLocal<Connection> conns = new ThreadLocal<Connection>();
	//注册驱动
	private static BasicDataSource ds = null;
	static DataSourceConnectionFactory connectionFactory = null;//连接池工厂
	
	static {
		try {
			ds = new BasicDataSource();//数据源
			ds.setDriverClassName(driver);
			ds.setUrl(url);
			ds.setUsername(user);
			ds.setPassword(pwd);
			
			/*
			  // 初始连接数
			ds.setInitialSize(20);
	        // 最大的获取连接数
			ds.setMaxActive(100);
	        // 最小可用空闲连接数
			ds.setMinIdle(10);
	        // 最大可用空闲连接数
			ds.setMaxIdle(30);       
			*/
			
			// 初始化连接池工厂
			connectionFactory = new DataSourceConnectionFactory(ds) ;
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获取数据库连接方法
	 * @return
	 */
	public static synchronized Connection getConnection() {
		try {
			Connection conn = conns.get();
			System.out.println(conn);
			if(null == conn || conn.isClosed()) {
				conn = connectionFactory.createConnection();
				System.out.println(conn.isClosed()+conn.toString());
				conns.set(conn); 
			}
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//关闭数据库资源
	public static void close(Connection conn,Statement stmt,ResultSet rs) {
		try {
			if(null != conn) conn.close();
			if(null != stmt) stmt.close();
			if(null != rs) rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//关闭数据库所有连接
	public static void closeAll() {
		Connection conn = conns.get();
		try {
			if(conn != null && !conn.isClosed())
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conns.remove();
	}
	public static void close(Connection conn) {
		close(conn,null,null);
	}
	public static void close(Connection conn,Statement stmt) {
		close( conn, stmt,null);
	}
}
