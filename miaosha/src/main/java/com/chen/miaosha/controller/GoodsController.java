package com.chen.miaosha.controller;



import com.chen.miaosha.domain.MiaoshaUser;
import com.chen.miaosha.service.GoodsService;
import com.chen.miaosha.service.MiaoshaUserService;
import com.chen.miaosha.vo.GoodsVo;
import com.sun.media.sound.MidiOutDeviceProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/to_list")
    public String to_list(HttpServletResponse response, Model model,
//                          @CookieValue(value = MiaoshaUserService.COOKIE_NAME_TOKEN, required = false) String cookieToken,
//                          @RequestParam(value=MiaoshaUserService.COOKIE_NAME_TOKEN, required = false) String paramToken,
                          MiaoshaUser user){
/*        if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)){
            return "login";
        }
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        user = miaoshaUserService.getByToken(response, token);*/
        model.addAttribute("user", user);

        //查询商品列表
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList",goodsList);

        return "goods_list";
    }


    @RequestMapping("/to_detail/{goodsId}")
    public String to_detail(Model model, MiaoshaUser user,
                            @PathVariable("goodsId")long goodsId){
        model.addAttribute("user", user);

        GoodsVo goods = goodsService.getGoodsByGoodsId(goodsId);
        model.addAttribute("goods", goods);

        //
        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remainSeconds = 0;
        if(now < startAt){  //秒杀还没开始
            miaoshaStatus = 0;
            remainSeconds = (int)(startAt - now) / 1000;
        }else if(now > endAt){  //秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        }else{                  //秒杀进行中
            miaoshaStatus = 1;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }

}
