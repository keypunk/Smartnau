package com.example.smartnau.model;

public class Publish implements ConnectionCredentials {
    private MqttClient mqttClient;

    public Publish(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    public void publishMessages(String message) {
        mqttClient.publish(CLIENT_MESSAGES, message);
    }

    // TODO: Add publish functions for robot state
}
