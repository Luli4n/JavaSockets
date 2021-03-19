package Krabel.Julian.Server;

import Krabel.Julian.Server.Connection.IncomingConnectionHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    public static void main(String[] args) throws IOException, InterruptedException {

        Thread c2hThread=new Thread(new IncomingConnectionHandler(3333));
        c2hThread.start();
        c2hThread.join();

    }


}
