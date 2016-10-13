package com.tedu.zab.model;

import com.tedu.zab.bean.UserEntity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;
/**
 * 实现注册、登录、查询用户信息，跟新用户信息、增删好友功能
 * @author zab
 *
 */
public class UserModel extends BaseModel {
	/** 单例模式 */
	private static UserModel ourInstance = new UserModel();

	public static UserModel getInstance() {
		return ourInstance;
	}

	private UserModel() {
	}

	
	/***
	 * 登录功能实现，使用自定义UserEntity类封装所有数
	 * @param name  账号
	 * @param pwd  密码
	 * @param listener  回调函数
	 */
	public void login(String name,String pwd,final LogInListener<UserEntity> listener){
		final UserEntity e=new UserEntity();
		e.setUsername(name);
		e.setPassword(pwd);
		e.login(getContext(), new SaveListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				listener.done(getCurrentUser(), null);
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				listener.done(e, new BmobException(arg0,arg1));
			}
		});
	}
	/**获得当前登录对象*/
	public UserEntity getCurrentUser(){
        return BmobUser.getCurrentUser(getContext(), UserEntity.class);
    }
	/**退出登录状态**/
	public void logout(){
	   BmobUser.logOut(getContext());	
	}
	
	/**
	 * 实现注册功能，当注册失败是通过回调函数将异常发送回去
	 * @param name  用户名
	 * @param pwd   密码
	 * @param listener  回调函数
	 */
	public void register(String name,String pwd,final LogInListener<UserEntity> listener){
		final UserEntity e=new UserEntity();
		e.setUsername(name);
		e.setPassword(pwd);
		e.signUp(getContext(), new SaveListener() {
			public void onSuccess() {
			///代表没有任何返回数据 表示登录成功
				listener.done(null, null);
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				listener.done(null, new BmobException(arg0,arg1));
			}
		});
	}
	
	
	
}
