package Program.Setup;

import Program.Program;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import Program.ConnectionVariables;

public class JoinServer extends JFrame {
    public JoinServer(Program.getInfo infoGrabber)
    {
        setSize(600,350);
        setTitle("Joining information");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JTextField ipField = new JTextField();
        try {
            URL url_name = new URL("http://bot.whatismyipaddress.com");

            BufferedReader sc =
                    new BufferedReader(new InputStreamReader(url_name.openStream()));
            // reads system IPAddress

            ipField.setText(sc.readLine().trim());
            //ipField.setText(InetAddress.getLocalHost().getHostAddress());
        } catch (Exception e) {
            ipField.setText("Unable to detect.");
        }
        JTextField port = new JTextField();
        JButton start = new JButton("Join Server");

        setLayout(new GridLayout(7,1));
        add(new JLabel("IP Address to join (or \"localhost\" if local): "));
        add(ipField);
        //add(new JLabel("If you want people outside your LAN to connect you're going to have to portforward your port."));
        add(new JLabel("Port: "));
        add(port);
        add(start);
        //add(new JLabel("Tip: If the client doesn't connect, try a different port."));

        start.addActionListener(e -> {
            ConnectionVariables.port = Integer.parseInt(port.getText());
            ConnectionVariables.ip = ipField.getText();
            System.out.println("Parsing port: " + port.getText());
            infoGrabber.setReady(true);
            dispose();
        });

        setVisible(true);
        port.requestFocus();
    }
}
