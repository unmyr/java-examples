public class FunctionReturnSingleValue {
  public static void main( String[] args ) {
    System.out.println(add(42, 13));
  }

  private static int add(int i, int j) {
    return i + j;
  }
}
