package com.zhongduan.allui;

import java.io.IOException;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.allui.R;
import com.zhongduan.config.Util;
import com.zhongduan.staticdata.StaticData;

public class DengLu_Activity extends BaseActivity {
	private EditText ipEditText, portEditText; // IP Port 文本框
	private Button tjButton, singleButton; // 脱机 登录

	//	private boolean flag=true;
	private boolean Connecting = false;
	private boolean flag=false;
	private static 	NetworkInfo myNetworkInfo;
	/**
	 * 意图动作
	 */
	public static final String WIFI_STATE_CHANGED = "android.net.wifi.WIFI_STATE_CHANGED";
	public static final String STATE_CHANGED = "android.net.wifi.STATE_CHANGE";
	/**
	 * 登陆按钮执行线程
	 */
	private Thread myThread = null;
	/**
	 * 登陆链接的handler
	 */
	private Myhandle hander;
	/**
	 * WIFI状态的handler
	 */
	private WIFIHandler wifihandler;


	//意图过滤器
	private IntentFilter intentFilter;
	//	private WifiManager manger;
	private static  boolean flagwifi=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		/**
		 * 加载布局
		 */
		setContentView(R.layout.login);
		/**
		 * 实例化组件
		 */
		initView();
		/**
		 * 链接Socket的handler
		 */
		hander = new Myhandle();	
		/**
		 * WIFI状态监听的handler
		 */
		wifihandler = new WIFIHandler(this);

