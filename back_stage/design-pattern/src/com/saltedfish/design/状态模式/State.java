package com.saltedfish.design.状态模式;
/**
 * 
 * @ClassName: State 
 * @Description: 抽象状态接口
 * @author: lkd
 * @date: 2017年4月17日 上午11:37:58
 */
public interface State {
	/**
	 * 
	 * @Title: handle 
	 * @Description: 根据传入的参数执行不同的方法
	 * @param sampleParameter
	 * @return: void
	 */
	public void handle(String sampleParameter);
}
