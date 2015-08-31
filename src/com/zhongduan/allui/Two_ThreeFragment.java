package com.zhongduan.allui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.allui.R;

public class Two_ThreeFragment extends Fragment implements OnClickListener {


	private Button txButton;//天线标定
	private TextView txFwView,txFyView,txJView;//方位俯仰 极化
	private LinearLayout txlLayout;  //天线的布局
	private EditText   ed_tx_FW, ed_tx_FY, ed_tx_JH;//布局中的文本框
	private Button  tx_sure_but,tx_cancle_but; //确定 取消按钮

	private Button lpButton;  //罗盘标定
	private TextView lpHxView,lpFyView,lpHgView;//航向 俯仰 横滚
	private LinearLayout lplLayout;//罗盘的布局
	private EditText   ed_lp_HX, ed_lp_FY, ed_lp_HG;//布局中的文本框
	private Button  lp_sure_but,lp_cancle_but;//确定 取消按钮

	private Button  xbButton;  //信标机标定
	private TextView xbBzView,xbZyView;//本振 增益
	private LinearLayout xblLayout;//信标的布局
	private EditText   ed_xb_BZ, ed_xb_ZY;//布局中的文本框
	private Button  xb_sure_but,xb_cancle_but;//确定取消按钮

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//加载布局文件
		View view = inflater.inflate(R.layout.xtcs, container ,false );

		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		/**
		 * 天线标定
		 */
		txButton=(Button)getActivity().findViewById(R.id.button_Xt_tianxian);
		/**
		 * 方位  俯仰  极化
		 */
		txFwView=(TextView)getActivity().findViewById(R.id.tc_Xt_fangwei);
		txFyView=(TextView)getActivity().findViewById(R.id.tc_Xt_fuyan);
		txJView=(TextView)getActivity().findViewById(R.id.tc_Xt_jihua);
		/**
		 * 天线的布局
		 */
		txlLayout=(LinearLayout)getActivity().findViewById(R.id.layout_tianxian);
		/**
		 * 天线的布局中的文本框
		 */
		ed_tx_FW=(EditText)getActivity().findViewById(R.id.tv_XT_tianxian_fw);
		ed_tx_FY=(EditText)getActivity().findViewById(R.id.tv_XT_tianxian_fy);
		ed_tx_JH=(EditText)getActivity().findViewById(R.id.tv_XT_tianxian_jh);
		/**
		 * 天线的布局中的确定 输入按钮
		 */
		tx_sure_but=(Button)getActivity().findViewById(R.id.tianxian_sure_but);
		tx_cancle_but=(Button)getActivity().findViewById(R.id.tianxian_cancle_but);
		tx_sure_but.setOnClickListener(this);
		tx_cancle_but.setOnClickListener(this);
		/**
		 * 罗盘标定
		 */
		lpButton=(Button)getActivity().findViewById(R.id.button_Xt_luopan);
		/**
		 *  航向 俯仰  横滚
		 */
		lpHxView=(TextView)getActivity().findViewById(R.id.tc_Xt_luopan_hangxiang);
		lpFyView=(TextView)getActivity().findViewById(R.id.tc_Xt_luopan_fuyang);
		lpHgView=(TextView)getActivity().findViewById(R.id.tc_Xt_luopan_henggun);
		/**
		 * 罗盘的布局
		 */
		lplLayout=(LinearLayout)getActivity().findViewById(R.id.layout_luopan);
		/**
		 * 罗盘的布局中的文本框
		 */
		ed_lp_HX=(EditText)getActivity().findViewById(R.id.tv_XT_luopan_hx);
		ed_lp_FY=(EditText)getActivity().findViewById(R.id.tv_XT_luopan_fy);
		ed_lp_HG=(EditText)getActivity().findViewById(R.id.tv_XT_luopan_hg);
		/**
		 * 罗盘的布局中的确定 取消按钮
		 */
		lp_sure_but=(Button)getActivity().findViewById(R.id.luopan_sure_but);
		lp_cancle_but=(Button)getActivity().findViewById(R.id.luopan_cancle_cancle);
		lp_sure_but.setOnClickListener(this);
		lp_cancle_but.setOnClickListener(this);
		/**
		 * 信标标定
		 */
		xbButton=(Button)getActivity().findViewById(R.id.button_Xt_xinbiao);
		/**
		 * 本震 增益
		 */
		xbBzView=(TextView)getActivity().findViewById(R.id.tc_Xt_xinbiao_benzhen);
		xbZyView=(TextView)getActivity().findViewById(R.id.tc_Xt_xinbiao_zengyi);
		/**
		 * 信标的布局
		 */
		xblLayout=(LinearLayout)getActivity().findViewById(R.id.layout_xinbiao);
		/**
		 * 信标标中的文本
		 */
		ed_xb_BZ=(EditText)getActivity().findViewById(R.id.tv_XT_xinbiao_bz);
		ed_xb_ZY=(EditText)getActivity().findViewById(R.id.tv_XT_xinbiao_zy);
		/**
		 * 信标中的确定 取消按钮
		 */
		xb_sure_but=(Button)getActivity().findViewById(R.id.xinbiao_sure_but);
		xb_cancle_but=(Button)getActivity().findViewById(R.id.xinbiao_cancle_but);

		xb_sure_but.setOnClickListener(this);
		xb_cancle_but.setOnClickListener(this);
		/**
		 * 天线标定设定 按钮   	罗盘标定设定按钮   信标标定设定按钮
		 */
		txButton.setOnClickListener(this);//天线标定设定 按钮
		lpButton.setOnClickListener(this);//罗盘标定设定按钮
		xbButton.setOnClickListener(this);//信标标定设定按钮
	}
	//天线标定，罗盘标定，信标标定，按钮点击出现对话框
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_Xt_tianxian:
			txlLayout.setVisibility(View.VISIBLE);
			break;
		case R.id.button_Xt_luopan:
			lplLayout.setVisibility(View.VISIBLE);
			break;
		case R.id.button_Xt_xinbiao:
			xblLayout.setVisibility(View.VISIBLE);
			break;
			/**
			 * 天线设定
			 */
		case R.id.tianxian_sure_but:
			txFwView.setText(ed_tx_FW.getText().toString());  // ed_tx_FW, ed_tx_FY, ed_tx_JH;
			txFyView.setText(ed_tx_FY.getText().toString()); 
			txJView.setText(ed_tx_JH.getText().toString()); 
			txlLayout.setVisibility(View.GONE);

			break;
		case R.id.tianxian_cancle_but:
			txlLayout.setVisibility(View.GONE);
			break;
			/**
			 * 罗盘标定
			 */
		case R.id.luopan_sure_but:
			lpHxView.setText(ed_lp_HX.getText().toString());    // ed_lp_HX, ed_lp_FY, ed_lp_HG;
			lpFyView.setText(ed_lp_FY.getText().toString()); 
			lpHgView.setText(ed_lp_HG.getText().toString()); 
			lplLayout.setVisibility(View.GONE);
			break;
		case R.id.luopan_cancle_cancle:
			lplLayout.setVisibility(View.GONE);
			break;
			/**
			 * 信标机设定
			 */
		case R.id.xinbiao_sure_but:
			xbBzView.setText(ed_xb_BZ.getText().toString());      // ed_xb_BZ, ed_xb_ZY;
			xbZyView.setText(ed_xb_ZY.getText().toString());  
			xblLayout.setVisibility(View.GONE);
			break;
		case R.id.xinbiao_cancle_but:
			xblLayout.setVisibility(View.GONE);
			break;
		}
	}

}
