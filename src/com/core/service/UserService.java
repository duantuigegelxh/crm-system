package com.core.service;

import com.core.po.User;

/**
 *用户service层接口
 */
public interface UserService {

	//通过帐号和密码查询
	public User findUser(String usercode,String password);
}
