package com.flamboyanz.android.list;

import android.app.Activity;
//import android.view.Window;

import android.os.Bundle;

public class LayoutsActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //---hides the title bar---
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.relative);
    }
}