package com.biz;

import com.fjh.UserInfo;
import com.fjh.dao.UserDao;



public interface UserBiz {
	/**
	 * 查询方法
	 * @param sql
	 * @return
	 */
	public boolean checkLogin(String username,String password,UserInfo userInfo);
	public void setUserDao(UserDao userdao);
	/**
	 * 注册、添加用户
	 * @param userinfo
	 * @return
	 */
	public boolean addUser(UserInfo userinfo);
	
	/**
	 * 判断用户是否已存在
	 * @param username
	 * @return
	 */
	public boolean userExist(String username,UserInfo userInfo);
}
