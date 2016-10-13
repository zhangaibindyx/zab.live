package com.tedu.zab.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class GuidancePagerAdapter extends PagerAdapter {
	int[] resImageId;
	private Context context;

	public GuidancePagerAdapter(Context context, int[] resImageId) {
		this.resImageId = resImageId;
		this.context = context;
	}

	@Override
	public int getCount() {
		if (resImageId==null)
			return 0;
		else
			return resImageId.length;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView iv = new ImageView(context);
		iv.setImageResource(resImageId[position]);
		container.addView(iv);
		return iv;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		super.destroyItem(container, position, object);
		container.removeView((View) object);
	}

}
