package Program;

import Program.Client.Client;

public class Program {
    public static void main(String[] args) throws Exception {
        Client client = new Client();

        Chatbox cb = new Chatbox(client);
    }
}
