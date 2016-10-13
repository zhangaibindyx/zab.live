package com.tedu.zab.activity;

import java.io.File;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import bf.cloud.android.modules.p2p.MediaCenter;
import bf.cloud.android.mxlbarrage.MxlBarrage;
import bf.cloud.android.mxlbarrage.MxlBarrage.BarrageListener;
import bf.cloud.android.playutils.LivePlayer;
import bf.cloud.hybrid.BFMediaPlayerControllerLive;

import com.tedu.zab.R;
import com.tedu.zab.base.BaseActivity;

public class LiveActivity extends BaseActivity {
	private EditText etText;
	/** 弹幕聊天发送按钮 */
	private Button btnSend;
	/** 直播控件 */
	private BFMediaPlayerControllerLive bpvl;
	/*** 直播控制器 */
	private LivePlayer mLivePlayer;
	/** 弹幕控件 **/
	private MxlBarrage mBarrage;
	/*** 直播频道 **/
	private String mUrl = "servicetype=2&uid=48235071&fid=3470AFA6025B57ECD6BFB1918419996E14E233C0";
	/*** 频道ID **/
	private String mGcid = "3470AFA6025B57ECD6BFB1918419996E14E233C0";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.activity_live_paly);
		init();
		Intent intent = getIntent();
		mUrl = intent.getStringExtra(LIVE_URL_KEY);
		mGcid = intent.getStringExtra(LIVE_ID_KEY);

	}

	// 初始化
	private void init() {
		// 设置视频缓存路径
		setDataPath();
		etText = (EditText) findViewById(R.id.live_et_message);
		btnSend = (Button) findViewById(R.id.live_btn_send_message);
		bpvl = (BFMediaPlayerControllerLive) findViewById(R.id.live_player);
		mLivePlayer =   (LivePlayer) bpvl.getPlayer();
		// 设置播放路径
		mLivePlayer.setDataSource(mUrl,mGcid);
		mLivePlayer.setForceStartFlag(true);
		// 设置弹幕目标路径
		mBarrage = new MxlBarrage(mGcid);
		// /这只弹幕监听回调方法
		mBarrage.registBarrageListener(new BarrageListener() {
			public void onMessage(String arg0, String arg1) {
				// 将所有的监听设置到播放界面
				bpvl.addDanmaku(false, arg1);
			}
		});

		btnSend.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// 发送弹幕
				String text = etText.getText().toString();
				if (TextUtils.isEmpty(text)) {
					toast(text);
					return;
				}
				mBarrage.sendMsg(text);
			}
		});
		mLivePlayer.start();
	}

	@Override
	protected void onPause() {
		// 设置弹幕控件暂停和播放暂停
		if (mBarrage != null && mBarrage.getIsWorking()) {
			mBarrage.stop();
			mLivePlayer.stop();
		}
		if (bpvl != null) {
			bpvl.onPause();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		// 设置弹幕控件工作
		if (mBarrage != null && !mBarrage.getIsWorking()) {
			mBarrage.start();
		}
		if (bpvl != null) {
			bpvl.onResume();// 控制UI层
		}

		super.onResume();
	}

	@Override
	protected void onDestroy() {
//		if (mLivePlayer != null) {
//			mLivePlayer.release();
//			mLivePlayer.stop();// 播放器
//		}
		try {
			bpvl.finalize();// 停止直播控件
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		super.onDestroy();
	}

	private void setDataPath() {
		File file = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath() + File.separator + "zab" + File.separator);
		if (!file.exists()) {
			file.mkdirs();
		}
		if (file.isDirectory()) {
			MediaCenter.setSettingDataPath(file.getAbsolutePath());
		}
	}

}
