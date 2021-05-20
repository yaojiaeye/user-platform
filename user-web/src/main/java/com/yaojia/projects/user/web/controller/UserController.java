package com.yaojia.projects.user.web.controller;
import com.yaojia.projects.user.domain.User;
import com.yaojia.projects.user.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.geektimes.web.mvc.controller.PageController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Set;

/**
 * @author yaojia
 */
@Path("/user")
public class UserController implements PageController {

    @Resource(name = "bean/UserService")
    private UserService userService ;


    @Resource(name = "bean/Validator")
    private Validator validator;

    /**
     * 用自研的MVC实现用户注册
     * @param request  HTTP 请求
     * @param response HTTP 相应
     * @return
     * @throws Throwable
     */
    @GET
    @Path("/doRegist")
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("email = " + email + " inputPassword = " + password);
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNumber("132792");
        checkParam(user);
        // 注册
        userService.register(user);
        return "index.jsp";
    }

    /**
     * 校验入参
     * @param user
     */
    private void checkParam(User user){
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if(CollectionUtils.isNotEmpty(violations)){
            violations.forEach(c -> {
                //在项目中：1、可以直接抛自定义Exception，然后自定义一个@ControllerAdvice 去统一处理
                throw new RuntimeException(c.getMessage());
                //2、或者直接return到fail.jsp中 并且把c.getMessage()的信息传给jsp进行提示
            });
        }
    }
}
