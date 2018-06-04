package com.core.dao;

import java.util.List;

import com.core.po.Customer;

/**
 * Custom接口
 *
 */
public interface CustomerDao {

	//客户列表的查询,传入一个Custom对象，根据客户名称，客户来源，所属行业和客户级别来查询，返回一个列表集合
	public List<Customer> selectCustomList(Customer customer);
	
	//客户数,传入一个Custom对象，根据客户名称，客户来源，所属行业和客户级别来查询，返回总数
	public Integer selectCustomListCount(Customer customer);

	//插入信息，并返回影响行数
	public int createCustomer(Customer customer);

	//通过id查询出对象的Customer对象
	public Customer getCustomerById(Integer id);

	//通过Customer对象更新数据库
	public int updateCustomer(Customer customer);

	//删除客户
	public int deleteCustomer(Integer id);
}
