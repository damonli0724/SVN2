package com.saltedfish.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Transaction;

import com.saltedfish.cmd.ValidateCmd;
import com.saltedfish.constants.Constants;
import com.saltedfish.controller.base.BaseController;
import com.saltedfish.controller.constants.Url;
import com.saltedfish.dto.BaseResultDTO;
import com.saltedfish.entity.security.SysUsers;
import com.saltedfish.exception.SMSException;
import com.saltedfish.service.sms.SmsSendService;
import com.saltedfish.utils.RedisUtil;


@Controller
public class AppTestController extends BaseController {

	protected final Logger logger = Logger.getLogger(this.getClass());

	
	@Autowired
	private SmsSendService smsSendService;
	@Autowired
	private RedisUtil redisUtil;


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
		redisUtil.set("account", 100); //默认设置红包100块
		
		long start = System.currentTimeMillis();
		int flag = 0;
		try {
			flag = bid();
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("系统繁忙，抢红包失败!");
			result.setStatus(Constants.R_STATUS_FAILTURE);
			return  result;
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
		}else{
			result.setMessage("抢红包失败!");
			result.setStatus(Constants.R_STATUS_FAILTURE);
		}
		long end = System.currentTimeMillis();
		System.out.println("--------------------------------------------请求耗时：" + (end - start) + "毫秒");
		return result;
	}

	
	/** 
	 * @Title: bid 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	private int bid() {
		int flag = 0;// 1,成功,2已经购买，3已经没钱了，其他異常
		// 每个请求对应一个userId
		int userId = new Random().nextInt(999999);
		
		RedisTemplate<Serializable, Object> rt = redisUtil.getRedisTemplate();
		
				// 观察 总标值，每人抢购一元
				rt.watch("account");
        		// 判断是否购买过 
        		Boolean isBuy = redisUtil.sismember("userIdSet", String.valueOf(userId));
        		if (isBuy) {
        			flag = 2;
        			return flag;
        		}
        		//投资额
			int r = 1;// new Random().nextInt(2);
			int lastAccount = 0;
			String balance = (String) redisUtil.get("account");
			if (StringUtils.isNotBlank(balance)) {
				lastAccount = Integer.valueOf(balance) - r;
			}
			if (lastAccount < 0) {
				flag = 3;
				break;
			}
			
			/*RedisTemplate<Serializable, Object> redis = redisUtil.getReRedisTemplate<Serializable, Object> redis = redisUtil.getRedisTemplate();
			
			redis.execute(new SessionCallback() {

				@Override
				public Object execute(RedisOperations operations){
				operations.watch(key);
                String origin = (String) operations.opsForValue().get(key);
                operations.multi();
                operations.opsForValue().set(key, origin + idx);
                Object rs = operations.exec();
                System.out.println("set:"+origin+idx+" rs:"+rs);
                return rs;
				}
			});
			*/
			
			
			rt.multi();
			SessionCallback<String> sessionCallback = new SessionCallback<String>() {  
	            @Override  
	            public <K, V> String execute(RedisOperations<K, V> operation)  
	                    throws DataAccessException {  
	                  
	                operation.multi();  
//	                for(int i=0;i<10;i++){  
//	                    BoundHashOperations<String, String, String> hs = ((RedisTemplate)operation).boundHashOps("zhang");  
//	                    hs.put("zhang"+i, "hao"+i);  
//	                }  
	                
	                
	              List<Object> result =  operation.exec();  
	                return null;  
	            }  
	        };  
	        rt.execute(sessionCallback);  
			
			tx.set("accountBalance", lastAccount + "");
			List<Object> result = tx.exec();
			if (result == null || result.isEmpty()) {
				jedis.unwatch();
			} else {
				System.out.println("恭喜您，" + userId + "已经中标" + r + "元，标余额" + lastAccount + "元");
				RedisAPI.set(Thread.currentThread().getName(), r + "");
				RedisAPI.sadd("userIdSet", userId + "");
				flag = 1;
				break;
			}
		return flag;
	}


	/**
	 * 不需要权限的Url
	 * @param cmd
	 * @param startPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = Url.UNSECURED_TEST, method = RequestMethod.GET)
	@ResponseBody
	public BaseResultDTO<String> secKill() {
		BaseResultDTO<String> result = new BaseResultDTO<String>();
		
		Thread  t = Thread.currentThread();
		System.err.println("t.getName()"+t.getName());
		System.err.println("t.getId()"+t.getId());
		
		result.setStatus(Constants.R_STATUS_SUCCESS);
		System.err.println(redisUtil.exists("lkd")+"==============================");
		SysUsers user = new SysUsers();
		user.setDepName("=======================================");
		redisUtil.set("lkd", user);
		System.err.println(redisUtil.exists("lkd")+"==============================");
		user = (SysUsers)redisUtil.get("lkd");
		System.err.println(user.toString()+"===========================");
		result.setMessage("query success!");
		redisUtil.set("sessionId", "accountId");
		return result;
	}
	
	
	
	
	
	/**
	 * 需要权限的Url
	 * @param cmd
	 * @param startPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = Url.SECURED_TEST, method = RequestMethod.GET)
	@ResponseBody
	public BaseResultDTO<String>  securedTest() {
		BaseResultDTO<String> result = new BaseResultDTO<String>();
		result.setStatus(Constants.R_STATUS_SUCCESS);
		System.err.println(redisUtil.exists("lkd")+"==============================");
		SysUsers user = new SysUsers();
		user.setDepName("=======================================");
		redisUtil.set("lkd", user);
		System.err.println(redisUtil.exists("lkd")+"==============================");
		user = (SysUsers)redisUtil.get("lkd");
		System.err.println(user.toString()+"===========================");
		result.setMessage("query success!");
		redisUtil.set("sessionId", "accountId");
		return result;
	}
	
	
	@RequestMapping(value=Url.EXCEPTION_VALIDATE_TEST,method=RequestMethod.GET)
	@ResponseBody
	public BaseResultDTO<String> exceptionAndValidateTest(@Valid ValidateCmd val ,BindingResult bindingResult){
		BaseResultDTO<String> result = null ;
		
		//返回校验信息
		BaseResultDTO<String> errorResult = checkErrors(bindingResult);
		if (errorResult != null) {
			return errorResult;
		}
		
		System.out.println(1/0);
		
		result = new BaseResultDTO<String>();
		result.setResult("fdsf");
		result.setStatus(Constants.R_STATUS_SUCCESS);
		return result;
	}
	
	/**
	 * 手机短信发送
	 * @return
	 */
	@RequestMapping(value = Url.SMS_SEND_MESSAGE, method = RequestMethod.POST)
	@ResponseBody
	public BaseResultDTO<String> smsMessageSend(String tel,String content) {
		logger.info("smsMessage send:tel"+tel+" content:"+content);
		BaseResultDTO<String> result = new BaseResultDTO<String>();
		try {
			smsSendService.sendMessage(tel, content);
			result.setStatus(Constants.R_STATUS_SUCCESS);
			result.setMessage("send success!");
		} catch (HttpException e) {
			result.setStatus(Constants.R_STATUS_FAILTURE);
			result.setMessage("send fail："+e.getMessage());
		} catch (IOException e) {
			result.setStatus(Constants.R_STATUS_FAILTURE);
			result.setMessage("send fail："+e.getMessage());
		} catch (SMSException e) {
			result.setStatus(Constants.R_STATUS_FAILTURE);
			result.setMessage("send fail："+e.getMessage());
		}
		
		return result;
	}
	
}
