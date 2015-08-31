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






public class First_ThreeFragment extends Fragment  implements OnClickListener {
	private TextView wz_tv_satellite_name,wz_tv_satellite_Longitude; //手动控制(位置模式)(速度模式)  卫星名字 经度
	private TextView  wz_tv_AGC;                       //手动控制(位置模式)  AGC
	private TextView  wz_tv_Ys_fw,wz_tv_Ys_fy,wz_tv_Ys_jh;  //手动控制(位置模式)  预设
	private TextView  wz_tv_Sj_fw,wz_tv_Sj_fy,wz_tv_Sj_jh; //手动控制(位置模式)  实际
	private Button  wz_but_fw,wz_but_jh,wz_but_fy;//手动控制(位置模式) 方位极化 俯仰 预置
	private Button wz_but_run,wz_but_stop;//手动控制(位置模式) 运行 停止

	private TextView  wz_tv_gps,zwz_tv_jsj; //手动控制(位置模式)  GPS 接收机
	private TextView   wz_tv_lp,wz_tv_tx; //手动控制(位置模式)  罗盘  天线
	private TextView wz_tv_left,wz_tv_right,wz_tv_up,wz_tv_down,wz_tv_s,wz_tv_n; //手动控制(位置模式)

	ReadDataOfAuto  rDataSpeed;


	/**
	 *实例化发送数据对象
	 */
	WriteDataOfAuto  writeData=new WriteDataOfAuto();

