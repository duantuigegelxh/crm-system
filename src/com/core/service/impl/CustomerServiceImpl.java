package com.core.service.impl;

import java.util.List;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.utils.Page;
import com.core.dao.CustomerDao;
import com.core.po.Customer;
import com.core.service.CustomerService;
/**
 * 客户管理
 *
 */
@Service("CustomerService")
@Transactional
public class CustomerServiceImpl implements CustomerService{

	//声明Dao属性注入
	@Autowired
	private CustomerDao customerDao;
	@Override
	public Page<Customer> findCustomerList(Integer page, Integer rows, String custName, String custSource,
			String custIndustry, String custLevel) {
		// TODO Auto-generated method stub
		//创建一个customer对象
		Customer customer=new Customer();
		//判断客户名称
		if(StringUtils.isNotBlank(custName)){//判断是否为空,不为空返回true
			/*
			 * isBlank 等价于 str == null || str.length == 0 || str.trim().length
			 * == 0
			 * StringUtils方法的操作对象是java.lang.String类型的对象，是JDK提供的String类型操作方法的补充，
			 * 并且是null安全的(即如果输入参数String为null则不会抛出NullPointerException，而是做了相应处理，
			 * 例如，如果输入为null则返回也是null等
			 */
			customer.setCust_name(custName);
		}
		//判断客户信息来源
		if(StringUtils.isNotBlank(custSource)){
			customer.setCust_source(custSource);
		}
		//判断客户行业
		if(StringUtils.isNotBlank(custIndustry)){
			customer.setCust_industry(custIndustry);
		}
		//判断客户级别
		if(StringUtils.isNotBlank(custLevel)){
			customer.setCust_level(custLevel);
		}
		//设置当前页
		customer.setStart((page-1)*rows);
		//每页数
		customer.setRows(rows);
		//客户查询列表
		List<Customer> customers=customerDao.selectCustomList(customer);
		//查询用户列表总记录数
		Integer count=customerDao.selectCustomListCount(customer);
		//创建一个page返回对象
		Page<Customer> result=new Page<Customer>();
		result.setPage(page);
		result.setRows(customers);
		result.setSize(rows);
		result.setTotal(count);
		return result;
	}
	
	/**
	 * 创建客户
	 */
	@Override
	public int createCustomer(Customer customer) {
		
		return customerDao.createCustomer(customer);
	}

	/**
	 * 通过id查询客户
	 */
	@Override
	public Customer getCustomerById(Integer id) {
		// TODO Auto-generated method stub
		Customer customer=customerDao.getCustomerById(id);
		String i=null;
		return customer;
	}

	/**
	 * 更新客户
	 */
	@Override
	public int updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerDao.updateCustomer(customer);
	}

	/**
	 * 删除客户
	 */
	@Override
	public int deleteCustomer(Integer id) {
		// TODO Auto-generated method stub
		return customerDao.deleteCustomer(id);
	}

}

















