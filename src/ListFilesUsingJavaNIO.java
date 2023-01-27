import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListFilesUsingJavaNIO {
  private static List<String> listFileNames(Path dirPath) {
    try (Stream<Path> stream = Files.list(dirPath)) {
      return stream.filter(
        path -> path.toFile().isFile()
      ).map(
        path -> path.getFileName().toString()
      ).collect(
        Collectors.toList()
      );
    } catch(IOException ex) {
      ex.printStackTrace();
    }

    return Collections.<String>emptyList();
  }

  public static void main( String[] args ) {
    for (String fileName : listFileNames(Paths.get(args[0]))) {
      System.out.println(fileName);
    }
  }
}
