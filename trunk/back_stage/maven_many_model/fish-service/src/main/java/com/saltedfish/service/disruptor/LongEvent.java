package com.saltedfish.service.disruptor;
/**
 * 
 * 定义事件:事件(Event)就是通过 Disruptor 进行交换的数据类型。
 *
 */
public class LongEvent
{
    private long value;


	public void setValue(long value) {
		 this.value = value;
	}


	public long getValue() {
		return value;
	}
}