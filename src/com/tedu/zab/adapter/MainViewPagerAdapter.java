package com.tedu.zab.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
	private List<Fragment> list;

	public MainViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
		super(fm);
		this.list = list;
	}

	@Override
	public Fragment getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

}
