package com.example.smartnau.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartnau.R;
import com.example.smartnau.databinding.ActivityChatBinding;
import com.example.smartnau.model.BaseMessage;
import com.example.smartnau.model.ChatMessage;
import com.example.smartnau.model.UserMessage;
import com.example.smartnau.viewmodels.ChatAdapter;

import java.util.List;


public class ChatActivity extends AppCompatActivity {
    private List<ChatMessage> chatMessages;
    private ActivityChatBinding binding;
    private RecyclerView mMessageRecycler;
    private ChatAdapter mMessageAdapter;
    private List<BaseMessage> mMessageList = List.of(new UserMessage());
    // TODO: Build Chat function with mocked data (with Char. Limit)
    // TODO: Char. Limit: Check String length and feed it to UI element





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mMessageRecycler = (RecyclerView) findViewById(R.id.chatBackground);
        mMessageAdapter = new ChatAdapter(this, mMessageList);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecycler.setAdapter(mMessageAdapter);
        final Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
    }

    private void sendMessage() {
        UserMessage message = new UserMessage();
        message.setMessage("findViewById(R.id.inputMessage).");
        message.setCreatedAt(System.currentTimeMillis());
        mMessageList.add(message);
    }








}