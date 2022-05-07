import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllLines;
import static java.nio.file.Paths.get;

public class Main {
    public static void main(String[] args) throws IOException {
        final Set<String> primes =
                Arrays.stream(readAllLines(get("src/main/resources/primes.csv"))
                        .stream()
                        .reduce(String::concat)
                        .get()
                        .split(","))
                        .collect(Collectors.toSet());

        final Set<String> circularPrimes = new HashSet<>();
        primes.forEach(prime -> {
            boolean isCircular = true;
            for (int digitIndex = 0; digitIndex < prime.length(); digitIndex++) {
                final String rotated =
                        prime.substring(digitIndex) +
                                prime.substring(0, digitIndex);
                if (!primes.contains(rotated)) {
                    isCircular = false;
                    break;
                }
            }

            if (isCircular) {
                circularPrimes.add(prime);
            }
        });

        System.out.println(circularPrimes.size());
    }
}
