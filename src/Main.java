public class Main {
    public static void main(String[] args) {


        CaesarCipher test = new CaesarCipher(3);
        System.out.println(test.encrypt("abc xyz ABC XYZ").toString());
        System.out.println(test.decrypt("def abc DEF ABC").toString());
    }
}