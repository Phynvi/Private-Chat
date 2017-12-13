package Program.Chatbox;

import Program.Client.Client;
import Program.Program;

import javax.swing.*;
import java.awt.*;

public class Chatbox extends JFrame {

    private Client client;
    private JTextField userField = new JTextField();
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

        chatArea.setLayout(new BorderLayout());

        chatHistory.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        chatHistory.setEditable(false);
        chatHistory.setAutoscrolls(true);
        chatHistory.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(chatHistory);
        chatArea.add(scroll, BorderLayout.CENTER);

        revalidate();

        client.getServerThread().setChatArea(chatHistory);

        return chatArea;
    }

    private JPanel createUserIn() {
        JPanel userIn = new JPanel();
        userIn.setLayout(new BorderLayout());

        userIn.add(userField);
        userIn.add(sendText, BorderLayout.EAST);


        userField.addActionListener(e -> {
            sendText();
        });
        sendText.addActionListener(e -> {

        });

        userIn.revalidate();

        return userIn;
    }

    private void sendText() {
        client.getServerThread().send(userField.getText());
        userField.setText("");
    }
}
