package com.chen.miaosha.controller;

import com.chen.miaosha.domain.MiaoshaUser;
import com.chen.miaosha.domain.User;
import com.chen.miaosha.redis.RedisService;
import com.chen.miaosha.redis.UserKey;
import com.chen.miaosha.result.CodeMsg;
import com.chen.miaosha.result.Result;
import com.chen.miaosha.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class SampleController {
    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;


    @ResponseBody
    @RequestMapping("/redis/get")
    public Result<User> redisGet(){
        User user = redisService.get(UserKey.getById, "" + 1, User.class);
        //System.out.println(user.getId());
        return Result.success(user);
    }

    @ResponseBody
    @RequestMapping("/redis/set")
    public Result<Boolean> redisSet(){
        User user = new User();
        user.setId(1);
        user.setName("1111");
        Boolean v1 = redisService.set(UserKey.getById, "" + 1, user);
        //System.out.println(user.getId());
        //User getUser = redisService.get(UserKey.getByName, "" + 1, User.class);
        return Result.success(v1);
    }

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx(){
        userService.tx();
        return Result.success(true);
    }
}
