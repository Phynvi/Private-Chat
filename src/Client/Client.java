package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static int portnumber = 4444;

    public static void main(String[] args) throws IOException, InterruptedException {
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

        Thread server = new Thread(new ServerThread(socket, "kj"/*name*/));
        server.start();
    }
}
