package com.biz.impl;

import java.sql.SQLException;
import java.util.List;

import com.biz.GoodsBiz;
import com.fjh.Goods;
import com.fjh.dao.DbDao;
import com.fjh.dao.GoodsDao;
import com.fjh.dao.impl.DbDaoImpl;


public class GoodsBizImpl implements GoodsBiz {
	GoodsDao bookdao = null;
	DbDao basedao = new DbDaoImpl();
	
	@Override
	public List<Goods> findGoodsByName(String goodsName,int page_goods, int page_No) {
		String sql = "select * from goods  where goods_name like '%"+goodsName+"%' limit "+(page_No-1)*page_goods+","+page_goods;	//模糊匹配查询
		return bookdao.query(sql);
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
	public List<Goods> findAll(int page_goods, int page_No) {
		//分页查询，MySQL中limit子句指定偏移量查询
		String sql = "select * from goods where goods_id limit "+(page_No-1)*page_goods+","+page_goods;	
		return bookdao.query(sql);
	}

	@Override
	public boolean changeStock(int gid, String count) {
		boolean flag = false;
		String sql = "update goods set stock=stock+"+count+" where goods_id="+gid;
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
