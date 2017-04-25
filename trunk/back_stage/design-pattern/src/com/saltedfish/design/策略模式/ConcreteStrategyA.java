package com.saltedfish.design.策略模式;
/**
 * @ClassName: ConcreteStrategyA 
 * @Description: 具体策略A,实现具体策略方法
 * @author: lkd
 * @date: 2017年4月19日 下午2:45:07
 */
public class ConcreteStrategyA implements Strategy{

	@Override
	public void strategyInterface() {
		System.err.println("正在使用策略方法A。。。");
	}

}
