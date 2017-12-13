package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;

public class ChatServer {
    private static ServerSocket serverSocket;
    static ArrayList<ClientThread> clients;
    private static int portnumber = 4444;

    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(portnumber);

        acceptClients();
    }

    public static void acceptClients() throws IOException {
        clients = new ArrayList<>();
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
}
