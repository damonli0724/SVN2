package com.saltedfish.controller.websocket.config;

import java.util.Date;

public class Message {
		private Integer sendUserId;  //发送者的Id
		private String  sendUserName;//发送者的名称
		private Integer reciveUserId;//接收者的Id
		private String content;//发送的内容
		private Date date;//发送日期
		/**
		 * @return the sendUserId
		 */
		public Integer getSendUserId() {
			return sendUserId;
		}
		/**
		 * @param sendUserId the sendUserId to set
		 */
		public void setSendUserId(Integer sendUserId) {
			this.sendUserId = sendUserId;
		}
		/**
		 * @return the sendUserName
		 */
		public String getSendUserName() {
			return sendUserName;
		}
		/**
		 * @param sendUserName the sendUserName to set
		 */
		public void setSendUserName(String sendUserName) {
			this.sendUserName = sendUserName;
		}
		/**
		 * @return the reciveUserId
		 */
		public Integer getReciveUserId() {
			return reciveUserId;
		}
		/**
		 * @param reciveUserId the reciveUserId to set
		 */
		public void setReciveUserId(Integer reciveUserId) {
			this.reciveUserId = reciveUserId;
		}
		/**
		 * @return the content
		 */
		public String getContent() {
			return content;
		}
		/**
		 * @param content the content to set
		 */
		public void setContent(String content) {
			this.content = content;
		}
		/**
		 * @return the date
		 */
		public Date getDate() {
			return date;
		}
		/**
		 * @param date the date to set
		 */
		public void setDate(Date date) {
			this.date = date;
		}
}
