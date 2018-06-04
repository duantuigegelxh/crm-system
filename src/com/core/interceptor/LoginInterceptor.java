package com.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.core.po.User;
/**
 *登录的拦截器,只有已经登录的用户才能通过，而对于未登录的用户的请求，系统会将请求转发到登录页面，并提示用户登录
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	//在控制器方法之前执行
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		// TODO Auto-generated method stub
		//获得请求的URI
		String url=request.getRequestURI();
		/**
		 * test1.jsp=======================
		 * <a href ="test.jsp?name=wf">跳转到test2.jsp</a>
		 * test2.jsp=======================
		 * HttpServletRequest req = (HttpServletRequest) request;
		 * HttpServletResponse resp = (HttpServletResponse) response;
		 * String servletPath = req.getRequestURI()
		 * String servletPath = req.getRequestURL()
		 * 得到的值为：req.getRequestURI()-----> /test/test.jsp 
		 * req.getRequestURL()-----> http://localhost:8080/test/test.jsp  
		 */
		//URI除了登录请求外，其他的URL都进行拦截控制
		if(url.indexOf("/login.action")>=0){
			return true;
		}
		//获取Session
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("USER_SESSION");//在UserController定义了session.setAttribute("USER_SESSION",user);
		//判断session中是否有这个用户数据，如果有则返回true，继续向下执行
		if(user!=null){
			return true;
		}
		//不符合条件的给出提示信息，并转发到登录页面
		request.setAttribute("msg", "您还没有登录，请先登录");//将值传给index.jsp页面
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		return false;
	}

}











