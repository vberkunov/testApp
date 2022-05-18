package com.application.clientServer.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
    private static final int PORT=3036;
    private static DatagramSocket datagramSocket;


    public static void main(String[] args)
    {

        try
        {
            datagramSocket=new DatagramSocket(PORT);
            ClientListener clientListener = new ClientListener(datagramSocket);
            new Thread(clientListener).start();
        }
        catch(SocketException sockEx)
        {
            System.out.println("unable to open ");
            System.exit(1);
        }

    }

}
