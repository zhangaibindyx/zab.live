package com.tedu.zab.bean;

import cn.bmob.v3.BmobUser;

/***
 * 用来保存登录账号
 * 
 * @author zab 其中手机号，邮箱，qq号暂时不用,只用自带的账号密码
 */
public class UserEntity extends BmobUser {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String qq;

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserEntity [qq=" + qq + "]";
	}

	public UserEntity(String qq) {
		super();
		this.qq = qq;
	}

	public UserEntity() {
		super();
	}

}
