package com.example;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.StandardProtocolFamily;
import java.net.UnixDomainSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
class FsDemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@TempDir
	static Path sharedTempDir;
	static Path regularFilePath;
	static Path socketFilePath;

	@BeforeAll
	static void createFiles() throws IOException {
			regularFilePath = sharedTempDir.resolve("numbers.txt");
			List<String> lines = Arrays.asList("1", "2", "3");
			Files.write(regularFilePath, lines);

			socketFilePath = sharedTempDir.resolve("dummy.sock");
			UnixDomainSocketAddress socketAddress = UnixDomainSocketAddress.of(socketFilePath);
			ServerSocketChannel serverChannel = ServerSocketChannel.open(StandardProtocolFamily.UNIX);
			serverChannel.bind(socketAddress);
	}

	/**
	 * List a regular file.
	 * @throws IOException
	 */
	@Test
	public void testRegularFile() throws IOException
	{
			try (Stream<Path> stream = Files.list(sharedTempDir)) {
					final List<String> files = stream.filter(
							path -> path.toFile().isFile()
					).map(
							path -> path.getFileName().toString()
					).collect(
							Collectors.toList()
					);
					assertEquals(files.size(), 1);

					String targetFileName = files.get(0);
					Path targetPath = sharedTempDir.resolve(targetFileName);
					assertAll(
							() -> assertEquals(targetFileName, "numbers.txt"),
							() -> assertTrue(Files.exists(targetPath), "File should exist"),
							() -> assertLinesMatch(
									Arrays.asList("1", "2", "3"),
									Files.readAllLines(targetPath)
							)
					);
			}
	}

	/**
	 * List a socket file.
	 * @throws IOException
	 */
	@Test
	public void testSocketFile() throws IOException
	{
			try (Stream<Path> stream = Files.list(sharedTempDir)) {
					final List<Path> files = stream.filter(
							path -> !path.toFile().isDirectory() && !path.toFile().isFile()
					).collect(
							Collectors.toList()
					);
					assertAll(
							() -> assertEquals(files.size(), 1),
							() -> assertEquals(
									files.get(0).getFileName().toString(), "dummy.sock"
							)
					);    
			}
	}
}
