package com.chongyou.mapUtil;


import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * �ͻ��˵��߳�
 */
public class ClientThread implements Runnable{
	private Socket s;
	// ������UI�̷߳�����Ϣ��Handler����
	private String res;
	// ���߳��������Socket����Ӧ��������
	BufferedReader br = null;
	OutputStream os = null;
	Write2txt write2txt=new Write2txt();

	public ClientThread(String str) {
		this.res = str;
		Log.i("tag", "���췽��"+res);
	}

	public void run() {
		try {
			Log.i("tag", "��ʼtry����");
			s = new Socket("192.168.253.7", 10046);
			//
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));//����
			os = s.getOutputStream();//���Ʒ����printwriter ��������
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
					Log.i("tag", "ִ�з���");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (SocketTimeoutException e1) {
			System.out.println("�������ӳ�ʱ����");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

