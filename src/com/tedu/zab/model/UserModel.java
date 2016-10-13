package com.tedu.zab.model;

import com.tedu.zab.bean.UserEntity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;
/**
 * ʵ��ע�ᡢ��¼����ѯ�û���Ϣ�������û���Ϣ����ɾ���ѹ���
 * @author zab
 *
 */
public class UserModel extends BaseModel {
	/** ����ģʽ */
	private static UserModel ourInstance = new UserModel();

	public static UserModel getInstance() {
		return ourInstance;
	}

	private UserModel() {
	}

	
	/***
	 * ��¼����ʵ�֣�ʹ���Զ���UserEntity���װ������
	 * @param name  �˺�
	 * @param pwd  ����
	 * @param listener  �ص�����
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
	/**��õ�ǰ��¼����*/
	public UserEntity getCurrentUser(){
        return BmobUser.getCurrentUser(getContext(), UserEntity.class);
    }
	/**�˳���¼״̬**/
	public void logout(){
	   BmobUser.logOut(getContext());	
	}
	
	/**
	 * ʵ��ע�Ṧ�ܣ���ע��ʧ����ͨ���ص��������쳣���ͻ�ȥ
	 * @param name  �û���
	 * @param pwd   ����
	 * @param listener  �ص�����
	 */
	public void register(String name,String pwd,final LogInListener<UserEntity> listener){
		final UserEntity e=new UserEntity();
		e.setUsername(name);
		e.setPassword(pwd);
		e.signUp(getContext(), new SaveListener() {
			public void onSuccess() {
			///����û���κη������� ��ʾ��¼�ɹ�
				listener.done(null, null);
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				listener.done(null, new BmobException(arg0,arg1));
			}
		});
	}
	
	
	
}
