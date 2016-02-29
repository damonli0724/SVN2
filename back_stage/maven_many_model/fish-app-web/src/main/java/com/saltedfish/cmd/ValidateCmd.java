package com.saltedfish.cmd;

import javax.validation.constraints.NotNull;

public class  ValidateCmd{
		@NotNull(message="{message.content.empty}")
		private String  name;
		private Integer  flag;
		private String tel;


		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getFlag() {
			return flag;
		}
		public void setFlag(Integer flag) {
			this.flag = flag;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		@Override
		public String toString() {
			return "Validate [name=" + name + ", flag=" + flag + ", tel=" + tel
					+ "]";
		}
		
	}