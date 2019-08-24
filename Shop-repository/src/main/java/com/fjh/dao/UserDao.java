package com.fjh.dao;

import java.util.List;

import com.fjh.UserInfo;


public interface UserDao {
	/**
	 * 
	 * @param sql
	 * @return
	 */
	public UserInfo query(String sql,UserInfo info);
	/**
	 * 
	 * @param userinfo
	 * @return
	 */
	public int insert(UserInfo userinfo);
	
//	public int deleteOrUpdate(String sql);
//	public int insert(String sql);
}
