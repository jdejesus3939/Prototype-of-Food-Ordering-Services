package com.jproject.foodorderingapps;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.jproject.foodorderingapps.library.HashAlgorithm;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MyActivity extends Activity {

	String word, cipher, plain;
	
	TextView tvWord, tvCipher, tvPlain;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myactivity);
		
		tvWord = (TextView) findViewById(R.id.word);
		tvCipher = (TextView) findViewById(R.id.cipher);
		tvPlain = (TextView) findViewById(R.id.plain);
		
		HashAlgorithm hash = new HashAlgorithm();
		word = "Hello, How are you? I'm fine, thanks.";
		tvWord.setText("Word is: "+ word);
		
		try {
			cipher = hash.SHA1(word);
			//plain = hash.sha1Hash(word);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tvCipher.setText(cipher);
		Log.e("cipher", cipher);
		
		//tvPlain.setText(plain);
		//Log.e("new cipher",plain);
		
		if(cipher.equals(cipher))
		{
			Log.e("cipher=cipher", "yes, cipher=cipher");
		}
		
		/*
		if(cipher.equals(plain))
		{
			Log.e("cipher=plain", "yes, cipher=plain");
		}
		
		if(plain.equals(plain))
		{
			Log.e("plain=plain", "yes, plain=plain");
		}
		*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
