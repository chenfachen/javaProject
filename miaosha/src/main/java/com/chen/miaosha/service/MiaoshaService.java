package com.chen.miaosha.service;

import com.chen.miaosha.dao.GoodsDao;
import com.chen.miaosha.domain.Goods;
import com.chen.miaosha.domain.MiaoshaUser;
import com.chen.miaosha.domain.OrderInfo;
import com.chen.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MiaoshaService {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        //减库存，下订单，写秒杀订单
        goodsService.reduceStock(goods);
        //order_info  miaosha_order
        return orderService.createOrder(user, goods);

    }
}
