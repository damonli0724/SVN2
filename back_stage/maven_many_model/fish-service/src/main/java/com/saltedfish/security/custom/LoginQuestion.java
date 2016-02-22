package com.saltedfish.security.custom;

import java.util.Hashtable;


public class LoginQuestion {

	private static Hashtable<Integer, String> questionTable = new Hashtable<Integer, String>();

	public static Hashtable<Integer, String> getQuestions() {
		if (questionTable.size() <= 0) {
			questionTable.put(1, "葡萄美酒夜光杯/欲饮琵琶马上催");
			questionTable.put(2, "故人西辞黄鹤楼/烟花三月下扬州");
			questionTable.put(3, "孤帆远影碧空尽/唯见长江天际流");
			questionTable.put(4, "相见时难别亦难/东风无力百花残");
			questionTable.put(5, "渔翁夜傍西岩宿/晓汲清湘燃楚竹");
		}
		return questionTable;
	}

}
