package com.application.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {

        try {
            Registry registry = LocateRegistry.getRegistry(2099);
            EchoService service = (EchoService) registry.lookup("server.echo");
            String request = "Hello";
            System.out.println(request);
            String response = service.echo(request);
            System.out.println(response);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
