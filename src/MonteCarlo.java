import java.util.random.RandomGenerator;

public class MonteCarlo {
  public static double calcPi(long n) {
    long success = 0;

    // RandomGenerator r = RandomGenerator.getDefault();
    RandomGenerator r = RandomGenerator.of("SplittableRandom");
    // RandomGenerator r = RandomGenerator.of("L64X1024MixRandom");
    for (int i = 0; i < n; i++) {
      double x = r.nextDouble();
      double y = r.nextDouble();
      if (x*x + y*y < 1d) {
        success++;
      }
    }
    return (4 * success) / (double)n;
  }

  public static void main(String[] args) {
    int[] trials = new int[] {
      1,
      10,
      100,
      1_000,
      10_000,
      100_000,
      1_000_000,
      10_000_000
    };
    for (int n=0; n < trials.length; n++) {
      double result = calcPi(trials[n]);
      System.out.printf(
        "trial=%10d, result=%.10f, error=%.10f\n",
        trials[n], result, Math.abs(result - Math.PI)
      );
    }
  }
}
