package com.chongyou.mapUtil;


import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * 客户端的线程
 */
public class ClientThread implements Runnable{
	private Socket s;
	// 定义向UI线程发送消息的Handler对象
	private String res;
	// 该线程所处理的Socket所对应的输入流
	BufferedReader br = null;
	OutputStream os = null;
	Write2txt write2txt=new Write2txt();

	public ClientThread(String str) {
		this.res = str;
		Log.i("tag", "构造方法"+res);
	}

	public void run() {
		try {
			Log.i("tag", "开始try方法");
			s = new Socket("192.168.253.7", 10046);
			//
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));//接收
			os = s.getOutputStream();//类似服务端printwriter 发送数据
			new Thread(){
				public void run(){
					String content=null;
					try{
						while((content=br.readLine())!=null){
							write2txt.writeTxtToFile(content,"res.txt");
						}
					}catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
			if (res != null) {
				try {
					os.write((res + "\r\n").getBytes("utf-8"));
					Log.i("tag", "执行发送");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (SocketTimeoutException e1) {
			System.out.println("网络连接超时！！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

