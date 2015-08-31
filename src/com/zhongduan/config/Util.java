package com.zhongduan.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.zhongduan.allui.BaseActivity;
import com.zhongduan.allui.First_FragmentActivity;
import com.zhongduan.staticdata.StaticData;

public class Util {
	/**
	 * �Ƿ�����������־λ
	 */
	public static boolean isOnline = false;
	public static boolean isInnerView = false;

	/**
	 * �Զ�ģʽSocket
	 */
	public static Socket clientsocket_auto = null;

	/**
	 * ������
	 */
	public static BufferedReader inreader_auto = null;
	/**
	 * �����
	 */
	public static PrintWriter outprint_auto = null;

	/**
	 * ���ӷ�����
	 */
	public static void connServer(){
		try {
			new Thread() {
				public void run() {
					try {
						Util.clientsocket_auto = new Socket(StaticData.ipAddress,StaticData.post);
						/**
						 *  ��ȡ������
						 */
						Util.inreader_auto = new BufferedReader(new InputStreamReader(Util.clientsocket_auto.getInputStream()));
						/**
						 * ��ȡ�����
						 */
						Util.outprint_auto = new PrintWriter(Util.clientsocket_auto.getOutputStream());
						Util.isOnline = true;

						if(null != First_FragmentActivity.first_OneFragment){
							First_FragmentActivity.first_OneFragment.setHandlerLinsener();
						}
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final List<BaseActivity> listActivity = new ArrayList<BaseActivity>();
	public static void addActivity(BaseActivity activity) {
		listActivity.add(activity);
	}

	/**
	 * finish���õ�Activity
	 */
	public static void finishAllActivity() {
		for (BaseActivity activity : listActivity) {
			if (!activity.isFinishing()) {
				activity.finish();
			}
		}
	}

	/**
	 * �˳���ǰ����
	 */
	public static void exit(Context context) {
		/**
		 * �ر�socket
		 */
		closeSocket();
		/**
		 * finish���еĽ���
		 */
		finishAllActivity();
		System.exit(0);
		/**
		 * ɱ������
		 */
		android.os.Process.killProcess(android.os.Process.myPid());
		/**
		 * ɱ�����̵���һ�ַ�ʽ
		 */
		//			ActivityManager activityMgr = (ActivityManager) context
		//					.getSystemService(context.ACTIVITY_SERVICE);
		//			activityMgr.restartPackage(context.getPackageName());
	}
	/**
	 * �ر�socket  ������  �����
	 */
	public static void closeSocket(){
		try {
			if(null != inreader_auto){
				inreader_auto.close();
			}
			if(null != outprint_auto){
				outprint_auto.flush();
				outprint_auto.close();
			}
			if(null != clientsocket_auto){
				clientsocket_auto.close();
			}
		} catch (IOException e) {
			//				e.printStackTrace();
			System.out.println("e:"+e.getMessage());
		}
	}
}
