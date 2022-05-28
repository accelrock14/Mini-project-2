import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class EncryptionProgram {

    private Scanner scanner;
    private ArrayList<Character> list;
    private ArrayList<Character> shuffledList;
    private char character;

    private char[] letters;

    EncryptionProgram() {
        scanner = new Scanner(System.in);
        list = new ArrayList();
        shuffledList = new ArrayList();
        character = ' ';

        letters = new char[1000];

        newKey();
        // askQuestion();
    }

    private void askQuestion() {
        while (true) {
            System.out.println("********************************************");
            System.out.println("What do you want to do?");
            System.out.println("(N)ewKey,(G)etKey,(E)ncrypt,(D)ecrypt,(Q)uit");
            char response = Character.toUpperCase(scanner.nextLine().charAt(0));

            switch (response) {
                case 'N':
                    newKey();
                    break;
                case 'G':
                    getKey();
                    break;
                case 'E':
                    encrypt("fitness.txt");
                    break;
                case 'D':
                    decrypt("pacer.txt");
                    break;
                case 'Q':
                    quit();
                    break;
                default:
                    System.out.println("Not a valid answer :(");
            }
        }
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

        // shuffledList = new ArrayList(list);
        // Collections.shuffle(shuffledList);
        System.out.println("*A new key has been generated*");

    }

    private void getKey() {
        System.out.println("Key: ");
        for (Character x : list) {
            System.out.print(x);
        }
        System.out.println();
        for (Character x : shuffledList) {
            System.out.print(x);
        }
        System.out.println();
    }

    protected void encrypt(String filename) {
        System.out.println("Enter a message to be encrypted: ");

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

        // String message = scanner.nextLine();

        /*
         * letters = message.toCharArray();
         */
        for (int i = 0; i < letters.length; i++) {

            for (int j = 0; j < shuffledList.size(); j++) {
                if (letters[i] == shuffledList.get(j)) {
                    letters[i] = list.get(j);
                    break;
                }
            }
        }
        System.out.println("Encrypted: ");
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(String.valueOf(letters));
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.print(String.valueOf(letters));

        System.out.println();
    }

    protected void decrypt(String filename) {
        System.out.println("Enter a message to be decrypted: ");
        // String message = scanner.nextLine();

        // letters = message.toCharArray();

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
        System.out.println("Decrypted: ");
        System.out.print(String.valueOf(letters));
        System.out.println();

    }

    private void quit() {
        System.out.println("Thank you, have a nice day!");
        System.exit(0);
    }
}