package com.fjh;

import java.util.Date;

import com.fjh.annotation.MyKey;
import com.fjh.annotation.MyTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MyTable (tableName="orders")//对应数据库中的表
public class Orders {
	
	@MyKey(columnName = "orders_id",isGenerator = true)
	private int oId;
	
	@MyKey(columnName = "o_time")
	private Date oTime;
	
	@MyKey(columnName = "state")
	private int state;
	
	@MyKey(columnName = "u_id")
	private int userId;
}
