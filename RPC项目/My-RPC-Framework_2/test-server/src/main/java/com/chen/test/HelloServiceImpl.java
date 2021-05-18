package com.chen.test;

import com.chen.rpc.api.HelloObject;
import com.chen.rpc.api.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloServiceImpl implements HelloService {
    private static Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hello(HelloObject object) {
        logger.info("接收到：{}", object.getMassage());
        return "这是调用的返回值，id=" + object.getId();
    }
}
