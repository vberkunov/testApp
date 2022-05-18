package com.application.clientServer.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientListener implements Runnable{
    private  DatagramSocket datagramSocket;

    public ClientListener(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    @Override
    public void run() {
        DatagramPacket inPacket,outPacket;
        byte[] buffer;
        try
        {
            String messageIn,messageOut;
            int numMessages=0;
            InetAddress clientAddress=null;
            int clientPort;
            do
            {
                buffer= new byte[256];
                inPacket=new DatagramPacket(buffer,buffer.length);
                datagramSocket.receive(inPacket);
                clientAddress=inPacket.getAddress();
                clientPort=inPacket.getPort();
                messageIn=new String(inPacket.getData(),0,inPacket.getLength());
                System.out.print(clientAddress);
                System.out.print(" : ");
                System.out.println(messageIn);
                numMessages++;
                messageOut="message" + numMessages + ": " + messageIn;
                outPacket=new DatagramPacket(messageOut.getBytes(),messageOut.length(),clientAddress,clientPort);
                datagramSocket.send(outPacket);
            }while(true);
        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        finally
        {
            System.out.println("\n Closing connection.. ");
            datagramSocket.close();
        }
    }
}
