//mohamed mohey
//2305186



import java.util.*;


 
public class PlayfairCipher {
  



    public static char[][] generatePlayfairMatrix(String key) {
        key = key.toLowerCase().replaceAll("[^a-z]", "");
        StringBuilder matrixString = new StringBuilder();
        boolean[] alphabetUsed = new boolean[26];
        char[][] matrix = new char[5][5];
        
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (!alphabetUsed[ch - 'a']) {
                alphabetUsed[ch - 'a'] = true;
                matrixString.append(ch);
            }
        }

        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (ch == 'j') continue;
            if (!alphabetUsed[ch - 'a']) {
                matrixString.append(ch);
            }
        }

        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = matrixString.charAt(index++);
            }
        }

        return matrix;
    }

    public static void printMatrix(char[][] matrix) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[] getPosition(char[][] matrix, char ch) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == ch) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static String prepareText(String text) {
        text = text.toLowerCase().replaceAll("[^a-z]", "");
        StringBuilder preparedText = new StringBuilder();
        
        for (int i = 0; i < text.length(); i++) {
            preparedText.append(text.charAt(i));
            if (i + 1 < text.length() && text.charAt(i) == text.charAt(i + 1)) {
                preparedText.append('x');
            }
        }

        if (preparedText.length() % 2 != 0) {
            preparedText.append('x');
        }

        return preparedText.toString();
    }

    public static String encrypt(String text, char[][] matrix) {
        StringBuilder encryptedText = new StringBuilder();
        text = prepareText(text);

        for (int i = 0; i < text.length(); i += 2) {
            char first = text.charAt(i);
            char second = text.charAt(i + 1);
            
            int[] firstPos = getPosition(matrix, first);
            int[] secondPos = getPosition(matrix, second);
            
            if (firstPos[0] == secondPos[0]) {
                encryptedText.append(matrix[firstPos[0]][(firstPos[1] + 1) % 5]);
                encryptedText.append(matrix[secondPos[0]][(secondPos[1] + 1) % 5]);
            }
            else if (firstPos[1] == secondPos[1]) {
                encryptedText.append(matrix[(firstPos[0] + 1) % 5][firstPos[1]]);
                encryptedText.append(matrix[(secondPos[0] + 1) % 5][secondPos[1]]);
            }
            else {
                encryptedText.append(matrix[firstPos[0]][secondPos[1]]);
                encryptedText.append(matrix[secondPos[0]][firstPos[1]]);
            }
        }

        return encryptedText.toString();
    }

    public static String decrypt(String text, char[][] matrix) {
        StringBuilder decryptedText = new StringBuilder();
        text = prepareText(text);

        for (int i = 0; i < text.length(); i += 2) {
            char first = text.charAt(i);
            char second = text.charAt(i + 1);
            
            int[] firstPos = getPosition(matrix, first);
            int[] secondPos = getPosition(matrix, second);
            
            if (firstPos[0] == secondPos[0]) {
                decryptedText.append(matrix[firstPos[0]][(firstPos[1] - 1 + 5) % 5]);
                decryptedText.append(matrix[secondPos[0]][(secondPos[1] - 1 + 5) % 5]);
            }
            else if (firstPos[1] == secondPos[1]) {
                decryptedText.append(matrix[(firstPos[0] - 1 + 5) % 5][firstPos[1]]);
                decryptedText.append(matrix[(secondPos[0] - 1 + 5) % 5][secondPos[1]]);
            }
            else {
                decryptedText.append(matrix[firstPos[0]][secondPos[1]]);
                decryptedText.append(matrix[secondPos[0]][firstPos[1]]);
            }
        }

        return decryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the key: ");
        String key = scanner.nextLine();
        
        char[][] matrix = generatePlayfairMatrix(key);
        
        System.out.println("Playfair matrix:");
        printMatrix(matrix);
        
        System.out.print("Do you want to (E)ncrypt or (D)ecrypt? ");
        char choice = scanner.nextLine().toUpperCase().charAt(0);
        
        System.out.print("Enter the text: ");
        String text = scanner.nextLine();
        
        String result;
        if (choice == 'E') {
            result = encrypt(text, matrix);
            System.out.println("Encrypted text: " + result);
        } else if (choice == 'D') {
            result = decrypt(text, matrix);
            System.out.println("Decrypted text: " + result);
        } else {
            System.out.println("Invalid choice.");
        }
        
        scanner.close();

}
}
