package com.tedu.zab.base;

import com.tedu.zab.uitl.ConfigUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
/**
 * ��¼��ע��activity��ĸ���
 * @author zab
 *
 */

public   class BaseActivity extends Activity implements ConfigUtil{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	protected void toast(String text){
		Toast.makeText(this, text, 0).show();
	}

	
	

}
