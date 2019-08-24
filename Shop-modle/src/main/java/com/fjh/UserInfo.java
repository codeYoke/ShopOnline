package com.fjh;

import com.fjh.annotation.MyColumn;
import com.fjh.annotation.MyKey;
import com.fjh.annotation.MyTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MyTable (tableName="users")//对应数据库中的表
public class UserInfo {
	@MyKey(columnName = "user_id",isGenerator = true)
	private Long userId;
	
	@MyColumn(columnName ="user_name" )
	private String userName;
	
	@MyColumn(columnName ="user_password" )
	private String password;
	
	@MyColumn(columnName ="pay" )
	private String pay;
	
	@MyColumn(columnName ="detail_id" )
	private Long detailId;
	
	@MyColumn(columnName ="email" )
	private String email;
	
}
