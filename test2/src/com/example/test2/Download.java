package com.example.test2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;

@SuppressLint("ShowToast")
public class Download extends Activity {
	
	//sadadfasfs
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.download);

		Button btnDownload = (Button) findViewById(R.id.btnDownload);

		btnDownload.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				AsyncHttpClient client = new AsyncHttpClient();
				String[] allowedContentTypes = new String[] { "text/plain" };
				client.get("https://emeroglu.com/qwe.txt",
						new BinaryHttpResponseHandler(allowedContentTypes) {
							@Override
							public void onSuccess(byte[] fileData) {
								FileOutputStream fos = null;
								try {
									fos = new FileOutputStream(Environment
											.getExternalStorageDirectory()
											.getAbsolutePath()
											+ "/download/qwe.txt");
								} catch (FileNotFoundException e) {
									e.printStackTrace();
								}
								try {
									fos.write(fileData);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								try {
									fos.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}

							@Override
							public void onStart() {

								super.onStart();
								Toast t = Toast.makeText(
										getApplicationContext(), "Started",
										10000);
								t.show();
							}

							@Override
							public void onFinish() {
								super.onFinish();

								Toast t = Toast.makeText(
										getApplicationContext(), "Finished",
										10000);
								t.show();
							}

							@Override
							public void onFailure(Throwable error,
									String content) {
								super.onFailure(error, content);
								error.printStackTrace();
								Toast t = Toast.makeText(
										getApplicationContext(), "Failed",
										10000);
								t.show();
							}

						});

			}
		});
	}
}
