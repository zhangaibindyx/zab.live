package bf.cloud.hybrid;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.os.Message;
import android.util.Log;
import android.view.OrientationEventListener;

public class PlayerOrientationMessageListener {
	private final static String TAG = PlayerOrientationMessageListener.class
			.getSimpleName();
	public static final int ORIENTATION_LIE = -1;// 平放
	public static final int ORIENTATION_TOP = 0;// 顶部向上
	public static final int ORIENTATION_LEFT = 1;// 左侧向上
	public static final int ORIENTATION_BOTTOM = 2;// 顶部向上
	public static final int ORIENTATION_RIGHT = 3;// 右侧向上

	private static int mCurrentOrigentation = -1;// 当前方向
	private static int mLastOrientation = -1;// 上一次方�?
	private Context mContext = null;
	private BFMediaPlayerControllerBase mController = null;
	private OrientationEventListener mOrientationEventListener = null;

	public PlayerOrientationMessageListener(Context context,
			BFMediaPlayerControllerBase controller) {
		mContext = context.getApplicationContext();
		mController = controller;
		init();
	}

	private void init() {
		mCurrentOrigentation = -1;
		mLastOrientation = -1;
		if (mContext == null) {
			Log.d(TAG, "Context is null, it is invailid");
			return;
		}
	}

	/**
	 * 注册屏幕旋转的监听器.
	 */
	public void start() {
		Log.d(TAG, "registerSensor");
		if (null == mOrientationEventListener) {
			mOrientationEventListener = new BFOrientationEventListener(
						mContext.getApplicationContext(), mController);
		}
		boolean canDetect = mOrientationEventListener.canDetectOrientation();
		Log.d(TAG, "registerSensor,canDetect=" + canDetect);
		if (canDetect) {
			mOrientationEventListener.enable();
		}
	}

	/**
	 * 注销屏幕旋转监听�?
	 */
	public void stop() {
		Log.d(TAG, "unRegisterSensor");
		if (mOrientationEventListener != null) {
			mOrientationEventListener.disable();
			mOrientationEventListener = null;
		}
	}
	
	/**
	 * 回收资源
	 */
	public void release(){
		stop();
		mContext = null;
		mController = null;
	}
	
	public int getCurrentOrigentation(){
		return mCurrentOrigentation;
	}
	
	public int getLastOrientation(){
		return mLastOrientation;
	}

	private static class BFOrientationEventListener extends OrientationEventListener {
		private WeakReference<BFMediaPlayerControllerBase> controllerInstance = null;

		public BFOrientationEventListener(Context context, BFMediaPlayerControllerBase con) {
			super(context);
			controllerInstance = new WeakReference<BFMediaPlayerControllerBase>(con);
		}

		@Override
		public void onOrientationChanged(int orientation) {
			if (orientation == OrientationEventListener.ORIENTATION_UNKNOWN) {
				// 平放
				mCurrentOrigentation = ORIENTATION_LIE;
			} else if ((orientation >= 0 && orientation < 10)
					|| (orientation >= 350 && orientation < 360)) {
				// 顶部向上
				mCurrentOrigentation = ORIENTATION_TOP;
			} else if (orientation >= 80 && orientation < 100) {
				// 左边向上
				mCurrentOrigentation = ORIENTATION_LEFT;
			} else if (orientation >= 170 && orientation < 190) {
				// 底部向上
				mCurrentOrigentation = ORIENTATION_BOTTOM;
			} else if (orientation >= 260 && orientation < 280) {
				// 右边向上
				mCurrentOrigentation = ORIENTATION_RIGHT;
			}

			if (mLastOrientation != mCurrentOrigentation) {
				if (controllerInstance == null)
					return;
				BFMediaPlayerControllerBase controller = controllerInstance.get();
				if (controller == null)
					return;
				controller
						.removeMessage(BFMediaPlayerControllerBase.MSG_ADJUST_ORIENTATION);
				Message msg = new Message();
				msg.what = BFMediaPlayerControllerBase.MSG_ADJUST_ORIENTATION;
				msg.arg1 = mCurrentOrigentation;
				msg.arg2 = mLastOrientation;
				controller.sendMessageDelayed(msg, 100);
				mLastOrientation = mCurrentOrigentation;
			}
		}

	}

}
