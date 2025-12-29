import java.io.*;

public class Exercise17_07 {
    public static void main(String[] args) {
        createSampleData();
        outputData();
    }

    private static void createSampleData() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Exercise17_07.dat"))) {
            output.writeObject(new Loan(5.5, 30, 250000));
            output.writeObject(new Loan(4.0, 15, 150000));
            output.writeObject(new Loan(6.0, 5, 50000));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void outputData() {
        double total = 0;
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("Exercise17_07.dat"))) {
            while (true) {
                Loan loan = (Loan) input.readObject();
                total += loan.getLoanAmount();
            }
        } catch (EOFException ex) {
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.printf("Total loan amount: $%.2f%n", total);
    }
}