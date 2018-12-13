package com.example.j_group.libraryapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchScreen extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ListView list;

    private String[] array_adapter_data = {"Apple", "Bike", "Cupcake",
            "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "1", "2", "3", "4"};

    private Activity thisActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SearchView search = findViewById(R.id.searchView1);

        list = findViewById(R.id.listView1);
        findViewById(R.id.listView1).setVisibility(View.INVISIBLE);

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array_adapter_data);
        list.setAdapter(adapter);
        list.setTextFilterEnabled(true);

        search.setIconifiedByDefault(false);

        // SearchViewのSubmitボタンを使用不可にする
        search.setSubmitButtonEnabled(true);

        // SearchViewに何も入力していない時のテキストを設定
        search.setQueryHint("検索文字を入力して下さい。");

        // SearchViewにOnQueryTextListenerを設定
        search.setOnQueryTextListener(this);


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
        // params = ""
        thisActivity = this;
        AsyncJsonLoader asyncJsonLoader = new AsyncJsonLoader(thisActivity);
        // asyncJsonLoader.setListener(createListener());
        // asyncJsonLoader.execute();

        /*
        * 画面遷移する際に情報を次の画面に渡す
        * LISTを押したときに画面遷移を行うところに記述する
        *
        * LISTに登録されている、本に関するデータをinfoArrayに追加する
        * タイトル、著者、発行日、ジャンル、C_codeの順に追加してほしい
        *
        * */
        // 本の情報を追加する処理
        // AraryList<String> infoArray = new ArrayList<String>();
        // infoArray.add("タイトル");
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

                /*
                 * サンプル：
                try {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (jsonObject.has("title")) {
                            if (!jsonObject.isNull("title")) {
                                String st = jsonObject.getString("title");
                                // stをListViewに追加する
                            }
                        }
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }*/

            }
        };
    }

    public boolean onQueryTextChange(String queryText) {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String queryText) {
        if (TextUtils.isEmpty(queryText)) {
            list.clearTextFilter();
        } else {
            list.setFilterText(queryText);
            findViewById(R.id.listView1).setVisibility(View.VISIBLE);
        }
        return true;
    }
}

