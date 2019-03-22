package com.omelchenkoaleks.dialogfragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogFragment_2 extends DialogFragment implements BrowserCall {
    private int mIndex;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Search Engine")
                .setSingleChoiceItems(MainActivity.ENGINE_NAMES, -1,
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mIndex = which;
                    }
                }).setPositiveButton("Select",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Activity activity = getActivity();
                        if (activity instanceof BrowserCall) {
                            ((BrowserCall) activity).callBrowser(mIndex);
                        } else {
                            Toast.makeText(activity, "Sorry ...", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("Not now", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }

    @Override
    public void callBrowser(int index) { }
}
