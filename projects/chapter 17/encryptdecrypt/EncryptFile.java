package encryptdecrypt;

import java.io.*;
import java.util.Scanner;

public class EncryptFile {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter input file name: ");
            String inFile = input.nextLine();
            System.out.print("Enter output file name: ");
            String outFile = input.nextLine();

            try (FileInputStream fis = new FileInputStream(inFile);
                 FileOutputStream fos = new FileOutputStream(outFile)) {
                int byteData;
                while ((byteData = fis.read()) != -1) {
                    fos.write(byteData + 5);
                }
                System.out.println("Encryption complete.");
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }
}