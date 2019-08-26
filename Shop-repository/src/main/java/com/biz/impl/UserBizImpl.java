package com.biz.impl;

import java.util.List;

import com.biz.UserBiz;
import com.fjh.UserInfo;
import com.fjh.dao.UserDao;


public class UserBizImpl implements UserBiz{
	private UserDao userDao = null;
	UserInfo info = new UserInfo();
	
	public UserDao getUserDao() {
		return userDao;
	}
	
	
	
	
	@Override
	public boolean checkLogin(String username,String password) {

		String sql = "select * from users where user_name='"+username+"'and user_password='"+password+"'";
		System.out.println(sql); 
		info = userDao.query(sql,info);	
		//System.out.println("检测登录密码用户名："+info.getUserName());
		System.out.println("检测登录密码用户名："+info.getUserName()==username ? true : false);
		return info.getUserName().equals(username) ? true : false;
	}




	@Override
	public void setUserDao(UserDao userdao) {
		// TODO Auto-generated method stub
		this.userDao = userdao;
	}




	@Override
	public boolean addUser(UserInfo userinfo) {
		int i = userDao.insert(userinfo);
		System.out.println("插入用户情况："+i);
		return i ==-1 ? false : true;
	}

	@Override
	public boolean userExist(String username,UserInfo userInfo) {
		String sql = "select * from users where user_name='"+username+"'";
		System.out.println(sql);
		userInfo = new UserInfo();
		info = userDao.query(sql,userInfo);
		System.out.println(info.getUserName());
		return info.getUserName()!=null ? true : false;	//如果查询有数据则已经被注册
	}
	
}
