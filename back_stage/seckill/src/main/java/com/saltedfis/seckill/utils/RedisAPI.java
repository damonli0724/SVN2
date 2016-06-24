/**   
 * Copyright © 2016 公司名. All rights reserved.
 * 
 * @Title: RedisAPI.java 
 * @Prject: seckill
 * @Package: com.saltedfis.seckill.utils 
 * @Description: TODO
 * @author: mjy   
 * @date: 2016年6月22日 下午3:34:48 
 * @version: V1.0   
 */
package com.saltedfis.seckill.utils;

import java.util.ResourceBundle;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/** 
 * Jedis操作步骤如下：
 * 1->获取Jedis实例需要从JedisPool中获取；
 * 2->用完Jedis实例需要返还给JedisPool；
 * 3->如果Jedis在使用过程中出错，则也需要还给JedisPool；
 * @ClassName: RedisAPI 
 * @Description: redis连接池客户端操作工具类
 * @author: lkd
 * @date: 2016年6月22日 下午3:34:48  
 * @
 */
public class RedisAPI {
	private static JedisPool pool = null;
	private static ThreadLocal<JedisPool> poolThreadLocal = new ThreadLocal<JedisPool>();
	
	/**
	 * 
	 * @Title: getPool 
	 * @Description: 创建jredis连接池
	 * @return
	 * @return: JedisPool
	 */
	public static JedisPool getPool() {
		if (pool == null) {
			//从redis.properties获取资源
			ResourceBundle bundle = ResourceBundle.getBundle("redis");
			if (bundle == null) 
				throw new IllegalArgumentException( "[redis.properties] is not found!");
			
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxActive(Integer.valueOf(bundle.getString("redis.pool.maxActive")));
			config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
			config.setMaxWait(Long.valueOf(bundle.getString("redis.pool.maxWait")));
			config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
			config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));
			pool = new JedisPool(config, bundle.getString("redis.ip"), Integer.valueOf(bundle.getString("redis.port")), 1000, String.valueOf(bundle.getString("redis.pass")));
			pool.getResource().set("accountBalance", "100"); 
			pool.getResource().set("count", "0");
			
		} 
		return pool;
	}
	
	/**
	 * @Title: getConnection 
	 * @Description: 返回redis的连接池
	 * @return
	 * @return: JedisPool
	 */
	public static JedisPool getConnection() {
		//①如果本地线程变量没有pool，重新获取线程池，并且设置到本地线程变量
		if (poolThreadLocal.get()==null) {
			JedisPool pool =getPool();
			poolThreadLocal.set(pool);	
			return pool;
		}
		//②直接返回本地线程变量里面的线程池
		return poolThreadLocal.get();
	}
	
	/**
	 * 
	 * @Title: get 
	 * @Description: 根据key获取value
	 * @param key
	 * @return
	 * @return: String
	 */
	public static String get(String key){
		String value = null;
		JedisPool pool = null;
		Jedis jedis = null;
		boolean success = true;
		try {
			pool = getPool();
			jedis=pool.getResource();
			value=jedis.get(key);
		}catch (Exception e) {
			 	success  = false;
			 	e.printStackTrace();
		}finally{
			 returnResource(pool,jedis,success);
		}	
		return value;
	}
	
	
	/**
	 * 
	 * @Title: set 
	 * @Description: 赋值数据,key-value
	 * @param key
	 * @return
	 * @return: String
	 */
	public static String set(String key,String value){
		JedisPool pool = null;
		Jedis jedis = null;
		boolean success = true;
		try {
			pool = getPool();
			jedis=pool.getResource();
			value=jedis.set(key,value);
		}catch (Exception e) {
			 	success  = false;
			 	e.printStackTrace();
		}finally{
			 returnResource(pool,jedis,success);
		}	
		return value;
	}
	/**
	 * 
	 * @Title: sismember 
	 * @Description: 判断set集合中是否有该值
	 * @param key set集合名称
	 * @param member
	 * @return
	 * @return: Boolean
	 */
	public static Boolean sismember(String key, String member) {
		Boolean result = null;
		JedisPool pool = null;
		Jedis jedis = null;
		boolean success = true;
		try {
			pool = getPool();
			jedis = pool.getResource();
			result = jedis.sismember(key, member);
		} catch (Exception e) {
			 success = false;
			e.printStackTrace();
		} finally {
			returnResource(pool, jedis,success);
		}
		
		return result;
	}
	

	/** 
	 * 
	 * 在instance出错时，必须调用returnBrokenResource返还给pool，否则下次通过getResource得到的instance的缓冲区可能还存在数据，出现问题！
	 * 在instance没有问题的时候，也要返回到连接池中
	 * @Title: returnResource 
	 * @Description: 将redis资源返回到连接池中
	 * @param pool
	 * @param jedis
	 * @param success
	 * @return: void
	 */
	public static void returnResource(JedisPool pool, Jedis jedis,boolean success) {
		if (jedis!=null) {
			if (success) {
				pool.returnResource(jedis);
			}else{
				pool.returnBrokenResource(jedis);
			}
		}
	}

	/**
	 * 
	 * @Title: sadd 
	 * @Description: set集合赋值数据
	 * @param key 集合名称
	 * @param value
	 * @return
	 * @return: Long
	 */
	public static Long sadd(String key, String value) {
		Long result = null;
		JedisPool pool = null;
		Jedis jedis = null;
		boolean  success = true;
		try {
			pool = getPool();
			jedis = pool.getResource();
			result = jedis.sadd(key, value);
		} catch (Exception e) {
			success =false;
			e.printStackTrace();
		} finally {
			// 返还到连接池
			returnResource(pool, jedis,success);
		}

		return result;
	}
	
	

}
