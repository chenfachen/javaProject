package com.chen.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(PORT);
            System.out.println("the time server is start in port: " + PORT);
            Socket socket;
            while (true){
                socket = serverSocket.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(serverSocket != null){
                System.out.println("the time server is close");
                try {
                    serverSocket.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
