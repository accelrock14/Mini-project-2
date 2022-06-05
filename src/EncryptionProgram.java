import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class EncryptionProgram {

    private ArrayList<Character> list;
    private ArrayList<Character> shuffledList;
    private char character;

    private char[] letters;

    EncryptionProgram() {
        list = new ArrayList();
        shuffledList = new ArrayList();
        character = ' ';
        letters = new char[1000];

        newKey();
    }

    private void newKey() {
        character = ' ';
        list.clear();
        shuffledList.clear();

        for (int i = 32; i < 127; i++) {
            list.add(Character.valueOf(character));
            character++;
        }

        try (FileReader reader = new FileReader("key.txt")) {
            int data = reader.read();
            while (data != -1) {
                System.out.print((char) data);
                shuffledList.add((char) data);
                data = reader.read();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected String encrypt(String filename) {
        try (FileReader reader = new FileReader(filename)) {
            int data = reader.read();
            int i = 0;
            while (data != -1) {
                System.out.print((char) data);
                letters[i] = (char) data;
                i++;
                data = reader.read();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 0; i < letters.length; i++) {

            for (int j = 0; j < shuffledList.size(); j++) {
                if (letters[i] == shuffledList.get(j)) {
                    letters[i] = list.get(j);
                    break;
                }
            }
        }

        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(String.valueOf(letters));
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return String.valueOf(letters);
    }

    protected String decrypt(String filename) {
        try (FileReader reader = new FileReader(filename)) {
            int data = reader.read();
            int i = 0;
            while (data != -1) {
                System.out.print((char) data);
                letters[i] = (char) data;
                i++;
                data = reader.read();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 0; i < letters.length; i++) {

            for (int j = 0; j < list.size(); j++) {
                if (letters[i] == list.get(j)) {
                    letters[i] = shuffledList.get(j);
                    break;
                }
            }
        }

        try {
            FileWriter writer = new FileWriter("decrypted contents.txt");
            writer.write(String.valueOf(letters));
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return String.valueOf(letters);
    }

    protected void saveDecryption(String saveString, String file) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(saveString);
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}