package Krabel.Julian.Client;

import Krabel.Julian.Client.Connection.ClientConnection;

import java.io.*;

public class ClientMain {

    public static void main(String[] args) {

        ClientConnection clientConnection= new ClientConnection("localhost",3333);
        clientConnection.connect();
    }

}
