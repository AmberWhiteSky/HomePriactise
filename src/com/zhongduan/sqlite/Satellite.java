package com.zhongduan.sqlite;

public class Satellite {
	private Integer  satellite_Id;
	private String satellite_Name;  //��������
	private String satellite_Longitude;//���Ǿ���
	private String satellite_Polarization;//������ʽ
	private String satellite_Beacon;//�ű�Ƶ��
	private String satellite_Carrier;//�ز�
	private String satellite_Sign;//����Ƶ��

	public Satellite() {     //�޲������췽��
		// TODO Auto-generated constructor stub
	}
	public	Satellite(String name,String longitude,String polarization,String beacon,String carrier,String sign){
		this.satellite_Name=name;
		this.satellite_Longitude=longitude;
		this.satellite_Polarization=polarization;
		this.satellite_Beacon=beacon;
		this.satellite_Carrier=carrier;
		this.satellite_Sign=sign;
	}
	public Integer getSatellite_Id() {
		return satellite_Id;
	}
	public void setSatellite_Id(Integer satellite_Id) {
		this.satellite_Id = satellite_Id;
	}
	public String getSatellite_Name() {
		return satellite_Name;
	}
	public void setSatellite_Name(String satellite_Name) {
		this.satellite_Name = satellite_Name;
	}
	public String getSatellite_Longitude() {
		return satellite_Longitude;
	}
	public void setSatellite_Longitude(String satellite_Longitude) {
		this.satellite_Longitude = satellite_Longitude;
	}
	public String getSatellite_Polarization() {
		return satellite_Polarization;
	}
	public void setSatellite_Polarization(String satellite_Polarization) {
		this.satellite_Polarization = satellite_Polarization;
	}
	public String getSatellite_Beacon() {
		return satellite_Beacon;
	}
	public void setSatellite_Beacon(String satellite_Beacon) {
		this.satellite_Beacon = satellite_Beacon;
	}
	public String getSatellite_Carrier() {
		return satellite_Carrier;
	}
	public void setSatellite_Carrier(String satellite_Carrier) {
		this.satellite_Carrier = satellite_Carrier;
	}
	public String getSatellite_Sign() {
		return satellite_Sign;
	}
	public void setSatellite_Sign(String satellite_Sign) {
		this.satellite_Sign = satellite_Sign;
	}

}
