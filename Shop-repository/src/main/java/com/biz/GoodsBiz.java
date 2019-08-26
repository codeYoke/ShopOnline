package com.biz;

import java.util.List;

import com.fjh.Goods;
import com.fjh.dao.GoodsDao;

/**
 * 业务逻辑层
 * @author Administrator
 *
 */
public interface GoodsBiz {
	/**
	 * 根据商品名模糊查询
	 * @param bookname
	 * @return List集合
	 */
	List<Goods> findGoodsByName(String goodsName,int page_goods, int page_No);
	
	//接口中所有方法默认为public
	/**
	 * 查询所有书籍数
	 * @return
	 */
	public int count();
	
	public void setBookdao(GoodsDao bookdao);
	/**
	 * 根据分页查询所有商品
	 * @param page_books 每页显示条数
	 * @param page_No 当前显示页面
	 * @return
	 */
	List<Goods> findAll(int page_books, int page_No);
	
	/**
	 * 修改商品库存,防止库存为负数
	 * @param bid 根据商品id修改
	 * @param count 数量
	 * @return
	 */
	public boolean changeStock(int bid, String count);
}
