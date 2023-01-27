import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListFilesUsingFileClass {
  private static List<String> listFileNames(File dirFile) {
    File[] files = dirFile.listFiles();
    if (files == null) {
      return Collections.<String>emptyList();  
    }

    return Stream.of(
      files
    ).filter(
      File::isFile
    ).map(
      File::getName
    ).collect(
      Collectors.toList()
    );
  }

  public static void main( String[] args ) {
    for (String fileName : listFileNames(new File(args[0]))) {
      System.out.println(fileName);
    }
  }
}
