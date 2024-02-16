public class Atbash {

    public Atbash() {}

    public void encrypt(String str) {
        StringBuilder result = new StringBuilder();
        int base;
        for(char c : str.toCharArray()) {
            if(Character.isAlphabetic(c)) {
                base = Character.isUpperCase(c) ? 'A' : 'a';
                result.append((char) (25 - (c - base) + base));
            }
            else {
                result.append(c);
            }
        }
        System.out.println(result.toString());
    }
}
