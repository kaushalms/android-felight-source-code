package com.android.mahiraj.fundooapp;
/**
 * @author Mahesh Rajput
 *
 */
import java.util.List;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.mahiraj.metadata.CategoryContents;

public class CustomListAdapter extends BaseAdapter {
	private Activity mActivity;
	private DisplayMetrics metrics_;
	private List<CategoryContents> mCategoryContents;

	public CustomListAdapter(Activity activity, DisplayMetrics metrics,
			List<CategoryContents> categoryContents) {
		mActivity = activity;
		metrics_ = metrics;
		mCategoryContents = categoryContents;
	}

	@Override
	public int getCount() {

		return mCategoryContents.size();
	}

	@Override
	public Object getItem(int position) {

		return position;
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder viewHolder = new ViewHolder();
		if (view == null) {
			view = mActivity.getLayoutInflater().inflate(R.layout.list_item,
					null);
			viewHolder.catContents = (TextView) view
					.findViewById(R.id.listItemText);
			((TextView) view).setText(mCategoryContents.get(position)
					.getmCategoryContents());

			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		Animation animation = null;

		// animation = new TranslateAnimation(metrics_.widthPixels / 2, 0, 0,
		// 0);

		animation = new ScaleAnimation((float) 1.0, (float) 1.0, (float) 0,
				(float) 1.0);

		animation.setDuration(750);
		view.startAnimation(animation);
		return view;
	}

	private static class ViewHolder {
		TextView catContents;

	}

}
