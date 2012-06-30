package uk.co.senab.photup.adapters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import uk.co.senab.photup.R;
import uk.co.senab.photup.cache.BitmapLruCache;
import uk.co.senab.photup.model.PhotoUpload;
import uk.co.senab.photup.views.PhotupImageView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class PhotosBaseAdapter extends BaseAdapter {

	private List<PhotoUpload> mItems;

	private final BitmapLruCache mCache;
	private final Context mContext;
	private final LayoutInflater mLayoutInflater;

	public PhotosBaseAdapter(Context context, BitmapLruCache cache) {
		mContext = context;
		mCache = cache;
		mItems = new ArrayList<PhotoUpload>();
		mLayoutInflater = LayoutInflater.from(mContext);
	}

	public int getCount() {
		return mItems.size();
	}

	public long getItemId(int position) {
		return position;
	}

	public PhotoUpload getItem(int position) {
		return mItems.get(position);
	}

	public void setItems(Collection<PhotoUpload> items) {
		mItems.clear();
		mItems.addAll(items);
		notifyDataSetChanged();
	}

	public View getView(int position, View view, ViewGroup parent) {
		if (null == view) {
			view = mLayoutInflater.inflate(R.layout.item_selected_photo, parent, false);
		}

		PhotupImageView iv = (PhotupImageView) view.findViewById(R.id.iv_photo);
		iv.requestThumbnailId(getItem(position), mCache);

		return view;
	}

	public void remove(int position) {
		mItems.remove(position);
	}

}
