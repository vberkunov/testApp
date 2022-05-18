package com.application.rmi;


import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements EchoService {

    public Server() throws RemoteException {
    }

    @Override
    public String echo(String aString) {
        System.out.println("Request: " + aString);
        StringBuilder sb=new StringBuilder(aString);
        sb.reverse();
        return sb.toString();
    }


    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {

            System.setSecurityManager(new SecurityManager());
        }
        try {
            Server server = new Server();
            Registry registry = LocateRegistry.createRegistry(2099);
            Remote stub = UnicastRemoteObject.exportObject(server, 0);
            //регистрация "заглушки" в реесте
            registry.bind("server.echo", stub);

            Thread.sleep(Integer.MAX_VALUE);

        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }


    }
}
