package com.example.smartnau.views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartnau.model.ChatMessage;
import com.example.smartnau.R;

import java.util.List;


public class ChatActivity extends AppCompatActivity {
    private List<ChatMessage> chatMessages;
    @Override
    // TODO: Build Chat function with mocked data (with Char. Limit)
    // TODO: Char. Limit: Check String length and feed it to UI element
    // TODO: https://www.youtube.com/watch?v=pAzby-pyStM
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);
    }



}
