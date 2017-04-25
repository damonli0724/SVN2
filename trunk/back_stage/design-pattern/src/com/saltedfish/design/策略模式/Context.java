package com.saltedfish.design.策略模式;
/**
 * 
 * @ClassName: Context 
 * @Description: 上下文对象，持有策略接口
 * @author: lkd
 * @date: 2017年4月19日 下午2:42:16
 */
public class Context {
	private Strategy strategy;

	public Context(Strategy strategy) {
		super();
		this.strategy = strategy;
	} ; 
	
	/**
	 * 
	 * @Title: contextInterface 
	 * @Description: 上下文使用策略接口
	 * @return: void
	 */
	public void contextInterface(){
		strategy.strategyInterface();
	}
	

}
