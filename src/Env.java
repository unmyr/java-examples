import java.util.Map;

public class Env {
  public static void main( String[] args ) {
    Map<String,String> envMap = System.getenv();
    System.out.println(envMap);
  }
}
