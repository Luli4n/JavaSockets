package Krabel.Julian.Server.Connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class IncomingConnectionHandler implements Runnable{

    private final ServerSocket server;
    private static int cid=0;
    protected final Logger log = Logger.getLogger(getClass().getName()); //java.util.logging.Logge

    public IncomingConnectionHandler(int port) {
        try {
            server = new ServerSocket(port);
            server.setSoTimeout(500);
        }
        catch (IOException ex)
        {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public void run(){

        log.info("Starting server.");
        while(!Thread.interrupted())
        {
            try {
                Socket socket = server.accept();
                new Thread(new ConnectionHandler(cid,socket)).start();
                cid++;
            }catch (SocketTimeoutException e){
                log.log(Level.FINEST,e.getMessage(),e);
            }
            catch (IOException ex){
                log.log(Level.WARNING,ex.getMessage(),ex);
            }
        }
        log.info("Closing server.");

    }
}
