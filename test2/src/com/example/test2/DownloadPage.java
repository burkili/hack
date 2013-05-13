package com.example.test2;

import ftp.FTPTransfer;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

public class DownloadPage extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.download);

		Button buttonDown = (Button) findViewById(R.id.btnDownload);
		buttonDown.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				FTPTransfer.download(getApplicationContext(),
						"httpdocs/new.docx", Environment
								.getExternalStorageDirectory()
								.getAbsolutePath()
								+ "/download/new.docx", new String[] {
								"94.199.204.138", "zcetinbu30944co",
								"burak1991" });
			}
		});
		//
		// Button buttonUp = (Button) findViewById(R.id.btnUpload);
		// buttonUp.setOnClickListener(new View.OnClickListener() {
		// public void onClick(View v) {
		// FTPTransfer.upload(getApplicationContext(), Environment
		// .getExternalStorageDirectory().getAbsolutePath()
		// + "/download/Burak_etin.docx", "httpdocs/new.docx",
		// new String[] { "94.199.204.138", "zcetinbu30944co",
		// "burak1991" });
		//
		// }
		// });

	}
}
