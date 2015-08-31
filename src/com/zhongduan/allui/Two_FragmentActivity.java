package com.zhongduan.allui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.allui.R;
import com.zhongduan.adapter.FragmentAdapter;
import com.zhongduan.allui.DengLu_Activity.WIFIBroadcastReceiver;
import com.zhongduan.allui.DengLu_Activity.WIFIHandler;
import com.zhongduan.config.Util;



public class Two_FragmentActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		Util.isInnerView = true;

		/**
		 * WIFI状态监听的handler
		 */
		WIFIHandler wifihandler = new WIFIHandler(this);
		/**
		 * 广播接收者
		 */
		HaMaBroadcastReceiver = new WIFIBroadcastReceiver(wifihandler);
		/**
		 * 意图过滤器
		 */
		intentFilter = new IntentFilter();
		/**
		 * //添加动作
		 */
		intentFilter.addAction(DengLu_Activity.WIFI_STATE_CHANGED);
		intentFilter.addAction(DengLu_Activity.STATE_CHANGED);



		Intent i = getIntent();
		int index = 0;
		String strId = null;
		if(null != i){
			index = i.getIntExtra("index", 0);
			strId = i.getStringExtra("satllie_id");
		}

		List<Fragment> list = new ArrayList<Fragment>();
		list.add(new Two_OneFragment());
		list.add(new Two_TwoFragment(strId));
		list.add(new Two_ThreeFragment());	
		ViewPager vp_01 = (ViewPager) findViewById(R.id.setting_pager);
		FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), list);
		vp_01.setAdapter(fragmentAdapter);
		/**
		 * 设置显示的界面
		 */
		vp_01.setCurrentItem(index);
	}

	/**
	 * 注册广播
	 */
	@Override
	protected void onResume() {
		super.onResume();
		registerReceiver(HaMaBroadcastReceiver, intentFilter);
	}

	/**
	 * 销毁广播
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(HaMaBroadcastReceiver);
	}

	/**
	 * 意图过滤器
	 */
	private IntentFilter intentFilter;
	/**
	 * WIFI的广播接收者
	 */
	private WIFIBroadcastReceiver HaMaBroadcastReceiver;
}

