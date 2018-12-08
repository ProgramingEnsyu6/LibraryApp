package com.example.j_group.libraryapp;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SearchResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Button map_button = findViewById(R.id.map_button);
        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment;
                dialogFragment = new Dialog_bookmap();
                FragmentManager manager = getSupportFragmentManager();
                dialogFragment.show(manager, "dialog");
            }
        });

        /*
        * 以下のコメントを外すと前の画面から本の情報を受け取れる
        *  infoArrayのindexは、
        * 0: タイトル
        * 1: 著者
        * 2: 製造日
        * 3: ジャンル
        * 4: C_code
        *  に応じた情報が入っている
        * */

        // 現在のintentを取得する
        //Intent intent = getIntent();
        // intentから指定キーの文字列を取得する
        //ArrayList<String> infoArray = intent.getStringArrayListExtra("info");

        /*
        * TextViewへの文字列の動的代入
        * 下に例を書いとくんで他の情報追加もこんな感じでお願いします
        * */
        // TextView title = findViewById(R.id.title);
        // title.setText(infoArray[0);
    }


}