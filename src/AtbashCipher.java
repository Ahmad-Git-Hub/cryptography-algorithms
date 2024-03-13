public class AtbashCipher {


    public String encrypt(String str) {
        StringBuilder result = new StringBuilder();
        int base;
        for(char c : str.toCharArray()) {
            if(Character.isAlphabetic(c)) {
                base = Character.isUpperCase(c) ? 'A' : 'a';
                result.append((char) ((2*base) - c + 25));
            }
            else {
                result.append(c);
            }
        }
        return result.toString();

    }

    public String decrypt(String str) {
        StringBuilder result = new StringBuilder();
        int base;
        int temp = 0;
        for(char c : str.toCharArray()) {
            if(Character.isAlphabetic(c)) {
                base = Character.isUpperCase(c) ? 'A' : 'a';
                temp = 25 - (c - base) ;
                result.append((char) (temp+base) );
            }
            else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
