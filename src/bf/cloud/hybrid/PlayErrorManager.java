package bf.cloud.hybrid;

import bf.cloud.android.playutils.BasePlayer;
import bf.cloud.android.playutils.PlayTaskType;
import bf.cloud.android.playutils.VideoManager;

public class PlayErrorManager {
	private int mErrorCode = 0;
	// error tips below
	public final static String PLAYER_ERROR_SHOW_TIPS = "哎呀，不小心异常�? :(";
	public final static String PLAYER_ERROR_TIPS_DECODE_FAILED_1012 = "解码失败，请重试";
	public final static String PLAYER_ERROR_TIPS_NO_NETWORK_1014 = "无网络可�?";
	public final static String PLAYER_ERROR_TIPS_MOBILE_1015 = "非WiFi环境下，继续播放将会产生流量费用";
	public final static String MEDIA_INFO_ERROR_SHOW_TIPS = "网络好慢啊，我都受不了了 :(";
	public final static String MEDIA_INFO_ERROR_TIPS_VOD_2005 = "亲，你确定这个视频还在吗?";
	public final static String MEDIA_INFO_ERROR_TIPS_LIVE_2005 = "直播还没有开�?:(";
	public final static String MEDIA_INFO_ERROR_TIPS_LIVE_2006 = "直播已经结束�?,再见:)";
	public final static String MEDIA_INFO_ERROR_TIPS_2008 = "没有权限看，赶紧去要�?个授权吧�?";
	public final static String MEDIA_INFO_ERROR_TIPS_2009 = "授权失效了，重新要一个吧�?";
	public final static String P2P_ERROR_TIPS_LIVE_3009 = "直播已经结束�?,再见:)";
	public final static String P2P_ERROR_TIPS_LIVE_3010 = "直播还没有开�?:)";
	public final static String P2P_ERROR_SHOW_TIPS = "哎呀，不小心异常�? :(";

	public PlayErrorManager() {
	}

	public String getErrorShowTips(PlayTaskType type) {
		String tips = "";
		if (mErrorCode >= VideoManager.ERROR_PLAYER_ERROR_MIN
				&& mErrorCode <= VideoManager.ERROR_PLAYER_ERROR_MAX) {
			switch (mErrorCode) {
			case VideoManager.ERROR_NO_NETWORK:
				tips = PLAYER_ERROR_TIPS_NO_NETWORK_1014;
				break;
			case VideoManager.ERROR_MOBILE_NO_PLAY:
				tips = PLAYER_ERROR_TIPS_MOBILE_1015;
				break;
			case VideoManager.ERROR_EXOPLAYER_DECODE_FAILED:
				tips = PLAYER_ERROR_TIPS_DECODE_FAILED_1012;
				break;
			default:
				tips = PLAYER_ERROR_SHOW_TIPS;
				break;
			}
		} else if (mErrorCode >= VideoManager.ERROR_MEDIA_INFO_ERROR_MIN
				&& mErrorCode <= VideoManager.ERROR_MEDIA_INFO_ERROR_MAX) {
			switch (mErrorCode) {
			case VideoManager.ERROR_MEDIA_MOVIE_INFO_NOT_FOUND: {
				if (type == PlayTaskType.VOD) {
					tips = MEDIA_INFO_ERROR_TIPS_VOD_2005;
				} else if (type == PlayTaskType.LIVE) {
					tips = MEDIA_INFO_ERROR_TIPS_LIVE_2005;
				}
				break;
			}
			case VideoManager.ERROR_MEDIA_MOVIE_INFO_LIVE_ENDED: {
				if (type == PlayTaskType.LIVE) {
					tips = MEDIA_INFO_ERROR_TIPS_LIVE_2006;
				}
				break;
			}
			case VideoManager.ERROR_MEDIA_MOVIE_INFO_FORBIDDEN: {
				tips = MEDIA_INFO_ERROR_TIPS_2008;
				break;
			}
			case VideoManager.ERROR_MEDIA_MOVIE_INFO_UNAUTHORIZED: {
				tips = MEDIA_INFO_ERROR_TIPS_2009;
				break;
			}
			default: {
				tips = MEDIA_INFO_ERROR_SHOW_TIPS;
				break;
			}
			}
		} else if (mErrorCode >= VideoManager.ERROR_P2P_ERROR_MIN
				&& mErrorCode <= VideoManager.ERROR_P2P_ERROR_MAX) {
			switch (mErrorCode) {
			case VideoManager.ERROR_P2P_LIVE_ENDED: {
				if (type == PlayTaskType.LIVE) {
					tips = P2P_ERROR_TIPS_LIVE_3009;
				}
				break;
			}
			case VideoManager.ERROR_P2P_LIVE_NOT_BEGIN: {
				if (type == PlayTaskType.LIVE) {
					tips = P2P_ERROR_TIPS_LIVE_3010;
				}
				break;
			}
			default: {
				tips = P2P_ERROR_SHOW_TIPS;
				break;
			}
			}
		}

		return tips;
	}

	public void setErrorCode(int errorCode) {
		mErrorCode = errorCode;
	}

	public int getErrorCode() {
		return mErrorCode;
	}
}
