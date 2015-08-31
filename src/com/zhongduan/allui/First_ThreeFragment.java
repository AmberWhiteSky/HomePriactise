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
	private TextView wz_tv_satellite_name,wz_tv_satellite_Longitude; //�ֶ�����(λ��ģʽ)(�ٶ�ģʽ)  �������� ����
	private TextView  wz_tv_AGC;                       //�ֶ�����(λ��ģʽ)  AGC
	private TextView  wz_tv_Ys_fw,wz_tv_Ys_fy,wz_tv_Ys_jh;  //�ֶ�����(λ��ģʽ)  Ԥ��
	private TextView  wz_tv_Sj_fw,wz_tv_Sj_fy,wz_tv_Sj_jh; //�ֶ�����(λ��ģʽ)  ʵ��
	private Button  wz_but_fw,wz_but_jh,wz_but_fy;//�ֶ�����(λ��ģʽ) ��λ���� ���� Ԥ��
	private Button wz_but_run,wz_but_stop;//�ֶ�����(λ��ģʽ) ���� ֹͣ

	private TextView  wz_tv_gps,zwz_tv_jsj; //�ֶ�����(λ��ģʽ)  GPS ���ջ�
	private TextView   wz_tv_lp,wz_tv_tx; //�ֶ�����(λ��ģʽ)  ����  ����
	private TextView wz_tv_left,wz_tv_right,wz_tv_up,wz_tv_down,wz_tv_s,wz_tv_n; //�ֶ�����(λ��ģʽ)

	ReadDataOfAuto  rDataSpeed;


	/**
	 *ʵ�����������ݶ���
	 */
	WriteDataOfAuto  writeData=new WriteDataOfAuto();

	/**
	 * handler ����UI
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
		 * ʵ�������
		 */
		initView();
		/**
		 * �������� ������UI
		 */
		initHandler();
		/**
		 *�ص��������ݵ�handler
		 */
		rDataSpeed=new ReadDataOfAuto(handler);
		/**
		 *  ʵ�����������ݵ��߳�
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
	 * �Ƿ�����
	 */
	private boolean isOnline;
	
	/**
	 * ʵ�������
	 */
	public void  initView(){
		/**
		 * �ֶ����� λ��ģʽ    �������� ����
		 */
		wz_tv_satellite_name=(TextView)getActivity().findViewById(R.id.tv_loaction_starname);
		wz_tv_satellite_Longitude=(TextView)getActivity().findViewById(R.id.tv_loaction_starjingdu);
		/**
		 * �ֶ����� λ��ģʽ  AGC
		 */
		wz_tv_AGC=(TextView)getActivity().findViewById(R.id.tv_loaction_agc);
		/**
		 * �ֶ����� λ��ģʽ   Ԥ��� ��λ ���� ����
		 */
		wz_tv_Ys_fw=(TextView)getActivity().findViewById(R.id.tv_loaction_yushe_fangwei);
		wz_tv_Ys_fy=(TextView)getActivity().findViewById(R.id.tv_loaction_yushe_fuyang);
		wz_tv_Ys_jh=(TextView)getActivity().findViewById(R.id.tv_loaction_yushe_jihua);
		/**
		 * �ֶ����� λ��ģʽ   ʵ�ʵķ�λ ���� ����
		 */
		wz_tv_Sj_fw=(TextView)getActivity().findViewById(R.id.tv_loaction_shiji_fangwei);
		wz_tv_Sj_fy=(TextView)getActivity().findViewById(R.id.tv_loaction_shiji_fuyang);
		wz_tv_Sj_jh=(TextView)getActivity().findViewById(R.id.tv_loaction_shiji_jihua);
		/**
		 * �ֶ����� λ��ģʽ   ��λ  ���� ������ť
		 */
		wz_but_fw=(Button)getActivity().findViewById(R.id.button_loaction_fangwei);
		wz_but_jh=(Button)getActivity().findViewById(R.id.button_loaction_jihua);
		wz_but_fy=(Button)getActivity().findViewById(R.id.button_loaction_fuyang);
		/**
		 * �ֶ����� λ��ģʽ   ���� ֹͣ ��ť
		 */
		wz_but_run=(Button)getActivity().findViewById(R.id.button_loaction_run);
		wz_but_stop=(Button)getActivity().findViewById(R.id.button_loaction_stop);
		/**
		 * �ֶ����� λ��ģʽ  GPS ���ջ�
		 */
		wz_tv_gps=(TextView)getActivity().findViewById(R.id.tv_loaction_gps);
		zwz_tv_jsj=(TextView)getActivity().findViewById(R.id.tv_loaction_jieshouji);
		/**
		 * �ֶ����� λ��ģʽ    ���� ����
		 */
		wz_tv_lp=(TextView)getActivity().findViewById(R.id.tv_loaction_luopan);
		wz_tv_tx=(TextView)getActivity().findViewById(R.id.tv_loaction_tianxian);
		/**
		 * �ֶ����� λ��ģʽ   ���� ���� ˳��
		 */ 
		wz_tv_left=(TextView)getActivity().findViewById(R.id.tv_loaction_left);
		wz_tv_right=(TextView)getActivity().findViewById(R.id.tv_loaction_right);
		wz_tv_up=(TextView)getActivity().findViewById(R.id.tv_loaction_up);
		wz_tv_down=(TextView)getActivity().findViewById(R.id.tv_loaction_down);
		wz_tv_s=(TextView)getActivity().findViewById(R.id.tv_loaction_shun);
		wz_tv_n=(TextView)getActivity().findViewById(R.id.tv_loaction_ni);
		/**
		 * ����onClickListener
		 */
		setLinsener();
	}
	
	 /**
     * ����101��handler
     */
	public void setHandlerLinsener(){
		handler.obtainMessage(101).sendToTarget();
	}
	
	/**
	 * setLinsener�ص�
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
					System.out.println("First_ThreeFragment>>>>" + strlist[0]);
					wz_tv_AGC.setText(strlist[0]);
				}else 
					if(msg.what == 101){
					setLinsener();
				}else if(msg.what == 100){
//					Toast.makeText(getActivity(), "msg.what == 100", 0).show();
					if(!Util.isOnline){
						Toast.makeText(getActivity(), "���粻���ã�", 0).show();
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
			System.out.println("��λ��ť");
			writeData.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,fangwei*ff");		
			System.out.println("��λ��ťA");
			Toast.makeText(getActivity(), "First_ThreeFragmentisOnline=="+Util.isOnline, 0).show();
			break;

		case R.id.button_loaction_jihua:
			System.out.println("��λ��ť");
			writeData.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,fuyang*ff");		
			System.out.println("��λ��ťA");
			Toast.makeText(getActivity(), "isOnline=="+Util.isOnline, 0).show();
			break;
		case R.id.button_loaction_fuyang:
			System.out.println("��λ��ť");
			writeData.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,jihua*ff");		
			System.out.println("��λ��ťA");
			Toast.makeText(getActivity(), "isOnline=="+Util.isOnline, 0).show();
			break;
		case R.id.button_loaction_run:
			System.out.println("��λ��ť");
			writeData.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,running*ff");		
			System.out.println("��λ��ťA");
			Toast.makeText(getActivity(), "isOnline=="+Util.isOnline, 0).show();
			break;
		case R.id.button_loaction_stop:
			System.out.println("��λ��ť");
			writeData.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,stopping*ff");		
			System.out.println("��λ��ťA");
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
