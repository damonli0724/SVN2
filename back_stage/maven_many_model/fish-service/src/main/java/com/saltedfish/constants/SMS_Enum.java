/**
 * 
 */
package com.saltedfish.constants;

/**
 *  短信错误提示枚举
 * @author LKD
 *
 */
public enum SMS_Enum {
	ERROR_USER(-1,"没有该用户"),ERROR_KEY(-2,"接口密钥不正确"),ERROR_MD5(-21,"MD5接口密钥加密不正确"),
	NO_SMS(-3,"短信条数不足"),FORBID_USER(-11,"该用户被禁用"),ERROR_CONTEXT(-14,"短信内容出现非法字符"),
	ERROR_TELNUMBER(-4,"手机号格式不正确"),EMPTY_TELNUMBER(-41,"手机号码为空"),EMPTY_CONTEXT(-42,"短信内容为空"),
	ERROR_SIGN(-51,"短信签名格式不正确"),LIMIT_IP(-6,"IP限制"),SEND_SUCCESS(1,"发送成功");
	;
	private int status;
	private String message;
	/**
	 * 
	 */
	private SMS_Enum(int status,String message) {
		this.status=status;
		this.message=message;
	}
	/**
	 * 获取状态对应的信息
	 * @param status
	 * @return
	 */
	public static String getMessage(int status){
		for (SMS_Enum s : SMS_Enum.values()) {
			if (s.getStatus()==status) {
				return s.message;
			}
		}
		return null;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
