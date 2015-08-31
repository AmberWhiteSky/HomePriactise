package com.zhongduan.allui;



import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.allui.R;
import com.zhongduan.appliction.HaMaApplication;
import com.zhongduan.config.ReadDataOfAuto;
import com.zhongduan.config.ReadDataOfAuto.CallBack;
import com.zhongduan.config.Util;
import com.zhongduan.config.WriteDataOfAuto;
import com.zhongduan.staticdata.StaticData;

public class First_TwoFragment extends Fragment implements  OnClickListener{

	private TextView sd_tv_satellite_name,sd_tv_satellite_Longitude; //手动控制(速度模式)(速度模式)  卫星名字 经度
	private TextView  sd_tv_AGC;                       //手动控制(速度模式)  AGC
	private TextView  sd_tv_Ys_fw,sd_tv_Ys_fy,sd_tv_Ys_jh;  //手动控制(速度模式)  预设
	private TextView  sd_tv_Sj_fw,sd_tv_Sj_fy,sd_tv_Sj_jh; //手动控制(速度模式)  实际
	private Button     sd_but_speeed,sd_but_left,sd_but_right,sd_but_up,
	sd_but_stop,sd_but_down,sd_but_shun,sd_but_ni; //手动控制(速度模式)  速度 方位左右，仰角上 停止，仰角下，极化 顺逆转

	private TextView  sd_tv_gps,zsd_tv_jsj; //手动控制(速度模式)  GPS 接收机
	private TextView   sd_tv_lp,sd_tv_tx; //手动控制(速度模式)  罗盘  天线
	private TextView sd_tv_left,sd_tv_right,sd_tv_up,sd_tv_down,sd_tv_s,sd_tv_n; //手动控制(速度模式)

	ReadDataOfAuto  rDataSpeed;


	/**
	 *实例化发送数据对象
	 */
	WriteDataOfAuto  writeDataSpeed=new WriteDataOfAuto();

