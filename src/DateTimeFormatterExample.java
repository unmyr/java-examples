import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeFormatterExample {
  public static void main( String[] args ) {
    TimeZone tz = TimeZone.getTimeZone("UTC");
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); 
    df.setTimeZone(tz);
    String nowAsISO = df.format(new Date());
    System.out.println(nowAsISO);  // -> 2022-01-06T14:31Z
  }
}