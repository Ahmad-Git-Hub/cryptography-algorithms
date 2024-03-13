public class PolybiusCipher {

    char[][] alphabets = new char[5][5];


    public PolybiusCipher() {
        int ascii = 65;
        for (int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(ascii == 74) {
                    alphabets[i][j] = (char) 75;
                    ascii = 76;
                }
                else {
                    alphabets[i][j] = (char) ascii++;
                }

            }

        }

    }



    public String encrypt(String userInput) {
        StringBuilder encrypted = new StringBuilder();
        userInput = userInput.toUpperCase();
        char c = ' ';
        for (int i = 0; i < userInput.length(); i++) {
            c = userInput.charAt(i);
            if (c == ' ') {
                encrypted.append(' ');
                continue;
            }


            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    if (alphabets[row][col] == c) {
                        encrypted.append(row+1);
                        encrypted.append(col+1);
                    }
                }
            }
        }
        return encrypted.toString();
    }




public String decrypt(String userInput) {
    char[] numbersArray= userInput.toCharArray();
    char c = ' ';
    int indexOf1 = 0;
    int indexOf2 = 1;
    int num1= 0;
    int num2= 0;
    StringBuilder decoded = new StringBuilder();
    for(int i = 0; i < numbersArray.length-1; i++) {
        if(numbersArray[i] == ' ') {
            decoded.append(' ');
        }
        else {
            num1 = numbersArray[i] - '0';
            num2 = numbersArray[i+1] - '0';
            num1--;
            num2--;
            i++;
           decoded.append(alphabets[num1][num2]);

        }
    }

    return decoded.toString();
}
}

