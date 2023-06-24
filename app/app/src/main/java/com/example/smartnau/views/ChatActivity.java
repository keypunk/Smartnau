package com.example.smartnau.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartnau.R;
import com.example.smartnau.databinding.ActivityChatBinding;
import com.example.smartnau.viewmodels.ChatViewModel;

import pl.droidsonroids.gif.GifImageView;


public class ChatActivity extends AppCompatActivity {
    private ActivityChatBinding binding;
    private RecyclerView mMessageRecycler;
    private ChatAdapter mMessageAdapter;


    // TODO: Build Chat function with mocked data (with Char. Limit)
    // TODO: Char. Limit: Check String length and feed it to UI element





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ChatViewModel viewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mMessageRecycler = (RecyclerView) findViewById(R.id.itemList);
        mMessageAdapter = new ChatAdapter(this, viewModel.getMessageList());
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecycler.setAdapter(mMessageAdapter);
        viewModel.greetUser();


        final Button button = findViewById(R.id.button2);
        EditText text_send = findViewById(R.id.inputMessage);
        GifImageView gifImageView = new GifImageView(this);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String message = text_send.getText().toString();
                    viewModel.sendMessage(message);
                    text_send.setText("");
                    gifImageView.setImageResource(R.drawable.smiling_bot);
                    mMessageAdapter.notifyItemInserted(viewModel.getMessageList().size());
                    mMessageRecycler.smoothScrollToPosition(viewModel.getMessageList().size());
                } catch (Exception e) {
                    Toast.makeText(ChatActivity.this, "You can't send empty message", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }










}
