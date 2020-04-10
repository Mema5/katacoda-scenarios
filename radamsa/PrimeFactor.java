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