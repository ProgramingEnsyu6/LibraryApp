package com.example.j_group.libraryapp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.j_group.libraryapp.R;

//このタイプのダイアログ（カスタムダイアログ）を使うなら以下の準備をよろしく
//
//レイアウトファイル
//
//1.タイトル用のレイアウト（任意、無くてもいい）
//  LinearLayoutで「タイトル(textView)と、閉じるボタン、:heavy_multiplication_x:の画像だといいと思う（ImageButton）」を横ならべ
//
//2.メインのレイアウト
//  レイアウトなんでもいいからImageViewをドーンとおいとく
//
//上の二つをセットしてdialog.findViewById(～～)で使えるようにする、あとはいけるやろ
public class Dialog_bookmap extends DialogFragment {
    private AlertDialog dialog;
    private AlertDialog.Builder alert;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        alert = new AlertDialog.Builder(getActivity());
        assert getArguments() != null;

        //呼び出し元からデータを受け取るならgetArgumentを使う
        //例　final int change = getArguments().getInt(ARG_CHANGE);

        //ダイアログのレイアウト
        //例
        //View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_salse_change,null);
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_map, null);

        // ViewをAlertDialog.Builderに追加
        alert.setView(view);

        // Dialogを生成
        dialog = alert.create();

        //カスタムタイトルをつけるなら↓
        dialog.setCustomTitle(getActivity().getLayoutInflater().inflate(R.layout.dialog_title, null));

        //Viewの処理をしたいならshowより後に書きましょう、前に書くと落ちるよ！
        dialog.show();

        //TextView title = dialog.findViewById(R.id.dialog_title);
        //title.setText("会計完了");
        //初見の場合は多分これ？地図表示
        ImageView imageView = dialog.findViewById(R.id.dialog_image);

        //閉じるボタンをつけるなら、ボタンの設定↓
        ImageButton button = dialog.findViewById(R.id.title_close);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return dialog;
    }
}
