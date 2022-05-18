package com.application.clientServer.ftp;

import java.io.Serializable;

public class TcpCommand implements Serializable {
    private static final long serialVersionUID = 1L;
    private String commandName;
    private String fileName;

    public TcpCommand() {
    }

    public TcpCommand(String commandName, String fileName) {
        this.commandName = commandName;
        this.fileName = fileName;
    }

    public String getCommandName() {
        return commandName;
    }


    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Command{" +
                "commandName='" + commandName + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
