import java.io.*;
import java.util.*;

public class Exercise12_15 {
    public static void main(String[] args) throws IOException {
        File file = new File("Exercise12_15.txt");

        if (!file.exists()) {
            try (PrintWriter writer = new PrintWriter(file)) {
                Random rand = new Random();
                for (int i = 0; i < 100; i++) {
                    writer.print(rand.nextInt(1000) + " ");
                }
            }
        }

        List<Integer> numbers = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextInt()) {
                numbers.add(scanner.nextInt());
            }
        }

        Collections.sort(numbers);

        for (int num : numbers) {
            System.out.print(num + " ");
        }
    }
}
