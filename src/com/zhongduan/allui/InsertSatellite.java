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
	private String satellite_Name;  //��������
	private String satellite_Longitude;//���Ǿ���
	private String satellite_Polarization;//������ʽ
	private String satellite_Beacon;//�ű�Ƶ��
	private String satellite_Carrier;//�ز�
	private String satellite_Sign;//����Ƶ��
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
	 * ��������
	 */
	ReadDataOfAuto  rData;


	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insert);
		Util.isInnerView = true;

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
		 * ʵ�������
		 */
		initView();
		



	}
	
	

	/**
	 * ʵ�������
	 */
	public  void  initView(){
		etName=(EditText)findViewById(R.id.et_Starname);
		etLongitude=(EditText)findViewById(R.id.et_Starjingdu);
		etPolarization=(EditText)findViewById(R.id.et_StarJihua);
		etBeacon=(EditText)findViewById(R.id.et_StarXinBiao);
		etCarrier=(EditText)findViewById(R.id.et_StarZaiBo);
		etSign=(EditText)findViewById(R.id.et_StarFuHao);
		btBack=(Button)findViewById(R.id.button_back);//���ذ�ť
		btInsert=(Button)findViewById(R.id.button_insert);//��Ӱ�ť
		btCancel=(Button)findViewById(R.id.button_quxiao);//ȡ����ť
		/**
		 * �����������ð�ť
		 */
		setLinsener();
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
	
	
	
	public boolean flagSatellite(){
		/**
		 * etName,etLongitude,etPolarization,etBeacon,etCarrier,etSign
		 */
		if (etName.getText().toString().length() == 0) {
			etName.setError("���Ʋ���Ϊ��");
			return false;
		}
		if (etLongitude.getText().toString().length() == 0) {
			etLongitude.setError("���Ȳ���Ϊ��");
			return false;
		}
		if (etPolarization.getText().toString().length() == 0) {
			etPolarization.setError("������ʽ����Ϊ��");
			return false;
		}
		if (etBeacon.getText().toString().length() == 0) {
			etBeacon.setError("�ű�Ƶ�ʲ���Ϊ��");
			return false;
		}
		if (etCarrier.getText().toString().length() == 0) {
			etCarrier.setError("�ز�����Ϊ��");
			return false;
		}
		if (etSign.getText().toString().length() == 0) {
			etSign.setError("����Ƶ�ʲ���Ϊ��");
			return false;
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		/**
		 * �������ݰ�ť
		 */
		case R.id.button_insert:
			insertSatellite();
			break;
			/**
			 * ȡ����ť
			 */
		case R.id.button_quxiao:
			nullTextView();
			break;
			/**
			 * ���ذ�ť
			 */
		case R.id.button_back:
			skipActivity();
			break;
		}
	}
	/**
	 * ȡ����ť�ķ����ÿ����е������
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
	 * ȷ�ϲ������ݵķ���
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
			satelliteService=new SatelliteService(InsertSatellite.this.getBaseContext());    //ʵ���� SatelliteService
			satellite=new Satellite();                                   //ʵ���� Satellite
			satellite.setSatellite_Name(name);
			satellite.setSatellite_Longitude(longitude);
			satellite.setSatellite_Polarization(polarization);
			satellite.setSatellite_Beacon(beacon);
			satellite.setSatellite_Carrier(carrier);
			satellite.setSatellite_Sign(sign);
			System.out.println("name="+name+"longitude="+longitude+"sign="+sign);
			satelliteService.insert(satellite);//��������
			Toast.makeText(InsertSatellite.this, "����ɹ�", 1).show();
			etName.setText(null);
			etLongitude.setText(null);
			etPolarization.setText(null);
			etBeacon.setText(null);
			etCarrier.setText(null);
			etSign.setText(null);
		}else{
			Toast.makeText(InsertSatellite.this, "ʧ��", 1).show();
		}
	}
	/**
	 * ���ذ�ť���¼���ת����
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
