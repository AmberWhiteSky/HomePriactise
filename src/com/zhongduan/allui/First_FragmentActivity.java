package com.zhongduan.allui;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.allui.R;
import com.zhongduan.adapter.FragmentAdapter;
import com.zhongduan.allui.DengLu_Activity.WIFIBroadcastReceiver;
import com.zhongduan.allui.DengLu_Activity.WIFIHandler;
import com.zhongduan.appliction.HaMaApplication;
import com.zhongduan.config.ReadDataOfAuto;
import com.zhongduan.config.ReadDataOfAuto.CallBack;
import com.zhongduan.config.Util;
import com.zhongduan.staticdata.StaticData;

public class First_FragmentActivity extends BaseActivity {
	/**
	 * ��������
	 */
	ReadDataOfAuto  rData;
	/**
	 * ����UIhandler 
	 */
	public Handler handler;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);
		/**
		 * �Ƿ�����
		 */
		Util.isInnerView = true;
		/**
		 * �������� ������UI
		 */
		initHandler();

		/**
		 *�ص��������ݵ�handler
		 */
		rData=new ReadDataOfAuto(handler);
		/**
		 *  ʵ�����������ݵ��߳�
		 */
		rData.ReadData(StaticData.ipAddress, StaticData.post, new CallBack() {
			@Override
			public void isException(boolean isException) {
				handler.obtainMessage(100).sendToTarget();
			}
		});
		/**
		 * WIFI״̬������handler
		 */
		WIFIHandler wifihandler = new WIFIHandler(this);
		/**
		 * �㲥������
		 */
		HaMaBroadcastReceiver = new WIFIBroadcastReceiver(wifihandler);
		/**
		 * ��ͼ������
		 */
		intentFilter = new IntentFilter();
		/**
		 * //��Ӷ���
		 */
		intentFilter.addAction(DengLu_Activity.WIFI_STATE_CHANGED);
		intentFilter.addAction(DengLu_Activity.STATE_CHANGED);
		/**
		 * ʵ����Fragment
		 */
		first_OneFragment = new First_OneFragment();
		first_TwoFragment = new First_TwoFragment();
		first_ThreeFragment=new First_ThreeFragment();
		List<Fragment> list = new ArrayList<Fragment>();
		list.add(first_OneFragment);
		list.add(first_TwoFragment);
		list.add(first_ThreeFragment);
		ViewPager viewPager = (ViewPager) findViewById(R.id.mypagers_pager);
		FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), list);
		viewPager.setAdapter(fragmentAdapter);


	}


	public static First_OneFragment first_OneFragment;
	public static First_TwoFragment  first_TwoFragment;
	public static  First_ThreeFragment first_ThreeFragment;


	OnPageChangeListener viewPagerChangeListener=new OnPageChangeListener(){

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}
		/**
		 * @see android.support.v4.view.ViewPager.OnPageChangeListener#onPageSelected(int)
		 * Fragment����ļ�������
		 */
		@Override
		public void onPageSelected(int position) {
			// TODO Auto-generated method stub
			if(position==0){
				first_OneFragment.initHandler();
			}else if(position==1){
				first_TwoFragment.initHandler();
			}else if(position==2){
				first_ThreeFragment.initHandler();
			}
		}

	};
	/**
	 * ע��㲥
	 */
	@Override
	protected void onResume() {
		super.onResume();
		registerReceiver(HaMaBroadcastReceiver, intentFilter);
	}

	/**
	 * ���ٹ㲥
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(HaMaBroadcastReceiver);
	}

	/**
	 * ��ͼ������
	 */
	private IntentFilter intentFilter;
	/**
	 * WIFI�Ĺ㲥������
	 */
	private WIFIBroadcastReceiver HaMaBroadcastReceiver;
	/**
	 * �Ƿ��˳���־λ
	 */
	private static Boolean isQuit = false;
	/**
	 * ��ʱ��
	 */
	private final Timer timer = new Timer();
	/**
	 * �������ݸ���UI
	 */
	public void initHandler(){
		/**
		 * ʵ���� handler
		 */
		handler=new Handler(){
			public void handleMessage(android.os.Message msg) {
				if(msg.what==1){
					String str1 = (String) msg.obj.toString();
					String[] strlist = str1.split(",");
//					System.out.println("First_FragmentActivity11111===" + strlist[0]);
//					HaMaApplication		myApplication =(HaMaApplication)First_FragmentActivity.this.getApplication();
//					myApplication.app_agc_String=strlist[0];
//					System.out.println("First_FragmentActivity2222===" +myApplication.app_agc_String);
				}else 

					if(msg.what == 101){

						//					Toast.makeText(First_FragmentActivity.this, "���磡", 0).show();
						System.out.println("�������");
					}else if(msg.what == 100){
						//					Toast.makeText(First_FragmentActivity.this, "msg.what == 100", 0).show();
						if(!Util.isOnline){
							Toast.makeText(First_FragmentActivity.this, "���粻���ã�", 0).show();

						}
					}
			}
		};
	}




	/**
	 * �˳�����
	 */

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// ˫���Ƴ�����
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (isQuit == false) {
				isQuit = true;
				Toast.makeText(getBaseContext(), "�ٰ�һ���˳�", Toast.LENGTH_SHORT).show();
				TimerTask task = null;
				task = new TimerTask() {
					@Override
					public void run() {
						isQuit = false;
					}
				};
				timer.schedule(task, 2000);
			} else {
				Util.exit(getApplicationContext());
			}
		}
		return true;
	}

}
