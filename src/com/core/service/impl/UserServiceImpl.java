package com.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.dao.UserDao;
import com.core.po.User;
import com.core.service.UserService;

/**
  *用户service层接口的实现类
  */
@Service("userService")
/*
 * 在applicationContext.xml文件中加一行
 *<context:component-scan base-package="com.itheima.core.service"/>
 * 加上这一行以后，将自动扫描路径下面的包，如果一个类带了@Service注解，
 * 将自动注册到Spring容器，不需要再在applicationContext.xml文件定义bean了，
 * 类似的还包括@Component、@Repository、@Controller.这里相当于
 *  <bean id="userService"
         class="com.itheima.core.service.UserServic>
         ......    
    </bean>   
 */
@Transactional//这个是实现事务的注解
public class UserServiceImpl implements UserService{

	//注入UserDao
	@Autowired
	private UserDao userDao;
	@Override
	//通过帐号和密码查询
	public User findUser(String usercode, String password) {
		// TODO Auto-generated method stub
		User user=this.userDao.findUser(usercode, password);
		return user;
	}

}
