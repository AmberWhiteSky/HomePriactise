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

	private TextView sd_tv_satellite_name,sd_tv_satellite_Longitude; //�ֶ�����(�ٶ�ģʽ)(�ٶ�ģʽ)  �������� ����
	private TextView  sd_tv_AGC;                       //�ֶ�����(�ٶ�ģʽ)  AGC
	private TextView  sd_tv_Ys_fw,sd_tv_Ys_fy,sd_tv_Ys_jh;  //�ֶ�����(�ٶ�ģʽ)  Ԥ��
	private TextView  sd_tv_Sj_fw,sd_tv_Sj_fy,sd_tv_Sj_jh; //�ֶ�����(�ٶ�ģʽ)  ʵ��
	private Button     sd_but_speeed,sd_but_left,sd_but_right,sd_but_up,
	sd_but_stop,sd_but_down,sd_but_shun,sd_but_ni; //�ֶ�����(�ٶ�ģʽ)  �ٶ� ��λ���ң������� ֹͣ�������£����� ˳��ת

	private TextView  sd_tv_gps,zsd_tv_jsj; //�ֶ�����(�ٶ�ģʽ)  GPS ���ջ�
	private TextView   sd_tv_lp,sd_tv_tx; //�ֶ�����(�ٶ�ģʽ)  ����  ����
	private TextView sd_tv_left,sd_tv_right,sd_tv_up,sd_tv_down,sd_tv_s,sd_tv_n; //�ֶ�����(�ٶ�ģʽ)

	ReadDataOfAuto  rDataSpeed;


	/**
	 *ʵ�����������ݶ���
	 */
	WriteDataOfAuto  writeDataSpeed=new WriteDataOfAuto();

	/**
	 * handler ����UI
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
		 * ʵ�������
		 */
		initView();

		/**
		 * �������� ������UI
		 */
		//		initHandler();
		/**
		 *�ص��������ݵ�handler
		 */
		rDataSpeed=new ReadDataOfAuto(handler);
		/**
		 *  ʵ�����������ݵ��߳�
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
		 * �Ƿ�����
		 */


		/**
		 * ���ǵ����� ����
		 */
		sd_tv_satellite_name=(TextView)getActivity().findViewById(R.id.tv_speed_starname);
		sd_tv_satellite_Longitude=(TextView)getActivity().findViewById(R.id.tv_speed_starjingdu);
		/**
		 * AGC
		 */
		sd_tv_AGC=(TextView)getActivity().findViewById(R.id.tv_speed_agc);
		/**
		 * Ԥ��ķ�λ ���� ����
		 */
		sd_tv_Ys_fw=(TextView)getActivity().findViewById(R.id.tv_speed_yushe_fangwei);
		sd_tv_Ys_fy=(TextView)getActivity().findViewById(R.id.tv_speed_yushe_fuyang);
		sd_tv_Ys_jh=(TextView)getActivity().findViewById(R.id.tv_speed_yushe_jihua);
		/**
		 * ʵ�ʵ� ��λ ���� ����
		 */
		sd_tv_Sj_fw=(TextView)getActivity().findViewById(R.id.tv_speed_shiji_fangwei);
		sd_tv_Sj_fy=(TextView)getActivity().findViewById(R.id.tv_speed_shiji_fuyang);
		sd_tv_Sj_jh=(TextView)getActivity().findViewById(R.id.tv_speed_shiji_jihua);
		/**
		 * �ٶ� ���� ����  ���� ���� ֹͣ ˳ת  ��ת
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
		 * GPS ���ջ�
		 */
		sd_tv_gps=(TextView)getActivity().findViewById(R.id.tv_speed_gps);
		zsd_tv_jsj=(TextView)getActivity().findViewById(R.id.tv_speed_jieshouji);
		/**
		 * ��������
		 */
		sd_tv_lp=(TextView)getActivity().findViewById(R.id.tv_speed_luopan);
		sd_tv_tx=(TextView)getActivity().findViewById(R.id.tv_speed_tianxian);
		/**
		 * ��������˳�� 
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
	 * handler��������
	 */
	public void setHandlerLinsener(){
		handler.obtainMessage(101).sendToTarget();
	}
	/**
	 * �����ж�֮�����й��ܲ��ɲ���
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
		 * ʵ���� handler
		 */
		handler=new Handler(){
			public void handleMessage(android.os.Message msg) {
				if(msg.what==1){
					String str1 = (String) msg.obj.toString();
					String[] strlist = str1.split(",");
					System.out.println("First_TwoFragment��������===" + strlist[0]);
					sd_tv_AGC.setText(strlist[0]);
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
		/**
		 * sd_but_speeed,sd_but_left,sd_but_right,sd_but_up,
	sd_but_stop,sd_but_down,sd_but_shun,sd_but_ni;
		 */
		switch (v.getId()) {
		/**
		 * �ٶ�ģʽ
		 */
		case R.id.button_speed_speed:
			System.out.println("�ٶȰ�ť");
			WriteDataOfAuto.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,speed*ff");		
			Toast.makeText(getActivity(), "First_TwoFragment>>>>>isOnline=="+Util.isOnline, 0).show();
			System.out.println("�ٶȰ�ťA");
			break;
			/**
			 * ����
			 */
		case R.id.button_speed_left:
			System.out.println("����ť");
			writeDataSpeed.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,left*ff");		
			System.out.println("����ťA");
			break;
			/**
			 * ����
			 */
		case R.id.button_speed_right:
			System.out.println("���Ұ�ť");
			writeDataSpeed.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,right*ff");		
			System.out.println("���Ұ�ťA");
			break;
			/**
			 * ����
			 */
		case R.id.button_speed_up:
			System.out.println("���ϰ�ť");
			writeDataSpeed.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,up*ff");		
			System.out.println("���ϰ�ťA");
			break;
			/**
			 * ֹͣ
			 */
		case R.id.button_speed_stop:
			System.out.println("ֹͣ��ť");
			writeDataSpeed.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,stop*ff");		
			System.out.println("ֹͣ��ťA");
			break;
			/**
			 * ����
			 */
		case R.id.button_speed_down:
			System.out.println("���°�ť");
			writeDataSpeed.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,down*ff");		
			System.out.println("���°�ťA");
			break;
			/**
			 * ˳ת
			 */
		case R.id.button_speed_shun:
			System.out.println("˳ת��ť");
			writeDataSpeed.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,shun*ff");		
			System.out.println("˳ת��ťA");
			break;
			/**
			 * ��ת
			 */
		case R.id.button_speed_ni:
			System.out.println("��ת��ť");
			writeDataSpeed.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,ni*ff");		
			System.out.println("��ת��ťA");
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


