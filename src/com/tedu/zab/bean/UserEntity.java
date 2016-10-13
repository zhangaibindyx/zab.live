package com.tedu.zab.bean;

import cn.bmob.v3.BmobUser;

/***
 * ���������¼�˺�
 * 
 * @author zab �����ֻ��ţ����䣬qq����ʱ����,ֻ���Դ����˺�����
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
