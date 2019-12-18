package com.example.products.Dialogs;

import android.app.Activity;
import android.app.Dialog;

import androidx.lifecycle.ViewModelProviders;

import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.products.R;

public class ViewDialogAbout {

    public void showDialog(final Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.about);

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_submit);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog.dismiss();

            }
        });
        dialog.show();

    }
}



