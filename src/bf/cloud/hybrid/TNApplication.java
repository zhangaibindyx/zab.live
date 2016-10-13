package bf.cloud.hybrid;

import com.tedu.zab.uitl.ConfigUtil;

import cn.bmob.newim.BmobIM;
import cn.bmob.v3.Bmob;
import android.app.Application;

public class TNApplication extends Application implements ConfigUtil {
	private static TNApplication INSTANCE;

	public static TNApplication INSTANCE() {
		return INSTANCE;
	}

	private void setInstance(TNApplication app) {
		setBmobIMApplication(app);
	}

	private static void setBmobIMApplication(TNApplication a) {
		TNApplication.INSTANCE = a;
	}

	@Override
	public void onCreate() {
		setInstance(this);
		// bmob≥ı ºªØ
		Bmob.initialize(this, KEY);
		BmobIM.init(this);

	}

}
