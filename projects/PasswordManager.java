package src;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class PasswordManager {
    private static final String CRED = "credentials";
    private static final String KEY = "secret.key";
    private static SecretKey secretKey;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        File key_file = new File(KEY);

        if (!key_file.exists()) {
            System.out.print("Create master password: ");
            key_compare(scanner.nextLine());
            System.out.println("Vault created");
        } else {
            while (true) {
                System.out.print("Enter password: ");
                if (key_compare(scanner.nextLine())) {
                    System.out.println("Access granted");
                    break;
                } else {
                    System.out.println("Wrong password, try again.");
                }
            }
        }

        new File(CRED).mkdirs();

        while (true) {
            System.out.println("\n1. Add credential\n2. View credentials\n3. Remove credential\n4. Exit");
            System.out.print("Choose: ");
            int choice = get_choice(scanner);

            switch (choice) {
                case 1 -> add_cred(scanner);
                case 2 -> view_cred(scanner);
                case 3 -> del_cred(scanner);
                case 4 -> {
                    System.out.println("closing...");
                    return;
                }
            }
        }
    }

    private static boolean key_compare(String password) {
        File key = new File(KEY);

        try {
            if (key.exists()) {
                secretKey = decrypt_key(Files.readAllBytes(key.toPath()), password);
                return true;
            } else {
                secretKey = KeyGenerator.getInstance("AES").generateKey();
                Files.write(key.toPath(), encrypt_key(secretKey, password));
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private static byte[] encrypt_key(SecretKey key, String password) throws Exception {
        SecretKeySpec derivedKey = derive_key(password);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, derivedKey, new IvParameterSpec(new byte[16]));
        return cipher.doFinal(key.getEncoded());
    }

    private static SecretKey decrypt_key(byte[] encrypted, String password) throws Exception {
        SecretKeySpec derivedKey = derive_key(password);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, derivedKey, new IvParameterSpec(new byte[16]));
        return new SecretKeySpec(cipher.doFinal(encrypted), "AES");
    }

    private static SecretKeySpec derive_key(String password) throws Exception {
        return new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
                .generateSecret(new PBEKeySpec(password.toCharArray(), "fixed_salt_1234".getBytes(), 65536, 256))
                .getEncoded(), "AES");
    }

    private static void add_cred(Scanner scanner) throws Exception {
        System.out.print("App name: ");
        String app = scanner.nextLine();
        System.out.print("Username: ");
        String user = scanner.nextLine();
        System.out.print("Password: ");
        String pass = scanner.nextLine();

        String encrypted_name = Base64.getUrlEncoder().withoutPadding().encodeToString(encrypt_to_bytes(app))
                .replaceAll("[^a-zA-Z0-9-_]", "_");
        Files.write(Paths.get(CRED, encrypted_name + ".enc"),
                (encrypt(user) + "\n" + encrypt(pass)).getBytes(StandardCharsets.UTF_8));
        System.out.println("Credential saved");
    }

    private static void view_cred(Scanner scanner) throws Exception {
        File[] files = cred_files();
        if (files.length == 0) {
            System.out.println("No stored credentials");
            return;
        }

        cred_list(files);
        System.out.print("View details (0 to cancel): ");
        int choice = get_choice(scanner, files.length);
        if (choice > 0)
            show_cred(files[choice - 1]);
    }

    private static void del_cred(Scanner scanner) throws Exception {
        File[] files = cred_files();
        if (files.length == 0) {
            System.out.println("Nothing to remove");
            return;
        }

        cred_list(files);
        System.out.print("Remove which (0 to cancel): ");
        int choice = get_choice(scanner, files.length);
        if (choice > 0 && files[choice - 1].delete()) {
            System.out.println("Credential removed");
        }
    }

    private static int get_choice(Scanner scanner) {
        while (true) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                return choice;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Try again: ");
                scanner.nextLine();
            }
        }
    }

    private static int get_choice(Scanner scanner, int max) {
        while (true) {
            int choice = get_choice(scanner);
            if (choice >= 0 && choice <= max)
                return choice;
            System.out.print("Invalid number. Try again: ");
        }
    }

    private static File[] cred_files() {
        File folder = new File(CRED);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".enc"));
        return files != null ? files : new File[0];
    }

    private static void cred_list(File[] files) throws Exception {
        System.out.println("\nStored credentials:");
        for (int i = 0; i < files.length; i++) {
            String encrypted_name = files[i].getName().replace(".enc", "");
            byte[] encryptedApp = Base64.getUrlDecoder().decode(encrypted_name);
            System.out.printf("%d. %s%n", i + 1, decrypt_from_bytes(encryptedApp));
        }
    }

    private static void show_cred(File file) throws Exception {
        String[] data = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8).split("\n");
        System.out.println("\nUsername: " + decrypt(data[0]));
        System.out.println("Password: " + decrypt(data[1]));
    }

    private static byte[] encrypt_to_bytes(String text) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
    }

    private static String decrypt_from_bytes(byte[] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(encrypted), StandardCharsets.UTF_8);
    }

    private static String encrypt(String text) throws Exception {
        return Base64.getUrlEncoder().encodeToString(encrypt_to_bytes(text));
    }

    private static String decrypt(String text) throws Exception {
        return decrypt_from_bytes(Base64.getUrlDecoder().decode(text));
    }
}