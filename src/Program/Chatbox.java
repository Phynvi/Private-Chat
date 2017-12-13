package Program;

import Program.Client.Client;

import javax.swing.*;
import java.awt.*;

public class Chatbox extends JFrame {

    private Client client;
    JTextPane userField = new JTextPane();
    JButton sendText = new JButton("Send");

    public Chatbox(Client client) {
        this.client = client;

        this.setSize(600,400);
        this.add(new JLabel("Chatbox"));
        this.setTitle("Chatbox");
        this.setVisible(true);

        setLayout(new BorderLayout());

        add(createUserIn(), BorderLayout.PAGE_END);
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
}
