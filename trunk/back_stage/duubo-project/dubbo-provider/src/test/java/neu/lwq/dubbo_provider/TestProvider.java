package neu.lwq.dubbo_provider;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestProvider {
	
	@Test
	public void testContextStart(){
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		context.start();
		System.out.println("Provider_1 is started.");
		Assert.assertNotNull(context.getBean("demoService"));
	}

}
