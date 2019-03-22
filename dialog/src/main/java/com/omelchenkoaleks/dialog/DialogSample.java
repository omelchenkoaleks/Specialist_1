package com.omelchenkoaleks.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogSample extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose web ...")
                .setItems(MainActivity.ENGINE_NAME, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /**
                         * На передающей стороне создаем Интент с правильным action.
                         * Запихиваем нужные данные.
                         * Запускаем Broadcast...
                         */
                        Intent intent = new Intent(MainActivity.ACTION_ENGINE_SELECTED);
                        intent.putExtra(MainActivity.KEY_INDEX, which);
                        getActivity().sendBroadcast(intent);

                        // ЗАМЕТКА: с использование приемнки на не нужно проверять
                        // реализует ли активность наш интерфейс или нет = если
                        // есть приемник уже хорошо ...
                    }
                });
        return builder.create();
    }
}
