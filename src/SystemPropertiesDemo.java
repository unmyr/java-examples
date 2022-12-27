import java.util.Properties;

public class SystemPropertiesDemo {
  public static void main(String[] args) {
    Properties properties = System.getProperties();
    properties.forEach((k, v) -> System.out.println(k + "=" + v));
  }
}
