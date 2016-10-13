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
 * 实现注册功能，完成注册后跳转到登录界面
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

	/** 初始化控件 */
	private void iniView() {
		etName = (EditText) findViewById(R.id.regist_et_user_name);
		etPwd = (EditText) findViewById(R.id.regist_et_user_pwd);
		etPwdAgain = (EditText) findViewById(R.id.regist_et_user_name_again);
		btnRegist = (Button) findViewById(R.id.regist_btn);
		tvLogin = (TextView) findViewById(R.id.regist_tv_back);
	}

	/** 初始化控件事件监听 */
	private void initListeners() {
		btnRegist.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String name = etName.getText().toString();
				String pwd = etPwd.getText().toString();
				String pwdAgain = etPwdAgain.getText().toString();
				if (name.equals("")) {
					etName.setError("请输入账号");
					return;
				}
				if (pwd.equals("")) {
					etPwd.setError("请输入密码");
					return;
				}
				if (pwdAgain.equals("")) {
					etPwdAgain.setError("请输入确认密码");
					return;
				}
				if (!pwd.equals(pwdAgain)) {
					etPwdAgain.setError("两次密码输入不一致");
					return;
				}

				UserModel.getInstance().register(name, pwdAgain,
						new LogInListener<UserEntity>() {
							public void done(UserEntity arg0, BmobException arg1) {
								// / 当arg0和arg1都为空时表示注册成功
								if (arg1 == null) {
									// 注册成功
									startActivity(new Intent(
											RegistActivity.this,
											LoginActivity.class));
									finish();
								} else {
									// 注册失败
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
