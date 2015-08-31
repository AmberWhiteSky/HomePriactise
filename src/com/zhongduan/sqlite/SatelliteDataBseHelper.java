package com.zhongduan.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SatelliteDataBseHelper  extends SQLiteOpenHelper {
	
	/**
	 * {@link Satellite卫星的参数}
	private Integer  satellite_Id;
	private String satellite_Name;  //卫星名称
	private String satellite_Longitude;//卫星经度
	private String satellite_Polarization;//极化方式
	private String satellite_Beacon;//信标频率
	private String satellite_Carrier;//载波
	private String satellite_Sign;//符号频率
	 */
	
	
	
	
	private  static  final  String  database_Name="star.db";  //数据库的名称
	private  static  final  int  database_Version = 1;     	//数据库的版本
	private  String  satellite_Sql="create table satelliteParameter_table (satellite_Id  integer  primary key autoincrement,satellite_Name varchar(30),satellite_Longitude vcarchar(30),satellite_Polarization vcarchar(30),satellite_Beacon vcarchar(30),satellite_Carrier vcarchar(30),satellite_Sign varchar(30))";
	
	

	public SatelliteDataBseHelper(Context context) {         //构造方法
		super(context,database_Name,null,database_Version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase sdb) {   //创建数据库
		// TODO Auto-generated method stub
		sdb.execSQL(satellite_Sql);   //创建数据库
	}

	@Override
	public void onUpgrade(SQLiteDatabase sdb, int arg1, int arg2) {  //版本更新
		// TODO Auto-generated method stub
		sdb.execSQL("DROP TABLE IF EXISTS satellite_Sql ");
		onCreate(sdb);
	}

}
