package com.fjh.dao;

import java.sql.SQLException;
import java.util.List;

public interface GoodsDao {

	/**
	 * 查询�?有图�?
	 * @param sql
	 * @return
	 */
	public List query(String sql);
	/**
	 * 查询书籍总数
	 * @return
	 */
	public int count();
	
	/**
	 * 修改商品库存，防止库存为负数
	 * @param sql
	 * @return
	 */
	public int update(String sql);
	public int queryStock(int bid) throws SQLException;
	
}
