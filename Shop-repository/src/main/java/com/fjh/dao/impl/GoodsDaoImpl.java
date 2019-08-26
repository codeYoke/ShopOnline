package com.fjh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.fjh.dao.GoodsDao;
import com.fjh.util.DbUtil;

public class GoodsDaoImpl extends DbDaoImpl implements GoodsDao {
	DbUtil db = new DbUtil();
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@Override
	public List query(String sql){
		List<Map<String, Object>> data =null;
		try {
			data = getDatas(sql);
			System.out.println(data.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public int count() {
		String sql = "select count(*) from goods";	
		conn = db.getConnection();
		int i = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				i = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close(conn, ps, rs);
		}
		
		return i;
	}

	@Override
	public int update(String sql) {
		//return deleteOrUpdate(sql);
		return 1;
	}

	/**
	 * 
	 * @Title: queryStock
	 * @Description: ²éÑ¯¿â´æ
	 *  @param bid
	 *  @return
	 *  @throws SQLException
	 */
public int queryStock(int bid) throws SQLException {
		
		int stock = 0;
		String sql = "select stock from books where bid="+bid;
		db.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		rs.next();
		return rs.getInt(1);
	}

}
