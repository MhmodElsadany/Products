package com.example.products.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.products.R;
import com.example.products.ViewModel.FeedbackViewModel;

public class ViewDialogFeedBack {

    public void showDialog(final Activity activity, String msg) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.feedback);

        final EditText text = (EditText) dialog.findViewById(R.id.txt_dia);
        text.setText(msg);

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_submit);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedbackViewModel model = ViewModelProviders.of((FragmentActivity) activity).get(FeedbackViewModel.class);
                model.getResult(activity, text.getText().toString()).observe((LifecycleOwner) activity, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                    }


                });


                dialog.dismiss();

            }
        });
        dialog.show();

    }
}

