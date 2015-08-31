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
	 * 是否链接正常标志位
	 */
	public static boolean isOnline = false;
	public static boolean isInnerView = false;

	/**
	 * 自动模式Socket
	 */
	public static Socket clientsocket_auto = null;

	/**
	 * 输入流
	 */
	public static BufferedReader inreader_auto = null;
	/**
	 * 输出流
	 */
	public static PrintWriter outprint_auto = null;

	/**
	 * 链接服务器
	 */
	public static void connServer(){
		try {
			new Thread() {
				public void run() {
					try {
						Util.clientsocket_auto = new Socket(StaticData.ipAddress,StaticData.post);
						/**
						 *  获取输入流
						 */
						Util.inreader_auto = new BufferedReader(new InputStreamReader(Util.clientsocket_auto.getInputStream()));
						/**
						 * 获取输出流
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
	 * finish所用的Activity
	 */
	public static void finishAllActivity() {
		for (BaseActivity activity : listActivity) {
			if (!activity.isFinishing()) {
				activity.finish();
			}
		}
	}

	/**
	 * 退出当前程序
	 */
	public static void exit(Context context) {
		/**
		 * 关闭socket
		 */
		closeSocket();
		/**
		 * finish所有的界面
		 */
		finishAllActivity();
		System.exit(0);
		/**
		 * 杀死进程
		 */
		android.os.Process.killProcess(android.os.Process.myPid());
		/**
		 * 杀死进程的另一种方式
		 */
		//			ActivityManager activityMgr = (ActivityManager) context
		//					.getSystemService(context.ACTIVITY_SERVICE);
		//			activityMgr.restartPackage(context.getPackageName());
	}
	/**
	 * 关闭socket  输入流  输出流
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
