Now that we know more what radamsa is capable of, let's use it to fuzz our programs!

For this purpose, I made a Java program called `PrimeFactor.java` which takes an integer as command line input, and outputs its prime factor decomposition.

```java
import java.util.ArrayList;
import java.util.List;

public class PrimeFactor {
    public static List<Integer> primeFactor(int n){
        List<Integer> factors = new ArrayList<Integer>();
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        return factors;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        for (int i : primeFactor(n)) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
```

You can compile this program by running `javac PrimeFactor.java`{{execute}} and then try it with `java PrimeFactor 52`{{execute}}.