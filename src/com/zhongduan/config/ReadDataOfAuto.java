package com.zhongduan.config;

import android.os.Handler;
import android.os.Message;

public class ReadDataOfAuto {
	/**
	 * ������
	 */
	static char[] buffer = new char[256];
	/**
	 * ��ȡ���ֽ���
	 */
	int count = 0;
	/**
	 * �������ݵ�handler
	 */
	Handler handler;
	/**
	 * ��ȡ���ݵ��߳�
	 */
	private  Thread thread=null;
	/**
	 * ���췽�� ����handler
	 * @param handler
	 */
	public ReadDataOfAuto(Handler handler){
		this.handler = handler;
	}
    /**
     * 
     * @author Administrator
     *�ص��ӿ� ��·�ж����»ָ�֮���������
     */
	public static interface CallBack{
		
		void isException(boolean isException);
	}

	/**
	 * �����߳�
	 * @param ip
	 * @param post
	 */
	public  void  ReadData(final String ip, final int post, final CallBack callBack){
		thread=new Thread(new  Runnable() {
			@Override
			public void run() {
				try {
					while (buffer.length > 0) {
						try {
							if ((count = Util.inreader_auto.read(buffer)) > 0) {
								/**
								 * �߳�����100
								 */
								Thread.sleep(100);
								getInfoBuff(buffer, count);// ��Ϣ����
							}
							/**
							 * �ص��ӿ�
							 */
							callBack.isException(true);
						}catch (Exception e) {
							e.printStackTrace();
							/**
							 * �ص��ӿ�
							 */
							callBack.isException(false);
							/**
							 * �����쳣 ���ܽ�������
							 */
							Util.isOnline = false;
							handler.obtainMessage(100).sendToTarget();
							System.out.println("��ȡ�̳߳����쳣��");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});  
		thread.start();	
	}

	/**
	 * ��ȡ����
	 * @param buff
	 * @param count
	 */
	private void getInfoBuff(char[] buff, int count) {
		char[] temp = new char[count];
		for (int i = 0; i < count; i++) {
			temp[i] = buff[i];
		}	
		System.out.println("��ȡ�̵߳�����==" + new String(temp));
		Message mes = new Message();
		mes.obj = new String(temp)+"\n";
		mes.what = 1;
		/**
		 *  ��handler�����ݷ��ͳ�ȥ
		 */
		handler.sendMessage(mes);
	}
}
