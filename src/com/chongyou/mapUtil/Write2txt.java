package com.chongyou.mapUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.RandomAccessFile;

import android.annotation.SuppressLint;
import android.util.Log;

public class Write2txt {
	@SuppressLint("SdCardPath")
	private static final String filePath="/sdcard/Test/";
	public Write2txt() {
	}

	public static void deleteTxt(){
		File file=new File(filePath);
		if(file.exists()){
			File[] files=file.listFiles();//获取文件列表
			for(int i=0;i<files.length;i++)
			{
			   if(!files[i].isFile()) continue;//如果不是文件就跳过（排除文件夹等）
			   String fileName=files[i].getName();
			   if(fileName.endsWith(".txt")) files[i].delete();//后缀名为txt就删除
			}
		}
	}

	// 将字符串写入到文本文件中
	public void writeTxtToFile(String strcontent, String fileName) {
		//生成文件夹之后，再生成文件，不然会出错
		makeFilePath(filePath, fileName);

		String strFilePath = filePath+fileName;
		// 每次写入时，都换行写
		String strContent = strcontent + "\r\n";
		try {
			File file = new File(strFilePath);
			if (!file.exists()) {
				Log.d("TestFile", "Create the file:" + strFilePath);
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			RandomAccessFile raf = new RandomAccessFile(file, "rwd");
			raf.seek(file.length());
			raf.write(strContent.getBytes());
			raf.close();
		} catch (Exception e) {
			Log.e("TestFile", "Error on write File:" + e);
		}
	}

	// 生成文件
	public File makeFilePath(String filePath, String fileName) {
		File file = null;
		makeRootDirectory(filePath);
		try {
			file = new File(filePath + fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

	// 生成文件夹
	public static void makeRootDirectory(String filePath) {
		File file = null;
		try {
			file = new File(filePath);
			if (!file.exists()) {
				file.mkdir();
			}
		} catch (Exception e) {
			Log.i("error:", e+"");
		}
	}
	public static String getLastLine(String filename){
		String string2= null;  
		try {  
		    FileReader fr = new FileReader(filePath+filename);  
		    BufferedReader br = new BufferedReader(fr);  
		    String str = null;  
		    while ((str = br.readLine()) != null) {  
		        string2= str;  
		    }  
		    br.close();  
		    fr.close();  
		} catch (Exception e) {  
		    e.printStackTrace();  
		}  
		System.out.println(string2);  
		return string2;
	}
}
