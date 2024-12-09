package filesearch.test;
import org.junit.jupiter.api.*;

import filesearch.FileSearch;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;

public class FileSearchTest {

    private Path tempDir;

    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary directory for testing
        tempDir = Files.createTempDirectory("testDir");
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Delete the temporary directory and its contents after each test
        deleteDirectoryRecursively(tempDir);
    }

    @Test
    public void testFileFoundInRootDirectory() throws IOException {
        Files.createFile(tempDir.resolve("testFile.txt"));

        // Act & Assert
        assertTrue(FileSearch.searchFile(tempDir.toFile(), "testFile.txt"),
                "File should be found in the root directory.");
    }

    @Test
    public void testFileFoundInSubdirectory() throws IOException {
        // Arrange: Create a subdirectory and a test file inside it
        Path subDir = Files.createDirectory(tempDir.resolve("subDir"));
        Path subDir2 = Files.createDirectory(tempDir.resolve("subDir2"));
        Files.createFile(subDir.resolve("testFile.txt"));
        Files.createFile(subDir2.resolve("testFile2.txt"));

        // Act & Assert
        assertTrue(FileSearch.searchFile(tempDir.toFile(), "testFile2.txt"),
                "File should be found in the subdirectory.");
    }

    @Test
    public void testFileNotFound() throws IOException {
        // Arrange: Ensure the file does not exist
    	Files.createDirectory(tempDir.resolve("subDir2"));
        String nonExistentFileName = "nonExistentFile.txt";

        // Act & Assert
        assertFalse(FileSearch.searchFile(tempDir.toFile(), nonExistentFileName),
                "File should not be found in the directory.");
    }

    @Test
    public void testInvalidDirectory() {
        // Arrange: Use a non-existent directory path
        File invalidDir = new File("invalidPath");

        // Act & Assert
        assertFalse(FileSearch.searchFile(invalidDir, "anyFile.txt"),
                "Invalid directory should return file not found.");
    }

    /**
     * Helper method to delete a directory and its contents recursively.
     *
     * @param path The path to the directory to delete.
     * @throws IOException If an I/O error occurs.
     */
    private void deleteDirectoryRecursively(Path path) throws IOException {
        Files.walk(path)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }
}
