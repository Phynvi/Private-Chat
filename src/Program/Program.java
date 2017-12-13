package Program;

import Program.Client.Client;
import Program.Server.ChatServer;
import Program.Setup.Chatbox;
import Program.Setup.NamePrompt;

public class Program {
    public static void main(String[] args) throws Exception {
        Program.getInfo getInfo = new Program.getInfo();
        new NamePrompt(getInfo);
        ChatServer server;

        while (!getInfo.isReady())
        {
            Thread.sleep(1000);
        }

        if (getInfo.isCreateServer())
        {
           server = new ChatServer();
           while (!server.isReady())
           {
               Thread.sleep(1000);
           }
        }

        Client client = new Client(getInfo.getName());

        new Chatbox(client);
    }

    public static class getInfo
    {
        private String name;
        private boolean createServer = false;
        private boolean ready = false;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isCreateServer() {
            return createServer;
        }

        public void setCreateServer(boolean createServer) {
            this.createServer = createServer;
        }

        public boolean isReady() {
            return ready;
        }

        public void setReady(boolean ready) {
            this.ready = ready;
        }
    }
}
