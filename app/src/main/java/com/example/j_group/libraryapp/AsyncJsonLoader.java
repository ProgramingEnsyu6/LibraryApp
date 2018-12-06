package com.example.j_group.libraryapp;

import android.app.Activity;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

public class AsyncJsonLoader extends AsyncTask<String, Void, JSONArray> {
    private Activity mainActivity;
    private Listener listener;

    public AsyncJsonLoader(Activity activity){
        this.mainActivity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected  JSONArray doInBackground(String... strings) {
        HttpsURLConnection con = null;
        URL url  = null;
        String urlSt = "https://afternoon-ridge-19723.herokuapp.com/api/v1/books";

        try{
            // URLを作成
            url = new URL(urlSt + strings[0]);
            // 接続用オブジェクトを作成
            con = (HttpsURLConnection)url.openConnection();
            // リクエストメッセージの設定
            con.setRequestMethod("GET");
            // URL接続からデータを読み取る許可
            con.setDoInput(true);
            // 接続の開始
            con.connect();

            int status = con.getResponseCode();

            if(status == HttpURLConnection.HTTP_OK) {

                InputStream in = con.getInputStream();
                String readSt = readInputStream(in);

                JSONArray jsonArray = new JSONArray(readSt);

                return jsonArray;
            }
        }catch (JSONException e){
            e.printStackTrace();
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    public String readInputStream(InputStream in) throws IOException, UnsupportedEncodingException{
        StringBuffer sb = new StringBuffer();
        String st = "";

        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        while((st = br.readLine()) != null){
            sb.append(st);
        }

        try{
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return sb.toString();
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray){
        super.onPostExecute(jsonArray);

        if(listener != null){
            listener.onSuccess(jsonArray);
        }
    }

    void setListener(Listener listener) {
        this.listener = listener;
    }

    interface Listener {
        void onSuccess(JSONArray jsonArray);
    }
}

