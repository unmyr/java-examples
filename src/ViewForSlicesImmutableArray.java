import java.util.Arrays;

public class ViewForSlicesImmutableArray {
  public static void main( String[] args )
  {
    int[] primes = {2, 3, 5, 7, 11, 13};
    System.out.println(
      Arrays.toString(Arrays.copyOfRange(primes, 1, 4))
    );
  }
}
