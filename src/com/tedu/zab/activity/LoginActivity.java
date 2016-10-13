package com.tedu.zab.activity;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

import com.tedu.zab.R;
import com.tedu.zab.base.BaseActivity;
import com.tedu.zab.bean.UserEntity;
import com.tedu.zab.model.UserModel;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/***
 * ʵ�ֵ�¼���� ���п���ʵ���Զ���¼����ת��ע�����
 * 
 * @author zab
 *
 */
public class LoginActivity extends BaseActivity {
	private EditText etName, etPwd;
	private Button btnLogin;
	private TextView tvRegist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		initListeners();
	}

	private void initView() {
		etName = (EditText) findViewById(R.id.login_et_user_name);
		etPwd = (EditText) findViewById(R.id.login_et_user_pwd);
		btnLogin = (Button) findViewById(R.id.login_btn);
		tvRegist = (TextView) findViewById(R.id.login_tv_regist);
	}

	private void initListeners() {
		tvRegist.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this,
						RegistActivity.class));
				finish();
			}
		});
		btnLogin.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String name = etName.getText().toString();
				String pwd = etPwd.getText().toString();
				if (TextUtils.isEmpty(name)) {
					etName.setError("�������˺�");
					return;
				}
				if (TextUtils.isEmpty(pwd)) {
					etPwd.setError("����������");
					return;
				}
				UserModel.getInstance().login(name, pwd,
						new LogInListener<UserEntity>() {
							public void done(UserEntity arg0, BmobException arg1) {
								// ����쳣Ϊ�գ������¼�ɹ�
								if (arg1 == null) {
									// �ݲ��������칦��
									// ֱ����ת��������
									startActivity(new Intent(
											LoginActivity.this,
											MainActivity.class));
									finish();
								} else {
									// ��ʾ�û���¼ʧ��
									toast(arg1.getMessage() + "("
											+ arg1.getErrorCode() + ")");
								}
							}
						});
			}
		});

	}
}
