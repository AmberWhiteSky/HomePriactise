package com.zhongduan.appliction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import android.R.integer;
import android.app.Application;

public class HaMaApplication extends Application {
	/**
	 * Socket的数据
	 */
	public static Socket socket;
	public static PrintWriter out_writer = null; // 输出流
	public static BufferedReader in_reader = null; // 输入流
	/**
	 * (接受的数据)卫星参数的全局变量
	 */

	public static String app_agc_String; // 全局AGC
	public static String app_sj_Fw, app_sj_Fy, app_sj_Jh;// 实际方位 俯仰 极化
	public static String app_gps_state, app_jsj_state;// 全局的GPS状态 接收机状态
	public static String app_lp_state, app_tx_state;// 罗盘状态 天线状态

	/**
	 * (数据库卫星的参数)卫星参数界面
	 */
	public static int    app_satllite_id;
	public static String app_nameString;// 卫星名字
	public static String app_JdString;// 卫星经度
	public static String app_jhString;// 卫星极化
	public static String app_xbString;// 卫星信标
	public static String app_zaiboString;// 卫星载波
	public static String app_fuhaoString;// 卫星符号

	/**
	 * 传感器界面的参数 从天线获得
	 */
	public static String app_tx_jdString;// 传感器界面 天线的经度
	public static String app_tx_wdString;// 传感器界面 天线的纬度
	/**
	 * 传感器界面的参数 从手机传感器获得
	 */
	public static String app_sc_jdString;// 传感器界面 手机获得的经度
	public static String app_sc_wdString;// 传感器界面 手机获得的纬度

	/**
	 * 传感器界面的参数 用户输入
	 */
	public static String app_yh_jdString;// 传感器界面 用户输入的经度
	public static String app_yh_wdString;;// 传感器界面 用户输入的纬度
	/**
	 * 系统的参数 天线 标定的数据
	 */
	public static String app_sys_tx_FwString;// 系统的参数 天线 标定的方位角
	public static String app_sys_tx_FyString;// 系统的参数 天线 标定的俯仰角
	public static String app_sys_tx_JhString;// 系统的参数 天线 标定的极化角
	/**
	 * 系统参数 罗盘设定数据
	 */

	public static String app_sys_lp_HxString;// 系统参数 罗盘设定数据航向
	public static String app_sys_lp_FyString;// 系统参数 罗盘设定数据俯仰
	public static String app_sys_lp_HgString;// 系统参数 罗盘设定数据横滚
	/**
	 * 系统参数 信标机设定数据
	 */
	public static String app_sys_xb_BzString;// 系统参数 信标机设定数据本振
	public static String app_sys_xb_ZyString;// 系统参数 信标机设定数据增益

