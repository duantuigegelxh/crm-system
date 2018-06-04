package com.core.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.core.po.User;
import com.core.service.UserService;
/**
 *用户控制器类
 */
@Controller
public class UserController {

	//依赖注入
	@Autowired
	private UserService userService;
	
	/**
	 * 用户登录
	 */
	@RequestMapping(value="/login.action",method=RequestMethod.POST)
	/*
	 * RequestMapping是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。
	 * 类定义处: 提供初步的请求映射信息。相对于 WEB 应用的根目录；
	 * 方法处: 提供进一步的细分映射信息。 相对于类定义处的 URL。
	 * 若类定义处未标注 @RequestMapping，则方法处标记的 URL相对于 WEB 应用的根目录
	 * 返回ModelAndView时的url会根据你的 @RequestMapping实际情况组成。
	 * 如果类上没有映射，那么url直接就是方法的映射；否则url为类上+方法上映射路径组合。
	 */
	public String login(String usercode,String password,Model model,HttpSession session){
		//通过帐号和密码查询用户
		User user=userService.findUser(usercode, password);
		if(user!=null){
			//将用户添加到session中
			session.setAttribute("USER_SESSION",user);
			//跳转到主页面
			return "redirect:/customer/list.action";
		}
		model.addAttribute("msg","帐号或者密码错误,请重新输入");
		return "login";
		
	}
	/**
	 * 模拟其他类中跳转到客户管理页面的方法
	 */
	@RequestMapping(value="/toCustomer.action")
	public String toCustomer(){
		return "customer";
	}
	
	
	/**
	 * 退出登录,需要在这里清除session，因此需要传入一个session
	 */
	@RequestMapping(value="/logout.action")
	public String logout(HttpSession session){
		//清楚session
		session.invalidate();//清楚session的方法
		//重定向到登录页面,使得这个逻辑清晰，退出登录只进行清楚session
		return "redirect:login.action";//所以还需要提供一个实现这样的登录功能,重定向是get提交方式
	}
	
	/**
	 * 向用户登录页面跳转
	 */
	@RequestMapping(value="/login.action",method=RequestMethod.GET)
	public String login(){
		return "login";
	}
}
















