package com.chen.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BioClient {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException{

        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            socket = new Socket(HOST, PORT);
            in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("i am client");
            String resp = in.readLine();
            System.out.println("当前服务器时间是：" + resp);
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            if(in != null){
                try{
                    in.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            if(out != null){
                out.close();
            }

            if(socket != null){
                try {
                    socket.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

}
