Now that we know more what radamsa is capable of, let's use it to fuzz our programs!

## A java program
For this purpose, we made a basic Java program called `PrimeFactor.java` which takes an integer as command line argument and outputs its prime factor decomposition:

<pre class="file">
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
</pre>

You can compile this program by running `javac PrimeFactor.java`{{execute}} and then try it with `java PrimeFactor 52`{{execute}}.

## Fuzz test it with radamsa
Now we will use radamsa to generate new inputs and see how our program behaves. 

We will repeatedly:
- generate a fuzzed input from a valid integer input and store it in a variable: `fuzzed=$(echo "52" | radamsa)`{{execute}}.
- call `java PrimeFactor $fuzzed`{{execute}} on it

The last command will likely raise an exception from the Java integer parser (overflow, illegal characters, wrong syntax...). To test this in bash we can for example check if the exit status stored in the variable `$?` is not equal to `0`.

This leads us to this infinite testing script written in bash:

<pre class="file">
#!/bin/sh

while true; do
    fuzzed=$(echo "52" | radamsa)
    java PrimeFactor $fuzzed > /dev/null 2>&1 # throw away stdout and stderr
    if [ $? -ne 0 ]; then
        echo "Crash found! Bad input:"
        echo $fuzzed
    fi
done
</pre>

You can test it yourself by running `sh crash_script.sh`{{execute}} and stop it when you want with <kbd>Ctrl</kbd> + <kbd>C</kbd>.