package com.fjh.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.fjh.UserInfo;
import com.fjh.dao.DbDao;
import com.fjh.dao.UserDao;
import com.fjh.exception.MyException;

public class UserDaoImpl implements UserDao {
	DbDao db = new DbDaoImpl();
	UserInfo info = null;
/**
 * 查询用户信息
 */
	@Override
	public UserInfo query(String sql,UserInfo userInfo) {
		
	
		try {
			 info = (UserInfo) db.excuteSql(sql, userInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}

	//注册后添加用户信息
	@Override
	public int insert(UserInfo userinfo) {
		try {
			int i = db.insert(userinfo).intValue();
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
