import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ReverseOdd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        int size = 10;
        int[] inputNumbers = new int[size];
        List<int[]> allNumbers = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String inputLine = scanner.nextLine();
            Scanner innerScanner = new Scanner(inputLine);
            int i = 0;
            while (innerScanner.hasNextInt()) {
                inputNumbers[i] = innerScanner.nextInt();
                i++;
                if (i == size) {
                    size *= 2;
                    // inputNumbes = Arrays.copyOf(inputNumbers, inputNumbers.length * 2);
                    int[] new_inputNumbers = new int[size];
                    System.arraycopy(inputNumbers, 0, newInputNumbers, 0, i);
                    inputNumbers = new_inputNumbers;
                }
            }
            innerScanner.close();
            int[] addNumbers = new int[i];
            System.arraycopy(inputNumbers, 0, addNumbers, 0, i);
            allNumbers.add(addNumbers);
        }
        scanner.close();
        for (int k = allNumbers.size() - 1; k >= 0; k--) {
            int[] numbers = allNumbers.get(k);
            for (int i = numbers.length - 1; i >= 0; i--) {
                if (Math.abs(numbers[i] % 2 ) != 0) {
                    System.out.print(numbers[i] + " ");
                }
            }
            System.out.println();
        }
    }
}
