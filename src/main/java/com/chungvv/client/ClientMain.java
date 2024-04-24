package com.chungvv.client;

import jakarta.json.Json;
import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.DeploymentException;
import javax.websocket.Session;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) throws URISyntaxException, DeploymentException, IOException {
        String serverUrl = "ws://localhost:8899/ws/api/chat";
        ClientManager clientManager = ClientManager.createClient();

        String message;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tiny Chat!");
        System.out.println("What's your name?");
        String user = scanner.nextLine();

        Session session = clientManager.connectToServer(ClientEndpoint.class, new URI(serverUrl));
        System.out.println("You are logged on with username: "+user);

        do {
            message = scanner.nextLine();
            String msgToSend = Json.createObjectBuilder()
                    .add("message", message)
                    .add("sender", user)
                    .add("received", "")
                    .build().toString();
            session.getBasicRemote().sendText(msgToSend);
        } while (!message.equalsIgnoreCase("quit"));
    }
}
