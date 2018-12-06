package com.example.j_group.libraryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

public class SearchScreen extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ListView list;

    private String[] array_adapter_data = {"Apple", "Bike", "Cupcake",
            "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "1", "2", "3", "4"};

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