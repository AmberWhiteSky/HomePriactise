package com.zhongduan.allui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.allui.R;
import com.zhongduan.sqlite.Satellite;
import com.zhongduan.sqlite.SatelliteService;

public class Two_TwoFragment extends Fragment {
	//	SharedPreferences sp;
	//	final String FLAG = "Satellite";
	/**
	 * 
	 * etName etLongitude etPolarization etBeacon etCarrier etSign
	 * 
	 */

	private RadioGroup jsjGroup; // 接收机单选
	private RadioButton chooseRadioButton; // 取消 确认

	private Button chooseSatBut;
	SatelliteService satelliteService;
	Satellite satellite;
	private TextView tv_name, tv_Longitude, tv_Polarization, tv_Beacon,
	tv_Carrier, tv_Sign;

	private String strId;

	public Two_TwoFragment(String strId) {
		this.strId = strId;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.star, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		/**
		 * 接收机的单选
		 */
		jsjGroup = (RadioGroup) getActivity().findViewById(R.id.RadioGroup_canshu_jieshouji);
		/**
		 * 选择卫星
		 */
		chooseSatBut = (Button) getActivity().findViewById(R.id.button_canshu_choose);
		/**
		 * 显示卫星选择数据
		 */
		tv_name = (TextView) getActivity().findViewById(R.id.tv_canshu_name);
		tv_Longitude = (TextView) getActivity().findViewById(R.id.tv_canshu_jingdu);
		tv_Polarization = (TextView) getActivity().findViewById(R.id.tv_canshu_jihua);
		tv_Beacon = (TextView) getActivity().findViewById(R.id.tv_canshu_xinbiao);
		tv_Carrier = (TextView) getActivity().findViewById(R.id.tv_canshu_zaibo);
		tv_Sign = (TextView) getActivity().findViewById(R.id.tv_canshu_fuhao);

		chooseSatBut.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
								Intent intent = new Intent(getActivity(), ChooseSatellite.class);
								startActivity(intent);
								getActivity().finish();

			}
		});
		

	}

	/**
	 * 单选按钮的获得
	 */
	@SuppressWarnings("unused")
	private String getChooseRadioButton() {
		RadioButton chooseRadioButton = (RadioButton) getActivity()
				.findViewById(jsjGroup.getCheckedRadioButtonId());
		return chooseRadioButton.getText().toString();

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setValue();
	}

	

	private void setValue() {
		if (!TextUtils.isEmpty(strId)) {
			final String str_id = strId;
			System.out.println("string==" + str_id);
			int sate_id = Integer.valueOf(str_id);
			satelliteService = new SatelliteService(getActivity().getBaseContext());
			satellite = new Satellite();
			satellite = satelliteService.find(sate_id);
			String name = satellite.getSatellite_Name();
			System.out.println("Two_TwoFragment中选中的卫星名字是name=" + name);

			tv_name.setText(satellite.getSatellite_Name());
			tv_Longitude.setText(satellite.getSatellite_Longitude());
			tv_Polarization.setText(satellite.getSatellite_Polarization());
			tv_Beacon.setText(satellite.getSatellite_Beacon());
			tv_Carrier.setText(satellite.getSatellite_Carrier());
			tv_Sign.setText(satellite.getSatellite_Sign());


		} else {
			tv_name.setText(null);
			tv_Longitude.setText(null);
			tv_Polarization.setText(null);
			tv_Beacon.setText(null);
			tv_Carrier.setText(null);
			tv_Sign.setText(null);
		}
	}

}
