/**
 * 
 */
package com.saltedfish.sms;

/**
 * @author LKD
 *中国网建SMS 短信通 发送短信
 *
 */
import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.saltedfish.constants.SMS_Enum;
import com.saltedfish.exception.SMSException;

public class SmsSendService {
private static String Uid="希瓦咸鱼";
private static String Key="e5bd6069b484210f0af4"; 
private static String Url="http://utf8.sms.webchinese.cn/";
	
	
//public static void main(String[] args)throws Exception
//{
/*
HttpClient client = new HttpClient();
PostMethod post = new PostMethod("http://utf8.sms.webchinese.cn/"); 
post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");//在头文件中设置转码
NameValuePair[] data ={ new NameValuePair("Uid", Uid),new NameValuePair("Key", Key),new NameValuePair("smsMob","15900459425"),new NameValuePair("smsText","验证码:123")};
post.setRequestBody(data);

client.executeMethod(post);
Header[] headers = post.getResponseHeaders();
int statusCode = post.getStatusCode();
System.out.println("statusCode:"+statusCode);
for(Header h : headers)
{
System.out.println(h.toString());
}
String result = new String(post.getResponseBodyAsString().getBytes("utf8")); 
System.out.println(result+"====="); //打印返回消息状态

post.releaseConnection();
*/
//}
public void sendMessage(String targetTel,String targetContext) throws HttpException, IOException{

	HttpClient client = new HttpClient();
	PostMethod post = new PostMethod(Url); 
	post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");//在头文件中设置转码
	NameValuePair[] data ={ new NameValuePair("Uid", Uid),new NameValuePair("Key", Key),new NameValuePair("smsMob",targetTel),new NameValuePair("smsText",targetContext)};
	post.setRequestBody(data);

	client.executeMethod(post);

	String result = new String(post.getResponseBodyAsString().getBytes("utf8")); 
	
	
		if (!result.equals(String.valueOf(SMS_Enum.SEND_SUCCESS.getStatus()))) {
			throw new SMSException(SMS_Enum.getMessage(Integer.valueOf(result)));
		}
	post.releaseConnection();

}

public static void main(String[] args) throws Exception, Exception {
	SmsSendService service = new SmsSendService();
	service.sendMessage("18565671771", "验证码:10108");
}
}
