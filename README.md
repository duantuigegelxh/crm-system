# crm-system
开发前准备
---

* 视频演示地址：https://pan.baidu.com/s/1y1SvON2ShxUwjbopr7u7aQ<br>
* 开发环境：mysql eclipse tomcat<br>
* 框架：spring springmvc mybatis<br>
* 本系统使用的技术如下：前台页面使用`HTML`，`CSS`、`JS`、和`BootStrap`框架。之间用`jquery`和`ajax`进行json数据之间的交互。后台使用`SSM`框架（Spring、Spring MVC和Mybatis）,其中`Spring`进行整合MVC三层结构，`Mybatis`作为持久层，用来连接数据库。`SpringMVC`作为控制层，进行一些业务处理。数据库为`mysql`。


一、需求分析
------
1.设计一个较为完善的客户管理系统<br>
2.实现登陆界面和登陆拦截，通过数据库里面的数据进行比对实现登陆功能，如果登陆成功，则直接进入信息显示界面，否则提示用户名或者密码错误。登陆拦截通过判断是否是已经登陆过的Session，如果是之前登陆过的Session,则可以通过直接输入url进入信息界面。否则会退出到刚开始的登陆界面并提示未登陆，请输入用户和密码。<br>
3.客户信息管理界面，实现对信息按照客户名称、客户来源、所属行业和客户级别进行查询。<br>
4.对相应的客户进行删除和修改。<br>
5.点击新建按钮能新建相应的客户。<br>
6.实现信息显示的分页功能。<br>
7.退出登陆，并销毁当前的Session。<br>


二、程序流程图
----
![](http://group.store.qq.com/qun/ULZkivhtQ*pGJCc0FCm.oA!!/V3t.xGzDm8AM1u4fxwG/800?w5=758&h5=393&rf=viewer_421)  



三、项目展示
----
![](http://group.store.qq.com/qun/ULZkivhtQ*pGJCc0FCm.oA!!/V3t.xGzDoYBM1saSwwF/800?w5=1266&h5=591&rf=viewer_421)  
![](http://group.store.qq.com/qun/ULZkivhtQ*pGJCc0FCm.oA!!/V3t.xGzDoUBM1uorlUI/800?w5=423&h5=269&rf=viewer_421)  
![](http://group.store.qq.com/qun/ULZkivhtQ*pGJCc0FCm.oA!!/V3t.xGzDoQBM1sTPJk1/800?w5=1464&h5=640&rf=viewer_421)  
![](http://group.store.qq.com/qun/ULZkivhtQ*pGJCc0FCm.oA!!/V3t.xGzDoMBM1sOtL8y/800?w5=554&h5=119&rf=viewer_421)   
![](http://group.store.qq.com/qun/ULZkivhtQ*pGJCc0FCm.oA!!/V3t.xGzDoEBM1tHnG4S/800?w5=716&h5=640&rf=viewer_421)
![](http://group.store.qq.com/qun/ULZkivhtQ*pGJCc0FCm.oA!!/V3t.xGzDoABM1sJN.o1/800?w5=707&h5=640&rf=viewer_421)
![](http://group.store.qq.com/qun/ULZkivhtQ*pGJCc0FCm.oA!!/V3t.xGzDn4BM1t7Rs4H/800?w5=360&h5=202&rf=viewer_421)

四、功能实现
----
* 增删改查由mabatis实现，我这里采用的事务管理是spring实现的dbcp事务管理。其实mabatis也有相应的事务管理，但是每次都要提交还有其他的麻烦事情，我就采用了spring的事务管理，只需要一个注解就好了。
* 登陆验证由SpringMVC的拦截器实现，根据request.getRequestURI()方法得到用户所访问的URL，如果之前已经登陆过客户管理系统，则直接进入管理界面，否则退回登陆界面并提示“还未登陆，请登陆”。
* 由这个SpringMVC的拦截器出发，我在后面又进行了改进，增加了权限管理，权限管理的框架有spring的security和apache的shiro。但是我使用的是基于SpringMVC的拦截器实现权限管理。使用这个方法实现权限管理可以去我的博客上看一篇我转载的文章https://blog.csdn.net/lxh123456789asd/article/details/80645171 里面还有大神使用框架实现权限管理的文章的链接。
