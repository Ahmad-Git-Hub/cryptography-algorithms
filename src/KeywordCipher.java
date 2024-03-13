import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class KeywordCipher {
public static void run() {
    Scanner scanner = new Scanner(System.in);
    // all alphabets
        List<Character> all_alpha = new ArrayList<>(Arrays.asList(
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        ));
         System.out.print("enter keyword: ");

         String keyword = scanner.nextLine();
        keyword = keyword.toUpperCase();
        List<Character> keyword1 = new ArrayList<>();

        // remove duplicate elements from keyword
        for (char c : keyword.toCharArray()) {
            if (!keyword1.contains(c)) {
                keyword1.add(c);
            }
        }

        // convert ciphertext to list
    System.out.print("enter plain text: ");
        String ciphertext = scanner.nextLine();
        ciphertext = ciphertext.toUpperCase();
        List<Character> ct = new ArrayList<>();
        for (char c : ciphertext.toCharArray()) {
            if (c != ' ') {
                ct.add(c);
            }
        }

        // stores the encryption list
        List<Character> encrypting = new ArrayList<>(keyword1);
        for (char c : all_alpha) {
            if (!encrypting.contains(c)) {
                encrypting.add(c);
            }
        }

        // removes spaces from the encryption list
        encrypting.remove(Character.valueOf(' '));

        // list and stores it in ciphertext
        StringBuilder message = new StringBuilder();
        for (char c : ct) {
            if (c != ' ') {
                int index = encrypting.indexOf(c);
                message.append(all_alpha.get(index));
            } else {
                message.append(' ');
            }
        }

        System.out.println("Keyword : " + keyword);
        System.out.println("Ciphered Text : " + ciphertext);
        System.out.println("Message before Ciphering : " + message);
    }
}
