package com.omelchenkoaleks.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogSample extends DialogFragment {
    public static final CharSequence[] WEB = {"Google",
            "Port", "Portal",};

    public DialogSample() {
        super();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose web ...")
                .setItems(WEB, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                                Uri address = Uri.parse(Constants.GOOGLE);
                                Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, address);
                                startActivity(openLinkIntent);
                            } else if (which == 1) {
                                Uri address = Uri.parse(Constants.PORT);
                                Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, address);
                                startActivity(openLinkIntent);
                            } else if (which == 2) {
                                Uri address = Uri.parse(Constants.PORTAL);
                                Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, address);
                                startActivity(openLinkIntent);
                            } else {
                               Log.d("Intent", "NO INTENT");
                            }
                        }
                });
        return builder.create();
    }
}
