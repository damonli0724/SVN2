package com.saltedfish.design.状态模式;

/**
 * 
 * @ClassName: 例子 
 * @Description: 状态模式:根据很多种状态，决定行为. 比如 早上工作亢奋状态-->下午萎靡状态-->晚上混沌状态
 * @author: lkd
 * @date: 2017年4月20日 下午7:35:29
 */
public class 例子 {

	public static void main(String[] args) {
		
	}
	
}

//多种状态抽出来的一个接口
interface MentalState{
	void show();
}

//亢奋状态
class HighMentalState implements MentalState{

	@Override
	public void show() {
		System.err.println("早上干活中。。。很亢奋");
	}
	
}

//萎靡状态
class LowMentalState implements MentalState{
	@Override
	public void show() {
		System.err.println("下午继续干活中。。。很萎靡...");
	}
}

//混沌状态
class DownMentalState implements MentalState{
	@Override
	public void show() {
		System.err.println("晚上继续干活中。。。混沌中...");
	}
}


class People {
	
	MentalState mentalState ; //精神状态
	
	public void work(){
		mentalState.show();
	}
}


