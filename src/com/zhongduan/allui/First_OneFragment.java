package com.zhongduan.allui;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.allui.R;
import com.zhongduan.appliction.HaMaApplication;
import com.zhongduan.config.ReadDataOfAuto;
import com.zhongduan.config.ReadDataOfAuto.CallBack;
import com.zhongduan.config.Util;
import com.zhongduan.config.WriteDataOfAuto;
import com.zhongduan.staticdata.StaticData;

public class First_OneFragment extends Fragment  implements OnClickListener { 



	/**
	 *  自动控制  卫星名字 经度
	 */
	private TextView zd_tv_satellite_name,zd_tv_satellite_Longitude; //自动控制  卫星名字 经度
	/**
	 * //自动控制  AGC
	 */
	private TextView  zd_tv_AGC;                       //自动控制  AGC
	/**
	 *  预设的方位角 俯仰角 极化角
	 */
	private TextView  zd_tv_Ys_fw,zd_tv_Ys_fy,zd_tv_Ys_jh;  //自动控制  预设
	/**
	 *  实际的 方位角 俯仰角 极化角
	 */
	private TextView  zd_tv_Sj_fw,zd_tv_Sj_fy,zd_tv_Sj_jh; //自动控制  实际
	/**
	 *  复位 对星，收藏 待机的四个按钮
	 */
	private Button     zd_but_fuwei,zd_but_dx,zd_but_shouc,zd_but_daiji; //自动控制  复位 对星，收藏 待机
	/**
	 * 自动控制系统设置按钮
	 */
	private Button zd_setting_but;  //自动控制  系统设置
	/**
	 * 自动控制 GPS 接收机显示的数据
	 */
	private TextView  zd_tv_gps,zd_tv_jsj; //自动控制  GPS 接收机
	/**
	 * 自动控制 罗盘 天线的状态
	 */
	private TextView   zd_tv_lp,zd_tv_tx; //自动控制  罗盘  天线
	/**
	 *自动控制 显示 左右 上下 顺逆
	 */
	private TextView  zd_tv_left,zd_tv_right,zd_tv_up,zd_tv_down,zd_tv_s,zd_tv_n; //自动控制 上下左右顺逆
	
	/**
	 * 淡蓝色区域的布局
	 */
	private LinearLayout  layout_exception;
	/**
	 * 接收数据
	 */
	ReadDataOfAuto  rData;


	/**
	 *实例化发送数据对象
	 */
	WriteDataOfAuto  writeData=new WriteDataOfAuto();

	/**
	 * 更新UIhandler 
	 */
	public Handler handler;
	/**
	 * 加载布局文件 
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.zd, container ,false );
		return view;
	}

	/**
	 * 实例化组件
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		/**
		 * 调用initView()实例化组件
		 */
		initView();

