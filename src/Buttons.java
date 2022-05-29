import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import java.awt.Desktop;

public class Buttons extends JFrame implements ActionListener {

    JButton decryptButton = new JButton("Decrypt");
    JButton encryptButton = new JButton("Encrypt");
    JButton saveButton = new JButton("Save");
    EncryptionProgram encryptor = new EncryptionProgram();
    Desktop desktop = Desktop.getDesktop();
    File file;
    String filename;
    String decryptedString;

    Buttons() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        decryptButton.addActionListener(this);
        encryptButton.addActionListener(this);
        saveButton.addActionListener(this);

        decryptButton.setBounds(100, 100, 100, 25);
        encryptButton.setBounds(200, 100, 100, 25);
        saveButton.setBounds(150, 200, 100, 25);

        this.add(decryptButton);
        this.add(encryptButton);
        this.add(saveButton);
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
                file = new File("decrypted contents.txt");
                // System.out.println(file);
                filename = fileChooser.getSelectedFile().getAbsolutePath();
                decryptedString = encryptor.decrypt(filename);
                // new DecryptionProgram(decryptedString);
                try {
                    desktop.open(file);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
        if (e.getSource() == encryptButton) {

            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setCurrentDirectory(new File(".")); // sets current directory

            int response = fileChooser.showOpenDialog(null); // select file to open

            if (response == JFileChooser.APPROVE_OPTION) {
                filename = fileChooser.getSelectedFile().getAbsolutePath();
                file = new File(filename);
                decryptedString = encryptor.encrypt(filename);
            }
        }
        if (e.getSource() == saveButton) {
            encryptor.saveDecryption(decryptedString, filename);
            try {
                desktop.open(new File(filename));
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
}