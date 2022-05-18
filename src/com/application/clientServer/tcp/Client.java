package com.application.clientServer.tcp;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class Client {
    private static InetAddress host;
    private static final int PORT=3035;
    private static Socket socket;
    private static PrintWriter out;
    private static  BufferedReader in;


    public boolean startConnection(){
        boolean isConnected=false;
        try
        {
            host=InetAddress.getLocalHost();
            socket = new Socket(host, PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connection opened");
            isConnected = true;

        }
        catch(UnknownHostException uhEx)
        {
            System.out.println("Host address not found. Try again.");
            System.exit(1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return isConnected;
    }

    public boolean stopConnection(){
        boolean isDisConnected=false;
        try {
            in.close();
            out.close();
            socket.close();
            System.out.println("Connection closed");
            isDisConnected = true;
        } catch (IOException e) {
            System.out.println("Problem with stopping connection");
            System.out.println(e.getMessage());
        }
    return isDisConnected;
    }

    public String sendMessage(String line){
        String response="";
        try {
        out.println(line);
        out.flush();
        response = in.readLine();
        System.out.println(" \n SERVER-->> "+ response);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return response;
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.startConnection();
        Scanner sc = new Scanner(System.in);
        String line = null;
        while (!"exit".equalsIgnoreCase(line)) {
            System.out.println("Enter message :");
            line = sc.nextLine();
            client.sendMessage(line);

        }
        sc.close();
        client.stopConnection();


    }
    @Test
    public void testMessageToServer(){
        Client client = new Client();
        client.startConnection();
        String line = sendMessage("Hello");
        assertEquals("Hello",line);
    }

    @Test
    public void testIsConnected(){
        Client client = new Client();
        boolean isConnected = client.startConnection();
        assertEquals(true,isConnected);
    }
    @Test
    public void testIsDisConnected(){
        Client client = new Client();
        client.startConnection();
        boolean isDisConnected = client.stopConnection();
        assertEquals(true,isDisConnected);
    }
}
