package com.core.dao;

import org.apache.ibatis.annotations.Param;

import com.core.po.User;

/**
 * User表的dao接口，还需要一个UserDao.xml文件
 *
 */
public interface UserDao {

	/**
	 * 通过帐号和密码查询用户
	 * @param("usercode")用来将值传入，这xml文件中可以用#{usercode}接收
	 */
	public User findUser(@Param("usercode")String usercode,@Param("password")String password);
		
	
}
