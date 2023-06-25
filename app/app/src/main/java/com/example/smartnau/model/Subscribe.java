package com.example.smartnau.model;

import android.util.Log;

import java.nio.charset.StandardCharsets;

public class Subscribe implements ConnectionCredentials {
    // Tag for Class
    private static final String TAG = Subscribe.class.getSimpleName();


    // Delegate MqttClient
    private final MqttClient mqttClient;

    public Subscribe(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    public void subscribeForMessages() {
        // Subscribe for messages
        mqttClient.subscribe(CLIENT_MESSAGES, (message) -> {
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
                throw new SubscribeException("Subscription for messages failed", e.getCause());
            }
        });
    }

    public void receiveMessages(String topic, String message) {
        Log.d(TAG, String.format("Received message '%s' from topic '%s'", topic, message));
        // TODO: Connect to ConnectionAdapter so it can be updated using DataBinding
    }

    // TODO: Separate yes and no button values from String (yy,nn)
    public void subscribeForPresses() {
        // Subscribe for button presses
        mqttClient.subscribe(ROOM_BUTTONPRESS, (presses) -> {
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
                throw new SubscribeException("Subscription for presses failed", e.getCause());
            }
        });
    }

    public void receiveButtonpresses(String topic, String pressesAsString) {
        Log.d(TAG, String.format("Received message '%s' from topic '%s'", topic, pressesAsString));
        // TODO: Connect to ConnectionAdapter so it can be updated using DataBinding
        try {
            int pressesAsInteger = Integer.parseInt(pressesAsString);
        } catch (NumberFormatException e) {
            throw new SubscribeException("Conversion mishandling in topic " + topic, e.getCause());
        }
    }

    public void unsubscribe(String topic) {
        mqttClient.unsubscribe(topic);
    }
}
