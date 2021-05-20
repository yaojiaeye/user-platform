package com.yaojia.projects.user.web.controller;

import org.geektimes.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * 输出 “Hello,World” Controller
 */
@Path("/hello")
public class HelloWorldController implements PageController {

    /**
     * 实现注册跳转
     * @param request  HTTP 请求
     * @param response HTTP 相应
     * @return
     * @throws Throwable
     */
    @Override
    @GET
    @Path("/doRegist") // /hello/world -> HelloWorldController
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        System.out.println("ilogin.jsp");
        return "login.jsp";
    }

//    @GET
//    @Path("/regist")
//    public String json(HttpServletRequest request, HttpServletResponse response){
//        System.out.println("login-form");
//        return "login.jsp";
//    }
}
