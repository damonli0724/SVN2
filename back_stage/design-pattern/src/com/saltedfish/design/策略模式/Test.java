package com.saltedfish.design.策略模式;

public class Test {

	public static void main(String[] args) {
		//创建具体策略
		Strategy strategyA = new ConcreteStrategyA();
		//创建上下文对象
		Context ctx = new Context(strategyA);
		//调用策略方法
		ctx.contextInterface();
		
	}
}
