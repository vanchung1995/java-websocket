package com.chungvv.server;

import org.glassfish.tyrus.server.Server;

import javax.websocket.DeploymentException;
import java.util.Scanner;

public class ServerMain {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8899;
        String rootPath = "/ws";
        Server server = new Server(host, port, rootPath, ServerEndpoint.class);

        try {
            server.start();
            System.out.println("Server Started");
            System.out.println("Press any key to stop the server!!!");
            new Scanner(System.in).nextLine();
        } catch (DeploymentException e) {
            throw new RuntimeException(e);
        } finally {
            server.stop();
        }
    }
}
