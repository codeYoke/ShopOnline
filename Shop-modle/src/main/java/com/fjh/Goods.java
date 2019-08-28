package com.fjh;

import java.util.Date;

import com.fjh.annotation.MyColumn;
import com.fjh.annotation.MyKey;
import com.fjh.annotation.MyTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MyTable (tableName="goods")//对应数据库中的表
public class Goods {
	@MyKey(columnName = "goods_id",isGenerator = true)
	private int goods_id;
	
	@MyColumn(columnName ="goods_name" )
	private String goods_name;
	
	@MyColumn(columnName ="goods_price" )
	private Double goods_price;
	
	@MyColumn(columnName ="stock" )
	private int stock;
	
	@MyColumn(columnName ="add_time" )
	private Date add_time;
	
	@MyColumn(columnName ="sales" )
	private int sales;
	
	@MyColumn(columnName ="types_id" )
	private int types_id;
	
	@MyColumn(columnName ="infor_id" )
	private int infor_id;
	
	@MyColumn(columnName ="g_img" )
	private String g_img;
}
