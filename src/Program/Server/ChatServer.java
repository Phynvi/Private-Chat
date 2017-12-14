package Program.Server;

import Program.ConnectionVariables;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer implements Runnable {
    private static ServerSocket serverSocket;
    static ArrayList<ClientThread> clients;
    private boolean ready;

    public ChatServer() throws Exception {
        serverSocket = new ServerSocket(ConnectionVariables.port);
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
        }
    }

    public boolean isReady() {
        return ready;
    }
}
