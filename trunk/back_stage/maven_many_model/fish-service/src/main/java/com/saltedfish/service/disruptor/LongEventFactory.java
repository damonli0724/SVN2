package com.saltedfish.service.disruptor;

import com.lmax.disruptor.EventFactory;


/*定义事件工厂
事件工厂(Event Factory)定义了如何实例化前面第1步中定义的事件(Event)，需要实现接口 com.lmax.disruptor.EventFactory<T>。
Disruptor 通过 EventFactory 在 RingBuffer 中预创建 Event 的实例。
一个 Event 实例实际上被用作一个“数据槽”，发布者发布前，先从 RingBuffer 获得一个 Event 的实例，然后往 Event 实例中填充数据，之后再发布到 RingBuffer 中，之后由 Consumer 获得该 Event 实例并从中读取数据。*/
public class LongEventFactory implements EventFactory<LongEvent>
{
    public  LongEvent newInstance()
    {
        return new LongEvent();
    }
}