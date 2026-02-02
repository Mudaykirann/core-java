import java.util.*;
import java.io.*;


class Reader {
    public void fileContentReader() {
        try (FileReader reader = new FileReader("note.txt")) {
            int character;
            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class IOOperaionsJava {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int countofA = 0;
        String content = "Hello! This is a text note in Java.";

        // Writing to file
        try (FileWriter writer = new FileWriter("note.txt")) {
            writer.write(content);
            System.out.println("Content written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reading from file - FIRST READ
        System.out.println("\n=== Initial File Content ===");
        try (FileReader reader = new FileReader("note.txt")) {
            int character;
            while ((character = reader.read()) != -1) {
                char c = (char) character;
                System.out.print(c);
                if (c == 'a' || c == 'A') countofA++;
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("The Count of A Alphabet in the sentence is: " + countofA);

        // How to Append the Content in a file without deleting the previous content
        String data;
        System.out.println("\nEnter the data to add to the file: ");
        data = sc.nextLine();

        // Appending to file (true flag enables append mode)
        try (FileWriter writer = new FileWriter("note.txt", true)) {
            writer.write("\n" + data); // Add newline before appending
            System.out.println("Data appended successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // SECOND READ - to verify appended content
        System.out.println("\n=== File Content After Appending ===");
        Reader r = new Reader();
        r.fileContentReader();
        sc.close(); // Close scanner
    }
}