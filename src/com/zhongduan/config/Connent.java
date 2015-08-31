package com.zhongduan.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import android.os.Handler;
import android.os.Message;

public  class Connent {
	static Thread cilentthread = null;
	static Socket clientsocket = null;
	public static BufferedReader inreader = null;
	public static PrintWriter outprint = null;
	Handler handler;

	/**
	 * 构造方法 传递handler
	 * @param handler
	 */
	//	public Connent(Handler handler){
	//		this.handler = handler;
	//	}
	/**
	 * 链接
	 * @param ip
	 * @param post
	 */
	public void requestConnect(final String ip,final int post){
		new Thread(new Runnable(){          
			@Override
			public void run(){
				try{
					clientsocket = new Socket(ip, post);
					// 获取输入流
					inreader = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));			
					// 获取输出流
					outprint = new PrintWriter(clientsocket.getOutputStream());	
					System.out.println("登陆界面连接成功");
				}catch(Exception e){			
					e.printStackTrace();
					System.out.println("登陆界面连接失败");
				}
			}
		}).start();	  
	}
}
