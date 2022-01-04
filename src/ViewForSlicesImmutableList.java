import java.util.Arrays;
import java.util.List;

public class ViewForSlicesImmutableList {
  public static void main( String[] args ) {
    List<Integer> primes = Arrays.asList(
      2, 3, 5, 7, 11, 13
    );
    System.out.println(
      primes.subList(1, 4).toString()
    );
  }
}
