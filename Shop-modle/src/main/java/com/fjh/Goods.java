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
	private int goodId;
	
	@MyColumn(columnName ="goods_name" )
	private String goodName;
	
	@MyColumn(columnName ="goods_price" )
	private Double goodPrice;
	
	@MyColumn(columnName ="add_time" )
	private Date addTime;
}
