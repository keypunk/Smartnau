package com.example.smartnau.model;

import android.util.Log;

import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;
import com.hivemq.client.mqtt.mqtt3.message.publish.Mqtt3Publish;
import com.hivemq.client.mqtt.mqtt3.message.unsubscribe.Mqtt3Unsubscribe;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.function.Consumer;

public class MqttClient {
    private static final String TAG = MqttClient.class.getSimpleName();
    private static final String BROKER_URL = "broker.hivemq.com";
    private static final int PORT = 1883;
    private static final String USER = "";
    private static final String PASSWORD = "";
    private static final String CLIENT_ID = UUID.randomUUID().toString();
    private static final String ROOM_BUTTONPRESS = "Room/Buttonpress";
    private static final String ROOM_MESSAGES = "Room/Messages";
    private static final String CLIENT_MESSAGES = "Client/Messages";
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

    public void connecting() {
        connectToBroker(CLIENT_ID, BROKER_URL, PORT, USER, PASSWORD);
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

    public void subscribeForMessages() {
        // Subscribe for messages
        subscribe(ROOM_MESSAGES, (message) -> {
            try {
                String convertedMessageContent = new String(message.getPayloadAsBytes()
                        , StandardCharsets.UTF_8);
                receiveMessages(message.getTopic().toString(), convertedMessageContent);
                Log.d(TAG, String.format("Message received from topic '%s':: '%s'%n"
                        , message.getTopic(),
                        convertedMessageContent));
            } catch (Exception e) {
                Log.e(TAG, String.format("Message from %s: %s", message.getTopic()
                        , message.getPayloadAsBytes()));
            }
        });
    }

    public void receiveMessages(String topic, String message) {
        Log.d(TAG, String.format("Received message '%s' from topic '%s'", topic, message));
        // TODO: Connect to MQTTViewModel so it can be updated using DataBinding
    }

    // TODO: Possible separation of 'Yes' and 'No' button presses
    public void subscribeForPresses() {
        // Subscribe for button presses
        subscribe(ROOM_BUTTONPRESS, (presses) -> {
            try {
                String convertedMessageContent = new String(presses.getPayloadAsBytes()
                        , StandardCharsets.UTF_8);
                receiveButtonpresses(presses.getTopic().toString(), convertedMessageContent);
                Log.d(TAG, String.format("Message received from topic '%s':: '%s'%n"
                        , presses.getTopic(),
                        convertedMessageContent));
            } catch (Exception e) {
                Log.e(TAG, String.format("Message from %s: %s", presses.getTopic()
                        , presses.getPayloadAsBytes()));
            }
        });
    }

    public void receiveButtonpresses(String topic, String pressesAsString) {
        Log.d(TAG, String.format("Received message '%s' from topic '%s'", topic, pressesAsString));
        // TODO: Connect to MQTTViewModel so it can be updated using DataBinding
        try {
            int pressesAsInteger = Integer.parseInt(pressesAsString);
        } catch (NumberFormatException e) {
        }
    }

    public void publish(String topic, String message) {
        client.publishWith().topic(topic).payload(message.getBytes()).send().whenComplete((publish, throwable) -> {
            if (throwable != null) {
                Log.e(TAG, "Publishing failed: " + throwable.getMessage());
            }
        });
    }

    public void publishMessages(String message) {
        publish(CLIENT_MESSAGES, message);
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