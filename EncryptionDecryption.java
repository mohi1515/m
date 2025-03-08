//mohamed mohey
//2305186
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class EncryptionDecryption {
 

    public static String caesarEncrypt(String text, int shift) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                encrypted.append((char) ((c - base + shift) % 26 + base));
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }

    public static String caesarDecrypt(String text, int shift) {
        return caesarEncrypt(text, 26 - shift);
    }

    public static String vigenereEncrypt(String text, String key) {
        StringBuilder encrypted = new StringBuilder();
        key = key.toUpperCase();
        int keyIndex = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                encrypted.append((char) ((c - base + key.charAt(keyIndex % key.length()) - 'A') % 26 + base));
                keyIndex++;
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }

    public static String vigenereDecrypt(String text, String key) {
        StringBuilder decrypted = new StringBuilder();
        key = key.toUpperCase();
        int keyIndex = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                decrypted.append((char) ((c - base - (key.charAt(keyIndex % key.length()) - 'A') + 26) % 26 + base));
                keyIndex++;
            } else {
                decrypted.append(c);
            }
        }
        return decrypted.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());

        System.out.println("Hello! Choose the technique you want to use:");
        System.out.println("1. Caesar");
        System.out.println("2. Vigenère");

        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Do you want to encrypt or decrypt?");
        System.out.println("1. Encrypt");
        System.out.println("2. Decrypt");
        int action = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("Enter the text: ");
            String text = scanner.nextLine();
            System.out.print("Enter the shift value: ");
            int shift = scanner.nextInt();

            if (action == 1) {
                System.out.println("Encrypted text: " + caesarEncrypt(text, shift));
            } else {
                System.out.println("Decrypted text: " + caesarDecrypt(text, shift));
            }
        } else if (choice == 2) {
            System.out.print("Enter the text: ");
            String text = scanner.nextLine();
            System.out.print("Enter the key: ");
            String key = scanner.nextLine();

            if (action == 1) {
                System.out.println("Encrypted text: " + vigenereEncrypt(text, key));
            } else {
                System.out.println("Decrypted text: " + vigenereDecrypt(text, key));
            }
        } else {
            System.out.println("Invalid choice!");
 }
}


