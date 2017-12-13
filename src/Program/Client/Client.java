package Program.Client;

import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static int portnumber = 4444;
    private ServerThread server;

    public Client() throws Exception {
        Socket socket = null;
        System.out.println("Please input your username: ");
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();

        socket = new Socket("localhost", portnumber);
        if (socket.isConnected())
        {
            System.out.println("Connected successfully!");
        }
        Thread.sleep(1000);

        server = new ServerThread(socket, name);

        Thread thread = new Thread(server);
        thread.start();
    }

    public ServerThread getServer()
    {
        return this.server;
    }
}
