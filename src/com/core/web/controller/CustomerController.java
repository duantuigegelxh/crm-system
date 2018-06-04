package com.core.web.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.utils.Page;
import com.core.po.BaseDict;
import com.core.po.Customer;
import com.core.po.User;
import com.core.service.BaseDictService;
import com.core.service.CustomerService;

/**
 * 客户管理控制器类
 *
 */
@Controller
public class CustomerController {

	//依赖注入
	@Autowired
	private CustomerService customerService;
	@Autowired
	private BaseDictService bseDictService;
	/*
	 * customer.from.type=002 
	 * customer.industry.type=001 
	 * customer.level.type=006
	 */
	//springmvc加载并读取属性配置文件resource.properties
	@Value("${customer.from.type}")
	private String FROM_TYPE;
	@Value("${customer.industry.type}")
	private String INDUSTRY_TYPE;
	@Value("${customer.level.type}")
	private String LEVEL_TYPE;
	/**
	 * 客户列表
	 */
	@RequestMapping(value="/customer/list.action")
	//@RequestParam(defaultValue="1")表示默认值为1
	public String list(@RequestParam(defaultValue="1")Integer page, @RequestParam(defaultValue="10")Integer rows, String custName, String custSource,
			String custIndustry, String custLevel,Model model){
		//改变get方法的编码，使其不产生乱码，//ISO8859-1显示数据以一个字节一个字节显示，而utf-8以几个字节一起表示
		String custName1=custName;
		/*byte[] bs;
		if (custName != null) {
			try {
				bs = custName.getBytes("ISO8859-1");
				custName1 = new String(bs, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		//根据条件查询所有的客户
		Page<Customer> customers=customerService.findCustomerList(page, rows, custName1, custSource, custIndustry, custLevel);
		model.addAttribute("page",customers);
		//客户来源
		List<BaseDict> fromType=bseDictService.findBaseDicByTypeCode(FROM_TYPE);
		//客户所属行业
		List<BaseDict> industryType=bseDictService.findBaseDicByTypeCode(INDUSTRY_TYPE);
		//客户级别
		List<BaseDict> levelType=bseDictService.findBaseDicByTypeCode(LEVEL_TYPE);
		model.addAttribute("fromType",fromType);
		model.addAttribute("industryType",industryType);
		model.addAttribute("levelType",levelType);
		//传过来的参数又要传回去，因为要进行比较
		model.addAttribute("custName",custName1);
		model.addAttribute("custSource",custSource);
		model.addAttribute("custIndustry",custIndustry);
		model.addAttribute("custLevel",custLevel);
		return "customer";
	}
	
	
	/**
	 * 创建客户
	 */
	@RequestMapping("/customer/create.action")
	@ResponseBody//直接往response中写内容而不经过视图解析器时可以使用@ResponseBody 
	public String customerCreate(Customer customer,HttpSession session){
		//获取session中当前用户信息
		User user=(User)session.getAttribute("USER_SESSION");//USER_SESSION之前存在com.itheima.core.web.controller.UserController类中
		//将当前用户id存储在客户对象中
		customer.setCust_create_id(user.getUser_id());
		//创建Date对象
		Date date=new Date();
		//得到一个timestamp格式时间，存入mysql中的时间格式“yyyy/MM/dd HH:mm:ss”
		//把一个java.util.Date类型转化成java.sql.Timestamp类型
		Timestamp timestamp=new Timestamp(date.getTime());
		customer.setCust_createtime(timestamp);
		//执行service层中的创建方法，返回的是影响的行数
		int rows=customerService.createCustomer(customer);
		if(rows>0){//操作成功
			return "OK";
		}else
		return "FAIL";
	}
	
	/**
	 * 通过id查找出相应的customer对象，将customer对象返回，使其显示在模态框中
	 */
	@RequestMapping("/customer/getCustomerById.action")
	@ResponseBody
	public Customer getCustomerById(Integer id){
		Customer customer=customerService.getCustomerById(id);
		return customer;
	}
	
	/**
	 * 执行更新操作
	 */
	@RequestMapping("/customer/update.action")
	@ResponseBody
	public String getcustomerupdate(Customer customer){
		int rows=customerService.updateCustomer(customer);
		if(rows>0){
			return "OK";
		}else
		return "FAIL";
	}
	
	
	/**
	 * 删除客户
	 */
	@RequestMapping("/customer/delete.action")
	@ResponseBody
	public String customerDelete(Integer id){
		int rows=customerService.deleteCustomer(id);
		if(rows>0){
			return "OK";
		}else
			return "FAIL";
	}
}














