public class Trifid {
    private final char[][][] cube;
    private String message;
    private int[] layerIndices;
    private int[] columnIndices;
    private int[] rowIndices;


    public Trifid() {
        final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ.";
        int charIndex = 0;
        cube = new char[3][3][3]; // Consistent variable naming
        for (int layer = 0; layer < 3; layer++) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    cube[layer][row][col] = ALPHABET.charAt(charIndex++);
                }
            }
        }
        printCube();
    }
    private void printCube() {
        for (char[][] layer : cube) {
            for (char[] row : layer) {
                for (char c : row) {
                    System.out.print(c + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public String encrypt(String message) {
        message = processMessage(message);
        this.message = message;
        StringBuilder cipherText = new StringBuilder();
        layerIndices = new int[message.length()];
        columnIndices = new int[message.length()];
        rowIndices = new int[message.length()];
        findCharIndicies();
        
        displayArrays();

        int[] combinedIndices = mergeIndices();
        for (int i = 0; i < combinedIndices.length - 2; i += 3) {
            cipherText.append(cube[combinedIndices[i]][combinedIndices[i + 2]][combinedIndices[i + 1]]);
        }
        return cipherText.toString();
    }
    
    private void findCharIndicies(){
        int index = 0;
        boolean found;
        for (int i = 0; i < message.length(); i++) {
            found = false;
            char currentChar = message.charAt(i);
            for (int layer = 0; layer < 3; layer++) {
                if (!found) {
                    for (int row = 0; row < 3; row++) {
                        if (!found) {
                            for (int col = 0; col < 3; col++) {
                                if (cube[layer][row][col] == currentChar) {
                                    layerIndices[index] = layer;
                                    columnIndices[index] = col;
                                    rowIndices[index] = row;
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
        
    }

    private int[] mergeIndices() {
        int[] combinedIndices = new int[message.length() * 3];
        int index = 0;
        int flag = 0;
        for (int i = 0; i < message.length() * 3; i++) {
            if (index == message.length()) {
                flag++;
                index = 0;
            }
            if (flag == 0) {
                combinedIndices[i] = layerIndices[index++];
            } else if (flag == 1) {
                combinedIndices[i] = columnIndices[index++];
            } else if (flag == 2) {
                combinedIndices[i] = rowIndices[index++];
            }
        }
        return combinedIndices;
    }

    public String decrypt(String cipherText) {
        cipherText = cipherText.toUpperCase();
        int[] indices = new int[cipherText.length() * 3];
        int index = 0;
        for (char currentChar : cipherText.toCharArray()) {
            for (int layer = 0; layer < 3; layer++) {
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        if (cube[layer][row][col] == currentChar) {
                            indices[index++] = layer;
                            indices[index++] = col;
                            indices[index++] = row;
                        }
                    }
                }
            }
        }

        int layerIndex = 0;
        int colIndex = indices.length / 3;
        int rowIndex = indices.length - colIndex;
        StringBuilder decryptedText = new StringBuilder();
        int i = 0;
        while (i < indices.length / 3) {
            decryptedText.append(cube[indices[layerIndex++]][indices[rowIndex++]][indices[colIndex++]]);
            i++;
        }
        return decryptedText.toString();
    }

    private String processMessage(String text) {
        return text.replaceAll(" ", "").toUpperCase();
    }

    private void displayArrays() {
        System.out.println("\nCharacters");
        for (int i = 0; i < message.length(); i++) {
            System.out.print(message.charAt(i) + "  |  ");
        }
        System.out.println("\nLayers");
        for (int i = 0; i < message.length(); i++) {
            System.out.print(layerIndices[i] + "  |  ");
        }
        System.out.println("\nCols");
        for (int i = 0; i < message.length(); i++) {
            System.out.print(columnIndices[i] + "  |  ");
        }
        System.out.println("\nRows");
        for (int i = 0; i < message.length(); i++) {
            System.out.print(rowIndices[i] + "  |  ");
        }
        System.out.println();
    }
}
