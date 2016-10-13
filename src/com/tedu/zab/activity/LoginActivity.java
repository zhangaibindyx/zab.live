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
 * 实现登录功能 其中可以实现自动登录和跳转到注册界面
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
					etName.setError("请输入账号");
					return;
				}
				if (TextUtils.isEmpty(pwd)) {
					etPwd.setError("请输入密码");
					return;
				}
				UserModel.getInstance().login(name, pwd,
						new LogInListener<UserEntity>() {
							public void done(UserEntity arg0, BmobException arg1) {
								// 如果异常为空，代表登录成功
								if (arg1 == null) {
									// 暂不处理聊天功能
									// 直接跳转到主界面
									startActivity(new Intent(
											LoginActivity.this,
											MainActivity.class));
									finish();
								} else {
									// 提示用户登录失败
									toast(arg1.getMessage() + "("
											+ arg1.getErrorCode() + ")");
								}
							}
						});
			}
		});

	}
}
