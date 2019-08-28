package com.biz.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.biz.GoodsBiz;
import com.fjh.Goods;
import com.fjh.dao.DbDao;
import com.fjh.dao.GoodsDao;
import com.fjh.dao.impl.DbDaoImpl;


public class GoodsBizImpl implements GoodsBiz {
	GoodsDao bookdao = null;
	DbDao basedao = new DbDaoImpl();
	
	@Override
	public List<Map<String, Object>> findGoodsByName(String goodsName,int page_goods, int page_No) {
		
		String sql = "select * from goods  where goods_name like '%"+goodsName+"%' limit "+(page_No-1)*page_goods+","+page_goods;	//模糊匹配查询
		System.out.println("执行模糊查询语句："+sql);
		return bookdao.query(sql);
	}

	@Override
	public int findGoodsByName(String goodsName) {
		String sql = "select count(*) from goods  where goods_name like '%"+goodsName+"%'";
		int countByName = bookdao.count(sql);
		return countByName;
	}
	@Override
	public int count() {
		return bookdao.count();
	}

	@Override
	public void setBookdao(GoodsDao bookdao) {
		this.bookdao = bookdao;
	}

	@Override
	public List<Map<String, Object>> findAll(int page_goods, int page_No) {
		//分页查询，MySQL中limit子句指定偏移量查询
		String sql = "select * from goods where goods_id limit "+(page_No-1)*page_goods+","+page_goods;	
		System.out.println("执行分页查找语句："+sql);
		return bookdao.query(sql);
	}

	@Override
	public boolean changeStock(int gid, String count) {
		boolean flag = false;
		String sql = "update goods set stock=stock+"+count+" where goods_id="+gid;
		System.out.println("执行修改库存语句："+sql);
		try {
			if(bookdao.queryStock(gid) > 0) {
				flag = bookdao.update(sql)>0?true:false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return flag;
	}

	

	
}
