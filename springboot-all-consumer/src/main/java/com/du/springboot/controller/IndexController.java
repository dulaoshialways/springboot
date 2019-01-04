package com.du.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.du.springboot.entity.User;
import com.du.springboot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author djg
 * @date 2018/12/18 0:13
 * @des
 */
@Controller
public class IndexController {

    @Reference
    private UserService userService;

    @RequestMapping("/login.html")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String passWord, Model model){
        User user = new User();
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username,passWord);
            subject.login(token);
            //跳转到登录成功页面
            Map<String,Object> map = new ConcurrentHashMap<>();
            map.put("start",0);
            map.put("pageSize",10);
            List<User> userList = userService.getUserByPage(map);
            model.addAttribute("userList",userList);
            return "user";
        } catch (UnknownAccountException e){
            e.printStackTrace();
        } catch (IncorrectCredentialsException e){
            System.out.println("账号或者密码不正确");
            e.printStackTrace();
        } catch (LockedAccountException e){
            System.out.println("账号已被锁定");
            e.printStackTrace();
        } catch (AuthenticationException e) {
            System.out.println("密码错误");
            e.printStackTrace();
        }

        return "login";
    }
}
