package com.example.smartnau.model;

import java.util.UUID;

public interface ConnectionCredentials {
    // Connection credentials
    String BROKER_URL = "broker.hivemq.com";
    int PORT = 1883;
    String USER = "";
    String PASSWORD = "";
    String CLIENT_ID = UUID.randomUUID().toString();

    // Topics to subscribe to
    // TODO: Add topics for robot state
    String ROOM_BUTTONPRESS = "Room/Buttonpress";
    String ROOM_MESSAGES = "Room/Messages";
    String CLIENT_MESSAGES = "Client/Messages";
}
