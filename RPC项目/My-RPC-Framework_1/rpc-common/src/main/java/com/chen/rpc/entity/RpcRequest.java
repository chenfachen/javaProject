package com.chen.rpc.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class RpcRequest implements Serializable {

    /**
     * 带调用接口名称
     */
    private String interfaceName;

    /**
     * 带调用方法名称
     */
    private String methodName;

    /**
     * 调用方法的参数
     */
    private Object[] parameters;

    /**
     * 调用方法的参数类型
     */
    private Class<?>[] paramTypes;
}
