package com.example.j_group.libraryapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

public class MainActivity extends AppCompatActivity {

    private Activity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.search_library);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SearchScreen.class);
                startActivity(intent);
            }
        });

        Button button1 = findViewById(R.id.calender);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 本の情報を追加する処理
                ArrayList<String> infoArray = new ArrayList<String>();
                infoArray.add("タイトル");
                infoArray.add("著者");
                infoArray.add("発行日");
                infoArray.add("ジャンル");
                infoArray.add("C_code");
                // 遷移先のactivityを指定してintentを作成
                Intent intent = new Intent( getApplication(), SearchResult.class );
                // intentへ添え字付で値を保持させる
                intent.putExtra( "info", infoArray );
                // 指定のActivityを開始する
                startActivity( intent );
            }
        });
    }

}

