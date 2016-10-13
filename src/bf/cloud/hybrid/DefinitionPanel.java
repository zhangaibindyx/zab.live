package bf.cloud.hybrid;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.tedu.zab.R;

/**
 * 清晰度面�?
 * 
 * @author wang
 * 
 */
public class DefinitionPanel extends PopupWindow {

	private static final String TAG = DefinitionPanel.class.getSimpleName();
	private View mRoot = null;
	private ListView mDeflist = null;
	private OnDefinitionClickListener mListener = null;
	private int mDefinitionCount = 1;
	private int mDefinitionWidth = -1;
	private int mDefinitionHeight = -1;
	private String mCurrentDefinition = null;
	private Context mContext = null;
	private DefinitionAdapter mAdapter = null;
	private ArrayList<String> mDefinitions = null;

	public DefinitionPanel(Context context) {
		if (context == null)
			throw new NullPointerException("context is null");
		mContext = context;
		init();
	}

	/**
	 * 在指定控件上方显�?
	 * 
	 * @param anchor
	 */
	public void showAsPullUp(View anchor) {
		// 高度�?=item的高�?*清晰度个�?
		showAsPullUp(anchor, 0, -mDefinitionHeight * mDefinitionCount);
	}

	/**
	 * 在指定控件上方显�?,默认x坐标与控件的x坐标相同
	 * 
	 * @param anchor
	 * @param xoff
	 * @param yoff
	 */
	private void showAsPullUp(View anchor, int xoff, int yoff) {
		Log.d(TAG, "showAsPullUp,xoff=" + xoff + ",yoff=" + yoff + "/anchor:"
				+ anchor);
		// 保存anchor在屏幕中的位�?
		int[] location = new int[2];
		// 保存anchor左上�?
		int[] anchorLefTop = new int[2];
		// 读取anchor坐标
		anchor.getLocationOnScreen(location);
		// 计算anchor左上坐标
		anchorLefTop[0] = location[0] + xoff;
		anchorLefTop[1] = location[1] + yoff;
		super.showAtLocation(anchor, Gravity.NO_GRAVITY, anchorLefTop[0],
				anchorLefTop[1]);
	}
	
	public void setDatas(ArrayList<String> allDefinitions, String currentDefinition){
		if (allDefinitions == null || currentDefinition == null){
			Log.d(TAG, "allDefinitions or CurrentDefinition is invailid");
			return;
		}
		mDefinitions = allDefinitions;
		mCurrentDefinition = currentDefinition;
		mDefinitionCount = mDefinitions.size();
		mAdapter = new DefinitionAdapter(mContext, mDefinitions);
		mAdapter.setSelectedIndex(mDefinitions.indexOf(mCurrentDefinition));
        mDeflist.setAdapter(mAdapter);
	}

	private void init() {
		mDefinitionWidth = (int) mContext.getResources().getDimension(
				R.dimen.vp_player_definite_width);
		mDefinitionHeight = (int) mContext.getResources().getDimension(
				R.dimen.vp_player_definite_height);
		setWidth(mDefinitionWidth);
		setHeight(LayoutParams.WRAP_CONTENT);
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot  = inflater.inflate(R.layout.vp_definition_panel, null);
		mDeflist = (ListView) mRoot.findViewById(R.id.definition_list);
        mDeflist.setItemsCanFocus(false);
        mDeflist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				Log.d(TAG, "position:" + position);
				mAdapter.setSelectedIndex(position);
				mDeflist.invalidate();
				if (mListener != null)
					mListener.onItemClick(position);
			}
		});
		setContentView(mRoot);
		setFocusable(true);
		setBackgroundDrawable(new BitmapDrawable(mContext.getResources(), (Bitmap) null));
	}
	
	public interface OnDefinitionClickListener{
		void onItemClick(int position);
	}
	
	public void registOnClickListener(OnDefinitionClickListener listener){
		mListener = listener;
	}
}
