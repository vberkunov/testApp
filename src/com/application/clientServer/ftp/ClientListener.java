package com.application.clientServer.ftp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Arrays;

public class ClientListener implements Runnable{
    private Socket clientSocket;
    private ServerSocket serverSocket;
    public BufferedReader in = null;
    public PrintStream ps = null;
    public DataOutputStream out = null;
    private String fileName = "command.dat";
    public static final String SERVER_DIR ="C:\\Users\\vital\\Reader\\test\\src\\com\\application\\clientServer\\ftp\\serverFile\\";


    public ClientListener(Socket clientSocket, ServerSocket serversocket2) {

        try{
            this.clientSocket = clientSocket;
            this.serverSocket = serversocket2;
            this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            this.ps = new PrintStream(this.clientSocket.getOutputStream());

        }
        catch(Exception e)
        {
            System.out.println("Could not open connection with: " +
                    clientSocket.getInetAddress()+ "Port: "+ clientSocket.getPort());
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {


    TcpCommand command = null;
    receiveCommand();
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SERVER_DIR + fileName))) {
        command = (TcpCommand) ois.readObject();
        System.out.println(command.getCommandName());
    } catch (Exception ex) {

        System.out.println(ex.getMessage());
    }

    switch (command.getCommandName()) {
        case "PUT":
            System.out.println("Upload command received at Server");
            this.receiveFile();
            break;
        case "DIR":
            System.out.println("List command received at Server");
            sendDirectiroies();
            break;
        case "GET":
            System.out.println("Download command received at Server");
            String fileName = command.getFileName();

            sendFile(fileName);
            break;

        case "EXIT":
            System.exit(1);
            break;
        default:
            System.out.println("Incorrect command received.");
    }


    }

    private void sendDirectiroies() {
        String response = "";
        File file = new File("C:\\Users\\vital\\Reader\\test\\src\\com\\application\\clientServer\\ftp\\serverFile\\");
        File[] files = file.listFiles();
        File[] f = files;
        int size = files.length;

        for(int i = 0; i < size; ++i) {
            File newFile = f[i];
            if (newFile.isDirectory()) {
                response = response + newFile.getName() + ":";
            }

            if (newFile.isFile()) {
                response = response + newFile.getName() + ":";
            }
        }
        OutputStream os = null;
        DataOutputStream dos = null;
        System.out.println(response);
        try {
            os = this.serverSocket.accept().getOutputStream();
            dos = new DataOutputStream(os);
            dos.writeUTF(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void receiveFile() {
        try {
            Socket socket = serverSocket.accept();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            fileName = dis.readUTF();
            System.out.println("Filename "+ fileName);
            System.out.println("");
            FileOutputStream fos = new FileOutputStream(SERVER_DIR+fileName);
            long size = dis.readLong();
            System.out.println("Size"+ size);

            int pointer;
            for(byte[] data = new byte[1024]; size > 0L && (pointer = dis.read(data, 0, (int)Math.min((long)data.length, size))) != -1; size -= (long)pointer) {
                fos.write(data, 0, pointer);
            }
fos.close();
            dis.close();

            System.out.println("File " + fileName + " received from client.");
        } catch (IOException var8) {
            System.err.println("Client error. Connection closed.");
        }

    }

    private void receiveCommand(){
        try {

            DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
            FileOutputStream fos = new FileOutputStream(SERVER_DIR+fileName);
            long size = dis.readLong();
            System.out.println("Длина: " + size);
            int pointer;
            for(byte[] data = new byte[1024]; size > 0L && (pointer = dis.read(data, 0, (int)Math.min((long)data.length, size))) != -1; size -= (long)pointer) {
                fos.write(data, 0, pointer);
            }
           fos.close();
            dis.close();

            System.out.println("Command " + fileName + " received from client.");
        } catch (IOException var8) {
            System.err.println("Client error. Connection closed.");
        }
    }

    private void sendFile(String fileName) {
        try {
            File file = new File("C:\\Users\\vital\\Reader\\test\\src\\com\\application\\clientServer\\ftp\\serverFile\\"+fileName);
            byte[] data = new byte[(int)file.length()];
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            DataInputStream dis = new DataInputStream(bis);
            dis.readFully(data, 0, data.length);
            Socket socket = serverSocket.accept();
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(file.getName());
            dos.writeLong((long)data.length);
            dos.write(data, 0, data.length);
            dos.flush();
            System.out.println("File " + fileName + " sent to client.");
        } catch (Exception var9) {
            System.err.println("File does not exist!");

        }

    }



}
