package com.chen.miaosha.redis;

public abstract class BasePrefix implements KeyPrefix{
    private int expireSeconds;
    private String prefix;

    public BasePrefix(String prefix){
        this(0, prefix);
    }

    public BasePrefix(int expireSeconds, String prefix){
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {    //默认0表示永不过期
        return expireSeconds;
    }

    //模板方法模式：用一个抽象方法定义，子类中调用抽象方法，这里就是  子类的类名+“：”+前缀
    @Override
    public String getPrefix() {
        String className = getClass().getName();
        return className + ":" + prefix;
    }
}
