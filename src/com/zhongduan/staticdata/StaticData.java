package com.zhongduan.staticdata;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;


public class StaticData {
	/**
	 * 19��
	 */
	
	public static String readInfo;
	public static String outInfo;
	public static String ipAddress;
	public static Socket  clientSocket;
	public static int post ;
	
	
	/**
	 * Socket������
	 */
	public static Socket socket;
	public static PrintWriter out_writer = null;   //�����
	public static BufferedReader in_reader = null; //������
	/**
	 * (���ܵ�����)���ǲ�����ȫ�ֱ���
	 */
	public static String app_agc_String;  //ȫ��AGC
	public static String app_sj_Fw,app_sj_Fy,app_sj_Jh;//ʵ�ʷ�λ ���� ����
	public static String app_gps_state,app_jsj_state;//ȫ�ֵ�GPS״̬  ���ջ�״̬
	public static String app_lp_state,app_tx_state;//����״̬ ����״̬

	/**
	 * (���ݿ����ǵĲ���)���ǲ�������
	 */
	public static int    app_id;
	public static String app_nameString;//��������
	public static String app_JdString;//���Ǿ���
	public static String app_jhString;//���Ǽ���
	public static String app_xbString;//�����ű�
	public static String app_zaiboString;//�����ز�
	public static String  app_fuhaoString;//���Ƿ���
	
	/**
	 * ����������Ĳ���  �����߻��
	 */
	public static String  app_tx_jdString;//���������� ���ߵľ���
	public static String  app_tx_wdString;//���������� ���ߵ�γ��
//	/**
//	 * ����������Ĳ���  ���ֻ����������
//	 */
	public static String  app_sc_jdString;//���������� �ֻ���õľ���
	public static String  app_sc_wdString;//���������� �ֻ���õ�γ��
//	
//	/**
//	 * ����������Ĳ��� �û�����
//	 */
	public static String  app_yh_jdString;//���������� �û�����ľ���
	public static String  app_yh_wdString;;//���������� �û������γ��
//	/**
//	 * ϵͳ�Ĳ��� ���� �궨������
//	 */
	public static String app_sys_tx_FwString;//ϵͳ�Ĳ��� ���� �궨�ķ�λ��
	public static String app_sys_tx_FyString;//ϵͳ�Ĳ��� ���� �궨�ĸ�����
	public static String app_sys_tx_JhString;//ϵͳ�Ĳ��� ���� �궨�ļ�����
//	/**
//	 * ϵͳ���� �����趨����
//	 */
//
	public static  String app_sys_lp_HxString;// ϵͳ���� �����趨���ݺ���
	public static String app_sys_lp_FyString;// ϵͳ���� �����趨���ݸ���
	public static String app_sys_lp_HgString;// ϵͳ���� �����趨���ݺ��
//	/**
//	 * ϵͳ���� �ű���趨����
//	 */
	public static String app_sys_xb_BzString;//ϵͳ���� �ű���趨���ݱ���
	public static String app_sys_xb_ZyString;//ϵͳ���� �ű���趨��������
	
	
	
	
	
	
	public static int     static_id;
	public static  String  static_str_id;
	public static String static_name;
	public static String static_jingdu;
	public static String  static_jihua;
	public static String   static_xinbiao;
	public static String   static_zaibo;
	public static String static_fuhao;
}
