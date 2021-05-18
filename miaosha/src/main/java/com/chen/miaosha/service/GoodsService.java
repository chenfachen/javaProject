package com.chen.miaosha.service;

import com.chen.miaosha.dao.GoodsDao;
import com.chen.miaosha.domain.Goods;
import com.chen.miaosha.domain.MiaoshaGoods;
import com.chen.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    GoodsDao goodsdao;

    public List<GoodsVo> listGoodsVo(){
        return goodsdao.listGoodsVo();
    }

    public GoodsVo getGoodsByGoodsId(long goodsId) {
        return goodsdao.getGoodsByGoodsId(goodsId);
    }

    public void reduceStock(GoodsVo goods) {
        MiaoshaGoods g = new MiaoshaGoods();
        g.setGoodsId(goods.getId());
        goodsdao.reduceStock(g);
    }
}
