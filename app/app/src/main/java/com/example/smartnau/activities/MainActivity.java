package com.example.smartnau.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.smartnau.R;
import com.example.smartnau.databinding.ActivityChatBinding;
import com.example.smartnau.model.MqttClient;
import com.google.android.material.snackbar.Snackbar;

import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    private static final String TOPIC = "Room/Temperature";
    private static final String TAG = MainActivity.class.getSimpleName();
    private AppBarConfiguration appBarConfiguration;
    private ActivityChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_chat);

        binding.layoutSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Message sent", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.layoutSend)
                        .setAction("Action", null).show();
            }
        });

        MqttClient mqtt = new MqttClient();
        mqtt.connectToBroker("my-mqtt-client-id", "broker.hivemq.com", 1883,
                "my-user", "my-password");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Log.e(TAG, "Thread " + Thread.currentThread().getName() + " was interrupted: " +
                    e.getMessage());
        }
        mqtt.subscribe(TOPIC, (message) -> {
            try {
                String convertedMessageContent = new String(message.getPayloadAsBytes(),
                        StandardCharsets.UTF_8);
                Log.d(TAG, String.format("Message received from topic '%s': '%s'%n",
                        message.getTopic(),
                        convertedMessageContent));
                // TODO: Insert functions to work with received data
            } catch (Exception e) {
                Log.e(TAG, String.format(TAG, "Message from %s: %s",
                        message.getTopic(),
                        message.getPayloadAsBytes()));
            }
        });
        mqtt.publish(TOPIC, "Hallo Welt!");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Log.e(TAG, "Thread " + Thread.currentThread().getName() + " was interrupted: " +
                    e.getMessage());
        }
        mqtt.unsubscribe(TOPIC);
        mqtt.disconnect();
    }

    private void receiveData(String topic, String message) {
        Log.d(TAG, String.format("Received message '%s' from topic '%s'", message, topic));
        try {
            // TODO: Insert data type to be used to convert from String
        } catch (Exception e) {
            // TODO: Specify Exception to be appropriate to the format exception.
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}