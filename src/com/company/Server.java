package com.company;

import java.io.*;
import java.net.Socket;



public class Server extends Thread{
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;


    public Server(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }
    @Override
    public void run() {
        String word;
        try {
            word = in.readLine();
            try {
                out.write(word + "\n");
                out.flush();
            } catch (IOException ex) {}
            try {
                while (true) {
                    word = in.readLine();
                    if(word.equals("exit")) {
                        this.downService();
                        break;
                    }
                    System.out.println("Эхо: " + word);

                for (Server vr : MainServer.serverList) {
                    vr.send(word);
                }
            }
            } catch (NullPointerException ex) {}
        } catch (IOException e) {
            this.downService();
        }
    }

    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {}
    }
    private void downService() {
        try {
            if(!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                for (Server vr : MainServer.serverList) {
                    if(vr.equals(this)) vr.interrupt();
                    MainServer.serverList.remove(this);
                }
            }
        } catch (IOException ignored) {}
    }

}
