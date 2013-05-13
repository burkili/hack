package com.example.test2;

import java.io.File;
import java.io.FileNotFoundException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

@SuppressLint("ShowToast")
public class Upload extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upload);

		Button btnDownload = (Button) findViewById(R.id.btnUpload);

		btnDownload.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				File f = new File(Environment.getExternalStorageDirectory()
						.getAbsolutePath() + "/download/qwe.txt");

				RequestParams params = new RequestParams();
				try {
					params.put("file", f);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				AsyncHttpClient client = new AsyncHttpClient();
				client.post("http://emeroglu.com/Upload.aspx", params,
						new AsyncHttpResponseHandler() {

							@Override
							public void onStart() {

								super.onStart();
								Toast t = Toast.makeText(
										getApplicationContext(), "Started",
										10000);
								t.show();
							}

							@Override
							public void onFailure(Throwable error,
									String content) {
								super.onFailure(error, content);
								Toast t = Toast.makeText(
										getApplicationContext(), "Failed",
										10000);
								t.show();
							}

							public void onSuccess(String response) {
								Toast t = Toast.makeText(
										getApplicationContext(), "Success",
										10000);
								t.show();
							}
						});
			}

		});
	}
}