		HaMaBroadcastReceiver = new WIFIBroadcastReceiver(wifihandler);
		/**
		 * 意图过滤器
		 */
		intentFilter=new IntentFilter(); 
		/**
		 * //添加动作
		 */
		intentFilter.addAction(WIFI_STATE_CHANGED);
		intentFilter.addAction(STATE_CHANGED);

	}
	/**
	 * 实例化组件
	 */
	public void initView(){
		ipEditText = (EditText) findViewById(R.id.et_login_ip);
		portEditText = (EditText) findViewById(R.id.et_login_port);
		tjButton = (Button) findViewById(R.id.button_login_tuoji);
		singleButton = (Button) findViewById(R.id.button_login_connect);
		tjButton.setOnClickListener(tjClickLister);
		singleButton.setOnClickListener(sgClick);
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
	protected void  onDestroy() {
		super.onDestroy();
		unregisterReceiver(HaMaBroadcastReceiver);
	}
	/**
	 * 正常登陆
	 */
	private OnClickListener sgClick=new OnClickListener() {


		@Override
		public void onClick(View arg0) {
						/**
						 * 此处的flag是判断IP和端口的
						 */
						flag = isEmpety();
						System.out.println("此处的flag是判断IP和端口的"+flag);
						if (flag){
							/**
							 *  如果isConnecting是ture 赋值false;
							 */
							if (Connecting) {
								Connecting = false;
								try {
									/**
									 *  判断socket
									 */
									if (Util.clientsocket_auto != null) {
										Util.clientsocket_auto.close();
										Util.clientsocket_auto = null;
										Util.outprint_auto.close();
										Util.outprint_auto = null;
									}
								} catch (IOException e) {
									e.printStackTrace();
								}
								/**
								 * 线程中断
								 */
								myThread.interrupt();
							} else {
								Connecting = true;
								myThread = new Thread(myRunable);
								/**
								 * 线程启动
								 */
								myThread.start();
			
							}
						}	
		}
	};
	/**
	 * 脱机登陆(离线登陆)事件
	 */
	private OnClickListener tjClickLister=new OnClickListener() {

		@Override
		public void onClick(View v) {
			boolean  isNormal =false;

		}

	};
	/**
	 * 判断IP和端口的判断
	 * @return
	 */
	public boolean isEmpety() {
		if (ipEditText.getText().toString().length() == 0) {
			ipEditText.setError("IP不能为空");
			return false;
		}
		if (portEditText.getText(). length()== 0) {
			portEditText.setError("端口号不能为空");
			return false;
		}
		return true;
	}


	/**
	 * 登录的线程
	 */
	private Runnable myRunable = new Runnable() {

		@Override
		public void run() {
			StaticData.ipAddress= ipEditText.getText().toString();
			StaticData.post = Integer.valueOf(portEditText.getText().toString());
			/**
			 * 重新连接
			 */
			doConnServerOfAuto();
		}
	};
	/**
	 * 登陆界面登陆
	 */
	private void doConnServerOfAuto(){
		try {
			/**
			 * Util.connServer()链接服务器
			 */
			Util.connServer();
			System.out.println("登录连接线程执行了");
			Message mes = Message.obtain();
			Bundle bundle = new Bundle();
			bundle.putString("Sign", "2");
			mes.setData(bundle);
			hander.sendMessage(mes);
		} catch (Exception e) {
			System.out.println("登录链接出现异常！");
			Message mes =  Message.obtain();
			Bundle bundle = new Bundle();
			bundle.putString("Sign", "1");
			mes.setData(bundle);
			hander.sendMessage(mes);
		}
	}

	/**
	 * 广播接收者
	 */
	public WIFIBroadcastReceiver HaMaBroadcastReceiver = null;

	public static class	WIFIBroadcastReceiver extends BroadcastReceiver {
		WIFIHandler wifihandler;	
		public WIFIBroadcastReceiver(WIFIHandler wifihandler) {
			this.wifihandler = wifihandler;
		}

		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			final WifiManager manger = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			// WIFI是否打开
			// 获取是否WIFI是否打开的boolean
			flagwifi = manger.isWifiEnabled();
			System.out.println("开关的情况是flag==" + flagwifi);
			if (flagwifi) {
				Message mes =Message.obtain();
				Bundle bundle = new Bundle();
				bundle.putString("WL", "1");
				mes.setData(bundle);
				wifihandler.sendMessage(mes);
				if (STATE_CHANGED.equals(action)) {
					myNetworkInfo = intent.getParcelableExtra("networkInfo");
					System.out.println("myNetworkInfo====" + myNetworkInfo);
					if (myNetworkInfo.getDetailedState() == NetworkInfo.DetailedState.CONNECTED) {
						Message mes1 = Message.obtain();
						Bundle	bundle1 = new Bundle();
						bundle.putString("WL", "3");
						mes1.setData(bundle);
					} else {
						Message mes1 =  Message.obtain();
						Bundle	bundle1= new Bundle();
						bundle.putString("WL", "4");
						mes1.setData(bundle);	
					}
				}
			} else {
				/**
				 * WIFI未打开
				 */
				Message mes = Message.obtain();
				Bundle bundle = new Bundle();
				bundle.putString("WL", "2");
				mes.setData(bundle);
				wifihandler.sendMessage(mes);
			}
		}
	};
	/**
	 *  handler接收线程反馈的数据
	 * @author Administrator
	 *
	 */
	class Myhandle extends Handler {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			String sss = msg.getData().getString("Sign");
			if (sss.equals("1")) {
				myThread.interrupt();
				Toast.makeText(DengLu_Activity.this, "链接失败", Toast.LENGTH_SHORT).show();
				// 13添加

			} else if (sss.equals("2")) {
				if(flagwifi){
					if(myNetworkInfo.getDetailedState() == NetworkInfo.DetailedState.CONNECTED){
						Intent intent = new Intent();
						intent.setClass(DengLu_Activity.this, First_FragmentActivity.class);
						startActivity(intent);
						DengLu_Activity.this.finish();
					}
					else{
						System.out.println("登陆中wifi没连接上");
					}
				}
				else{
					System.out.println("登陆中wifi没开");
				}
			}
			else{

			}
		}
	}
	/**
	 * 返回键
	 */
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Util.exit(getApplicationContext());
	}


	/**
	 * WIFIHandler接收返回的数据
	 * @author Administrator
	 *
	 */
	public static class WIFIHandler extends Handler {
		Context context;
		public WIFIHandler(Context context) {
			this.context = context;
		}
		@Override
		public void handleMessage(Message msg) {
			System.out.println("WIFIHandler执行了");
			super.handleMessage(msg);
			String sss = msg.getData().getString("WL");
			if (sss.equals("2")) {
				Toast.makeText(context, "请打开WIFI",Toast.LENGTH_SHORT).show();
				Util.isOnline = false;
				if(null != First_FragmentActivity.first_OneFragment){
					First_FragmentActivity.first_OneFragment.setHandlerLinsener();
				}else if(null != First_FragmentActivity.first_TwoFragment){
					First_FragmentActivity.first_TwoFragment.setHandlerLinsener();
				}else if(null!=First_FragmentActivity.first_ThreeFragment){
					First_FragmentActivity.first_ThreeFragment.setHandlerLinsener();
				}
			}

			else if (sss.equals("1")) {
				Toast.makeText(context, "WIFI正在打开",Toast.LENGTH_SHORT).show();
			} 
			else if (sss.equals("4")) {
				Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
			}
			else if (sss.equals("3")) {
				Toast.makeText(context, "网络连接正常", Toast.LENGTH_SHORT).show();


				if(!Util.isOnline && Util.isInnerView){
					/**
					 * 重新链接服务器
					 */
					reConnServer();
				}

				if(null != First_FragmentActivity.first_OneFragment){
					First_FragmentActivity.first_OneFragment.setHandlerLinsener();
				}else if(null!=First_FragmentActivity.first_TwoFragment){
					First_FragmentActivity.first_TwoFragment.setHandlerLinsener();
				}else if(null!=First_FragmentActivity.first_ThreeFragment){
					First_FragmentActivity.first_ThreeFragment.setHandlerLinsener();
				}
			}

		}
		/**
		 * 网络中断 重新连接之后
		 */
		public void reConnServer(){
			Toast.makeText(context, "网络连接正常--reConnServer", Toast.LENGTH_LONG).show();
			Util.connServer();
		}
	}
}
