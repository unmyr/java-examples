import java.util.Map;

public class Env {
  public static void main( String[] args ) {
    Map envMap = System.getenv();
    System.out.println(envMap);
  }
}
