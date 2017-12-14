package Program.Client;

import Program.ConnectionVariables;

import javax.swing.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private ServerThread serverThread;
    private String name;

    public Client(String name) throws Exception {
        Socket socket = new Socket(ConnectionVariables.ip, ConnectionVariables.port);
        this.name = name;

        /*if (socket.isConnected())
        {
            System.out.println("Connected successfully!");
        }*/
        Thread.sleep(1000);

        //Client's thread to talk to server
        serverThread = new ServerThread(socket, name);

        Thread thread = new Thread(serverThread);
        thread.start();
    }

    public ServerThread getServerThread()
    {
        return this.serverThread;
    }

    public String getName() {
        return name;
    }
}
