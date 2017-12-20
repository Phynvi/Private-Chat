package Program.Setup;

import Program.Program;

import javax.swing.*;
import java.awt.*;

public class NamePrompt extends JFrame {

    private Program.getInfo infoGrabber;
    private JTextField nameField = new JTextField();
    private JTextField passField = new JTextField();
    private JButton client = new JButton("Enter pre-existing server");
    private JButton server = new JButton("Create and enter server");

    public NamePrompt(Program.getInfo infoGrabber)
    {
        this.infoGrabber = infoGrabber;

        setSize(320,160);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setTitle("Join chat room");

        add(nameArea(), BorderLayout.PAGE_START);
        add(confirm(), BorderLayout.PAGE_END);

        setVisible(true);
    }

    private JPanel confirm() {
        JPanel confirmPan = new JPanel();
        confirmPan.setLayout(new BorderLayout());

        confirmPan.add(client, BorderLayout.PAGE_START);
        confirmPan.add(server, BorderLayout.PAGE_END);

        client.addActionListener(e -> {
            if (!checkName())
            {
                return;
            }
            infoGrabber.setName(nameField.getText());
            infoGrabber.setPassword(passField.getText());
            infoGrabber.setCreateServer(false);
            new JoinServer(infoGrabber);
            dispose();
        });

        server.addActionListener(e -> {
            if (!checkName())
            {
                return;
            }
            infoGrabber.setName(nameField.getText());
            infoGrabber.setCreateServer(true);
            infoGrabber.setPassword(passField.getText());
            new SetServer(infoGrabber);
            dispose();
        });

        confirmPan.revalidate();
        return confirmPan;
    }

    private boolean checkName() {
        if (nameField.getText().length() < 2)
        {
            JOptionPane.showMessageDialog(null, "Your name must be longer than 1 character.", "Invalid name", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    private JPanel nameArea() {
        JPanel nameAreaPan = new JPanel();
        JPanel formatPan = new JPanel();
        JPanel encryptionAreaPan = new JPanel();
        formatPan.setLayout(new GridLayout(4, 1));
        nameAreaPan.setLayout(new GridLayout(1, 4));
        encryptionAreaPan.setLayout(new GridLayout(1,4));

        nameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        formatPan.add(new JLabel());
        nameAreaPan.add(new JLabel());
        nameAreaPan.add(new JLabel("Name: "));//Placement 6
        nameAreaPan.add(nameField);//Placement 7
        nameAreaPan.add(new JLabel());

        encryptionAreaPan.add(new JLabel());
        encryptionAreaPan.add(new JLabel("Password: "));
        encryptionAreaPan.add(passField);
        encryptionAreaPan.add(new JLabel());
        formatPan.add(nameAreaPan);
        formatPan.add(encryptionAreaPan);

        return formatPan;
    }
}
