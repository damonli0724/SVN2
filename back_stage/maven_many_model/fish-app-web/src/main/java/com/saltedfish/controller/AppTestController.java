package com.saltedfish.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import com.saltedfish.cmd.ValidateCmd;
import com.saltedfish.constants.Constants;
import com.saltedfish.controller.base.BaseController;
import com.saltedfish.controller.constants.Url;
import com.saltedfish.dto.BaseResultDTO;
import com.saltedfish.entity.security.SysUsers;
import com.saltedfish.exception.SMSException;
import com.saltedfish.service.sms.SmsSendService;
import com.saltedfish.utils.RedisAPI;
import com.saltedfish.utils.RedisUtil;

@Controller
public class AppTestController extends BaseController {

	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private SmsSendService smsSendService;
	@Autowired
	private RedisUtil redisUtil;

	public static JedisPool pool = RedisAPI.getPool();

	/**
	 * 
	 * @Title: SEC_KILL_TEST
	 * @Description: redis 秒杀测试
	 * @return
	 * @return: BaseResultDTO<String>
	 */
	@RequestMapping(value = Url.SEC_KILL_TEST, method = RequestMethod.GET)
	@ResponseBody
	public BaseResultDTO<String> SEC_KILL_TEST() {
		BaseResultDTO<String> result = new BaseResultDTO<String>();

		long start = System.currentTimeMillis();
		int flag = 0;
		try {
			Jedis jedis = pool.getResource();
			flag = secKill(jedis);

		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("系统繁忙，抢红包失败!");
			result.setStatus(Constants.R_STATUS_FAILTURE);
			return result;
		}

		if (flag == 1) {
			result.setMessage("抢红包成功!");
			result.setStatus(Constants.R_STATUS_SUCCESS);
		} else if (flag == 2) {
			result.setMessage("你已经抢过，不可重复抢!");
			result.setStatus(Constants.R_STATUS_FAILTURE);
		} else if (flag == 0) {
			result.setMessage("红包没啦!");
			result.setStatus(Constants.R_STATUS_FAILTURE);
		} else {
			result.setMessage("抢红包失败!");
			result.setStatus(Constants.R_STATUS_FAILTURE);
		}
		long end = System.currentTimeMillis();
		System.out.println("--------------------------------------------请求耗时："
				+ (end - start) + "毫秒");
		return result;
	}

	/**
	 * @Title: secKill
	 * @Description: TODO
	 * @param jedis
	 * @return
	 * @return: int
	 */
	private int secKill(Jedis jedis) {

		int flag = 0;// 1,成功,2已经购买，3已经没钱了，其他異常
		// 每个请求对应一个userId (模拟100块，100个人抢)
		int userId = new Random().nextInt(99) + 1;

		// 观察 总标值，每人抢购一元
		while ("OK".equals(jedis.watch(Constants.SEC_ALL_MONEY_ACCOUNT,Constants.SEC_BID_USERS))) {
			// 判断是否购买过
			Boolean isBuy = RedisAPI.sismember(Constants.SEC_BID_USERS,String.valueOf(userId));
			if (isBuy) {
				flag = 2;
				return flag;
			}
			// 投资额
			int r = 1;
			int lastAccount = 0;
			String balance = RedisAPI.get(Constants.SEC_ALL_MONEY_ACCOUNT);
			if (StringUtils.isNotBlank(balance)) {
				lastAccount = Integer.valueOf(balance) - r;
			}
			if (lastAccount < 0) {
				flag = 3;
				break;
			}
			Transaction tx = jedis.multi();
			tx.set(Constants.SEC_ALL_MONEY_ACCOUNT, String.valueOf(lastAccount));
			tx.set(Thread.currentThread().getName(), String.valueOf(r));
			tx.sadd(Constants.SEC_BID_USERS, String.valueOf(userId));
				
			List<Object> result = tx.exec();
			if (result == null || result.isEmpty()) {
				jedis.unwatch();
			} else {
				logger.error("恭喜您，" + userId + "已经中标" + r + "元，标余额" + lastAccount + "元");
				// RedisAPI.set(Thread.currentThread().getName(), r + "");
				// RedisAPI.sadd("userIdSet3", userId + "");
				flag = 1;
				break;
			}
		}
		return flag;
	}

