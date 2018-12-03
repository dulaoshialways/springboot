package com.du.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.du.springboot.entity.User;
import com.du.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author djg
 * @date 2018/12/2 15:58
 * @des
 */
@Controller
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping("/")
    public String index(Model model,@RequestParam(value = "currentPage",required = false) Integer currentPage){

        if(null == currentPage){
            currentPage = 1;
        }
        //每页展示10条数据
        int pageSize = 10;
        //总数
        int total = userService.getUserByTotal();
        //计算分页
        int totalPages = total / pageSize;
        int left = total % pageSize;
        if(left > 0){
            totalPages = totalPages + 1;
        }

        int start = (currentPage - 1) * pageSize;
        Map<String,Object> map = new ConcurrentHashMap<>();
        map.put("start",start);
        map.put("pageSize",pageSize);
        List<User> userList = userService.getUserByPage(map);
        model.addAttribute("userList",userList);
        return "index";
    }
}
