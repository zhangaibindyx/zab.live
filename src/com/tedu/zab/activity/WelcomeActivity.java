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
 * �����Ľ��� ��һ�μ�������ָ�����棬�ǵ�һ�μ��ػ�ӭ����
 * 
 * @author zab
 */
public class WelcomeActivity extends BaseActivity {
	/** ƫ�����ö�ȡ��һ�ε�¼״̬ */
	SharedPreferences sp;
	/** ��¼��һ��״̬ */
	boolean isLogin;
	/** ����ָ�������ViewPagerʹ�� */
	ViewPager vp;
	/*** ��ת��ť */
	Button btn;
	/** handler������ת���� */
	Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// ��ת����
			if (msg.what == START_MAIN_TYPE) {
				startActivity(new Intent(WelcomeActivity.this,
						LoginActivity.class));
				finish();
			}
		};
	};
	/***����ָ��ͼƬ��Դ*/
	private int[] resImageId=new int[]{R.drawable.ic_launcher};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ��ʼ��sp
		sp = getSharedPreferences("login", Context.MODE_PRIVATE);
		isLogin = sp.getBoolean(LOGIN_KEY_SP, false);
		if (isLogin) {
			// ���ǵ�һ������ ���ػ�ӭ����
			setContentView(R.layout.activity_welcome);
			new Thread() {
				public void run() {
					// �����߳�3�����ת��������
					SystemClock.sleep(2000);
					mHandler.sendEmptyMessage(START_MAIN_TYPE);
				};
			}.start();
		} else {
			// �ǵ�һ��������������ָ������
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
