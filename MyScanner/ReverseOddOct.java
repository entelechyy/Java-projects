import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import MyScanner.MyScanner;


public class ReverseOddOct {
    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner(System.in);
        int counter = 0;
        int size = 10;
        long[] inputNumbers = new long[size];
        List<long[]> allNumbers = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String inputLine = scanner.nextLine();
            MyScanner innerScanner = new MyScanner(inputLine);
            int i = 0;
            while (innerScanner.hasNextIntOct()) {
                inputNumbers[i] = innerScanner.nextIntOct();
                i++;
                if (i == size) {
                    size *= 2;
                    long[] newInputNumbers = new long[size];
                    System.arraycopy(inputNumbers, 0, newInputNumbers, 0, i);
                    inputNumbers = newInputNumbers;
                }
            }
            innerScanner.close();
            long[] addNumbers = new long[i];
            System.arraycopy(inputNumbers, 0, addNumbers, 0, i);
            allNumbers.add(addNumbers);
        }
        scanner.close();
        for (int k = allNumbers.size() - 1; k >= 0; k--) {
            long[] numbers = allNumbers.get(k);
            for (int i = numbers.length - 1; i >= 0; i--) {
                if (Math.abs(numbers[i] % 2 ) != 0) {
                    System.out.print(numbers[i] + " ");
                }
            }
            System.out.println();
        }
    }
}
