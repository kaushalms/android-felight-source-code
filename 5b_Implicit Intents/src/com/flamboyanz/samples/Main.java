package com.flamboyanz.samples;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
        // Get a reference to the image view in main screen
        ImageView iv = (ImageView) findViewById(R.id.imageView1);
        Uri uri = (Uri) getIntent().getExtras().get(Intent.EXTRA_STREAM);
        iv.setImageURI(uri);
    }
}