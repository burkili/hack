package com.example.test2;

import java.io.InputStream;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HelloGridView extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);

		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new ImageAdapter(this));

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Toast.makeText(HelloGridView.this, "" + position,
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	private class ImageAdapter extends BaseAdapter {
		private Context mContext;

		public ImageAdapter(Context c) {
			mContext = c;
		}

		public int getCount() {
			return 8;
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		// create a new ImageView for each item referenced by the Adapter
		public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater = LayoutInflater.from(mContext);
			ViewHolder holder;

			if (convertView == null) { // if it's not recycled, initialize some
										// attributes
				convertView = inflater.inflate(R.layout.page, null, true);
				holder = new ViewHolder();
				holder.image = (ImageView) convertView
						.findViewById(R.id.imageView1);
				holder.name = (TextView) convertView
						.findViewById(R.id.imageText);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			// holder.image.setImageResource(mThumbIds[position]);

			new DownloadImageTask(holder.image)
					.execute("http://cetinburak.com/sample_" + position
							+ ".png");
			holder.name.setText("Item " + String.valueOf(position));

			return convertView;
		}

		private class ViewHolder {
			TextView name;
			ImageView image;
		}

		private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
			ImageView bmImage;

			public DownloadImageTask(ImageView bmImage) {
				this.bmImage = bmImage;
			}

			protected Bitmap doInBackground(String... urls) {
				String urldisplay = urls[0];
				Bitmap mIcon11 = null;
				try {
					InputStream in = new java.net.URL(urldisplay).openStream();
					mIcon11 = BitmapFactory.decodeStream(in);
				} catch (Exception e) {
					Log.e("Error", e.getMessage());
					e.printStackTrace();
				}
				return mIcon11;
			}

			protected void onPostExecute(Bitmap result) {
				bmImage.setImageBitmap(result);
			}
		}
		//
		// private Integer[] mThumbIds = { R.drawable.sample_2,
		// R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5,
		// R.drawable.sample_6, R.drawable.sample_7, R.drawable.sample_0,
		// R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_3,
		// R.drawable.sample_4, R.drawable.sample_5, R.drawable.sample_6,
		// R.drawable.sample_7, R.drawable.sample_0, R.drawable.sample_1,
		// R.drawable.sample_2, R.drawable.sample_3, R.drawable.sample_4,
		// R.drawable.sample_5, R.drawable.sample_6, R.drawable.sample_7 };
	}
}
