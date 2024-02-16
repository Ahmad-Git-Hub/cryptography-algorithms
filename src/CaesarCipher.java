public class CaesarCipher {
    final private int shift;



    public CaesarCipher(int shift) {
        this.shift = shift;
    }
    public String encrypt(String str) {
        StringBuilder result = new StringBuilder();
        char base;
        for (char c : str.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                base = Character.isUpperCase(c) ? 'A' : 'a';
                result.append((char) (((c - base + shift) % 26) + base));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
    public String decrypt(String str) {
        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                int decryptedChar = ((c - base - shift + 26) % 26) + base;
                result.append((char) decryptedChar);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}

