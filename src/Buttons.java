import java.awt.FlowLayout;

import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class Buttons extends JFrame implements ActionListener {

    JButton decryptButton = new JButton("Decrypt");
    JButton encryptButton = new JButton("Encrypt");
    EncryptionProgram encryptor = new EncryptionProgram();

    Buttons() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        decryptButton.addActionListener(this);
        encryptButton.addActionListener(this);

        decryptButton.setBounds(100, 100, 100, 25);
        encryptButton.setBounds(200, 100, 100, 25);

        this.add(decryptButton);
        this.add(encryptButton);
        // this.pack();
        this.setSize(400, 400);
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == decryptButton) {

            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setCurrentDirectory(new File(".")); // sets current directory

            int response = fileChooser.showOpenDialog(null); // select file to open
            // int response = fileChooser.showSaveDialog(null); //select file to save

            if (response == JFileChooser.APPROVE_OPTION) {
                // File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                // System.out.println(file);
                String filename = fileChooser.getSelectedFile().getAbsolutePath();
                encryptor.decrypt(filename);
            }
        }
        if (e.getSource() == encryptButton) {

            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setCurrentDirectory(new File(".")); // sets current directory

            int response = fileChooser.showOpenDialog(null); // select file to open

            if (response == JFileChooser.APPROVE_OPTION) {
                String filename = fileChooser.getSelectedFile().getAbsolutePath();
                encryptor.encrypt(filename);
            }
        }
    }
}