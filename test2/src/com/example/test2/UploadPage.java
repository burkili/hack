package com.example.test2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import ftp.FTPTransfer;

public class UploadPage extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upload);

		Button buttonUp = (Button) findViewById(R.id.btnUpload);
		buttonUp.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				FTPTransfer.upload(getApplicationContext(), Environment
						.getExternalStorageDirectory().getAbsolutePath()
						+ "/download/Burak_etin.docx", "httpdocs/new.docx",
						new String[] { "94.199.204.138", "zcetinbu30944co",
								"burak1991" });

			}
		});

	}
}