	/**
	 * 不需要权限的Url
	 * 
	 * @param cmd
	 * @param startPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = Url.UNSECURED_TEST, method = RequestMethod.GET)
	@ResponseBody
	public BaseResultDTO<String> secKill() {
		BaseResultDTO<String> result = new BaseResultDTO<String>();

		Thread t = Thread.currentThread();
		System.err.println("t.getName()" + t.getName());
		System.err.println("t.getId()" + t.getId());

		result.setStatus(Constants.R_STATUS_SUCCESS);
		System.err.println(redisUtil.exists("lkd")
				+ "==============================");
		SysUsers user = new SysUsers();
		user.setDepName("=======================================");
		redisUtil.set("lkd", user);
		System.err.println(redisUtil.exists("lkd")
				+ "==============================");
		user = (SysUsers) redisUtil.get("lkd");
		System.err.println(user.toString() + "===========================");
		result.setMessage("query success!");
		redisUtil.set("sessionId", "accountId");
		return result;
	}

	/**
	 * 需要权限的Url
	 * 
	 * @param cmd
	 * @param startPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = Url.SECURED_TEST, method = RequestMethod.GET)
	@ResponseBody
	public BaseResultDTO<String> securedTest() {
		BaseResultDTO<String> result = new BaseResultDTO<String>();
		result.setStatus(Constants.R_STATUS_SUCCESS);
		System.err.println(redisUtil.exists("lkd")
				+ "==============================");
		SysUsers user = new SysUsers();
		user.setDepName("=======================================");
		redisUtil.set("lkd", user);
		System.err.println(redisUtil.exists("lkd")
				+ "==============================");
		user = (SysUsers) redisUtil.get("lkd");
		System.err.println(user.toString() + "===========================");
		result.setMessage("query success!");
		redisUtil.set("sessionId", "accountId");
		return result;
	}

	@RequestMapping(value = Url.EXCEPTION_VALIDATE_TEST, method = RequestMethod.GET)
	@ResponseBody
	public BaseResultDTO<String> exceptionAndValidateTest(
			@Valid ValidateCmd val, BindingResult bindingResult) {
		BaseResultDTO<String> result = null;

		// 返回校验信息
		BaseResultDTO<String> errorResult = checkErrors(bindingResult);
		if (errorResult != null) {
			return errorResult;
		}

		System.out.println(1 / 0);

		result = new BaseResultDTO<String>();
		result.setResult("fdsf");
		result.setStatus(Constants.R_STATUS_SUCCESS);
		return result;
	}

	/**
	 * 手机短信发送
	 * 
	 * @return
	 */
	@RequestMapping(value = Url.SMS_SEND_MESSAGE, method = RequestMethod.POST)
	@ResponseBody
	public BaseResultDTO<String> smsMessageSend(String tel, String content) {
		logger.info("smsMessage send:tel" + tel + " content:" + content);
		BaseResultDTO<String> result = new BaseResultDTO<String>();
		try {
			smsSendService.sendMessage(tel, content);
			result.setStatus(Constants.R_STATUS_SUCCESS);
			result.setMessage("send success!");
		} catch (HttpException e) {
			result.setStatus(Constants.R_STATUS_FAILTURE);
			result.setMessage("send fail：" + e.getMessage());
		} catch (IOException e) {
			result.setStatus(Constants.R_STATUS_FAILTURE);
			result.setMessage("send fail：" + e.getMessage());
		} catch (SMSException e) {
			result.setStatus(Constants.R_STATUS_FAILTURE);
			result.setMessage("send fail：" + e.getMessage());
		}

		return result;
	}
 
	
	
	
	
}
