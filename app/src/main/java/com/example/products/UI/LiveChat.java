package com.example.products.UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.products.Model.ChatMessage;
import com.example.products.Model.ClientsModel;
import com.example.products.R;
import com.example.products.ViewModel.GetUserViewModel;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LiveChat extends AppCompatActivity {
    SharedPreferences mSharedPreferences;
    private FirebaseListAdapter<ChatMessage> adapter;
    @BindView(R.id.input)
    EditText input;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    String id_client;
    String user;
    String id_product;
    GetUserViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_chat);
        ButterKnife.bind(this);

        model = ViewModelProviders.of(this).get(GetUserViewModel.class);
        model.getResult(LiveChat.this, getIntent())
                .observe(this, new Observer<ClientsModel>() {
                    @Override
                    public void onChanged(@Nullable ClientsModel s) {
                        user = s.getUsername();
                        id_client = s.getId();
                        id_product = s.getPhone();
                        if (id_product != null) {
                            displayChatMessagespro();

                        } else {
                            displayChatMessages();
                        }
                    }


                });


        if (savedInstanceState != null) {
            input.setText(savedInstanceState.getString("edittext"));
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (id_product != null) {
                    FirebaseDatabase.getInstance().getReference().child("all").child(id_product + id_client).push().
                            setValue(new ChatMessage(input.getText().toString(), user));

                } else {
                    FirebaseDatabase.getInstance().getReference().child("all").child("company" + id_client).push().
                            setValue(new ChatMessage(input.getText().toString(), user));
                }
                input.setText("");
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("edittext", input.getText().toString());
    }

    private void displayChatMessages() {
        ListView listOfMessages = (ListView) findViewById(R.id.list_of_messages);

        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                R.layout.rowinfochatingofcourse, FirebaseDatabase.getInstance().getReference().child("all").child("company" + id_client)) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                TextView messageText = (TextView) v.findViewById(R.id.message_text);
                TextView messageUser = (TextView) v.findViewById(R.id.message_user);
                TextView messageTime = (TextView) v.findViewById(R.id.message_time);

                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };

        listOfMessages.setAdapter(adapter);
    }

    private void displayChatMessagespro() {
        ListView listOfMessages = (ListView) findViewById(R.id.list_of_messages);

        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                R.layout.rowinfochatingofcourse, FirebaseDatabase.getInstance().getReference().child("all").child(id_product + id_client)) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                TextView messageText = (TextView) v.findViewById(R.id.message_text);
                TextView messageUser = (TextView) v.findViewById(R.id.message_user);
                TextView messageTime = (TextView) v.findViewById(R.id.message_time);

                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };

        listOfMessages.setAdapter(adapter);
    }
}
