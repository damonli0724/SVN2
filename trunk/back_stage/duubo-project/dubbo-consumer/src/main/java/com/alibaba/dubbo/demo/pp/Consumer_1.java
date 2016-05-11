package com.alibaba.dubbo.demo.pp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.rpc.RpcContext;
import com.unj.dubbotest.provider.DemoService;

public class Consumer_1 {

	public static void main(String[] args) throws Exception {
		final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		context.start();
		
		new Thread(new Runnable() {
			public void run() {
				try{
					while(true){
						DemoService demoService = (DemoService) context.getBean("demoService");
						
						RpcContext.getContext().setAttachment(Constants.MONITOR_KEY,"true");
						
						String hello = demoService.sayHello("LKD");
						
						System.out.println(hello);
						
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							System.out.println("出了异常");
							e.printStackTrace();
							break;
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}).start();
	}

}