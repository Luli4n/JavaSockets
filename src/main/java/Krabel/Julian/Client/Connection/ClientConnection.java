package Krabel.Julian.Client.Connection;

import Krabel.Julian.Common.Message;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientConnection {


    private final Socket client;

    protected final Logger log = Logger.getLogger(getClass().getName()); //java.util.logging.Logger;

    public ClientConnection(String proxy,int port){

        try {
            client = new Socket(proxy, port);
        }
        catch (IOException ex)
        {
            throw new IllegalStateException(ex);
        }
    }

    public void connect() {
        Scanner scan = new Scanner(System.in);
        String str="";
        int n=0;

        log.info("Client starts");

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
             BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
             BufferedWriter output = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {

            do {
                str = input.readLine();
            } while(!str.equals("ready"));

            log.info(str);

            n = scan.nextInt();
            output.write(n);
            output.flush();

            do {
                str = input.readLine();
            } while(!str.equals("ready for messages"));

            log.info(str);

            scan.nextLine();

            for (int i=0;i<n;i++) {
                System.out.println("Message No."+i);
                str = scan.nextLine();
                Message msg = new Message(i, str);
                objectOutputStream.writeObject(msg);
            }

            do {
                str = input.readLine();
            } while(!str.equals("finished"));

            client.close();
        }
        catch (IOException ex){
            log.log(Level.WARNING,ex.getMessage(),ex);
        }
        log.info("Client ends");
    }
}
