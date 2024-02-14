public class Main {
    // abcdefghijklmnopqrstuvwxyz
    public static void main(String[] args) {
        CaesarCipher test = new CaesarCipher(3);
        System.out.println(test.encrypt("abc xyz ABC XYZ").toString());


    }
}