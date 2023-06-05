package com.example.smartnau.utilities;

import android.util.Log;

import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;
import com.hivemq.client.mqtt.mqtt3.message.publish.Mqtt3Publish;
import com.hivemq.client.mqtt.mqtt3.message.unsubscribe.Mqtt3Unsubscribe;

import java.util.function.Consumer;

public class MqttClient {
    private static final String TAG = MqttClient.class.getSimpleName();
    private Mqtt3AsyncClient client;

    public void connectToBroker(String identifier, String host, int port, String username, String password) {
        client = com.hivemq.client.mqtt.MqttClient.builder().useMqttVersion3().identifier(identifier).serverHost(host)
                                                  .serverPort(port).buildAsync();

        client.connectWith().simpleAuth().username(username).password(password.getBytes()).applySimpleAuth().send()
              .whenComplete((connAck, throwable) -> {
                  if (throwable != null) {
                      Log.e(TAG, "Connection failed: " + throwable.getMessage());
                  } else {
                      Log.d(TAG, String.format("Connected to '%s:%d'", host, port));
                  }
              });
    }

    public void subscribe(String topic, Consumer<Mqtt3Publish> consumer) {
        client.subscribeWith().topicFilter(topic)
              //.callback(publish -> receiveData(publish.getTopic().toString(), new String(publish.getPayloadAsBytes())))
              .callback(consumer).send().whenComplete((subAck, throwable) -> {
                  if (throwable != null) {
                      Log.e(TAG, "Subscribing failed: " + throwable.getMessage());
                  } else {
                      Log.d(TAG, String.format("Subscribed to '%s'", topic));
                  }
              });

    }

    public void publish(String topic, String message) {
        client.publishWith().topic(topic).payload(message.getBytes()).send().whenComplete((publish, throwable) -> {
            if (throwable != null) {
                Log.e(TAG, "Publishing failed: " + throwable.getMessage());
            }
        });
    }

    public void logStream(){
    }


    public void unsubscribe(String topic) {
        client.unsubscribe(Mqtt3Unsubscribe.builder().topicFilter(topic).build());
        Log.d(TAG, String.format("Unsubscribed from '%s'", topic));
    }

    public void disconnect() {
        String serverHost = client.getConfig().getServerHost();
        client.disconnect();
        Log.d(TAG, String.format("Disconnected from '%s'", serverHost));
    }
}