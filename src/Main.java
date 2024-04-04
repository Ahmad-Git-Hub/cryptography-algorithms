
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
                case MenuOptions.KEYWORD_CIPHER:
                    handelKeywordCipher();
                    break;
                case MenuOptions.POLYBIUS_CIPHER:
                    handelPolybiusCipher();
                    break;
                case MenuOptions.PLAYFAIR_CIPHER:
                    handelPlayfair();
                case MenuOptions.EXIT:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != MenuOptions.EXIT);
    }

    private static void handelPlayfair() {
        String userInput;
        try {
            Playfair testPlayfairCipher = new Playfair("IRAQ");
            userInput = promptUser("encrypt");
            String cipherText = testPlayfairCipher.encrypt(userInput);
            System.out.println("Encrypted text with Playfair algorithm: " + cipherText);

            userInput = promptUser("decrypt");

            String originalText = testPlayfairCipher.decrypt(cipherText);
            System.out.println("Decrypted text with Playfair algorithm: " + originalText);
        } catch (InputMismatchException e) {
                System.out.println("A string is required");
            }
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

    private static void handelPolybiusCipher() {

        String userInput;
        try {

            PolybiusCipher testPolybiusCipher = new PolybiusCipher();
            userInput = promptUser("encrypt");
            String cipherText = testPolybiusCipher.encrypt(userInput);
            System.out.println("Encrypted text with polybius algorithm: " + cipherText);

            userInput = promptUser("decrypt");

            String originalText = testPolybiusCipher.decrypt(userInput);
            System.out.println("Decrypted text with polybius algorithm: " + originalText);
        }
        catch (InputMismatchException e){
            System.out.println("A string is required");
        }

    }

    private static void handelKeywordCipher() {
        KeywordCipher.run();

    }
    public static void handleCaesarCipher() {
        String userInput;

        try {
            System.out.print("Enter number of shift: ");
            int shift = scanner.nextInt();
            scanner.nextLine();
            CaesarCipher testCaesarCipher = new CaesarCipher(shift);
            userInput = promptUser("encrypt");
            String cipherText = testCaesarCipher.encrypt(userInput);
            System.out.println("Encrypted text with Caesar algorithm: " + cipherText);

            userInput = promptUser("decrypt");
            String originalText = testCaesarCipher.decrypt(userInput);
            System.out.println("Decrypted text with Caesar algorithm: " + originalText);
        }
        catch (InputMismatchException e){
            System.out.println("A number is required");
        }
    }

    public static void handleAtbashCipher() {
        String userInput = promptUser("encrypt");
        AtbashCipher testAtbashCipher = new AtbashCipher();
        String encryptionResult = testAtbashCipher.encrypt(userInput);
        System.out.println("Encrypted text with Atbash: " + encryptionResult);

        userInput = promptUser("decrypt");
        String decryptionResult = testAtbashCipher.decrypt(userInput);
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
        System.out.println("║ \033[1;36m3. Keyword Cipher\033[0m          ║");
        System.out.println("║ \033[1;36m4. Polybuis Cipher \033[0m        ║");
        System.out.println("║ \033[1;36m5. Playfair Cipher \033[0m        ║");
        System.out.println("║ \033[1;31m0. Exit\033[0m                    ║");
        System.out.println("╚════════════════════════════╝");
        System.out.print("\033[1mEnter your choice:\033[0m ");
    }

    static class MenuOptions {
        public static final int CAESAR_CIPHER = 1;
        public static final int ATBASH_CIPHER = 2;
        public static final int KEYWORD_CIPHER = 3;
        public static final int POLYBIUS_CIPHER = 4;
        public static final int PLAYFAIR_CIPHER = 5;

        public static final int EXIT = 0;
    }


}
//        String message = "This is a secret message";
//        String key = "swordfish";
//        System.out.println("String: "+ message);
//        System.out.println("key: " + key);
//
//        String cipherText = BeaufortCipher.encrypt(message, key);
//        System.out.println("Encrypted Text: " + cipherText);
//
//        String plainText = BeaufortCipher.decrypt(cipherText, key);
//        System.out.println("Decrypted Text: " + plainText);



//        String key = "LEMON";
//        String message = "ATTACKATDAWN";
//        String encryptedMsg = VigenereCipher.encrypt(message, key);
//        System.out.println("String: " + message);
//        System.out.println("Encrypted message: " + encryptedMsg);
//        System.out.println("Decrypted message: " + VigenereCipher.decrypt(encryptedMsg, key));