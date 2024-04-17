public class Trifid {

    char[][][] layers = new char[3][3][3];
    String text = "";
    int[] layerNumbers;
    int[] colNumbers;
    int[] rowNumbers;

    final String ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ.";

    public Trifid() {
        int charIndex = 0;
        for (int l = 0; l < 3; l++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    layers[l][i][j] = ALPHABETS.charAt(charIndex++);
                }
            }
        }

        for (char[][] chars : layers) {
            for (char[] aChar : chars) {
                for (char c : aChar) {
                    System.out.print(c + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }


    public String encrypt(String text) {
        int index = 0;
        text = process(text);
        this.text = text;
        StringBuilder cipher = new StringBuilder();
        layerNumbers = new int[text.length()];
        colNumbers = new int[text.length()];
        rowNumbers = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            boolean found = false;
            char currentChar = text.charAt(i);
            for (int l = 0; l < 3; l++) {
                if (!found) {
                    for (int r = 0; r < 3; r++) {
                        if (!found) {
                            for (int c = 0; c < 3; c++) {
                                if (layers[l][r][c] == currentChar) {
                                    layerNumbers[index] = l;
                                    rowNumbers[index] = r;
                                    colNumbers[index] = c;
                                    found = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            index++;
        }
        displayArrays();

        int[] numbers = mergeArraysNumbers();
        for(int i = 0; i < numbers.length-2; i+=3) {
            cipher.append(layers[numbers[i]][numbers[i+2]] [numbers[i+1]]);
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
                numbers[i] = layerNumbers[index++];
            }
            else if(flag == 1) {
                numbers[i] = colNumbers[index++];
            }
            else if(flag == 2) {
                numbers[i] = rowNumbers[index++];
            }

        }
        return numbers;
    }

    public String decrypt(String cipher){
        int[] numbers = new int[cipher.length()*3];
        int index = 0;
        for(char currentChar : cipher.toCharArray()) {
            for (int l = 0; l < 3; l++) {
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        if(layers[l][r][c] == currentChar) {
                            numbers[index++] = l;
                            numbers[index++] = c;
                            numbers[index++] = r;
                        }
                    }
                }
            }

        }


        int layerIndex = 0;
        int colIndex = (numbers.length / 3);
        int rowIndex = (numbers.length - colIndex);
        StringBuilder decryptedText = new StringBuilder();
        int i = 0;
        while(i < numbers.length/3) {
           decryptedText.append(layers[numbers[layerIndex++]][numbers[rowIndex++]][numbers[colIndex++]]);
           i++;
        }
        return decryptedText.toString();
    }

    public void displayArrays(){
        System.out.println("\ncharacters");
        for(int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i)+"  |  ");
        }
        System.out.println("\nlayers");
        for(int i = 0; i < text.length(); i++) {
            System.out.print(layerNumbers[i]+"  |  ");
        }
        System.out.println("\ncols");
        for(int i = 0; i < text.length(); i++) {
            System.out.print(colNumbers[i]+"  |  ");
        }
        System.out.println("\nrows");
        for(int i = 0; i < text.length(); i++) {
            System.out.print(rowNumbers[i]+"  |  ");
        }

    }
    public String process(String text) {
        text = text.toUpperCase();
        return text.replaceAll(" ", "");
    }
}
