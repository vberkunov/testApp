package com.application.clientServer.ftp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int SERVER_PORT = 3035;
    private ServerSocket serverSocket;


    public void startServer(){
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server is running on PORT:" + SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server closed unexpectedly");
            System.out.println(e.getMessage());
        }

    }

    public void handleConnection(){
        while (true){
            Socket client = null;
            try {
                client = serverSocket.accept();
                ClientListener clientSock = new ClientListener(client);
                new Thread(clientSock).start();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Warning");
                System.out.println(e.getMessage());
            }

        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
        server.handleConnection();
    }

}
