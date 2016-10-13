package com.tedu.zab.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.tedu.zab.R;
import com.tedu.zab.adapter.MainViewPagerAdapter;
import com.tedu.zab.fragment.FragmentChat;
import com.tedu.zab.fragment.FragmentList;
import com.tedu.zab.fragment.FragmentSet;

/***
 * 实现点播、直播平台列表、聊天、设置等主界面 为ViewPager形式存在
 * 
 * @author zab
 *
 */
public class MainActivity extends FragmentActivity {
	private RadioGroup rg;
	private RadioButton rbList, rbChat, rbSet;
	private ViewPager vp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initListener();
	}

	private void initView() {
		rg = (RadioGroup) findViewById(R.id.main_rg);
		rbChat = (RadioButton) findViewById(R.id.main_rb_live_chat);
		rbList = (RadioButton) findViewById(R.id.main_rb_live_list);
		rbSet = (RadioButton) findViewById(R.id.main_rb_live_set);
		vp = (ViewPager) findViewById(R.id.main_vp);
		List<Fragment> list = new ArrayList<Fragment>();
		list.add(new FragmentList());
		list.add(new FragmentChat());
		list.add(new FragmentSet());
		MainViewPagerAdapter adpater = new MainViewPagerAdapter(
				getSupportFragmentManager(), list);
		vp.setAdapter(adpater);

	}

	private void initListener() {
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.main_rb_live_list:
					vp.setCurrentItem(0);
					break;
				case R.id.main_rb_live_chat:
					vp.setCurrentItem(1);
					break;
				case R.id.main_rb_live_set:
					vp.setCurrentItem(2);
					break;
				}
			}
		});
		vp.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int arg0) {
				switch (arg0) {
				case 0:
					rbList.setChecked(true);
					break;
				case 1:
					rbChat.setChecked(true);
					break;
				case 2:
					rbSet.setChecked(true);
					break;
				}
			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

}
