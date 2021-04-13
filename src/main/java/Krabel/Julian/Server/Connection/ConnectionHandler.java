package Krabel.Julian.Server.Connection;

import Krabel.Julian.Common.Message;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionHandler implements Runnable{

    private final int id;
    private final Socket socket;

    protected final Logger log = Logger.getLogger(getClass().getName()); //java.util.logging.Logge


    public ConnectionHandler(int id,Socket socket)
    {
        this.id=id;
        this.socket=socket;
    }

    @Override
    public void run() {
        Scanner scan=new Scanner(System.in);
        int n=0;

        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream()))
            {

                 output.write("ready");
                 output.newLine();
                 output.flush();

                 n = input.read();
                 log.info("For Client id: "+id+" Value of n: "+String.valueOf(n));


                output.write("ready for messages");
                output.newLine();
                output.flush();

                for (int i=0;i<n;i++) {
                    Message msg = (Message) objectInputStream.readObject();
                    System.out.println("Message No."+msg.getNumber()+" from "+id+": "+msg.getContent());
                }

                output.write("finished");
                output.newLine();
                output.flush();
            }
            catch (IOException | ClassNotFoundException ex) {
                 log.log(Level.WARNING,ex.getMessage(),ex);
            }
            finally {
                this.socket.close();   
            }

        }
}
