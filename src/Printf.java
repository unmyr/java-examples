import java.util.Date;

public class Printf {
  public static void main(String[] args) {
    Date date = new Date();
    System.out.printf("HH:MM:SS %tT%n", date);
    System.out.printf("HH:MM:SS %tH:%tM:%tS%n", date, date, date);
    System.out.printf("%8s %-8s%n", "Right", "Left");
    System.out.printf("%8s %8s%n", "--------", "--------");
    System.out.printf("%8s %-8S%n", "Hello", "World");
    System.out.printf("%8d %-8d%n", 1, 1);
    System.out.printf("%08d%n", 1);
    System.out.printf("%8.2f %-8.2f%n", Math.PI, Math.PI);
    System.out.printf("%8b %-8b%n", true, false);
    System.out.printf("%8B %-8B%n", true, false);
  }
}
