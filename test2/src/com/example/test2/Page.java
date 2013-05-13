package com.example.test2;

import java.io.InputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

public class Page extends Activity {

	Button button;
	ImageView image;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page);
		addListenerOnButton();
	}

	public void addListenerOnButton() {
		// image = (ImageView) findViewById(R.id.imageView1);
		// button = (Button) findViewById(R.id.btnChangeImage);
		// button.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View arg0) {
		// // image.setImageResource(R.drawable.image);
		// new DownloadImageTask((ImageView) findViewById(R.id.imageView1))
		// .execute("http://cetinburak.com/alsana.png");
		// }
		//
		// });
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

}