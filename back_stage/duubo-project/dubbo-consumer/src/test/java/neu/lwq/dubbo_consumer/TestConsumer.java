package neu.lwq.dubbo_consumer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestConsumer {
	
	private ClassPathXmlApplicationContext context;
	
	@Before
	public void start(){
		context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		context.start();
	}
	
	@Test
	public void testContextStart(){
		/*Assert.assertNotNull(context.getBean("demoService"));
		DemoService demoService = (DemoService) context.getBean("demoService");
		String result = demoService.sayHello("tom");
		Assert.assertEquals("Hello tom from provider_1", result);*/
	}
	
	@After
	public void stop(){
		context.stop();
	}

}
