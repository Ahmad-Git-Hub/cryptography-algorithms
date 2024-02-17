import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
        scanner.close();
    }

    public static void menu() {
        int choice;
        do {
            displayMenu();
            choice = getUserChoice();

            switch (choice) {
                case MenuOptions.CAESAR_CIPHER:
                    handleCaesarCipher();
                    break;
                case MenuOptions.ATBASH_CIPHER:
                    handleAtbashCipher();
                    break;
                case MenuOptions.EXIT:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != MenuOptions.EXIT);
    }

    public static int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public static void handleCaesarCipher() {
        String userInput = promptUser("encrypt");

        try {
            System.out.print("Enter number of shift: ");
            int shift = scanner.nextInt();
            scanner.nextLine();
            CaesarCipher testCaesar = new CaesarCipher(shift);
            String cipherText = testCaesar.encrypt(userInput);
            System.out.println("Encrypted text with Caesar algorithm: " + cipherText);

            userInput = promptUser("decrypt");
            String originalText = testCaesar.decrypt(userInput);
            System.out.println("Decrypted text with Caesar algorithm: " + originalText);
        }
        catch (InputMismatchException e){
            System.out.println("A number is required");
        }
    }

    public static void handleAtbashCipher() {
        String userInput = promptUser("encrypt");
        Atbash testAtbash = new Atbash();
        String encryptionResult = testAtbash.encrypt(userInput);
        System.out.println("Encrypted text with Atbash: " + encryptionResult);

        userInput = promptUser("decrypt");
        String decryptionResult = testAtbash.decrypt(userInput);
        System.out.println("Decrypted text with Atbash: " + decryptionResult);
    }

    public static String promptUser(String type) {
        if (type.equals("encrypt"))
            System.out.print("Enter a string to encrypt: ");
        else if (type.equals("decrypt")) {
            System.out.print("Enter a string to decrypt: ");
        }
        return scanner.nextLine();
    }

    public static void displayMenu() {
        System.out.println("╔════════════════════════════╗");
        System.out.println("║           \033[1;33mMenu\033[0m             ║");
        System.out.println("╠════════════════════════════╣");
        System.out.println("║ \033[1;36m1. Caesar cipher\033[0m           ║");
        System.out.println("║ \033[1;36m2. Atbash Cipher\033[0m           ║");
        System.out.println("║ \033[1;36m3. Option 3\033[0m                ║");
        System.out.println("║ \033[1;31m0. Exit\033[0m                    ║");
        System.out.println("╚════════════════════════════╝");
        System.out.print("\033[1mEnter your choice:\033[0m ");
    }

    static class MenuOptions {
        public static final int CAESAR_CIPHER = 1;
        public static final int ATBASH_CIPHER = 2;
        public static final int EXIT = 0;
    }
}
