package Program.Chatbox;

import Program.Client.Client;
import Program.Program;

import javax.swing.*;
import java.awt.*;

public class Chatbox extends JFrame {

    private Client client;
    private JTextPane userField = new JTextPane();
    private JButton sendText = new JButton("Send");
    private JTextArea chatHistory = new JTextArea();

    public Chatbox(Client client) {
        this.client = client;

        createChatbox();
    }

    private void createChatbox() {
        setSize(600,400);
        add(new JLabel("Chatbox"));
        setTitle("Chatbox");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        add(createChatArea(), BorderLayout.CENTER);
        add(createUserIn(), BorderLayout.PAGE_END);

        setVisible(true);
    }

    private JPanel createChatArea() {
        JPanel chatArea = new JPanel();
        JScrollPane scroll = new JScrollPane(chatHistory);
        chatArea.add(scroll);

        revalidate();

        client.getServer().setChatArea(chatHistory);

        return chatArea;
    }

    private JPanel createUserIn() {
        JPanel userIn = new JPanel();
        userIn.setLayout(new BorderLayout());

        userIn.add(userField);
        userIn.add(sendText, BorderLayout.EAST);

        sendText.addActionListener(e -> {
            client.getServer().send(userField.getText());
        });

        userIn.revalidate();

        return userIn;
    }

    public void appendToChatbox(String toAppend)
    {
        chatHistory.append(toAppend);
    }
}
