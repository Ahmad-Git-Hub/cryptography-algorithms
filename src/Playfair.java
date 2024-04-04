import java.util.HashSet;
import java.util.Set;

public class Playfair {
    private final char[][] matrix = new char[5][5];
    public Playfair(String keyword) {
        fillMatrix(keyword);
    }

    public void fillMatrix(String str) {
        str = removeRedundantChars(str).toUpperCase();
        Set<Character> usedChars = new HashSet<>();
        int row = 0;
        int col = 0;
        for (char c : str.toCharArray()) {
            if (!usedChars.contains(c)) {
                matrix[row][col] = c;
                usedChars.add(c);
                col++;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
        }

        // Fill matrix with remaining alphabets
        char c = 'A';
        while (row < 5) {
            if (c != 'J' && !usedChars.contains(c)) {
                matrix[row][col] = c;
                usedChars.add(c);
                col++;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
            c++;
        }

        for (char[] rowArray : matrix) {
            for (char cell : rowArray) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }


    public static String removeRedundantChars(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (sb.indexOf(String.valueOf(c)) == -1 && str.charAt(i) != ' ') {
                sb.append(c);
            }
        }
        return sb.toString();

    }

    public String encrypt(String userInput) {
        userInput = processUserInput(userInput);
        StringBuilder cipher = new StringBuilder();
        for(int i = 0; i < userInput.length() - 1; i+=2) {
            int row1 = 0;
            int col1 = 0;
            int row2 = 0;
            int col2 = 0;
            boolean letter1Found = false;
            boolean letter2Found = false;
            for (int row = 0; row < 5; row++) {
                if(letter1Found && letter2Found) {
                    break;
                }
                for (int col = 0; col < 5; col++) {
                    if (!letter1Found) {
                        if (matrix[row][col] == userInput.charAt(i)) {
                            row1 = row;
                            col1 = col;
                            letter1Found = true;
                        }
                    }
                    if (!letter2Found) {
                        if (matrix[row][col] == userInput.charAt(i + 1)) {
                            row2 = row;
                            col2 = col;
                            letter2Found = true;
                        }
                    }
                }
            }

            // Now we check for the three cases
            // case 1 same different row and column
            if (row1 != row2 && col1 != col2) {
//                System.out.println("case 1");
//                System.out.println("letters " + userInput.charAt(i) + " and " + userInput.charAt(i + 1));
//                System.out.println("row1=" + row1 + " col1=" + col1);
                cipher.append(matrix[row1][col2]);
                cipher.append(matrix[row2][col1]);
//                System.out.println("first letter" + matrix[row1][col2]);
//                System.out.println("second letter" + matrix[row2][col1]);


            }
            // case 2 same row different column
            else if (row1 == row2 && col1 != col2) {
//                System.out.println("case 2");
//                System.out.println("letters " + userInput.charAt(i) + " and " + userInput.charAt(i + 1));
                col1 = ++col1 % 5;
                col2 = ++col2 % 5;
                cipher.append(matrix[row1][col1]);
                cipher.append(matrix[row2][col2]);

            }
            // case 3 different row and same column
            else if (row1 != row2) {
//                System.out.println("case 3");
//                System.out.println("letters " + userInput.charAt(i) + " and " + userInput.charAt(i + 1));
                row1 = ++row1 % 5;
                row2 = ++row2 % 5;
                cipher.append(matrix[row1][col1]);
                cipher.append(matrix[row2][col2]);
            }
        }

        return cipher.toString();
    }

    public String processUserInput(String userInput){
        StringBuilder result = new StringBuilder();
        for(int i = 0 ;i < userInput.length(); i++) {
            if(userInput.charAt(i) != ' ') {
                result.append(userInput.charAt(i));
            }
        }
        if((result.length() % 2) > 0) {
            result.append('X');
        }
        return result.toString().toUpperCase();
    }

    public String decrypt(String userInput) {
        StringBuilder original = new StringBuilder();
        for(int i = 0; i < userInput.length() - 1; i+=2) {
            int row1 = 0;
            int col1 = 0;
            int row2 = 0;
            int col2 = 0;
            boolean letter1Found = false;
            boolean letter2Found = false;
            for (int row = 0; row < 5; row++) {
                if(letter1Found && letter2Found) {
                    break;
                }
                for (int col = 0; col < 5; col++) {
                    if (!letter1Found) {
                        if (matrix[row][col] == userInput.charAt(i)) {
                            row1 = row;
                            col1 = col;
                            letter1Found = true;
                        }
                    }
                    if (!letter2Found) {
                        if (matrix[row][col] == userInput.charAt(i + 1)) {
                            row2 = row;
                            col2 = col;
                            letter2Found = true;
                        }
                    }
                }
            }

            // Now we check for the three cases
            // case 1 same different row and column
            if (row1 != row2 && col1 != col2) {
//                System.out.println("case 1");
//                System.out.println("letters " + userInput.charAt(i) + " and " + userInput.charAt(i + 1));
//                System.out.println("row1=" + row1 + " col1=" + col1);
                original.append(matrix[row1][col2]);
                original.append(matrix[row2][col1]);
//                System.out.println("first letter" + matrix[row1][col2]);
//                System.out.println("second letter" + matrix[row2][col1]);


            }
            // case 2 same row different column
            else if (row1 == row2 && col1 != col2) {
//                System.out.println("case 2");
//                System.out.println("letters " + userInput.charAt(i) + " and " + userInput.charAt(i + 1));

                col1--;
                col2--;
                if(col1 < 0)
                    col1 = 0;
                if(col2< 0)
                    col2 = 0;

                original.append(matrix[row1][col1]);
                original.append(matrix[row2][col2]);

            }
            // case 3 different row and same column
            else if (row1 != row2) {
//                System.out.println("case 3");
//                System.out.println("letters " + userInput.charAt(i) + " and " + userInput.charAt(i + 1));
                row1--;
                row2--;
                if(row1 < 0)
                    col1 = 0;
                if(row2 < 0)
                    col2 = 0;
                original.append(matrix[row1][col1]);
                original.append(matrix[row2][col2]);
            }
        }

        return original.toString();
    }



}
