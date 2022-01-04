import java.util.Arrays;
import java.util.List;

public class ViewForSlicesMut {
  public static void main( String[] args )
  {
    List<String> names = Arrays.asList(
      "John", "Paul", "George", "Ringo"
    );
    System.out.println("names=" + names.toString());

    List<String> a = names.subList(0, 2);
    System.out.println("a=" + a.toString());

    List<String> b = names.subList(1, 3);
    System.out.println("b=" + b.toString());

    b.set(0, "XXX");
    System.out.println("names=" + names.toString());
  }
}
