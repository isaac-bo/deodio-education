package com.deodio.elearning.persistence.model;

public class UserModelExt extends UserModel{
	
		
		private String idCardImgId;
		private String idCardImgName;
		//二级域名
		private String accountDomain;
		//验证码
		private String valCode;
		
		
		

		public String getValCode() {
			return valCode;
		}

		public void setValCode(String valCode) {
			this.valCode = valCode;
		}

		public String getIdCardImgName() {
			return idCardImgName;
		}

		public void setIdCardImgName(String idCardImgName) {
			this.idCardImgName = idCardImgName;
		}

		public String getIdCardImgId() {
			return idCardImgId;
		}

		public void setIdCardImgId(String idCardImgId) {
			this.idCardImgId = idCardImgId;
		}

		public String getAccountDomain() {
			return accountDomain;
		}

		public void setAccountDomain(String accountDomain) {
			this.accountDomain = accountDomain;
		}

}
