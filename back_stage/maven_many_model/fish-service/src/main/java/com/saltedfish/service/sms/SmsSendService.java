/**
 * 
 */
package com.saltedfish.service.sms;

/**
 * @author LKD
 *中国网建SMS 短信通 发送短信
 *
 */
import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.saltedfish.constants.SMS_Enum;
import com.saltedfish.exception.SMSException;

@Service
public class SmsSendService {
	
@Value("${sms.uid}")
private  String Uid;
@Value("${sms.key}")
private  String Key; 
@Value("${sms.url}")
private  String Url ;

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
}
