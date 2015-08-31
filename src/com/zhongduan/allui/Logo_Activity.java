package com.zhongduan.allui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.example.allui.R;

public class Logo_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);  //È«ÆÁÆÌÂú
		setContentView(R.layout.activity_main);
		
		
		new  Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent=new Intent(Logo_Activity.this,DengLu_Activity.class);
				Logo_Activity.this.startActivity(intent);
				Logo_Activity.this.finish();
			}
		}, 3000);
	}
}
