package com.zhongduan.staticdata;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;


public class StaticData {
	/**
	 * 19号
	 */
	
	public static String readInfo;
	public static String outInfo;
	public static String ipAddress;
	public static Socket  clientSocket;
	public static int post ;
	
	
	/**
	 * Socket的数据
	 */
	public static Socket socket;
	public static PrintWriter out_writer = null;   //输出流
	public static BufferedReader in_reader = null; //输入流
	/**
	 * (接受的数据)卫星参数的全局变量
	 */
	public static String app_agc_String;  //全局AGC
	public static String app_sj_Fw,app_sj_Fy,app_sj_Jh;//实际方位 俯仰 极化
	public static String app_gps_state,app_jsj_state;//全局的GPS状态  接收机状态
	public static String app_lp_state,app_tx_state;//罗盘状态 天线状态

	/**
	 * (数据库卫星的参数)卫星参数界面
	 */
	public static int    app_id;
	public static String app_nameString;//卫星名字
	public static String app_JdString;//卫星经度
	public static String app_jhString;//卫星极化
	public static String app_xbString;//卫星信标
	public static String app_zaiboString;//卫星载波
	public static String  app_fuhaoString;//卫星符号
	
	/**
	 * 传感器界面的参数  从天线获得
	 */
	public static String  app_tx_jdString;//传感器界面 天线的经度
	public static String  app_tx_wdString;//传感器界面 天线的纬度
//	/**
//	 * 传感器界面的参数  从手机传感器获得
//	 */
	public static String  app_sc_jdString;//传感器界面 手机获得的经度
	public static String  app_sc_wdString;//传感器界面 手机获得的纬度
//	
//	/**
//	 * 传感器界面的参数 用户输入
//	 */
	public static String  app_yh_jdString;//传感器界面 用户输入的经度
	public static String  app_yh_wdString;;//传感器界面 用户输入的纬度
//	/**
//	 * 系统的参数 天线 标定的数据
//	 */
	public static String app_sys_tx_FwString;//系统的参数 天线 标定的方位角
	public static String app_sys_tx_FyString;//系统的参数 天线 标定的俯仰角
	public static String app_sys_tx_JhString;//系统的参数 天线 标定的极化角
//	/**
//	 * 系统参数 罗盘设定数据
//	 */
//
	public static  String app_sys_lp_HxString;// 系统参数 罗盘设定数据航向
	public static String app_sys_lp_FyString;// 系统参数 罗盘设定数据俯仰
	public static String app_sys_lp_HgString;// 系统参数 罗盘设定数据横滚
//	/**
//	 * 系统参数 信标机设定数据
//	 */
	public static String app_sys_xb_BzString;//系统参数 信标机设定数据本振
	public static String app_sys_xb_ZyString;//系统参数 信标机设定数据增益
	
	
	
	
	
	
	public static int     static_id;
	public static  String  static_str_id;
	public static String static_name;
	public static String static_jingdu;
	public static String  static_jihua;
	public static String   static_xinbiao;
	public static String   static_zaibo;
	public static String static_fuhao;
}
