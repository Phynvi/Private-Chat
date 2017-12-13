package Program.Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {
    private Socket socket;
    private String name;
    private BufferedReader serverIn;
    private BufferedReader userIn;
    private PrintWriter out;

    private boolean readyToSend = false;
    private String userInput;

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

            while (!socket.isClosed()) {

                if (serverIn.ready()) {
                    System.out.println("Program.Server ready");
                    String input = serverIn.readLine();
                    if (input != null) {
                        System.out.println(input);
                    }
                }
                if (readyToSend)
                {
                    out.println(name + " > " + userInput);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(String text) {
        this.readyToSend = true;
        this.userInput = text;
    }
}
