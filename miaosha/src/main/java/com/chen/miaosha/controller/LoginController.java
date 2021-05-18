package com.chen.miaosha.controller;

import com.chen.miaosha.result.CodeMsg;
import com.chen.miaosha.result.Result;
import com.chen.miaosha.service.MiaoshaUserService;
import com.chen.miaosha.util.ValidatorUtil;
import com.chen.miaosha.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response,  @Valid LoginVo loginVo){    //前端传过来的数据，用一个对象loginVo接收？
        log.info(loginVo.toString());
        //参数校验
        //String passInput = loginVo.getPassword();
        //String mobile = loginVo.getMobile();
        /*if(passInput == null){      //密码为空
            return Result.error(CodeMsg.PASSWORD_EMPTY);
        }
        if(mobile == null){
            return Result.error(CodeMsg.MOBILE_EMPTY);
        }
        if(!ValidatorUtil.isMobile(mobile)){
            return Result.error(CodeMsg.MOBILE_ERROR);
        }*/
        //登陆
        CodeMsg cm = miaoshaUserService.login(response, loginVo);
        if(cm.getCode() == 0){
            return Result.success(true);
        }else{
            return Result.error(cm);
        }

    }
}
