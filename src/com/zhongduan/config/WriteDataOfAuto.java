package com.zhongduan.config;


public class WriteDataOfAuto {

/**
 * ��������
 * @param ip
 * @param post
 * @param str
 */
	public static void requestConnect(final String ip,final int post,final String str){
		new Thread(new Runnable(){          
			@Override
			public void run(){
				try{
					Util.outprint_auto.print(str);
					Util.outprint_auto.flush();
					System.out.println("�����߳�����==="+str);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}).start();	  
	}
}
