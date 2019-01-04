package com.du.springboot.controller;

import com.du.springboot.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author djg
 * @date 2018/12/18 0:13
 * @des
 */
@Controller
public class IndexController {

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
