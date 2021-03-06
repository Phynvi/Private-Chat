package Program.Client;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static Program.Encrypter.getDecryptedVersion;
import static Program.Encrypter.getEncryptedVersion;

/**
 *  Handles the communication with the server in a separate thread
 */
public class ServerThread implements Runnable {
    private Socket socket;
    private String name;
    private String password;
    private BufferedReader serverIn;
    private BufferedReader userIn;
    private PrintWriter out;

    private boolean readyToSend = false;
    private String userInput;

    private JTextArea chatArea;

    public ServerThread(Socket socket, String name, String password) throws IOException {
        this.socket = socket;
        this.name = name;
        this.password = password;

        out = new PrintWriter(socket.getOutputStream(), true);
        serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        userIn = new BufferedReader(new InputStreamReader(System.in));

        out.println(getEncryptedVersion(name + " has entered the channel.", password));
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {

                if (serverIn.ready()) {
                    String input = serverIn.readLine();
                    if (input != null && chatArea != null) {
                        chatArea.append(getDecryptedVersion(input, password) + "\n");
                    }
                }
                if (readyToSend)
                {
                    out.println(getEncryptedVersion(name + ">" + userInput, password));
                    readyToSend = false;
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

    public void setChatArea(JTextArea chatArea) {
        this.chatArea = chatArea;
    }
}