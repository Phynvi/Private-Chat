package Program.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    private static ServerSocket serverSocket;
    static ArrayList<ClientThread> clients;
    private static int portnumber = 4444;
    private boolean ready;

    public ChatServer() throws Exception {
        serverSocket = new ServerSocket(portnumber);

        acceptClients();
    }

    private void acceptClients() throws Exception {
        clients = new ArrayList<>();
        ready = true;
        while (true)
        {
            Socket socket = serverSocket.accept();
            ClientThread client = new ClientThread(socket);

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
