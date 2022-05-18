package com.application.clientServer.ftp;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static final String USER_DIR = "C:\\Users\\vital\\Reader\\test\\src\\com\\application\\clientServer\\ftp\\clientFile\\";
    public static final String FTP_DIR = "C:\\Users\\vital\\Reader\\test\\src\\com\\application\\clientServer" +
            "\\ftp\\";

    private static InetAddress host;
    private static final int PORT=3035;
    private static Socket commandSocket;
    public static Scanner sc;
    public static DataInputStream dis = null;
    public static OutputStream os = null;
    public static InputStream is = null;
    public static BufferedInputStream bis = null;
    public static DataOutputStream dos = null;
    public static FileInputStream fis = null;
    public static FileOutputStream fos = null;
    private static PrintStream ps = null;
    private static ObjectOutputStream oos = null;

    public boolean startConnection(){
        boolean isConnected=false;
        try
        {
            host=InetAddress.getLocalHost();
            commandSocket = new Socket(host, PORT);
            ps = new PrintStream(commandSocket.getOutputStream());
            dis = new DataInputStream(commandSocket.getInputStream());
            sc = new Scanner(System.in);
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
            commandSocket.close();
            System.out.println("Connection closed");
            isDisConnected = true;
        } catch (IOException e) {
            System.out.println("Problem with stopping connection");
            System.out.println(e.getMessage());
        }
        return isDisConnected;
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.startConnection();

        while (true) {
            try{
                System.out.println("Please make your choice : \nPUT <filename> - send file " +
                        "\nGET <filename> -receive file \nDIR - get list of files \nYour Choice: ");

                String com = sc.nextLine();
                System.out.println(com);
                String[]data = com.split(" ");
                TcpCommand command = null;
                if(data.length>1) {
                    command = new TcpCommand(data[0], data[1]);
                }else {
                    command = new TcpCommand(data[0], "null");

                }
                String commandFile = "command.dat";
                fos = new FileOutputStream(USER_DIR+commandFile);
                dis = new DataInputStream(commandSocket.getInputStream());
                oos = new ObjectOutputStream(fos);
                oos.writeObject(command);
                oos.close();
                switch(command.getCommandName())
                {

                    case "PUT":
                        System.out.println("We are sending your file");
                        client.sendCommand(commandFile);
                        client.sendFile(command.getFileName());
                        break;
                    case "GET":

                        client.sendCommand(commandFile);
                        client.receiveFile(command);

                        break;
                    case "DIR":
                        client.sendCommand(commandFile);
                        client.getFilesDirectories();
                        break;
                    case "EXIT":
                        client.stopConnection();
                    default:
                        System.out.println("Invalid Option !");
                }
            }
            catch(Exception e)
            {
                System.out.println("Some Error Occured!");
                System.out.println(e.getMessage());
            }
        }


    }

    private  void getFilesDirectories() {
        try {
            Socket socket = new Socket(host, 3036);
            DataInputStream dis2 = new DataInputStream(socket.getInputStream());
            System.out.println( dis2.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

        public void sendCommand(String command){
        try {
            File file;
            byte[] data;
            file = new File(USER_DIR+command);
            if (file.isFile()) {
                data = new byte[(int) file.length()];
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                dis = new DataInputStream(bis);
                dis.readFully(data, 0, data.length);
                os = commandSocket.getOutputStream();
                dos = new DataOutputStream(os);
                dos.writeLong((long) data.length);
                dos.write(data, 0, data.length);
                dos.flush();
                System.out.println("Command " + file + " sent to Server.");
            }else
            {
                System.out.println("File Not Found!");
            }
        }catch (Exception e) {
            System.err.println("Exception: " + e);
        }
    }

    public  void sendFile(String fileName) {
        try {
            File file;
            byte[] data;
            System.out.println("filename" + fileName);
            file = new File(USER_DIR+fileName);
            if (file.isFile()) {
                data = new byte[(int) file.length()];
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                dis = new DataInputStream(bis);
                dis.readFully(data, 0, data.length);
                Socket socket = new Socket(host,3036);
                os = socket.getOutputStream();
                dos = new DataOutputStream(os);
                dos.writeUTF(file.getName());
                dos.writeLong((long) data.length);
                dos.write(data, 0, data.length);
                dos.flush();
                System.out.println("File " + file + " sent to Server.");
            }else
            {
                System.out.println("File Not Found!");
            }
        }catch (Exception e) {
                System.err.println("Exception in send: " + e);
        }

    }

    public  void receiveFile(TcpCommand command){
        try{
            Socket socket = new Socket(host,3036);
            is = socket.getInputStream();
            dis = new DataInputStream(is);
            String filename = dis.readUTF();
            if(!filename.equals("File does not exist")) {

                fos = new FileOutputStream("C:\\Users\\vital\\Reader\\test\\src\\com\\application\\clientServer\\ftp\\clientFile\\" + filename);
                long size = dis.readLong();
                int pointer;
                for (byte[] data = new byte[1024]; size > 0L && (pointer = dis.read(data, 0, (int) Math.min((long) data.length, size))) != -1; size -= (long) pointer) {
                    fos.write(data, 0, pointer);
                }


                System.out.println("Successfully uploaded file!");
            }else{
                System.out.println("File does not exist");
            }

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }



}
