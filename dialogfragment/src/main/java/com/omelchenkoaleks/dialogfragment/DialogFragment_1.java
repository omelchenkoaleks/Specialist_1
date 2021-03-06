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

public class DialogFragment_1 extends DialogFragment implements BrowserCall {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Search Engine")
                .setItems(MainActivity.ENGINE_NAMES, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Activity activity = getActivity();
                        if (activity instanceof BrowserCall) {
                            ((BrowserCall) activity).callBrowser(which);
                        } else {
                            Toast.makeText(activity, "Sorry ... )))",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return builder.create();
    }

    @Override
    public void callBrowser(int index) { }
}
