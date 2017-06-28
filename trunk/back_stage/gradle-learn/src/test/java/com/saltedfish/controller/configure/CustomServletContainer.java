package com.saltedfish.controller.configure;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CustomServletContainer implements EmbeddedServletContainerCustomizer {

	
	//配置服务器的一些基本信息(端口，根路径，错误页面等等，具体在接口实现类中查找)
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
			//这些页面默认放在 src/**/resources/static/ 下面
		 	ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
	        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
	        ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");

	        container.addErrorPages(error401Page, error404Page, error500Page);
	}

}