	/**
	 * handler 更新UI
	 */
	public Handler handler;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.sswz, container ,false );

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
		initHandler();
		/**
		 *回调接收数据的handler
		 */
		rDataSpeed=new ReadDataOfAuto(handler);
		/**
		 *  实例化接收数据的线程
		 */
		rDataSpeed.ReadData(StaticData.ipAddress, StaticData.post, new CallBack() {
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
	 * 实例化组件
	 */
	public void  initView(){
		/**
		 * 手动控制 位置模式    卫星名字 经度
		 */
		wz_tv_satellite_name=(TextView)getActivity().findViewById(R.id.tv_loaction_starname);
		wz_tv_satellite_Longitude=(TextView)getActivity().findViewById(R.id.tv_loaction_starjingdu);
		/**
		 * 手动控制 位置模式  AGC
		 */
		wz_tv_AGC=(TextView)getActivity().findViewById(R.id.tv_loaction_agc);
		/**
		 * 手动控制 位置模式   预设的 方位 俯仰 极化
		 */
		wz_tv_Ys_fw=(TextView)getActivity().findViewById(R.id.tv_loaction_yushe_fangwei);
		wz_tv_Ys_fy=(TextView)getActivity().findViewById(R.id.tv_loaction_yushe_fuyang);
		wz_tv_Ys_jh=(TextView)getActivity().findViewById(R.id.tv_loaction_yushe_jihua);
		/**
		 * 手动控制 位置模式   实际的方位 俯仰 极化
		 */
		wz_tv_Sj_fw=(TextView)getActivity().findViewById(R.id.tv_loaction_shiji_fangwei);
		wz_tv_Sj_fy=(TextView)getActivity().findViewById(R.id.tv_loaction_shiji_fuyang);
		wz_tv_Sj_jh=(TextView)getActivity().findViewById(R.id.tv_loaction_shiji_jihua);
		/**
		 * 手动控制 位置模式   方位  极化 俯仰按钮
		 */
		wz_but_fw=(Button)getActivity().findViewById(R.id.button_loaction_fangwei);
		wz_but_jh=(Button)getActivity().findViewById(R.id.button_loaction_jihua);
		wz_but_fy=(Button)getActivity().findViewById(R.id.button_loaction_fuyang);
		/**
		 * 手动控制 位置模式   运行 停止 按钮
		 */
		wz_but_run=(Button)getActivity().findViewById(R.id.button_loaction_run);
		wz_but_stop=(Button)getActivity().findViewById(R.id.button_loaction_stop);
		/**
		 * 手动控制 位置模式  GPS 接收机
		 */
		wz_tv_gps=(TextView)getActivity().findViewById(R.id.tv_loaction_gps);
		zwz_tv_jsj=(TextView)getActivity().findViewById(R.id.tv_loaction_jieshouji);
		/**
		 * 手动控制 位置模式    罗盘 天线
		 */
		wz_tv_lp=(TextView)getActivity().findViewById(R.id.tv_loaction_luopan);
		wz_tv_tx=(TextView)getActivity().findViewById(R.id.tv_loaction_tianxian);
		/**
		 * 手动控制 位置模式   左右 上线 顺逆
		 */ 
		wz_tv_left=(TextView)getActivity().findViewById(R.id.tv_loaction_left);
		wz_tv_right=(TextView)getActivity().findViewById(R.id.tv_loaction_right);
		wz_tv_up=(TextView)getActivity().findViewById(R.id.tv_loaction_up);
		wz_tv_down=(TextView)getActivity().findViewById(R.id.tv_loaction_down);
		wz_tv_s=(TextView)getActivity().findViewById(R.id.tv_loaction_shun);
		wz_tv_n=(TextView)getActivity().findViewById(R.id.tv_loaction_ni);
		/**
		 * 调用onClickListener
		 */
		setLinsener();
	}
	
	 /**
     * 发送101给handler
     */
	public void setHandlerLinsener(){
		handler.obtainMessage(101).sendToTarget();
	}
	
	/**
	 * setLinsener回调
	 */
	
	public void setLinsener(){
		wz_but_fw.setOnClickListener(this);
		wz_but_jh.setOnClickListener(this);
		wz_but_fy.setOnClickListener(this);
		wz_but_run.setOnClickListener(this);
		wz_but_stop.setOnClickListener(this);

		Toast.makeText(getActivity(), "First_OneFragment->>>>isOnline=="+Util.isOnline, 0).show();

		wz_but_fw.setEnabled(Util.isOnline);
		wz_but_jh.setEnabled(Util.isOnline);
		wz_but_fy.setEnabled(Util.isOnline);
		wz_but_run.setEnabled(Util.isOnline);
		wz_but_stop.setEnabled(Util.isOnline);
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
					System.out.println("First_ThreeFragment>>>>" + strlist[0]);
					wz_tv_AGC.setText(strlist[0]);
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
		switch (v.getId()) {
		case R.id.button_loaction_fangwei:
			System.out.println("方位按钮");
			writeData.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,fangwei*ff");		
			System.out.println("方位按钮A");
			Toast.makeText(getActivity(), "First_ThreeFragmentisOnline=="+Util.isOnline, 0).show();
			break;

		case R.id.button_loaction_jihua:
			System.out.println("方位按钮");
			writeData.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,fuyang*ff");		
			System.out.println("方位按钮A");
			Toast.makeText(getActivity(), "isOnline=="+Util.isOnline, 0).show();
			break;
		case R.id.button_loaction_fuyang:
			System.out.println("方位按钮");
			writeData.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,jihua*ff");		
			System.out.println("方位按钮A");
			Toast.makeText(getActivity(), "isOnline=="+Util.isOnline, 0).show();
			break;
		case R.id.button_loaction_run:
			System.out.println("方位按钮");
			writeData.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,running*ff");		
			System.out.println("方位按钮A");
			Toast.makeText(getActivity(), "isOnline=="+Util.isOnline, 0).show();
			break;
		case R.id.button_loaction_stop:
			System.out.println("方位按钮");
			writeData.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,stopping*ff");		
			System.out.println("方位按钮A");
			Toast.makeText(getActivity(), "isOnline=="+Util.isOnline, 0).show();
			break;
		}
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("First_ThreeFragment>>>onDestroy"
				);
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		System.out.println("First_ThreeFragment>>>onPause");
	}
}
