package com.zhongduan.allui;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.allui.R;
import com.zhongduan.allui.DengLu_Activity.WIFIBroadcastReceiver;
import com.zhongduan.allui.DengLu_Activity.WIFIHandler;
import com.zhongduan.config.ReadDataOfAuto;
import com.zhongduan.config.Util;
import com.zhongduan.config.WriteDataOfAuto;
import com.zhongduan.sqlite.Satellite;
import com.zhongduan.sqlite.SatelliteService;


public class InsertSatellite extends BaseActivity  implements OnClickListener {
	/**
	 * private Integer  satellite_Id;
	private String satellite_Name;  //卫星名称
	private String satellite_Longitude;//卫星经度
	private String satellite_Polarization;//极化方式
	private String satellite_Beacon;//信标频率
	private String satellite_Carrier;//载波
	private String satellite_Sign;//符号频率
	 * 
	 * 
	 *             etName
					etLongitude
					etPolarization
					etBeacon
					etCarrier
					etSign
	 */
	private EditText  etName,etLongitude,etPolarization,etBeacon,etCarrier,etSign;
	private String  name,longitude,polarization,beacon,carrier,sign;
	private Button       btInsert,btCancel,btBack;
	private boolean  flag=true;
	SatelliteService  satelliteService;
	Satellite satellite;
	/**
	 * 接收数据
	 */
	ReadDataOfAuto  rData;


	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insert);
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
		
		
		/**
		 * 实例化组件
		 */
		initView();
		



	}
	
	

	/**
	 * 实例化组件
	 */
	public  void  initView(){
		etName=(EditText)findViewById(R.id.et_Starname);
		etLongitude=(EditText)findViewById(R.id.et_Starjingdu);
		etPolarization=(EditText)findViewById(R.id.et_StarJihua);
		etBeacon=(EditText)findViewById(R.id.et_StarXinBiao);
		etCarrier=(EditText)findViewById(R.id.et_StarZaiBo);
		etSign=(EditText)findViewById(R.id.et_StarFuHao);
		btBack=(Button)findViewById(R.id.button_back);//返回按钮
		btInsert=(Button)findViewById(R.id.button_insert);//添加按钮
		btCancel=(Button)findViewById(R.id.button_quxiao);//取消按钮
		/**
		 * 根据网络设置按钮
		 */
		setLinsener();
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
	
	
	
	public boolean flagSatellite(){
		/**
		 * etName,etLongitude,etPolarization,etBeacon,etCarrier,etSign
		 */
		if (etName.getText().toString().length() == 0) {
			etName.setError("名称不能为空");
			return false;
		}
		if (etLongitude.getText().toString().length() == 0) {
			etLongitude.setError("经度不能为空");
			return false;
		}
		if (etPolarization.getText().toString().length() == 0) {
			etPolarization.setError("极化方式不能为空");
			return false;
		}
		if (etBeacon.getText().toString().length() == 0) {
			etBeacon.setError("信标频率不能为空");
			return false;
		}
		if (etCarrier.getText().toString().length() == 0) {
			etCarrier.setError("载波不能为空");
			return false;
		}
		if (etSign.getText().toString().length() == 0) {
			etSign.setError("符号频率不能为空");
			return false;
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		/**
		 * 插入数据按钮
		 */
		case R.id.button_insert:
			insertSatellite();
			break;
			/**
			 * 取消按钮
			 */
		case R.id.button_quxiao:
			nullTextView();
			break;
			/**
			 * 返回按钮
			 */
		case R.id.button_back:
			skipActivity();
			break;
		}
	}
	/**
	 * 取消按钮的方法置空所有的输入框
	 */
	public  void nullTextView(){
		etName.setText(null);
		etLongitude.setText(null);
		etPolarization.setText(null);
		etBeacon.setText(null);
		etCarrier.setText(null);
		etSign.setText(null);	
	}
	/**
	 * 确认插入数据的方法
	 */
	public void  insertSatellite(){
		flag=flagSatellite();
		if(flag){
			name=	etName.getText().toString();
			longitude=	etLongitude.getText().toString();
			polarization=	etPolarization.getText().toString();
			beacon=	etBeacon.getText().toString();
			carrier=	etCarrier.getText().toString();
			sign=	etSign.getText().toString();
			satelliteService=new SatelliteService(InsertSatellite.this.getBaseContext());    //实例化 SatelliteService
			satellite=new Satellite();                                   //实例化 Satellite
			satellite.setSatellite_Name(name);
			satellite.setSatellite_Longitude(longitude);
			satellite.setSatellite_Polarization(polarization);
			satellite.setSatellite_Beacon(beacon);
			satellite.setSatellite_Carrier(carrier);
			satellite.setSatellite_Sign(sign);
			System.out.println("name="+name+"longitude="+longitude+"sign="+sign);
			satelliteService.insert(satellite);//插入数据
			Toast.makeText(InsertSatellite.this, "插入成功", 1).show();
			etName.setText(null);
			etLongitude.setText(null);
			etPolarization.setText(null);
			etBeacon.setText(null);
			etCarrier.setText(null);
			etSign.setText(null);
		}else{
			Toast.makeText(InsertSatellite.this, "失败", 1).show();
		}
	}
	/**
	 * 返回按钮的事件跳转界面
	 */
	public  void  skipActivity(){
		Intent intent=new Intent(InsertSatellite.this,ChooseSatellite.class);
		startActivity(intent);
	
	}
	public  void  setLinsener(){
		btInsert.setOnClickListener(this);
		btCancel.setOnClickListener(this);
		btBack.setOnClickListener(this);
	}

}
