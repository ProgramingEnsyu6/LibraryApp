package com.example.j_group.libraryapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchScreen extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ListView listView;

    private SimpleAdapter adapter;

    private Activity thisActivity;

    private String params;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SearchView search = findViewById(R.id.searchView);

        listView = findViewById(R.id.listView);
        findViewById(R.id.listView).setVisibility(View.INVISIBLE);

        search.setIconifiedByDefault(false);
        search.setSubmitButtonEnabled(true);

        // SearchViewに何も入力していない時のテキストを設定
        search.setQueryHint("検索文字を入力して下さい。");

        // SearchViewにOnQueryTextListenerを設定
        search.setOnQueryTextListener(this);

        ((RadioButton)findViewById(R.id.radioButton1)).setChecked(true);

        // リスト項目が選択されたときのイベントを追加
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /*
             * @param parent ListView
             * @param view 選択した項目
             * @param position 選択した項目の添え字
             * @param id 選択した項目のID
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*
                 * 画面遷移する際に情報を次の画面に渡す
                 * LISTを押したときに画面遷移を行うところに記述する
                 *
                 * LISTに登録されている、本に関するデータをinfoArrayに追加する
                 * タイトル、著者、発行日、ジャンル、C_codeの順に追加してほしい
                 *
                 * */
                // 本の情報を追加する処理
                // ArrayList<String> infoArray = new ArrayList();
                // infoArray.add("");
                // infoArray.add("著者");
                // infoArray.add("発行日");
                // infoArray.add("ジャンル");
                // infoArray.add("C_code");
                // 遷移先のactivityを指定してintentを作成
                //Intent intent = new Intent( this, ScreenResult.class );
                // intentへ添え字付で値を保持させる
                // intent.putExtra( "info", infoArray );
                // 指定のActivityを開始する
                // startActivity( intent );
            }
        });
    }


    /*
    * 使用するActivity内で記述することで利用可能
    * この関数内でAPIの検索結果を受け取ることができる。
    *  */
    private AsyncJsonLoader.Listener createListener(){
        return new AsyncJsonLoader.Listener() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                // 検索結果がJSONArrayで返される
                // ここにJSONArrayをString(文字列)にして
                // ListViewに書き込む処理を記述する

                final List<Map<String, String>> book = new ArrayList<>();
                try {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Map<String, String> map = new HashMap<>();
                        if (jsonObject.has("title")) {
                            if (!jsonObject.isNull("title")) {
                                String st = jsonObject.getString("title");
                                // stをListViewに追加する
                                map.put("title", st);
                            }

                        }
                        if (jsonObject.has("author")) {
                            if (!jsonObject.isNull("author")) {
                                String st = jsonObject.getString("author");
                                // stをListViewに追加する
                                map.put("author", st);
                            }

                        }
                        if (jsonObject.has("genre")) {
                            if (!jsonObject.isNull("genre")) {
                                String st = jsonObject.getString("genre");
                                // stをListViewに追加する
                                map.put("title", st);
                            }

                        }
                        if (jsonObject.has("release_data")) {
                            if (!jsonObject.isNull("release_data")) {
                                String st = jsonObject.getString("release_data");
                                // stをListViewに追加する
                                map.put("release_data", st);
                            }

                        }
                        if (jsonObject.has("c_code")) {
                            if (!jsonObject.isNull("c_code")) {
                                String st = jsonObject.getString("c_code");
                                // stをListViewに追加する
                                map.put("c_code", st);
                            }

                        }
                        book.add(map);
                    }
                    adapter = new SimpleAdapter(getApplicationContext(), book, R.layout.search_adapter,
                            new String[]{"title", "author", "genre"}, new int[]{R.id.textView1, R.id.textView2, R.id.textView3});
                    listView.setAdapter(adapter);
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        };
    }

    public boolean onQueryTextChange(String queryText) {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String queryText) {
        /*
         * 以下のコメントを外すとAPI参照用のコードが使える
         * このコードは、検索ボタンが押されたときに呼び出すようにすること。
         * 検索ボタンが押されたときにする処理のところに移動をお願いします。
         * */
        /*
         * paramsは、検索用のパラメータ
         *
         * 全部検索 : ""
         * タイトル検索 : "?title=「タイトル名」"
         * ジャンル検索 : "?genre=「ジャンル名」"
         * 著者検索 : "?author=「著者名」"
         *
         * を検索条件に合わせて代入する
         * */

        RadioGroup group = findViewById(R.id.radioGroup);
        int checkedId = group.getCheckedRadioButtonId();

        if (TextUtils.isEmpty(queryText)) {
            findViewById(R.id.listView).setVisibility(View.INVISIBLE);
        } else {
            if (checkedId != -1) {
                if (checkedId == R.id.radioButton1) {
                    //通常検索
                    params = "";
                    thisActivity = this;
                    AsyncJsonLoader asyncJsonLoader = new AsyncJsonLoader(thisActivity);
                    asyncJsonLoader.setListener(createListener());
                    asyncJsonLoader.execute(params);
                } else if (checkedId == R.id.radioButton2) {
                    //タイトル検索
                    params = "?title= queryText";
                    thisActivity = this;
                    AsyncJsonLoader asyncJsonLoader = new AsyncJsonLoader(thisActivity);
                    asyncJsonLoader.setListener(createListener());
                    asyncJsonLoader.execute(params);
                } else if (checkedId == R.id.radioButton3) {
                    //著者検索
                    params = "?author= queryText";
                    thisActivity = this;
                    AsyncJsonLoader asyncJsonLoader = new AsyncJsonLoader(thisActivity);
                    asyncJsonLoader.setListener(createListener());
                    asyncJsonLoader.execute(params);
                } else if (checkedId == R.id.radioButton4) {
                    //ジャンル検索
                    params = "?genre= queryText";
                    thisActivity = this;
                    AsyncJsonLoader asyncJsonLoader = new AsyncJsonLoader(thisActivity);
                    asyncJsonLoader.setListener(createListener());
                    asyncJsonLoader.execute(params);
                }
                findViewById(R.id.listView).setVisibility(View.VISIBLE);
            }
        }
        return true;
    }
}

