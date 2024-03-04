import java.io.*;
import java.math.*;
import java.util.stream.*;


class Result {
    static final BigInteger TEN = BigInteger.valueOf(10);

    // Add your solution code here
    public static int superDigit(String n, int k) {
        final var countingMap = n.chars()
                                 .mapToObj(Character::getNumericValue)
                                 .collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        
        var sum = BigInteger.ZERO;
        
        for (final int i : countingMap.keySet()) {
            final Long sumOfI = Long.valueOf(i * k) * countingMap.get(i);
            sum = sum.add(BigInteger.valueOf(sumOfI));
        }

        if (sum.compareTo(TEN) < 0) {
            return sum.intValue();
        }
        return Result.superDigit(sum.toString(), 1);
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        String n = firstMultipleInput[0];

        int k = Integer.parseInt(firstMultipleInput[1]);

        int result = Result.superDigit(n, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
