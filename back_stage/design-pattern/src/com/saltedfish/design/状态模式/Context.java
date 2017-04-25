package com.saltedfish.design.状态模式;

/**
 * 
 * @ClassName: Context 
 * @Description: 环境角色类
 * @author: lkd
 * @date: 2017年4月17日 上午11:40:25
 */
public class Context {
    //持有一个State类型的对象实例
    private State state;

    public void setState(State state) {
        this.state = state;
    }
    
    
    /**
     * 用户感兴趣的接口方法
     */
    public void request(String sampleParameter) {
        //转调state来处理
        state.handle(sampleParameter);
    }
}