package com.example.smartnau.views;

import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartnau.R;
import com.example.smartnau.databinding.ActivityChatBinding;
import com.example.smartnau.model.BaseMessage;
import com.example.smartnau.model.ChatMessage;
import com.example.smartnau.model.UserMessage;
import com.example.smartnau.viewmodels.MessageListAdapter;

import java.util.List;


public class ChatActivity extends AppCompatActivity {
    private List<ChatMessage> chatMessages;
    private ActivityChatBinding binding;
    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;
    private List<BaseMessage> mMessageList = List.of(new BaseMessage());
    // TODO: Build Chat function with mocked data (with Char. Limit)
    // TODO: Char. Limit: Check String length and feed it to UI element
    // TODO: https://www.youtube.com/watch?v=pAzby-pyStM




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mMessageRecycler = (RecyclerView) findViewById(R.id.chatBackground);
        mMessageAdapter = new MessageListAdapter(this, mMessageList);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecycler.setAdapter(mMessageAdapter);
    }

    private void createMessage(String messageText) {
        UserMessage message = new UserMessage();
        message.setMessage(messageText);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            message.setCreatedAt(System.currentTimeMillis());
        }
    }
    private void sendMessage(BaseMessage message) {
        mMessageList.add(message);
    }





}
