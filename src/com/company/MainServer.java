package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class MainServer {
    public static final int port = 8080;
    public static LinkedList<Server> serverList = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(port);
        System.out.println("Сервер запущен!");
        try {
            while (true) {

                Socket socket = server.accept();
                try {
                    serverList.add(new Server(socket));
                } catch (IOException e) {
                   socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}
