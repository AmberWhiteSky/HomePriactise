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
	 * ���췽�� ����handler
	 * @param handler
	 */
	//	public Connent(Handler handler){
	//		this.handler = handler;
	//	}
	/**
	 * ����
	 * @param ip
	 * @param post
	 */
	public void requestConnect(final String ip,final int post){
		new Thread(new Runnable(){          
			@Override
			public void run(){
				try{
					clientsocket = new Socket(ip, post);
					// ��ȡ������
					inreader = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));			
					// ��ȡ�����
					outprint = new PrintWriter(clientsocket.getOutputStream());	
					System.out.println("��½�������ӳɹ�");
				}catch(Exception e){			
					e.printStackTrace();
					System.out.println("��½��������ʧ��");
				}
			}
		}).start();	  
	}
}
