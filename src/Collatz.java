import java.util.Arrays;
import java.util.LinkedList;

public class Collatz {
  public static LinkedList<Long> collatz(long n) {
    if (n == 1) {
      return new LinkedList<>(Arrays.asList(1L));
    }

    LinkedList<Long> v = collatz(
      (n % 2 == 0) ? n / 2 : n * 3 + 1
    );
    v.addFirst(n);
    return v;
  }

  public static void main(String args[]) {
    System.out.println(collatz(Long.valueOf(args[0])));
  }
}
