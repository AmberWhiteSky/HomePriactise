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


	private Button txButton;//���߱궨
	private TextView txFwView,txFyView,txJView;//��λ���� ����
	private LinearLayout txlLayout;  //���ߵĲ���
	private EditText   ed_tx_FW, ed_tx_FY, ed_tx_JH;//�����е��ı���
	private Button  tx_sure_but,tx_cancle_but; //ȷ�� ȡ����ť

	private Button lpButton;  //���̱궨
	private TextView lpHxView,lpFyView,lpHgView;//���� ���� ���
	private LinearLayout lplLayout;//���̵Ĳ���
	private EditText   ed_lp_HX, ed_lp_FY, ed_lp_HG;//�����е��ı���
	private Button  lp_sure_but,lp_cancle_but;//ȷ�� ȡ����ť

	private Button  xbButton;  //�ű���궨
	private TextView xbBzView,xbZyView;//���� ����
	private LinearLayout xblLayout;//�ű�Ĳ���
	private EditText   ed_xb_BZ, ed_xb_ZY;//�����е��ı���
	private Button  xb_sure_but,xb_cancle_but;//ȷ��ȡ����ť

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//���ز����ļ�
		View view = inflater.inflate(R.layout.xtcs, container ,false );

		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		/**
		 * ���߱궨
		 */
		txButton=(Button)getActivity().findViewById(R.id.button_Xt_tianxian);
		/**
		 * ��λ  ����  ����
		 */
		txFwView=(TextView)getActivity().findViewById(R.id.tc_Xt_fangwei);
		txFyView=(TextView)getActivity().findViewById(R.id.tc_Xt_fuyan);
		txJView=(TextView)getActivity().findViewById(R.id.tc_Xt_jihua);
		/**
		 * ���ߵĲ���
		 */
		txlLayout=(LinearLayout)getActivity().findViewById(R.id.layout_tianxian);
		/**
		 * ���ߵĲ����е��ı���
		 */
		ed_tx_FW=(EditText)getActivity().findViewById(R.id.tv_XT_tianxian_fw);
		ed_tx_FY=(EditText)getActivity().findViewById(R.id.tv_XT_tianxian_fy);
		ed_tx_JH=(EditText)getActivity().findViewById(R.id.tv_XT_tianxian_jh);
		/**
		 * ���ߵĲ����е�ȷ�� ���밴ť
		 */
		tx_sure_but=(Button)getActivity().findViewById(R.id.tianxian_sure_but);
		tx_cancle_but=(Button)getActivity().findViewById(R.id.tianxian_cancle_but);
		tx_sure_but.setOnClickListener(this);
		tx_cancle_but.setOnClickListener(this);
		/**
		 * ���̱궨
		 */
		lpButton=(Button)getActivity().findViewById(R.id.button_Xt_luopan);
		/**
		 *  ���� ����  ���
		 */
		lpHxView=(TextView)getActivity().findViewById(R.id.tc_Xt_luopan_hangxiang);
		lpFyView=(TextView)getActivity().findViewById(R.id.tc_Xt_luopan_fuyang);
		lpHgView=(TextView)getActivity().findViewById(R.id.tc_Xt_luopan_henggun);
		/**
		 * ���̵Ĳ���
		 */
		lplLayout=(LinearLayout)getActivity().findViewById(R.id.layout_luopan);
		/**
		 * ���̵Ĳ����е��ı���
		 */
		ed_lp_HX=(EditText)getActivity().findViewById(R.id.tv_XT_luopan_hx);
		ed_lp_FY=(EditText)getActivity().findViewById(R.id.tv_XT_luopan_fy);
		ed_lp_HG=(EditText)getActivity().findViewById(R.id.tv_XT_luopan_hg);
		/**
		 * ���̵Ĳ����е�ȷ�� ȡ����ť
		 */
		lp_sure_but=(Button)getActivity().findViewById(R.id.luopan_sure_but);
		lp_cancle_but=(Button)getActivity().findViewById(R.id.luopan_cancle_cancle);
		lp_sure_but.setOnClickListener(this);
		lp_cancle_but.setOnClickListener(this);
		/**
		 * �ű�궨
		 */
		xbButton=(Button)getActivity().findViewById(R.id.button_Xt_xinbiao);
		/**
		 * ���� ����
		 */
		xbBzView=(TextView)getActivity().findViewById(R.id.tc_Xt_xinbiao_benzhen);
		xbZyView=(TextView)getActivity().findViewById(R.id.tc_Xt_xinbiao_zengyi);
		/**
		 * �ű�Ĳ���
		 */
		xblLayout=(LinearLayout)getActivity().findViewById(R.id.layout_xinbiao);
		/**
		 * �ű���е��ı�
		 */
		ed_xb_BZ=(EditText)getActivity().findViewById(R.id.tv_XT_xinbiao_bz);
		ed_xb_ZY=(EditText)getActivity().findViewById(R.id.tv_XT_xinbiao_zy);
		/**
		 * �ű��е�ȷ�� ȡ����ť
		 */
		xb_sure_but=(Button)getActivity().findViewById(R.id.xinbiao_sure_but);
		xb_cancle_but=(Button)getActivity().findViewById(R.id.xinbiao_cancle_but);

		xb_sure_but.setOnClickListener(this);
		xb_cancle_but.setOnClickListener(this);
		/**
		 * ���߱궨�趨 ��ť   	���̱궨�趨��ť   �ű�궨�趨��ť
		 */
		txButton.setOnClickListener(this);//���߱궨�趨 ��ť
		lpButton.setOnClickListener(this);//���̱궨�趨��ť
		xbButton.setOnClickListener(this);//�ű�궨�趨��ť
	}
	//���߱궨�����̱궨���ű�궨����ť������ֶԻ���
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
			 * �����趨
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
			 * ���̱궨
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
			 * �ű���趨
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
