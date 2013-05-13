package com.example.test2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button buttonMD = (Button) findViewById(R.id.buttonMD);
		buttonMD.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent("com.example.test2.DOWNLOAD"));
			}
		});

		Button buttonMU = (Button) findViewById(R.id.buttonMU);
		buttonMU.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent("com.example.test2.UPLOAD"));
			}
		});

		Button buttonMP = (Button) findViewById(R.id.buttonMP);
		buttonMP.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent("com.example.test2.PAGE"));
			}
		});

		Button buttonML = (Button) findViewById(R.id.buttonML);
		buttonML.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent("com.example.test2.LIST"));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
