package com.example.smartnau.viewmodels;

import com.example.smartnau.model.MqttClient;


public class ConnectionAdapter {


    // Add Subscribe & Publish classes to access subscribe and publish functions
    // MqttClient is required as an argument for both constructors
    private MqttClient mqtt;

    public ConnectionAdapter() {
        MqttClient mqttClient = MqttClient.getMqttClient();
    }

    /* TODO: Insert functions for Databinding i.e. display message & button presses through
             ViewModel using XML & Databinding. Therefore using Observers updating UI will be
             possible using only MQTTViewModel and MainActivity
     */
}
