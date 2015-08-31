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
		initView();
		BindData();
	
	}
	/**
	 * 实例化组件
	 */
	public  void  initView(){
		myListView=(ListView)findViewById(R.id.mylist);
		insert_but=(Button)findViewById(R.id.button_list_insert);
		back_but=(Button)findViewById(R.id.button_list_back);
		 setListener();
		
	}
	/**
	 * 按钮事件
	 */
	public void  setListener(){
	    
		insert_but.setOnClickListener(this);
		back_but.setOnClickListener(this);
	}
	
	/**
	 * 绑定数据
	 */
	public void  BindData(){
		satelliteService=new SatelliteService(ChooseSatellite.this.getBaseContext());  //实例化SatelliteService
		satellite=new Satellite();                   //实例化Satellite
		sList=	satelliteService.selectAll();   //查询所有数据
		System.out.println("SelectsList=="+sList.size());
	
		if(sList!=null){       //判断集合
			if(sList.size()>0){
				satelliteService=new SatelliteService(ChooseSatellite.this.getBaseContext());  //实例化SatelliteService
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
				myListView.setAdapter(simpleAdapter);   //myListView绑定适配器	

				/**
				 * myListView的单击事件
				 */
				myListView.setOnItemClickListener( new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,	final int position, long id) {
						ListView listView = (ListView) parent;
						@SuppressWarnings("unchecked")
						final HashMap<String, String> itemdata = (HashMap<String, String>) listView.getItemAtPosition(position);
						stringdelete = itemdata.get("s_id");  //选中的名字
						System.out.println("Dalig中的选择的数据的ID++++====" + stringdelete);
						final CharSequence[] items = { "选择", "删除", "返回" };
						AlertDialog.Builder builder = new AlertDialog.Builder(ChooseSatellite.this);
						builder.setTitle("可进行以下操作");
						builder.setItems(items, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int item) {
								switch (item) {
								case 0:  //选择卫星

									deleteid=Integer.parseInt(stringdelete);
									String str_id=	String.valueOf(deleteid);
									System.out.println("选中的的卫星ID=="+deleteid);
									Intent  intent=new Intent(ChooseSatellite.this, Two_FragmentActivity.class);  //这里可以通过选择的卫星吧ID 穿过去
									intent.putExtra("index", 1);
									intent.putExtra("satllie_id", str_id);//-1 Result_OK,1 为 Result_CANCLE
									startActivity(intent);
									finish();
									break;
									// 删除
								case 1:		
									System.out.println("对话框中的卫星ID=="+deleteid);
									deleteid=Integer.parseInt(stringdelete);
									satelliteService.deleteId(deleteid);
									mylist.remove(position);
									simpleAdapter.notifyDataSetChanged();
									System.out.println("删除成功");
									break;
									//修改
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
			
			onBackPressed();//返回到之前的Fragment
			break;
		}

	}
}
