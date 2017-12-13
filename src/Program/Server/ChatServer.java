package Program.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer implements Runnable {
    private static ServerSocket serverSocket;
    static ArrayList<ClientThread> clients;
    private static int portnumber = 4444;
    private boolean ready;

    public ChatServer() throws Exception {
        System.out.println("Making serverSocket\n\n");
        serverSocket = new ServerSocket(portnumber);
    }

    public void run() {
        clients = new ArrayList<>();
        ready = true;
        ClientThread client = null;
        while (true)
        {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                client = new ClientThread(socket, clients);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Thread thread = new Thread(client);
            thread.start();

            clients.add(client);
            System.out.println("Client added!");
        }
    }

    public boolean isReady() {
        return ready;
    }
}
