package com.zhongduan.allui;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.allui.R;
import com.zhongduan.allui.DengLu_Activity.WIFIBroadcastReceiver;
import com.zhongduan.allui.DengLu_Activity.WIFIHandler;
import com.zhongduan.config.Util;
import com.zhongduan.sqlite.Satellite;
import com.zhongduan.sqlite.SatelliteService;
import com.zhongduan.staticdata.StaticData;

public class ChooseSatellite extends BaseActivity  implements OnClickListener{
	private ListView  myListView;
	private  Button  insert_but,back_but;

	SatelliteService satelliteService;
	Satellite satellite;
	List<Satellite>  sList;
	public  SimpleAdapter simpleAdapter;
	private  int  id;
	private  int deleteid;
	private String   satellite_Id;
	private String  stringdelete;
	public StaticData  staticData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.liststar);
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
		initView();
		BindData();
	
	}
	/**
	 * ʵ�������
	 */
	public  void  initView(){
		myListView=(ListView)findViewById(R.id.mylist);
		insert_but=(Button)findViewById(R.id.button_list_insert);
		back_but=(Button)findViewById(R.id.button_list_back);
		 setListener();
		
	}
	/**
	 * ��ť�¼�
	 */
	public void  setListener(){
	    
		insert_but.setOnClickListener(this);
		back_but.setOnClickListener(this);
	}
	
	/**
	 * ������
	 */
	public void  BindData(){
		satelliteService=new SatelliteService(ChooseSatellite.this.getBaseContext());  //ʵ����SatelliteService
		satellite=new Satellite();                   //ʵ����Satellite
		sList=	satelliteService.selectAll();   //��ѯ��������
		System.out.println("SelectsList=="+sList.size());
	
		if(sList!=null){       //�жϼ���
			if(sList.size()>0){
				satelliteService=new SatelliteService(ChooseSatellite.this.getBaseContext());  //ʵ����SatelliteService
				final List<Map<String, String>>   mylist=new ArrayList<Map<String,String>>();
				for(Satellite satellite :sList){
					Map<String, String> mymap = new HashMap<String, String>();
					id=	satellite.getSatellite_Id();
					satellite_Id=String.valueOf(id);
					mymap.put("s_id", satellite_Id);
					mymap.put("s_name", satellite.getSatellite_Name());
					mymap.put("s_longitude", satellite.getSatellite_Longitude());
					mymap.put("s_polarization", satellite.getSatellite_Polarization());
					mymap.put("s_beacon", satellite.getSatellite_Beacon());
					mymap.put("s_carrier", satellite.getSatellite_Carrier());
					mymap.put("s_sign", satellite.getSatellite_Sign());
					System.out.println("ID==="+id+"s_name=="+satellite.getSatellite_Name());
					mylist.add(mymap);	

				}
				simpleAdapter=new SimpleAdapter(ChooseSatellite.this, mylist, R.layout.listitem, 
						new String[]{"s_name","s_longitude","s_polarization","s_beacon","s_carrier","s_sign"},
						new int []{R.id.tv_name,R.id.tv_jingdu,R.id.tv_jihua,R.id.tv_xinbiao,R.id.tv_zaibo,R.id.tv_fuhao});
				System.out.println("mylist=="+mylist.size());
				myListView.setAdapter(simpleAdapter);   //myListView��������	

				/**
				 * myListView�ĵ����¼�
				 */
				myListView.setOnItemClickListener( new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,	final int position, long id) {
						ListView listView = (ListView) parent;
						@SuppressWarnings("unchecked")
						final HashMap<String, String> itemdata = (HashMap<String, String>) listView.getItemAtPosition(position);
						stringdelete = itemdata.get("s_id");  //ѡ�е�����
						System.out.println("Dalig�е�ѡ������ݵ�ID++++====" + stringdelete);
						final CharSequence[] items = { "ѡ��", "ɾ��", "����" };
						AlertDialog.Builder builder = new AlertDialog.Builder(ChooseSatellite.this);
						builder.setTitle("�ɽ������²���");
						builder.setItems(items, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int item) {
								switch (item) {
								case 0:  //ѡ������

									deleteid=Integer.parseInt(stringdelete);
									String str_id=	String.valueOf(deleteid);
									System.out.println("ѡ�еĵ�����ID=="+deleteid);
									Intent  intent=new Intent(ChooseSatellite.this, Two_FragmentActivity.class);  //�������ͨ��ѡ������ǰ�ID ����ȥ
									intent.putExtra("index", 1);
									intent.putExtra("satllie_id", str_id);//-1 Result_OK,1 Ϊ Result_CANCLE
									startActivity(intent);
									finish();
									break;
									// ɾ��
								case 1:		
									System.out.println("�Ի����е�����ID=="+deleteid);
									deleteid=Integer.parseInt(stringdelete);
									satelliteService.deleteId(deleteid);
									mylist.remove(position);
									simpleAdapter.notifyDataSetChanged();
									System.out.println("ɾ���ɹ�");
									break;
									//�޸�
								case 2:


									break;
								}
							}
						});
						AlertDialog alert = builder.create();
						alert.show();
					}
				});
			}
		}
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
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_list_insert:
			Intent  intent=new Intent(ChooseSatellite.this,InsertSatellite.class);
			startActivity(intent);
			ChooseSatellite.this.finish();
			break;

		case R.id.button_list_back:
			
			onBackPressed();//���ص�֮ǰ��Fragment
			break;
		}

	}
}
