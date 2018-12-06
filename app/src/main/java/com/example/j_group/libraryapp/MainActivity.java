package com.example.j_group.libraryapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.security.auth.callback.Callback;

public class MainActivity extends AppCompatActivity {

    private Activity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.calender);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    /*  AsyncJsonLoader asyncJsonLoader = new AsyncJsonLoader(thisActivity);
        asyncJsonLoader.setListener(createListener());
        asyncJsonLoader.execute();                 */
    /* 使用するActivity内で記述することで利用可能 */
    private AsyncJsonLoader.Listener createListener(){
        return new AsyncJsonLoader.Listener() {
            @Override
            public void onSuccess(JSONArray jsonArray) {

            }
        };
    }

}
