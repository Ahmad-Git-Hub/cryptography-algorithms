import javax.security.sasl.SaslServer;

public class AtbashCipher {
    public String encrypt(String message) {
        String cipher = "";
        message = message.toUpperCase();
        for (char letter : message.toCharArray()) {
            if (Character.isLetter(letter)) {
                char transformed = (char) ('Z' - (letter - 'A'));
                cipher += transformed;
            } else {
                cipher += letter;
            }
        }
        return cipher;
    }
}
