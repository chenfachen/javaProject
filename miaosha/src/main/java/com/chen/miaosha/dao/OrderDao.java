package com.chen.miaosha.dao;

import com.chen.miaosha.domain.MiaoshaOrder;
import com.chen.miaosha.domain.OrderInfo;
import org.apache.ibatis.annotations.*;


@Mapper
public interface OrderDao {

    @Select("select * from miaosha_order where user_id=#{userId} and goods_id=#{goodsId}")
    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(@Param("userId") Long userId, @Param("goodsId") long goodsId);

    @Insert("insert into order_info(user_id, user_name, delivery_address, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
            + "#{userId},#{userName}, #{deliveryAddress},  #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
    @SelectKey(keyColumn = "id", keyProperty = "id", resultType = long.class, before = false, statement = "select last_insert_id()")
    public long insert(OrderInfo orderInfo);

    @Insert("insert into miaosha_order (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
    public int insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);
}
