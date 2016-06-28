package com.enya.test.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerAvailable {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 3128;
        try {
            ServerSocket server = new ServerSocket(port, 0, InetAddress.getByName(host));
            System.out.println("server is started");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("Server: %s:%d         status: %s", host,port, available(host, port)));
    }

    private static boolean available(String host, int port) {
        try (Socket socket = new Socket(host, port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
