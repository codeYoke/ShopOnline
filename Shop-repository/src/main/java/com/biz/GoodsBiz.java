package com.biz;

import java.util.List;
import java.util.Map;

import com.fjh.Goods;
import com.fjh.dao.GoodsDao;

/**
 * 业务逻辑层
 * @author Administrator
 *
 */
public interface GoodsBiz {
	/**
	 * 
	 * @Title: findGoodsByName
	 * @Description: 根据书名模糊查询（非所有模糊名数据）
	 *  @param goodsName 模糊查询的关键字
	 *  @param page_goods 查询的条数
	 *  @param page_No 当前页码
	 *  @return 商品集合
	 */
	public List<Map<String, Object>> findGoodsByName(String goodsName,int page_goods, int page_No);
	
	/**
	 * 
	 * @Title: findGoodsByName
	 * @Description: 通过关键字模糊查询所有商品记录
	 *  @param goodsName 搜索框关键字
	 *  @return 返回记录条数
	 */
	public int findGoodsByName(String goodsName);
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
	List<Map<String, Object>> findAll(int page_books, int page_No);
	
	/**
	 * 修改商品库存,防止库存为负数
	 * @param bid 根据商品id修改
	 * @param count 数量
	 * @return
	 */
	public boolean changeStock(int bid, String count);
}
