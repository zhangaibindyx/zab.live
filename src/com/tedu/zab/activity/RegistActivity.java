package com.tedu.zab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

import com.tedu.zab.R;
import com.tedu.zab.base.BaseActivity;
import com.tedu.zab.bean.UserEntity;
import com.tedu.zab.model.UserModel;

/**
 * ʵ��ע�Ṧ�ܣ����ע�����ת����¼����
 * 
 * @author zab
 */
public class RegistActivity extends BaseActivity {
	private EditText etName, etPwd, etPwdAgain;
	private Button btnRegist;
	private TextView tvLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		iniView();
		initListeners();
	}

	/** ��ʼ���ؼ� */
	private void iniView() {
		etName = (EditText) findViewById(R.id.regist_et_user_name);
		etPwd = (EditText) findViewById(R.id.regist_et_user_pwd);
		etPwdAgain = (EditText) findViewById(R.id.regist_et_user_name_again);
		btnRegist = (Button) findViewById(R.id.regist_btn);
		tvLogin = (TextView) findViewById(R.id.regist_tv_back);
	}

	/** ��ʼ���ؼ��¼����� */
	private void initListeners() {
		btnRegist.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String name = etName.getText().toString();
				String pwd = etPwd.getText().toString();
				String pwdAgain = etPwdAgain.getText().toString();
				if (name.equals("")) {
					etName.setError("�������˺�");
					return;
				}
				if (pwd.equals("")) {
					etPwd.setError("����������");
					return;
				}
				if (pwdAgain.equals("")) {
					etPwdAgain.setError("������ȷ������");
					return;
				}
				if (!pwd.equals(pwdAgain)) {
					etPwdAgain.setError("�����������벻һ��");
					return;
				}

				UserModel.getInstance().register(name, pwdAgain,
						new LogInListener<UserEntity>() {
							public void done(UserEntity arg0, BmobException arg1) {
								// / ��arg0��arg1��Ϊ��ʱ��ʾע��ɹ�
								if (arg1 == null) {
									// ע��ɹ�
									startActivity(new Intent(
											RegistActivity.this,
											LoginActivity.class));
									finish();
								} else {
									// ע��ʧ��
									toast(arg1.getMessage() + "("
											+ arg1.getErrorCode() + ")");
								}
							}
						});
			}
		});
		tvLogin.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(RegistActivity.this,
						LoginActivity.class));
				finish();
			}
		});

	}
}
