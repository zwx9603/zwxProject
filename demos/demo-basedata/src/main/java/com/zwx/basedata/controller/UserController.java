package com.zwx.basedata.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zwx.basedata.entity.MyDataSource1;
import com.zwx.basedata.entity.MyDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/userController")
@ResponseBody
@PropertySource(value = {"classpath:config/data.properties"})
public class UserController {

    @Autowired
    MyDataSource1 myDataSource1;
    @Autowired
    MyDataSource myDataSource;

    @RequestMapping("/login1")
    public String toLogin(HttpServletRequest request){
        StringBuffer requestURL = request.getRequestURL(); // 返回除了参数之外的信息
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
        System.out.println(remoteHost);

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
        return null;
    }

    /*
     * 发送验证码
     * */
    @RequestMapping("/sendSecurityCode")
    public String sendSecurityCode(HttpServletRequest request, HttpServletResponse response, Model model){
        Map<String, Object> map = new HashMap<String,Object>();
        try {
            String mobile = request.getParameter("mobile");
            JSONObject json = null;

            //生成6位验证码
            String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
            String message="您的验证码为：" + verifyCode + " ，您正在使用短信验证码登录，5分钟内有效，感谢您的使用！【XXX服务平台】";
            // 调用发送短信的接口
            // 此处先假设调用接口成功
            String result = "success";
            if(result != "success"){//发送短信失败
                map.put("result","fail");
            }
            json = new JSONObject();
            json.put("mobile", mobile);
            json.put("sendedVarCode", verifyCode);
            json.put("createTime", System.currentTimeMillis());
            map.put("result","success");
            map.put("sendData", json);
            return JSON.toJSONString(map);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result","fail");
        }
        return JSON.toJSONString(map);
    }
}
