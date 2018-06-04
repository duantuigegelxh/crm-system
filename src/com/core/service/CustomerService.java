package com.core.service;

import com.common.utils.Page;
import com.core.po.Customer;

/**
 * 客户信息的service接口
 *
 */
public interface CustomerService {

	// 查询客户列表
	public Page<Customer> findCustomerList(Integer page, Integer rows, 
			String custName, String custSource,String custIndustry,String custLevel);

	//创建客户
	public int createCustomer(Customer customer);

	//通过id来得到一个Customer对象
	public Customer getCustomerById(Integer id);

	//根据Customer对象进行修改
	public int updateCustomer(Customer customer);

	//删除客户
	public int deleteCustomer(Integer id);
}
