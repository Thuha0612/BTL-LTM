package org.example.demo1.service;

import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketClient extends org.java_websocket.client.WebSocketClient {

    public WebSocketClient(String serverUri) throws URISyntaxException {
        super(new URI(serverUri));
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connected to WebSocket server.");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Received message: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Connection closed with code: " + code + " and reason: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("WebSocket error: " + ex.getMessage());
    }

    public void sendMessage(String message) {
        System.out.println("Sending message: " + message);
        send(message); // Gửi thông điệp tới server
    }
}
