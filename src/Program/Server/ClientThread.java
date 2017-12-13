package Program.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends ChatServer implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientThread(Socket socket) throws Exception
    {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //while the socket it alive
            while (!socket.isClosed())
            {
                String input = in.readLine();
                if (input != null)
                {
                    for (ClientThread client: clients)
                    {
                        client.getWriter().write(input);
                        System.out.println("Wrote " + input);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PrintWriter getWriter()
    {
        return out;
    }
}