	/**
	 * handler 更新UI
	 */
	public Handler handler;






	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.ssdd, container ,false );

		return view;

	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		/**
		 * 实例化组件
		 */
		initView();

		/**
		 * 接收数据 并更新UI
		 */
		//		initHandler();
		/**
		 *回调接收数据的handler
		 */
		rDataSpeed=new ReadDataOfAuto(handler);
		/**
		 *  实例化接收数据的线程
		 */
		rDataSpeed.ReadData(StaticData.ipAddress, StaticData.post,new CallBack() {

			@Override
			public void isException(boolean isException) {
				isOnline = isException;	
				handler.obtainMessage(100).sendToTarget();
			}
		});
	}

	private	boolean isOnline;

	public  void  initView(){
		/**
		 * 是否链接
		 */


		/**
		 * 卫星的名字 经度
		 */
		sd_tv_satellite_name=(TextView)getActivity().findViewById(R.id.tv_speed_starname);
		sd_tv_satellite_Longitude=(TextView)getActivity().findViewById(R.id.tv_speed_starjingdu);
		/**
		 * AGC
		 */
		sd_tv_AGC=(TextView)getActivity().findViewById(R.id.tv_speed_agc);
		/**
		 * 预设的方位 俯仰 极化
		 */
		sd_tv_Ys_fw=(TextView)getActivity().findViewById(R.id.tv_speed_yushe_fangwei);
		sd_tv_Ys_fy=(TextView)getActivity().findViewById(R.id.tv_speed_yushe_fuyang);
		sd_tv_Ys_jh=(TextView)getActivity().findViewById(R.id.tv_speed_yushe_jihua);
		/**
		 * 实际的 方位 俯仰 极化
		 */
		sd_tv_Sj_fw=(TextView)getActivity().findViewById(R.id.tv_speed_shiji_fangwei);
		sd_tv_Sj_fy=(TextView)getActivity().findViewById(R.id.tv_speed_shiji_fuyang);
		sd_tv_Sj_jh=(TextView)getActivity().findViewById(R.id.tv_speed_shiji_jihua);
		/**
		 * 速度 向左 向右  向上 向下 停止 顺转  逆转
		 */
		sd_but_speeed=(Button)getActivity().findViewById(R.id.button_speed_speed);
		sd_but_left=(Button)getActivity().findViewById(R.id.button_speed_left);
		sd_but_right=(Button)getActivity().findViewById(R.id.button_speed_right);
		sd_but_up=(Button)getActivity().findViewById(R.id.button_speed_up);
		sd_but_stop=(Button)getActivity().findViewById(R.id.button_speed_stop);
		sd_but_down=(Button)getActivity().findViewById(R.id.button_speed_down);
		sd_but_shun=(Button)getActivity().findViewById(R.id.button_speed_shun);
		sd_but_ni=(Button)getActivity().findViewById(R.id.button_speed_ni);
		/**
		 * GPS 接收机
		 */
		sd_tv_gps=(TextView)getActivity().findViewById(R.id.tv_speed_gps);
		zsd_tv_jsj=(TextView)getActivity().findViewById(R.id.tv_speed_jieshouji);
		/**
		 * 罗盘天线
		 */
		sd_tv_lp=(TextView)getActivity().findViewById(R.id.tv_speed_luopan);
		sd_tv_tx=(TextView)getActivity().findViewById(R.id.tv_speed_tianxian);
		/**
		 * 上下左右顺逆 
		 */
		sd_tv_left=(TextView)getActivity().findViewById(R.id.tv_speed_left);
		sd_tv_right=(TextView)getActivity().findViewById(R.id.tv_speed_right);
		sd_tv_up=(TextView)getActivity().findViewById(R.id.tv_speed_up);
		sd_tv_down=(TextView)getActivity().findViewById(R.id.tv_speed_down);
		sd_tv_s=(TextView)getActivity().findViewById(R.id.tv_speed_shun);
		sd_tv_n=(TextView)getActivity().findViewById(R.id.tv_speed_ni);

		setLinsener();
	}
	/**
	 * handler发送数据
	 */
	public void setHandlerLinsener(){
		handler.obtainMessage(101).sendToTarget();
	}
	/**
	 * 网络中断之后所有功能不可操作
	 */
	public void setLinsener(){
		sd_but_speeed.setOnClickListener(this);
		sd_but_left.setOnClickListener(this);
		sd_but_right.setOnClickListener(this);
		sd_but_up.setOnClickListener(this);
		sd_but_stop.setOnClickListener(this);
		sd_but_down.setOnClickListener(this);
		sd_but_shun.setOnClickListener(this);
		sd_but_ni.setOnClickListener(this);

		Toast.makeText(getActivity(), "First_TwoFragment.>>isOnline=="+Util.isOnline, 0).show();

		sd_but_speeed.setEnabled(Util.isOnline);
		sd_but_left.setEnabled(Util.isOnline);
		sd_but_right.setEnabled(Util.isOnline);
		sd_but_up.setEnabled(Util.isOnline);
		sd_but_stop.setEnabled(Util.isOnline);
		sd_but_down.setEnabled(Util.isOnline);
		sd_but_shun.setEnabled(Util.isOnline);
		sd_but_ni.setEnabled(Util.isOnline);
	}



	public void initHandler(){
		/**
		 * 实例化 handler
		 */
		handler=new Handler(){
			public void handleMessage(android.os.Message msg) {
				if(msg.what==1){
					String str1 = (String) msg.obj.toString();
					String[] strlist = str1.split(",");
					System.out.println("First_TwoFragment的数据是===" + strlist[0]);
					sd_tv_AGC.setText(strlist[0]);
				}else

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




	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		/**
		 * sd_but_speeed,sd_but_left,sd_but_right,sd_but_up,
	sd_but_stop,sd_but_down,sd_but_shun,sd_but_ni;
		 */
		switch (v.getId()) {
		/**
		 * 速度模式
		 */
		case R.id.button_speed_speed:
			System.out.println("速度按钮");
			WriteDataOfAuto.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,speed*ff");		
			Toast.makeText(getActivity(), "First_TwoFragment>>>>>isOnline=="+Util.isOnline, 0).show();
			System.out.println("速度按钮A");
			break;
			/**
			 * 向左
			 */
		case R.id.button_speed_left:
			System.out.println("向左按钮");
			writeDataSpeed.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,left*ff");		
			System.out.println("向左按钮A");
			break;
			/**
			 * 向右
			 */
		case R.id.button_speed_right:
			System.out.println("向右按钮");
			writeDataSpeed.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,right*ff");		
			System.out.println("向右按钮A");
			break;
			/**
			 * 向上
			 */
		case R.id.button_speed_up:
			System.out.println("向上按钮");
			writeDataSpeed.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,up*ff");		
			System.out.println("向上按钮A");
			break;
			/**
			 * 停止
			 */
		case R.id.button_speed_stop:
			System.out.println("停止按钮");
			writeDataSpeed.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,stop*ff");		
			System.out.println("停止按钮A");
			break;
			/**
			 * 向下
			 */
		case R.id.button_speed_down:
			System.out.println("向下按钮");
			writeDataSpeed.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,down*ff");		
			System.out.println("向下按钮A");
			break;
			/**
			 * 顺转
			 */
		case R.id.button_speed_shun:
			System.out.println("顺转按钮");
			writeDataSpeed.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,shun*ff");		
			System.out.println("顺转按钮A");
			break;
			/**
			 * 逆转
			 */
		case R.id.button_speed_ni:
			System.out.println("逆转按钮");
			writeDataSpeed.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,ni*ff");		
			System.out.println("逆转按钮A");
			break;

		}
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("First_TwoFragment>>>onDestroy"
				);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		System.out.println("First_TwoFragment>>>onPause");
	}
}


