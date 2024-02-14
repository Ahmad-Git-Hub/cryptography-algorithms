public class CaesarCipher {
    private final int shift;
    public CaesarCipher(int shift) {
        this.shift = shift;
    }
    public StringBuilder encrypt(String str) {
        StringBuilder cipher = new StringBuilder();
        int t = 0;
        for(int i = 0; i < str.length(); i++) {
            t = (int) str.charAt(i);
            for(int j = 0; j < shift; j++) {
                t++;
                if(t > 90 && t < 97) {
                    t = 65;
                }
                else if(t > 122) {
                    t = 97;
                }
            }
            cipher.append((char) t);
        }
        return cipher;
    }
    public String decrypt(String str) {
        return "";
    }
}
