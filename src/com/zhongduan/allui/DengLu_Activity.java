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
	private EditText ipEditText, portEditText; // IP Port �ı���
	private Button tjButton, singleButton; // �ѻ� ��¼

	//	private boolean flag=true;
	private boolean Connecting = false;
	private boolean flag=false;
	private static 	NetworkInfo myNetworkInfo;
	/**
	 * ��ͼ����
	 */
	public static final String WIFI_STATE_CHANGED = "android.net.wifi.WIFI_STATE_CHANGED";
	public static final String STATE_CHANGED = "android.net.wifi.STATE_CHANGE";
	/**
	 * ��½��ťִ���߳�
	 */
	private Thread myThread = null;
	/**
	 * ��½���ӵ�handler
	 */
	private Myhandle hander;
	/**
	 * WIFI״̬��handler
	 */
	private WIFIHandler wifihandler;


	//��ͼ������
	private IntentFilter intentFilter;
	//	private WifiManager manger;
	private static  boolean flagwifi=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		/**
		 * ���ز���
		 */
		setContentView(R.layout.login);
		/**
		 * ʵ�������
		 */
		initView();
		/**
		 * ����Socket��handler
		 */
		hander = new Myhandle();	
		/**
		 * WIFI״̬������handler
		 */
		wifihandler = new WIFIHandler(this);

		HaMaBroadcastReceiver = new WIFIBroadcastReceiver(wifihandler);
		/**
		 * ��ͼ������
		 */
		intentFilter=new IntentFilter(); 
		/**
		 * //��Ӷ���
		 */
		intentFilter.addAction(WIFI_STATE_CHANGED);
		intentFilter.addAction(STATE_CHANGED);

	}
	/**
	 * ʵ�������
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
	protected void  onDestroy() {
		super.onDestroy();
		unregisterReceiver(HaMaBroadcastReceiver);
	}
	/**
	 * ������½
	 */
	private OnClickListener sgClick=new OnClickListener() {


		@Override
		public void onClick(View arg0) {
						/**
						 * �˴���flag���ж�IP�Ͷ˿ڵ�
						 */
						flag = isEmpety();
						System.out.println("�˴���flag���ж�IP�Ͷ˿ڵ�"+flag);
						if (flag){
							/**
							 *  ���isConnecting��ture ��ֵfalse;
							 */
							if (Connecting) {
								Connecting = false;
								try {
									/**
									 *  �ж�socket
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
								 * �߳��ж�
								 */
								myThread.interrupt();
							} else {
								Connecting = true;
								myThread = new Thread(myRunable);
								/**
								 * �߳�����
								 */
								myThread.start();
			
							}
						}	
		}
	};
	/**
	 * �ѻ���½(���ߵ�½)�¼�
	 */
	private OnClickListener tjClickLister=new OnClickListener() {

		@Override
		public void onClick(View v) {
			boolean  isNormal =false;

		}

	};
	/**
	 * �ж�IP�Ͷ˿ڵ��ж�
	 * @return
	 */
	public boolean isEmpety() {
		if (ipEditText.getText().toString().length() == 0) {
			ipEditText.setError("IP����Ϊ��");
			return false;
		}
		if (portEditText.getText(). length()== 0) {
			portEditText.setError("�˿ںŲ���Ϊ��");
			return false;
		}
		return true;
	}


	/**
	 * ��¼���߳�
	 */
	private Runnable myRunable = new Runnable() {

		@Override
		public void run() {
			StaticData.ipAddress= ipEditText.getText().toString();
			StaticData.post = Integer.valueOf(portEditText.getText().toString());
			/**
			 * ��������
			 */
			doConnServerOfAuto();
		}
	};
	/**
	 * ��½�����½
	 */
	private void doConnServerOfAuto(){
		try {
			/**
			 * Util.connServer()���ӷ�����
			 */
			Util.connServer();
			System.out.println("��¼�����߳�ִ����");
			Message mes = Message.obtain();
			Bundle bundle = new Bundle();
			bundle.putString("Sign", "2");
			mes.setData(bundle);
			hander.sendMessage(mes);
		} catch (Exception e) {
			System.out.println("��¼���ӳ����쳣��");
			Message mes =  Message.obtain();
			Bundle bundle = new Bundle();
			bundle.putString("Sign", "1");
			mes.setData(bundle);
			hander.sendMessage(mes);
		}
	}

	/**
	 * �㲥������
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
			// WIFI�Ƿ��
			// ��ȡ�Ƿ�WIFI�Ƿ�򿪵�boolean
			flagwifi = manger.isWifiEnabled();
			System.out.println("���ص������flag==" + flagwifi);
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
				 * WIFIδ��
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
	 *  handler�����̷߳���������
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
				Toast.makeText(DengLu_Activity.this, "����ʧ��", Toast.LENGTH_SHORT).show();
				// 13���

			} else if (sss.equals("2")) {
				if(flagwifi){
					if(myNetworkInfo.getDetailedState() == NetworkInfo.DetailedState.CONNECTED){
						Intent intent = new Intent();
						intent.setClass(DengLu_Activity.this, First_FragmentActivity.class);
						startActivity(intent);
						DengLu_Activity.this.finish();
					}
					else{
						System.out.println("��½��wifiû������");
					}
				}
				else{
					System.out.println("��½��wifiû��");
				}
			}
			else{

			}
		}
	}
	/**
	 * ���ؼ�
	 */
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Util.exit(getApplicationContext());
	}


	/**
	 * WIFIHandler���շ��ص�����
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
			System.out.println("WIFIHandlerִ����");
			super.handleMessage(msg);
			String sss = msg.getData().getString("WL");
			if (sss.equals("2")) {
				Toast.makeText(context, "���WIFI",Toast.LENGTH_SHORT).show();
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
				Toast.makeText(context, "WIFI���ڴ�",Toast.LENGTH_SHORT).show();
			} 
			else if (sss.equals("4")) {
				Toast.makeText(context, "�����쳣", Toast.LENGTH_SHORT).show();
			}
			else if (sss.equals("3")) {
				Toast.makeText(context, "������������", Toast.LENGTH_SHORT).show();


				if(!Util.isOnline && Util.isInnerView){
					/**
					 * �������ӷ�����
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
		 * �����ж� ��������֮��
		 */
		public void reConnServer(){
			Toast.makeText(context, "������������--reConnServer", Toast.LENGTH_LONG).show();
			Util.connServer();
		}
	}
}
