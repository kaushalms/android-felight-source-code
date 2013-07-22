package com.felight.android.intent;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends Activity {
    /** Called when the activity is first created. */
	 TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active2);
        String s=(String)getIntent().getStringExtra("key1");
        tv=(TextView)findViewById(R.id.txt);
        tv.setText(s);
    }
  
}