		/**
		 * 接收数据 并更新UI
		 */
		initHandler();
		/**
		 *回调接收数据的handler
		 */
		rData=new ReadDataOfAuto(handler);
		/**
		 *  实例化接收数据的线程
		 */
		rData.ReadData(StaticData.ipAddress, StaticData.post, new CallBack() {
			@Override
			public void isException(boolean isException) {
				isOnline = isException;
								handler.obtainMessage(100).sendToTarget();
			}
		});

	}

	/**
	 * 是否链接
	 */
	private boolean isOnline;
	/**
	 * 实例化First_OneFragment中的组件
	 */
	public void initView(){
		/**
		 * 淡蓝色区域 GPS状态布局
		 */
		layout_exception=(LinearLayout)getActivity().findViewById(R.id.layout_exception);
		/**
		 * 卫星的名字和经度
		 */
		zd_tv_satellite_name=(TextView)getActivity().findViewById(R.id.tv_zidong_starname);
		zd_tv_satellite_Longitude=(TextView)getActivity().findViewById(R.id.tv_zidong_starjingdu);
		/**
		 *  AGC的强度
		 */
		zd_tv_AGC=(TextView)getActivity().findViewById(R.id.tv_zd_agc);
		/**
		 * 预设的 方位 俯仰 极化
		 */
		zd_tv_Ys_fw=(TextView)getActivity().findViewById(R.id.tv_zidong_yushe_fangwei);
		zd_tv_Ys_fy=(TextView)getActivity().findViewById(R.id.tv_zidong_yushe_fuyang);
		zd_tv_Ys_jh=(TextView)getActivity().findViewById(R.id.tv_zidong_yushe_jihua);
		/**
		 * 实际的 方位  俯仰 极化
		 */
		zd_tv_Sj_fw=(TextView)getActivity().findViewById(R.id.tv_zidong_shiji_fangwei);
		zd_tv_Sj_fy=(TextView)getActivity().findViewById(R.id.tv_zidong_shiji_fuyang);
		zd_tv_Sj_jh=(TextView)getActivity().findViewById(R.id.tv_zidong_shiji_jihua);
		/**
		 * 复位  对星 收藏  待机
		 */

		zd_but_fuwei=(Button)getActivity().findViewById(R.id.button_zidong_fuwei);
		zd_but_dx=(Button)getActivity().findViewById(R.id.button_zidong_duixing);
		zd_but_shouc=(Button)getActivity().findViewById(R.id.button_zidong_shoucang);
		zd_but_daiji=(Button)getActivity().findViewById(R.id.button_zidong_daiji);
		/**
		 * GPS 接收机
		 */
		zd_tv_gps=(TextView)getActivity().findViewById(R.id.tv_zidong_gps);
		zd_tv_jsj=(TextView)getActivity().findViewById(R.id.tv_zidong_jieshouji);
		/**
		 *罗盘天线
		 */
		zd_tv_lp=(TextView)getActivity().findViewById(R.id.tv_zidong_luopan);
		zd_tv_tx=(TextView)getActivity().findViewById(R.id.tv_zidong_tianxian);


		/**
		 * 上下左右顺逆 
		 */
		zd_tv_left=(TextView)getActivity().findViewById(R.id.tv_zidong_left);
		zd_tv_right=(TextView)getActivity().findViewById(R.id.tv_zidong_right);
		zd_tv_up=(TextView)getActivity().findViewById(R.id.tv_zidong_up);
		zd_tv_down=(TextView)getActivity().findViewById(R.id.tv_zidong_down);
		zd_tv_s=(TextView)getActivity().findViewById(R.id.tv_zidong_shun);
		zd_tv_n=(TextView)getActivity().findViewById(R.id.tv_zidong_ni);
		/**
		 * 系统设置按钮
		 */
		zd_setting_but =(Button) getActivity().findViewById(R.id.button_zidong_shezhi);

		/**
		 * 网络中断之后所有功能不可操作
		 */
		setLinsener();

		/**
		 * 系统设置按钮点击事件  进入传感器参数 卫星参数  系统参数界面
		 */

	}
	/**
	 * 发送101给handler
	 */
	public void setHandlerLinsener(){
		handler.obtainMessage(101).sendToTarget();
	}

	/**
	 * 网络中断之后所有功能不可操作
	 */
	public void setLinsener(){
		zd_setting_but.setOnClickListener(this);
		zd_but_fuwei.setOnClickListener(this);
		zd_but_dx.setOnClickListener(this);
		zd_but_shouc.setOnClickListener(this);
		zd_but_daiji.setOnClickListener(this);
		layout_exception.setOnClickListener(this);

//		Toast.makeText(getActivity(), "First_OneFragment->>>>isOnline=="+Util.isOnline, 0).show();
		layout_exception.setEnabled(Util.isOnline);
		zd_setting_but.setEnabled(Util.isOnline);
		zd_but_fuwei.setEnabled(Util.isOnline);
		zd_but_dx.setEnabled(Util.isOnline);
		zd_but_shouc.setEnabled(Util.isOnline);
		zd_but_daiji.setEnabled(Util.isOnline);
	}



	/**
	 * 接收数据更新UI
	 */
	public void initHandler(){
		/**
		 * 实例化 handler
		 */
		handler=new Handler(){
			public void handleMessage(android.os.Message msg) {
				if(msg.what==1){
					String str1 = (String) msg.obj.toString();
					String[] strlist = str1.split(",");
					System.out.println("First_OneFragment>>>>" + strlist[0]);
					zd_tv_AGC.setText(strlist[0]);
				}else 
//					String str1 = (String) msg.obj.toString();
//					String[] strlist = str1.split(",");
//					System.out.println("strlist===" + strlist[0]);
//					//
//					HaMaApplication		myApplication =(HaMaApplication)getActivity().getApplication();		
//					System.out.println("First_OneFragment===" +myApplication.app_agc_String);
//					zd_tv_AGC.setText(myApplication.app_agc_String);
				
					
					if(msg.what == 101){
					setLinsener();
				}else if(msg.what == 100){
//					Toast.makeText(getActivity(), "msg.what == 100", 0).show();
					if(!Util.isOnline){
						Toast.makeText(getActivity(), "网络不可用！", 0).show();
						setLinsener();
					}
				}
			}
		};
	}

	/**
	 * 复位按钮事件  对星事件   收藏事件 待机事件  设置事件
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		/**
		 * 复位事件
		 */
		case R.id.button_zidong_fuwei:
			System.out.println("方位按钮");
			writeData.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,reset*ff");		
			System.out.println("方位按钮A");
			Toast.makeText(getActivity(), "isOnline=="+Util.isOnline, 0).show();
			break;
			/**
			 * 对星事件
			 */
		case R.id.button_zidong_duixing:
			System.out.println("对星按钮");
			writeData.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,search *ff");		
			System.out.println("对星按钮点击A");
			break;
			/**
			 * 收藏事件
			 */
		case  R.id.button_zidong_shoucang:
			System.out.println("收藏按钮");
			writeData.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,stow *ff");		
			System.out.println("收藏按钮点击了");
			break;
			/**
			 * 待机事件
			 */
		case R.id.button_zidong_daiji:
			System.out.println("待机按钮");
			writeData.requestConnect(StaticData.ipAddress, StaticData.post,"3333");		
			System.out.println("待机按钮了");
			break;
			/**
			 * 设置事件
			 */
		case  R.id.button_zidong_shezhi:
			System.out.println("设置按钮");
			Intent  intent=new Intent(getActivity(),Two_FragmentActivity.class);
			startActivity(intent);	
			System.out.println("设置按钮点击了");
			break;
		case R.id.layout_exception:
			Intent  intent_exception=new  Intent(getActivity(),Exception_Activity.class);
			startActivity(intent_exception);
			
		}
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("First_OneFragment>>>onDestroy");
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		System.out.println("First_OneFragment>>>onPause");
	}
}


