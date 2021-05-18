package com.chen.test;

import com.chen.rpc.api.HelloService;
import com.chen.rpc.registry.DefaultServiceRegistry;
import com.chen.rpc.registry.ServiceRegistry;
import com.chen.rpc.server.RpcServer;

public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        RpcServer rpcServer = new RpcServer(serviceRegistry);
        rpcServer.start(9000);
    }
}
