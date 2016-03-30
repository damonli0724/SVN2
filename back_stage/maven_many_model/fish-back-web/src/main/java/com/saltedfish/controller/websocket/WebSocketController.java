package com.saltedfish.controller.websocket;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.saltedfish.controller.base.BaseController;
import com.saltedfish.controller.constants.Url;
import com.saltedfish.controller.constants.View;


@Controller
public class WebSocketController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = Url.WEB_SOCKET_PAGE, method = RequestMethod.GET)
	public String turnToWelcomPage(HttpServletRequest request,ModelMap map) {
		return View.WEB_SOCKET_VIEW;
	}



}
