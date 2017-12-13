package Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread implements Runnable {
    private Socket socket;
    private String name;
    private BufferedReader serverIn;
    private BufferedReader userIn;
    private PrintWriter out;

    public ServerThread(Socket socket, String name)
    {
        this.socket = socket;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            userIn = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Running shit");

            while (!socket.isClosed()) {

                if (serverIn.ready()) {
                    System.out.println("Server ready");
                    String input = serverIn.readLine();
                    if (input != null) {
                        System.out.println(input);
                    }
                }
                String userInput;
                while ((userInput = userIn.readLine()) != null)
                {
                    out.println(name + " > " + userInput);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
