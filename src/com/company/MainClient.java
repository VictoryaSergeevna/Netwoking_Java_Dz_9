package com.company;

import java.io.IOException;

public class MainClient {
    public static String ipAddr = "localhost";
    public static int port = 8080;
    public static void main(String[] args) throws IOException {
        new Client(ipAddr, port);
    }
}