	/**
	 * 
	 * 
	 * 
	 *
	 *
	 */
	public void initSocket(String ip, int post) throws IOException, Exception {
		this.socket = new Socket();
		this.out_writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		this.in_reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public PrintWriter getOut_writer() {
		return out_writer;
	}

	public void setOut_writer(PrintWriter out_writer) {
		this.out_writer = out_writer;
	}

	public BufferedReader getIn_reader() {
		return in_reader;
	}

	public void setIn_reader(BufferedReader in_reader) {
		this.in_reader = in_reader;
	}

	
	
	public static String getApp_agc_String() {
		return app_agc_String;
	}

	public static void setApp_agc_String(String app_agc_String) {
		HaMaApplication.app_agc_String = app_agc_String;
	}

	public static String getApp_sj_Fw() {
		return app_sj_Fw;
	}

	public static void setApp_sj_Fw(String app_sj_Fw) {
		HaMaApplication.app_sj_Fw = app_sj_Fw;
	}

	public static String getApp_sj_Fy() {
		return app_sj_Fy;
	}

	public static void setApp_sj_Fy(String app_sj_Fy) {
		HaMaApplication.app_sj_Fy = app_sj_Fy;
	}

	public static String getApp_sj_Jh() {
		return app_sj_Jh;
	}

	public static void setApp_sj_Jh(String app_sj_Jh) {
		HaMaApplication.app_sj_Jh = app_sj_Jh;
	}

	public static String getApp_gps_state() {
		return app_gps_state;
	}

	public static void setApp_gps_state(String app_gps_state) {
		HaMaApplication.app_gps_state = app_gps_state;
	}

	public static String getApp_jsj_state() {
		return app_jsj_state;
	}

	public static void setApp_jsj_state(String app_jsj_state) {
		HaMaApplication.app_jsj_state = app_jsj_state;
	}

	public static String getApp_lp_state() {
		return app_lp_state;
	}

	public static void setApp_lp_state(String app_lp_state) {
		HaMaApplication.app_lp_state = app_lp_state;
	}

	public static String getApp_tx_state() {
		return app_tx_state;
	}

	public static void setApp_tx_state(String app_tx_state) {
		HaMaApplication.app_tx_state = app_tx_state;
	}

	public static String getApp_nameString() {
		return app_nameString;
	}

	public static void setApp_nameString(String app_nameString) {
		HaMaApplication.app_nameString = app_nameString;
	}

	public static String getApp_JdString() {
		return app_JdString;
	}

	public static void setApp_JdString(String app_JdString) {
		HaMaApplication.app_JdString = app_JdString;
	}

	public static String getApp_jhString() {
		return app_jhString;
	}

	public static void setApp_jhString(String app_jhString) {
		HaMaApplication.app_jhString = app_jhString;
	}

	public static String getApp_xbString() {
		return app_xbString;
	}

	public static void setApp_xbString(String app_xbString) {
		HaMaApplication.app_xbString = app_xbString;
	}

	public static String getApp_zaiboString() {
		return app_zaiboString;
	}

	public static void setApp_zaiboString(String app_zaiboString) {
		HaMaApplication.app_zaiboString = app_zaiboString;
	}

	public static String getApp_fuhaoString() {
		return app_fuhaoString;
	}

	public static void setApp_fuhaoString(String app_fuhaoString) {
		HaMaApplication.app_fuhaoString = app_fuhaoString;
	}

	public static String getApp_tx_jdString() {
		return app_tx_jdString;
	}

	public static void setApp_tx_jdString(String app_tx_jdString) {
		HaMaApplication.app_tx_jdString = app_tx_jdString;
	}

	public static String getApp_tx_wdString() {
		return app_tx_wdString;
	}

	public static void setApp_tx_wdString(String app_tx_wdString) {
		HaMaApplication.app_tx_wdString = app_tx_wdString;
	}

	public static String getApp_sc_jdString() {
		return app_sc_jdString;
	}

	public static void setApp_sc_jdString(String app_sc_jdString) {
		HaMaApplication.app_sc_jdString = app_sc_jdString;
	}

	public static String getApp_sc_wdString() {
		return app_sc_wdString;
	}

	public static void setApp_sc_wdString(String app_sc_wdString) {
		HaMaApplication.app_sc_wdString = app_sc_wdString;
	}

	public static String getApp_yh_jdString() {
		return app_yh_jdString;
	}

	public static void setApp_yh_jdString(String app_yh_jdString) {
		HaMaApplication.app_yh_jdString = app_yh_jdString;
	}

	public static String getApp_yh_wdString() {
		return app_yh_wdString;
	}

	public static void setApp_yh_wdString(String app_yh_wdString) {
		HaMaApplication.app_yh_wdString = app_yh_wdString;
	}

	public static String getApp_sys_tx_FwString() {
		return app_sys_tx_FwString;
	}

	public static void setApp_sys_tx_FwString(String app_sys_tx_FwString) {
		HaMaApplication.app_sys_tx_FwString = app_sys_tx_FwString;
	}

	public static String getApp_sys_tx_FyString() {
		return app_sys_tx_FyString;
	}

	public static void setApp_sys_tx_FyString(String app_sys_tx_FyString) {
		HaMaApplication.app_sys_tx_FyString = app_sys_tx_FyString;
	}

	public static String getApp_sys_tx_JhString() {
		return app_sys_tx_JhString;
	}

	public static void setApp_sys_tx_JhString(String app_sys_tx_JhString) {
		HaMaApplication.app_sys_tx_JhString = app_sys_tx_JhString;
	}

	public static String getApp_sys_lp_HxString() {
		return app_sys_lp_HxString;
	}

	public static void setApp_sys_lp_HxString(String app_sys_lp_HxString) {
		HaMaApplication.app_sys_lp_HxString = app_sys_lp_HxString;
	}

	public static String getApp_sys_lp_FyString() {
		return app_sys_lp_FyString;
	}

	public static void setApp_sys_lp_FyString(String app_sys_lp_FyString) {
		HaMaApplication.app_sys_lp_FyString = app_sys_lp_FyString;
	}

	public static String getApp_sys_lp_HgString() {
		return app_sys_lp_HgString;
	}

	public static void setApp_sys_lp_HgString(String app_sys_lp_HgString) {
		HaMaApplication.app_sys_lp_HgString = app_sys_lp_HgString;
	}

	public static String getApp_sys_xb_BzString() {
		return app_sys_xb_BzString;
	}

	public static void setApp_sys_xb_BzString(String app_sys_xb_BzString) {
		HaMaApplication.app_sys_xb_BzString = app_sys_xb_BzString;
	}

	public static String getApp_sys_xb_ZyString() {
		return app_sys_xb_ZyString;
	}

	public static void setApp_sys_xb_ZyString(String app_sys_xb_ZyString) {
		HaMaApplication.app_sys_xb_ZyString = app_sys_xb_ZyString;
	}

	/**
	 * (non-Javadoc)
	 * @see android.app.Application#onCreate()
	 * 初始化变量
	 */   
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

	}
}
