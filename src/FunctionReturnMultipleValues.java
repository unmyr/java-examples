public class FunctionReturnMultipleValues {
  public static void main( String[] args ) {
    assert swap("hello", "world") == new String[] {"world", "hello"};
  }

  private static String[] swap(String a, String b) {
    return new String[] {b, a};
  }
}
