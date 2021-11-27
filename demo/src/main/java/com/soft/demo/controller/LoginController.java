package com.soft.demo.controller;

import com.soft.demo.entity.UserEntity;
import com.soft.demo.service.UserService;
import com.soft.demo.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Controller
@RequestMapping("/LoginController")
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/login1")
    public String toLogin(HttpServletRequest request, String userName, String passdWord){
        /*StringBuffer requestURL = request.getRequestURL(); // 返回除了参数之外的信息
        String contextPath = request.getContextPath();
        Enumeration<String> headerNames = request.getHeaderNames();
        String contentType = request.getContentType();
        String queryString = request.getQueryString(); // 返回URL的参数
        String remoteHost = request.getRemoteHost(); // 获取主机ip....
        int remotePort = request.getRemotePort(); // 端口号
        String scheme = request.getScheme();
        System.out.println(requestURL);
        System.out.println(contextPath);
        System.out.println(headerNames.toString());
        System.out.println(contentType);
        System.out.println(queryString);
        System.out.println(remoteHost);*/
        /*
         * 浏览器访问前段页面的方法:
         * 1.页面放在static文件夹下: localhost:8080/login.html
         *  1.1在static文件夹下建立文件夹html: localhost:8080/html/login.html
         * 2.将页面放在templates目录下访问(springBoot不能直接访问templates下的静态资源),不推荐使用
         * 3.通过controller控制层跳转访问的资源(页面在templates下)
         *   3.1 注意：一定要导入thymeleaf依赖
         *   3.2 编写控制层
         *       @RequestMapping("/test3")
         *       public String test3(){return  "test3" }
         *        访问: localhost:8080/controller/test3
         *   3.3 注意:此方法最重要的一点:用@Controller注解
         *            不能用@RestController注解(返回字符串)
         * */
        UserEntity userEntity = userService.queryUserInfo(userName, passdWord);
//        return "login";
        return "register";
    }
}
