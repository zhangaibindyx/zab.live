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
	/** ��Ļ���췢�Ͱ�ť */
	private Button btnSend;
	/** ֱ���ؼ� */
	private BFMediaPlayerControllerLive bpvl;
	/*** ֱ�������� */
	private LivePlayer mLivePlayer;
	/** ��Ļ�ؼ� **/
	private MxlBarrage mBarrage;
	/*** ֱ��Ƶ�� **/
	private String mUrl = "servicetype=2&uid=48235071&fid=3470AFA6025B57ECD6BFB1918419996E14E233C0";
	/*** Ƶ��ID **/
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

	// ��ʼ��
	private void init() {
		// ������Ƶ����·��
		setDataPath();
		etText = (EditText) findViewById(R.id.live_et_message);
		btnSend = (Button) findViewById(R.id.live_btn_send_message);
		bpvl = (BFMediaPlayerControllerLive) findViewById(R.id.live_player);
		mLivePlayer =   (LivePlayer) bpvl.getPlayer();
		// ���ò���·��
		mLivePlayer.setDataSource(mUrl,mGcid);
		mLivePlayer.setForceStartFlag(true);
		// ���õ�ĻĿ��·��
		mBarrage = new MxlBarrage(mGcid);
		// /��ֻ��Ļ�����ص�����
		mBarrage.registBarrageListener(new BarrageListener() {
			public void onMessage(String arg0, String arg1) {
				// �����еļ������õ����Ž���
				bpvl.addDanmaku(false, arg1);
			}
		});

		btnSend.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// ���͵�Ļ
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
		// ���õ�Ļ�ؼ���ͣ�Ͳ�����ͣ
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
		// ���õ�Ļ�ؼ�����
		if (mBarrage != null && !mBarrage.getIsWorking()) {
			mBarrage.start();
		}
		if (bpvl != null) {
			bpvl.onResume();// ����UI��
		}

		super.onResume();
	}

	@Override
	protected void onDestroy() {
//		if (mLivePlayer != null) {
//			mLivePlayer.release();
//			mLivePlayer.stop();// ������
//		}
		try {
			bpvl.finalize();// ֱֹͣ���ؼ�
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
