package Program.Client;

import Program.ConnectionVariables;

import javax.swing.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private ServerThread serverThread;
    private String name;
    private String password;

    public Client(String name, String password) throws Exception {
        Socket socket = new Socket(ConnectionVariables.ip, ConnectionVariables.port);
        this.name = name;
        this.password = password;

        /*if (socket.isConnected())
        {
            System.out.println("Connected successfully!");
        }*/
        Thread.sleep(1000);

        //Client's thread to talk to server
        serverThread = new ServerThread(socket, name, password);

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
