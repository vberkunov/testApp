package com.application.rmi;

import java.rmi.Remote;

public interface EchoService extends Remote {

    public String echo(String aString);
}
