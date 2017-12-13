package Program.Client;

import javax.swing.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static int portnumber = 4444;
    private ServerThread serverThread;

    public Client(String name) throws Exception {
        Socket socket = new Socket("localhost", portnumber);

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
}
