package com.commnon;

import java.util.ResourceBundle;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis操作接口
 *
 * @author 林计钦
 * @version 1.0 2013-6-14 上午08:54:14
 */
public class RedisAPI {
	private static JedisPool pool = null;
	private static ThreadLocal<JedisPool> poolThreadLocal = new ThreadLocal<JedisPool>();

	/**
	 * 构建redis连接池
	 * 
	 * @param ip
	 * @param port
	 * @return JedisPool
	 */
	public static JedisPool getPool() {
		if (pool == null) {
			ResourceBundle bundle = ResourceBundle.getBundle("redis");
			if (bundle == null) {
				throw new IllegalArgumentException(
						"[redis.properties] is not found!");
			}
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxActive(Integer.valueOf(bundle
					.getString("redis.pool.maxActive")));
			config.setMaxIdle(Integer.valueOf(bundle
					.getString("redis.pool.maxIdle")));
			config.setMaxWait(Long.valueOf(bundle.getString("redis.pool.maxWait")));
			config.setTestOnBorrow(Boolean.valueOf(bundle
					.getString("redis.pool.testOnBorrow")));
			config.setTestOnReturn(Boolean.valueOf(bundle
					.getString("redis.pool.testOnReturn")));
//			pool = new JedisPool(config, bundle.getString("redis.ip"),Integer.valueOf(bundle.getString("redis.port")));
			pool = new JedisPool(config, bundle.getString("redis.ip"), Integer.valueOf(bundle.getString("redis.port")), 3000, String.valueOf(bundle.getString("redis.pass")));
			pool.getResource().set("accountBalance", "10");
			System.err.println("----------"+pool.getResource().get("accountBalance"));
		}
		return pool;
	}
	
	public static JedisPool getConnection() {
		// ②如果poolThreadLocal没有本线程对应的JedisPool创建一个新的JedisPool，将其保存到线程本地变量中。
		if (poolThreadLocal.get() == null) {
			pool = RedisAPI.getPool();
			poolThreadLocal.set(pool);
			return pool;
		} else {
			return poolThreadLocal.get();// ③直接返回线程本地变量
		}
	}

	/**
	 * 返还到连接池
	 * 
	 * @param pool
	 * @param redis
	 */
	public static void returnResource(JedisPool pool, Jedis redis) {
		if (redis != null) {
			pool.returnResource(redis);
		}
	}

	/**
	 * 获取数据
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		String value = null;

		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			value = jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放redis对象
			pool.returnBrokenResource(jedis);
			// 返还到连接池
			returnResource(pool, jedis);
		}

		return value;
	}

	/**
	 * 赋值数据
	 * 
	 * @param key
	 * @return
	 */
	public static String set(String key, String value) {
		String result = null;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			result = jedis.set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放redis对象
			pool.returnBrokenResource(jedis);
			// 返还到连接池
			returnResource(pool, jedis);
		}

		return result;
	}

	/**
	 * 赋值数据
	 * 
	 * @param key
	 * @return
	 */
	public static Long sadd(String key, String value) {
		Long result = null;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			result = jedis.sadd(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放redis对象
			pool.returnBrokenResource(jedis);
			// 返还到连接池
			returnResource(pool, jedis);
		}

		return result;
	}
	
	/**
	 * 判断set中是否有值
	 * 
	 * @param key
	 * @return
	 */
	public static Boolean sismember(String key, String member) {
		Boolean result = null;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			result = jedis.sismember(key, member);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放redis对象
			pool.returnBrokenResource(jedis);
			// 返还到连接池
			returnResource(pool, jedis);
		}

		return result;
	}

}