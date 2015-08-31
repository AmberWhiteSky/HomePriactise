package com.zhongduan.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SatelliteDataBseHelper  extends SQLiteOpenHelper {
	
	/**
	 * {@link Satellite���ǵĲ���}
	private Integer  satellite_Id;
	private String satellite_Name;  //��������
	private String satellite_Longitude;//���Ǿ���
	private String satellite_Polarization;//������ʽ
	private String satellite_Beacon;//�ű�Ƶ��
	private String satellite_Carrier;//�ز�
	private String satellite_Sign;//����Ƶ��
	 */
	
	
	
	
	private  static  final  String  database_Name="star.db";  //���ݿ������
	private  static  final  int  database_Version = 1;     	//���ݿ�İ汾
	private  String  satellite_Sql="create table satelliteParameter_table (satellite_Id  integer  primary key autoincrement,satellite_Name varchar(30),satellite_Longitude vcarchar(30),satellite_Polarization vcarchar(30),satellite_Beacon vcarchar(30),satellite_Carrier vcarchar(30),satellite_Sign varchar(30))";
	
	

	public SatelliteDataBseHelper(Context context) {         //���췽��
		super(context,database_Name,null,database_Version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase sdb) {   //�������ݿ�
		// TODO Auto-generated method stub
		sdb.execSQL(satellite_Sql);   //�������ݿ�
	}

	@Override
	public void onUpgrade(SQLiteDatabase sdb, int arg1, int arg2) {  //�汾����
		// TODO Auto-generated method stub
		sdb.execSQL("DROP TABLE IF EXISTS satellite_Sql ");
		onCreate(sdb);
	}

}
