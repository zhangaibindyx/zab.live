package bf.cloud.hybrid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import bf.cloud.demo.VodDemo;

import com.tedu.zab.R;

@SuppressLint("SetJavaScriptEnabled")
public class HybridActivity extends Activity {

	private WebView webView = null;
	public Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hybrid);

		webView = (WebView) this.findViewById(R.id.webView);
		webView.getSettings().setDefaultTextEncodingName("UTF-8");
		webView.getSettings().setJavaScriptEnabled(true);

		// 寮�鍚疛avaScript鏀寔
		webView.getSettings().setJavaScriptEnabled(true);
		webView.addJavascriptInterface(new JsInterface(this, handler),
				"jsInterface");
		// 鍔犺浇assets鐩綍涓嬬殑鏂囦欢
		String url = "http://www.baofengcloud.com/test/hybridplayerdemo.html";
		webView.loadUrl(url);
	}

	public void showVideo(String url, int mode) {
		Intent intent = new Intent();
		intent.setClass(HybridActivity.this, VodDemo.class);
		Bundle mBundle = new Bundle();
		mBundle.putString("videoUrl", url);
		mBundle.putInt("videoMode", mode);
		intent.putExtras(mBundle);
		HybridActivity.this.startActivity(intent);
	}
}
