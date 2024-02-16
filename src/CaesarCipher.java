public class CaesarCipher {
    final private int shift;



    public CaesarCipher(int shift) {
        this.shift = shift;
    }
    public StringBuilder encrypt(String str) {
        StringBuilder cipher = new StringBuilder();
        int ascii;
        for(int i = 0; i < str.length(); i++) {
            ascii = str.charAt(i);
            if(ascii == ' ') {
                cipher.append(' ');
                continue;
            }
            for(int j = 0; j < shift; j++) {
                ascii++;
                if(ascii > 90 && ascii < 97) {
                    ascii = 65;
                }
                else if(ascii > 122) {
                    ascii = 97;
                }
            }
            cipher.append((char) ascii);
        }
        return cipher;
    }
    public StringBuilder decrypt(String str) {
        StringBuilder original_text = new StringBuilder();
        int ascii;
        for(int i = 0; i < str.length(); i++) {
            ascii = str.charAt(i);
            if(ascii == ' ') {
                original_text.append(' ');
                continue;
            }
            for(int j = 0; j < shift; j++) {
                ascii--;
                if(ascii < 65) {
                    ascii = 90;
                }
                else if(ascii > 90 && ascii < 97) {
                    ascii = 122;
                }
            }
            original_text.append((char) ascii);
        }
        return original_text;
    }
}

