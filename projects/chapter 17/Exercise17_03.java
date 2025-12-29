import java.io.*;
import java.util.Random;

public class Exercise17_03 {

    public static void createFile() throws IOException {
        try (DataOutputStream out = new DataOutputStream(
                new FileOutputStream("Exercise17_03.dat", true))) {
            Random rand = new Random();
            for (int i = 0; i < 100; i++) {
                out.writeInt(rand.nextInt(1000));
            }
        }
    }

    public static long sumFile() throws IOException {
        long sum = 0;
        try (DataInputStream in = new DataInputStream(
                new FileInputStream("Exercise17_03.dat"))) {
            while (true) {
                sum += in.readInt();
            }
        } catch (EOFException e) {
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        createFile();
        System.out.println("Sum = " + sumFile());
    }
}