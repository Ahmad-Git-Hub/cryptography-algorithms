public class Trifid {
    char[][] layer1 = new char[3][3];
    char[][] layer2 = new char[3][3];
    char[][] layer3 = new char[3][3];
    String text = "";
    int[] layer;
    int[] cols;
    int[] rows;
    int layer1_char = 0;
    int layer2_char = 9;
    int layer3_char = 18;
    String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ.";
    
    public Trifid() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                layer1[i][j] = alphabets.charAt(layer1_char++);
                layer2[i][j] = alphabets.charAt(layer2_char++);
                layer3[i][j] = alphabets.charAt(layer3_char++);
            }
            System.out.println();
        }
    }

    public String encrypt(String text) {
        int index = 0;
        text = process(text);
        this.text = text;
        StringBuilder cipher = new StringBuilder();
        layer = new int[text.length()];
        cols = new int[text.length()];
        rows = new int[text.length()];
        for(int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            int ascii = currentChar - 'A';
            if(ascii <= 8) {
                for(int i1 = 0; i1 < 3; i1++) {
                    for(int j1 = 0; j1<3; j1++) {
                        if(layer1[i1][j1] == currentChar) {
                            cols[index] = j1;
                            rows[index] = i1;
                            layer[index] = 0;
                            break;

                        }
                    }
                }
            }
            else if(ascii > 8 && ascii < 18){
                for(int i2 = 0; i2 < 3; i2++) {
                    for(int j2 = 0; j2<3; j2++) {
                        if(layer2[i2][j2] == currentChar) {
                            cols[index] = j2;
                            rows[index] = i2;
                            layer[index] = 1;
                            break;

                        }
                    }
                }
            }
            else if(ascii >= 18 && ascii <= 26) {
                for(int i3 = 0; i3 < 3; i3++) {
                    for(int j3 = 0; j3 <3; j3++) {
                        if(layer3[i3][j3] == currentChar) {
                            cols[index] = j3;
                            rows[index] = i3;
                            layer[index] = 2;
                            break;
                        }
                    }
                }
            }
            index++;
        }

        displayArrays();
        int[] numbers = mergeArraysNumbers();
        for(int i = 0; i < numbers.length-2; i+=3) {
            if(numbers[i] == 0) {
                cipher.append(layer1[ numbers[i+2]] [numbers[i+1]]);
            }
            else if(numbers[i] == 1) {
                cipher.append(layer2[ numbers[i+2]] [numbers[i+1]]);
            }
            else{
                cipher.append(layer3[ numbers[i+2]] [numbers[i+1]]);

            }
        }
        return cipher.toString();

    }

    public int[] mergeArraysNumbers() {
        int[] numbers = new int[text.length()*3];
        int index = 0;
        int flag = 0;
        System.out.println();
        for(int i = 0; i < text.length()*3; i++) {
            if(index == text.length()) {
                flag++;
                index = 0;
            }
            if(flag == 0) {
                numbers[i] = layer[index++];
            }
            else if(flag == 1) {
                numbers[i] = cols[index++];
            }
            else if(flag == 2) {
                numbers[i] = rows[index++];
            }

        }
        return numbers;
    }

    public void displayArrays(){
        for(int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i)+"  |  ");
        }
        System.out.println("\nlayers");
        for(int i = 0; i < text.length(); i++) {

            System.out.print(layer[i]+"  |  ");
        }
        System.out.println("\ncols");
        for(int i = 0; i < text.length(); i++) {
            System.out.print(cols[i]+"  |  ");
        }
        System.out.println("\nrows");
        for(int i = 0; i < text.length(); i++) {
            System.out.print(rows[i]+"  |  ");
        }

    }
    public String process(String text) {
        text = text.toUpperCase();
        return text.replaceAll(" ", "");
    }
}
