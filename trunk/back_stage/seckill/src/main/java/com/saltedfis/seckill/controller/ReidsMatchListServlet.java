package com.saltedfis.seckill.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.saltedfis.seckill.utils.RedisAPI;

public class ReidsMatchListServlet extends HttpServlet {
	public static JedisPool pool= RedisAPI.getPool();
	public static Jedis jedis;
	static {
		jedis = pool.getResource();
	}

	private static final long serialVersionUID = 1L; 

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		list(request, response);
		try {
			response.sendRedirect("list.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void list(HttpServletRequest request, HttpServletResponse response) {
		Set set = jedis.smembers("userIdSet");
		Iterator ite = set.iterator();
		System.out.println("中标名单-------------------------");
		int i = 0;
		Map<String, String> map = new HashMap<String, String>();
		while (ite.hasNext()) {
			i++;
			Object obj1 = ite.next();
			System.out.println("第" + i + "名：" + obj1);
			map.put("第" + i + "名：", obj1 + "");
		}
		request.getSession().setAttribute("user", map);
		System.out.println("中标名单-------------------------");
	}

}