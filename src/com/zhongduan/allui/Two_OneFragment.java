package com.zhongduan.allui;


import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.allui.R;

public class Two_OneFragment extends Fragment {
	private RadioGroup  gpsGroup;//GPS参数单选
	private RadioButton  gpsRadButton;//GPS参数单选的属性
	private TextView  txJdView,txWdView;//天线GPS状态
	private TextView  scJdView,scWdView;//手持机GPS状态
	private TextView  userJdView,userWdView;//用户输入GPS状态
	private Button sdButton;//用户输设定按钮
	private TextView lpHxView,lpFyView,lpHgView;//罗盘参数显示
	private Button  but_backButton;//返回按钮
	private 	LinearLayout  userLayout;
	private EditText jDText,wDText;
	private Button   surebut,canclebut;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.cgq, container ,false );

		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		gpsGroup=(RadioGroup)getActivity().findViewById(R.id.GPSRadio);
		/**
		 * 天线的数据
		 */
		txJdView=(TextView)getActivity().findViewById(R.id.tv_Cg_txjd);
		txWdView=(TextView)getActivity().findViewById(R.id.tv_Cg_txwd);
		/**
		 * 手机本地的传感数据
		 */
		
		scJdView=(TextView)getActivity().findViewById(R.id.tv_Cg_scjd);
		scWdView=(TextView)getActivity().findViewById(R.id.tv_Cg_scwd);
		/**
		 * 用户输入显示的
		 */
		
		userJdView=(TextView)getActivity().findViewById(R.id.tv_Cg_yhjd);
		userWdView=(TextView)getActivity().findViewById(R.id.tv_Cg_yhwd);
		/**
		 * 设定按钮
		 */
		sdButton=(Button)getActivity().findViewById(R.id.button_Cg_YhSd);
		/**
		 * 罗盘参数
		 */
		lpHxView=(TextView)getActivity().findViewById(R.id.tv_cgq_hx);
		lpFyView=(TextView)getActivity().findViewById(R.id.tv_cgq_fy);
		lpHgView=(TextView)getActivity().findViewById(R.id.tv_cgq_hg);
		/**
		 * 用户输入
		 */

		jDText=(EditText)getActivity().findViewById(R.id.tv_Cg_Shuru_jd);
		wDText=(EditText)getActivity().findViewById(R.id.tv_Cg_Shuru_wd);
		/**
		 * 确定 取消 按钮
		 */
		surebut=(Button)getActivity().findViewById(R.id.user_sure_but);
		canclebut=(Button)getActivity().findViewById(R.id.user_cancle_but);
		but_backButton=(Button)getActivity().findViewById(R.id.but_cgq_back);
		/**
		 * 用户输入设定按钮事件
		 */
		sdButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				userLayout=(LinearLayout)getActivity().findViewById(R.id.layout_user);
				userLayout.setVisibility(View.VISIBLE);


			}
		});
		but_backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent  intent=new Intent(getActivity(),First_FragmentActivity.class);
				startActivity(intent);	
				getActivity().finish();
			}
		});
		surebut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				userJdView.setText(jDText.getText().toString());	
				userWdView.setText(wDText.getText().toString());
				userLayout.setVisibility(View.GONE);

				
			}
		});
		canclebut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				userLayout.setVisibility(View.GONE);
			}
		});
	}
	/**
	 * GPS参数获取的方式
	 */

	private String getGpsState(){
		gpsRadButton=(RadioButton)getActivity().findViewById(gpsGroup.getCheckedRadioButtonId());
		return gpsRadButton.getText().toString();

	}
}
