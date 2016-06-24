package com.saltedfis.seckill.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import com.saltedfis.seckill.utils.RedisAPI;

public class ReidsMatchServlet extends HttpServlet {
	
	private static Logger logger = LoggerFactory.getLogger(ReidsMatchServlet.class);  
	public static JedisPool pool = RedisAPI.getPool();

	// RedisAPI.set("accountBalance", "999999999");// 标还剩999999999块钱

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Jedis jedis = pool.getResource();
		long start = System.currentTimeMillis();
		int flag = 0;
		boolean success = true;
		try {
			flag = bid(request, response, jedis);
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			response.getWriter().write("fail buy");
		} finally {
			RedisAPI.returnResource(pool, jedis,success); 
		}
		if (flag == 1) {
			response.getWriter().write("success buy");
		} else if (flag == 2) {
			response.getWriter().write("have buy");
		} else if (flag == 0) {
			response.getWriter().write("bid is zero ,you can not buy");
		}else{
			response.getWriter().write("fail buy,left money is empty");
		}
		long end = System.currentTimeMillis();
		logger.info("请求耗时[{}]毫秒",(end-start));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private int bid(HttpServletRequest request, HttpServletResponse response, Jedis jedis) throws Exception {
		int flag = 0;// 1,成功,2已经购买，3已经没钱了，其他異常
		// 每个请求对应一个userId  (模拟100块，100个人抢)
		int userId =  new Random().nextInt(100000)+1; 
		
		// 观察 总标值，每人抢购一元 
		/**
		 * 当accountBalance加了乐观锁，则会获取当前acc的版本号，然后开启事务，执行事务，然后 set(accountBalance,'xx')。 
		 * 如果有并发线程，先一步执行了 set(accountBalance,'xx')。则会修改版本号，然后 在watch里面的事务则不会执行，因为版本号变了
		 * 
		 */
		while ("OK".equals(jedis.watch("accountBalance","userIdSet3"))) {
        		// 判断是否购买过
        		Boolean isBuy = RedisAPI.sismember("userIdSet3", userId + "");
        		if (isBuy) {
        			flag = 2;
        			return flag; 
        		}
        		//投资额
			int r = 1;// new Random().nextInt(2);
			int lastAccount = 0;
			String balance = RedisAPI.get("accountBalance");
			if (StringUtils.isNotBlank(balance)) {
				lastAccount = Integer.valueOf(balance) - r;
			}
			if (lastAccount < 0) {
				flag = 3;
				break;
			}
			Transaction tx = jedis.multi();
			tx.set("accountBalance", lastAccount + "");
			tx.set(Thread.currentThread().getName(), r+"");
			tx.sadd("userIdSet3", userId + "");
			
			List<Object> result = tx.exec();
			if (result == null || result.isEmpty()) {
				jedis.unwatch(); 
			} else {
				logger.error("恭喜您，" + userId + "已经中标" + r + "元，标余额" + lastAccount + "元");
//				RedisAPI.set(Thread.currentThread().getName(), r + "");
//				RedisAPI.sadd("userIdSet3", userId + "");
				flag = 1;
				break;
			}
	}
		return flag;
	}
	
	
	public static void main(String[] args) {
		while(true){
			
//			int i =  new Random().nextInt(1)+1;
			int i =new Random().nextInt(5);
			System.err.println(i);
		}
	}
}