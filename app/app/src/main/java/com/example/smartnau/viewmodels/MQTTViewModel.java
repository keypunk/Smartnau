package com.example.smartnau.viewmodels;

import com.example.smartnau.model.MqttClient;


public class MQTTViewModel {


    private MqttClient mqtt;

    public MQTTViewModel() {
        mqtt = new MqttClient();
    }

    /* TODO: Insert functions for Databinding i.e. display message & button presses through
             ViewModel using XML & Databinding. Therefore using Observers updating UI will be
             possible using only MQTTViewModel and MainActivity
     */
}
