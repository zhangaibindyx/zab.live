package com.tedu.zab.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.tedu.zab.R;
import com.tedu.zab.activity.LiveActivity;
import com.tedu.zab.uitl.ConfigUtil;

public class FragmentList extends Fragment implements ConfigUtil {
	private ListView lv;
	private ViewPager vp;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = View.inflate(getActivity(), R.layout.fragment_list, null);
		initViews(v);
		return v;
	}

	private void initViews(View v) {
		lv = (ListView) v.findViewById(R.id.fragment_list_view);
		vp = (ViewPager) v.findViewById(R.id.fragment_list_vp);
		Button b = (Button) v.findViewById(R.id.fragment_live_paly);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), LiveActivity.class);
				intent.putExtra(LIVE_URL_KEY,
						"servicetype=2&uid=48235071&fid=3470AFA6025B57ECD6BFB1918419996E14E233C0");
				intent.putExtra(LIVE_ID_KEY,
						"3470AFA6025B57ECD6BFB1918419996E14E233C0");
				startActivity(intent);
			}
		});

	}

}
