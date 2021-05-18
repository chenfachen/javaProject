package com.chen.miaosha.service;

import com.chen.miaosha.dao.MiaoshaUserDao;
import com.chen.miaosha.domain.MiaoshaUser;
import com.chen.miaosha.redis.MiaoshaUserKey;
import com.chen.miaosha.redis.RedisService;
import com.chen.miaosha.result.CodeMsg;
import com.chen.miaosha.util.MD5Util;
import com.chen.miaosha.util.UUIDUtil;
import com.chen.miaosha.vo.LoginVo;
import com.sun.deploy.net.HttpResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class MiaoshaUserService {
    private static Logger log = LoggerFactory.getLogger(MiaoshaUserService.class);
    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    @Autowired
    RedisService redisService;

    public MiaoshaUser getById(long id){
        return miaoshaUserDao.getById(id);
    }

    public MiaoshaUser getByToken(HttpServletResponse response, String token){
        if(StringUtils.isEmpty(token)){
            return null;
        }
        MiaoshaUser user =  redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
        if(user != null){
            //生成cookie
            addCookie(response, token, user);
        }
        return user;
    }

    public CodeMsg login(HttpServletResponse response, LoginVo loginVo){
        if(loginVo == null){
            return CodeMsg.SERVER_ERROR;
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if(user == null){
            return CodeMsg.MOBILE_NOT_EXIST;
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDb = user.getSalt();
        String calcPass = MD5Util.formPassDbPass(formPass, saltDb);
        if(!calcPass.equals(dbPass)){
            return CodeMsg.PASSWORD_ERROR;
        }
        //生成cookie
        String token = UUIDUtil.uuid();
        addCookie(response, token, user);

        return CodeMsg.SUCCESS;
    }

    private void addCookie(HttpServletResponse response ,String token, MiaoshaUser user){
        redisService.set(MiaoshaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN,token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
