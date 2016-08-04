package com.helloio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.helloio.db.HelloDatabaseUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HelloDatabaseUtil.copyDataBaseToPhone(this);
        HelloDatabaseUtil.testCopyResult();
        String a = new String();
        a.concat("as");

    }
}
