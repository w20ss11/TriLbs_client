package com.chongyou.main;

import com.chongyou.mapUtil.Write2txt;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MapActivity extends Activity{
	
	private ImageView iv;
	private TextView res;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		//获取布局文件中的容器
        iv=(ImageView) findViewById(R.id.iv);
        iv.setBackgroundResource(R.drawable.map);
        
        res=(TextView) findViewById(R.id.res);
        res.setText("haha");
        String reString=Write2txt.getLastLine("res.txt");
        res.setText(reString);
	}
}
