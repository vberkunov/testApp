package com.application.clientServer.tcp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT=3035;
    private static Socket socket;
    private static PrintWriter out;
    private static BufferedReader in;


    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(3035);
            while (true){
                Socket client = server.accept();
                ClientListener clientSock = new ClientListener(client);
                new Thread(clientSock).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (server != null) {
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
