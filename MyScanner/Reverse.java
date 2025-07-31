import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import MyScanner.MyScanner;

public class Reverse {
    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner(System.in);
        int counter = 0;
        int size = 10;
        int[] inputNumbers = new int[size];
        List<int[]> allNumbers = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String inputLine = scanner.nextLine();
            MyScanner innerScanner = new MyScanner(inputLine);
            //System.err.println(innerScanner.nextLine());
            int i = 0;
            while (innerScanner.hasNextInt()) {
                inputNumbers[i] = innerScanner.nextInt();
                i++;
                if (i == size) {
                    size *= 2;
                    int[] new_inputNumbers = new int[size];
                    System.arraycopy(inputNumbers, 0, new_inputNumbers, 0, i);
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
                System.out.print(numbers[i] + " ");
            }
            System.out.println();
        }
    }
}
