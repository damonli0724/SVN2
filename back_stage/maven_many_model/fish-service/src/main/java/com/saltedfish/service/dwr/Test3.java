package com.saltedfish.service.dwr;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Test3 {
	
 public static String StringFilter(String str) throws PatternSyntaxException { 
	// 清除掉所有特殊字符 
	String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]"; 
	Pattern p = Pattern.compile(regEx); 
	Matcher m = p.matcher(str);
	return m.replaceAll("").trim();
	} 
		
 
 public static void main(String[] args) {
	
	 String str = "*\'adCVs*34_a _09_b5*[/435^*&城池()^$$&*).{}+.|.)%%*(*.中国}34{45[]12.fd'*&999下面是中文的字符￥……{}【】。，；’“‘”？"; 
	 System.out.println(str); 
	 System.out.println(StringFilter(str));
}
}
