package com.zhongduan.config;

import android.os.Handler;
import android.os.Message;

public class ReadDataOfAuto {
	/**
	 * 缓冲区
	 */
	static char[] buffer = new char[256];
	/**
	 * 读取的字节数
	 */
	int count = 0;
	/**
	 * 发送数据的handler
	 */
	Handler handler;
	/**
	 * 读取数据的线程
	 */
	private  Thread thread=null;
	/**
	 * 构造方法 传递handler
	 * @param handler
	 */
	public ReadDataOfAuto(Handler handler){
		this.handler = handler;
	}
    /**
     * 
     * @author Administrator
     *回调接口 网路中断重新恢复之后接收数据
     */
	public static interface CallBack{
		
		void isException(boolean isException);
	}

	/**
	 * 开启线程
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
								 * 线程休眠100
								 */
								Thread.sleep(100);
								getInfoBuff(buffer, count);// 消息换行
							}
							/**
							 * 回调接口
							 */
							callBack.isException(true);
						}catch (Exception e) {
							e.printStackTrace();
							/**
							 * 回调接口
							 */
							callBack.isException(false);
							/**
							 * 出现异常 不能接收数据
							 */
							Util.isOnline = false;
							handler.obtainMessage(100).sendToTarget();
							System.out.println("读取线程出现异常！");
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
	 * 读取数据
	 * @param buff
	 * @param count
	 */
	private void getInfoBuff(char[] buff, int count) {
		char[] temp = new char[count];
		for (int i = 0; i < count; i++) {
			temp[i] = buff[i];
		}	
		System.out.println("读取线程的数据==" + new String(temp));
		Message mes = new Message();
		mes.obj = new String(temp)+"\n";
		mes.what = 1;
		/**
		 *  用handler吧数据发送出去
		 */
		handler.sendMessage(mes);
	}
}
