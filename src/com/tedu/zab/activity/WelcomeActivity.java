package com.tedu.zab.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tedu.zab.R;
import com.tedu.zab.adapter.GuidancePagerAdapter;
import com.tedu.zab.base.BaseActivity;

/***
 * 启动的界面 第一次加载新手指导界面，非第一次加载欢迎界面
 * 
 * @author zab
 */
public class WelcomeActivity extends BaseActivity {
	/** 偏好设置读取第一次登录状态 */
	SharedPreferences sp;
	/** 登录第一次状态 */
	boolean isLogin;
	/** 新手指导界面的ViewPager使用 */
	ViewPager vp;
	/*** 跳转按钮 */
	Button btn;
	/** handler机制跳转界面 */
	Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// 跳转界面
			if (msg.what == START_MAIN_TYPE) {
				startActivity(new Intent(WelcomeActivity.this,
						LoginActivity.class));
				finish();
			}
		};
	};
	/***新手指导图片资源*/
	private int[] resImageId=new int[]{R.drawable.ic_launcher};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化sp
		sp = getSharedPreferences("login", Context.MODE_PRIVATE);
		isLogin = sp.getBoolean(LOGIN_KEY_SP, false);
		if (isLogin) {
			// 不是第一次启动 加载欢迎界面
			setContentView(R.layout.activity_welcome);
			new Thread() {
				public void run() {
					// 启动线程3秒后跳转到主界面
					SystemClock.sleep(2000);
					mHandler.sendEmptyMessage(START_MAIN_TYPE);
				};
			}.start();
		} else {
			// 是第一次启动加载新手指导界面
			setContentView(R.layout.activity_guidance);
			setViewPager();
			sp.edit().putBoolean(LOGIN_KEY_SP, true);
		}
	}

	@SuppressLint("CommitPrefEdits")
	private void setViewPager() {
		vp = (ViewPager) findViewById(R.id.guidance_vp);
		GuidancePagerAdapter adapter = new GuidancePagerAdapter(
				WelcomeActivity.this, resImageId);
		vp.setAdapter(adapter);
		btn = (Button) findViewById(R.id.guidance_btn_start_main);
		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(WelcomeActivity.this,
						LoginActivity.class));
				finish();
				sp.edit().putBoolean(LOGIN_KEY_SP, true);
			}
		});

	}
}
