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
	 *  �Զ�����  �������� ����
	 */
	private TextView zd_tv_satellite_name,zd_tv_satellite_Longitude; //�Զ�����  �������� ����
	/**
	 * //�Զ�����  AGC
	 */
	private TextView  zd_tv_AGC;                       //�Զ�����  AGC
	/**
	 *  Ԥ��ķ�λ�� ������ ������
	 */
	private TextView  zd_tv_Ys_fw,zd_tv_Ys_fy,zd_tv_Ys_jh;  //�Զ�����  Ԥ��
	/**
	 *  ʵ�ʵ� ��λ�� ������ ������
	 */
	private TextView  zd_tv_Sj_fw,zd_tv_Sj_fy,zd_tv_Sj_jh; //�Զ�����  ʵ��
	/**
	 *  ��λ ���ǣ��ղ� �������ĸ���ť
	 */
	private Button     zd_but_fuwei,zd_but_dx,zd_but_shouc,zd_but_daiji; //�Զ�����  ��λ ���ǣ��ղ� ����
	/**
	 * �Զ�����ϵͳ���ð�ť
	 */
	private Button zd_setting_but;  //�Զ�����  ϵͳ����
	/**
	 * �Զ����� GPS ���ջ���ʾ������
	 */
	private TextView  zd_tv_gps,zd_tv_jsj; //�Զ�����  GPS ���ջ�
	/**
	 * �Զ����� ���� ���ߵ�״̬
	 */
	private TextView   zd_tv_lp,zd_tv_tx; //�Զ�����  ����  ����
	/**
	 *�Զ����� ��ʾ ���� ���� ˳��
	 */
	private TextView  zd_tv_left,zd_tv_right,zd_tv_up,zd_tv_down,zd_tv_s,zd_tv_n; //�Զ����� ��������˳��
	
	/**
	 * ����ɫ����Ĳ���
	 */
	private LinearLayout  layout_exception;
	/**
	 * ��������
	 */
	ReadDataOfAuto  rData;


	/**
	 *ʵ�����������ݶ���
	 */
	WriteDataOfAuto  writeData=new WriteDataOfAuto();

	/**
	 * ����UIhandler 
	 */
	public Handler handler;
	/**
	 * ���ز����ļ� 
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.zd, container ,false );
		return view;
	}

	/**
	 * ʵ�������
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		/**
		 * ����initView()ʵ�������
		 */
		initView();

		/**
		 * �������� ������UI
		 */
		initHandler();
		/**
		 *�ص��������ݵ�handler
		 */
		rData=new ReadDataOfAuto(handler);
		/**
		 *  ʵ�����������ݵ��߳�
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
	 * �Ƿ�����
	 */
	private boolean isOnline;
	/**
	 * ʵ����First_OneFragment�е����
	 */
	public void initView(){
		/**
		 * ����ɫ���� GPS״̬����
		 */
		layout_exception=(LinearLayout)getActivity().findViewById(R.id.layout_exception);
		/**
		 * ���ǵ����ֺ;���
		 */
		zd_tv_satellite_name=(TextView)getActivity().findViewById(R.id.tv_zidong_starname);
		zd_tv_satellite_Longitude=(TextView)getActivity().findViewById(R.id.tv_zidong_starjingdu);
		/**
		 *  AGC��ǿ��
		 */
		zd_tv_AGC=(TextView)getActivity().findViewById(R.id.tv_zd_agc);
		/**
		 * Ԥ��� ��λ ���� ����
		 */
		zd_tv_Ys_fw=(TextView)getActivity().findViewById(R.id.tv_zidong_yushe_fangwei);
		zd_tv_Ys_fy=(TextView)getActivity().findViewById(R.id.tv_zidong_yushe_fuyang);
		zd_tv_Ys_jh=(TextView)getActivity().findViewById(R.id.tv_zidong_yushe_jihua);
		/**
		 * ʵ�ʵ� ��λ  ���� ����
		 */
		zd_tv_Sj_fw=(TextView)getActivity().findViewById(R.id.tv_zidong_shiji_fangwei);
		zd_tv_Sj_fy=(TextView)getActivity().findViewById(R.id.tv_zidong_shiji_fuyang);
		zd_tv_Sj_jh=(TextView)getActivity().findViewById(R.id.tv_zidong_shiji_jihua);
		/**
		 * ��λ  ���� �ղ�  ����
		 */

		zd_but_fuwei=(Button)getActivity().findViewById(R.id.button_zidong_fuwei);
		zd_but_dx=(Button)getActivity().findViewById(R.id.button_zidong_duixing);
		zd_but_shouc=(Button)getActivity().findViewById(R.id.button_zidong_shoucang);
		zd_but_daiji=(Button)getActivity().findViewById(R.id.button_zidong_daiji);
		/**
		 * GPS ���ջ�
		 */
		zd_tv_gps=(TextView)getActivity().findViewById(R.id.tv_zidong_gps);
		zd_tv_jsj=(TextView)getActivity().findViewById(R.id.tv_zidong_jieshouji);
		/**
		 *��������
		 */
		zd_tv_lp=(TextView)getActivity().findViewById(R.id.tv_zidong_luopan);
		zd_tv_tx=(TextView)getActivity().findViewById(R.id.tv_zidong_tianxian);


		/**
		 * ��������˳�� 
		 */
		zd_tv_left=(TextView)getActivity().findViewById(R.id.tv_zidong_left);
		zd_tv_right=(TextView)getActivity().findViewById(R.id.tv_zidong_right);
		zd_tv_up=(TextView)getActivity().findViewById(R.id.tv_zidong_up);
		zd_tv_down=(TextView)getActivity().findViewById(R.id.tv_zidong_down);
		zd_tv_s=(TextView)getActivity().findViewById(R.id.tv_zidong_shun);
		zd_tv_n=(TextView)getActivity().findViewById(R.id.tv_zidong_ni);
		/**
		 * ϵͳ���ð�ť
		 */
		zd_setting_but =(Button) getActivity().findViewById(R.id.button_zidong_shezhi);

		/**
		 * �����ж�֮�����й��ܲ��ɲ���
		 */
		setLinsener();

		/**
		 * ϵͳ���ð�ť����¼�  ���봫�������� ���ǲ���  ϵͳ��������
		 */

	}
	/**
	 * ����101��handler
	 */
	public void setHandlerLinsener(){
		handler.obtainMessage(101).sendToTarget();
	}

	/**
	 * �����ж�֮�����й��ܲ��ɲ���
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
						Toast.makeText(getActivity(), "���粻���ã�", 0).show();
						setLinsener();
					}
				}
			}
		};
	}

	/**
	 * ��λ��ť�¼�  �����¼�   �ղ��¼� �����¼�  �����¼�
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		/**
		 * ��λ�¼�
		 */
		case R.id.button_zidong_fuwei:
			System.out.println("��λ��ť");
			writeData.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,reset*ff");		
			System.out.println("��λ��ťA");
			Toast.makeText(getActivity(), "isOnline=="+Util.isOnline, 0).show();
			break;
			/**
			 * �����¼�
			 */
		case R.id.button_zidong_duixing:
			System.out.println("���ǰ�ť");
			writeData.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,search *ff");		
			System.out.println("���ǰ�ť���A");
			break;
			/**
			 * �ղ��¼�
			 */
		case  R.id.button_zidong_shoucang:
			System.out.println("�ղذ�ť");
			writeData.requestConnect(StaticData.ipAddress, StaticData.post,"$cmd,stow *ff");		
			System.out.println("�ղذ�ť�����");
			break;
			/**
			 * �����¼�
			 */
		case R.id.button_zidong_daiji:
			System.out.println("������ť");
			writeData.requestConnect(StaticData.ipAddress, StaticData.post,"3333");		
			System.out.println("������ť��");
			break;
			/**
			 * �����¼�
			 */
		case  R.id.button_zidong_shezhi:
			System.out.println("���ð�ť");
			Intent  intent=new Intent(getActivity(),Two_FragmentActivity.class);
			startActivity(intent);	
			System.out.println("���ð�ť�����");
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


