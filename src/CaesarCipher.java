public class CaesarCipher {
    final private int shift;
    String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public CaesarCipher(int shift) {
        this.shift = shift;
    }

    public String encrypt(String input) {
        input = input.toUpperCase();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            int pos = alphabets.indexOf(currentChar);
            result.append(alphabets.charAt((pos + shift) % 26));
        }
        return result.toString();
    }

    public String decrypt(String input) {
        input = input.toUpperCase();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            int pos = alphabets.indexOf(currentChar);
            result.append(alphabets.charAt((pos - shift + 26) % 26));
        }
        return result.toString();
    }
}

