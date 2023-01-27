import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ListFilesUsingDirectoryStream {
  private static List<String> listFileNames(Path dirPath) {
    DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
      @Override
      public boolean accept(Path entry) throws IOException {
          return entry.toFile().isFile();
      }
    };

    try (DirectoryStream<Path> ds = Files.newDirectoryStream(dirPath, filter)) {
      Stream<Path> stream = StreamSupport.stream(ds.spliterator(), false);
      return stream.map(
        path -> path.getFileName().toString()
      ).collect(
        Collectors.toList()
      );
    } catch(java.io.IOException ex) {
      ex.printStackTrace();
    }

    return Collections.<String>emptyList();
  }

  public static void main( String[] args ) {
    for(String fileName : listFileNames(Paths.get(args[0]))) {
      System.out.println(fileName);
    }
  }
}
