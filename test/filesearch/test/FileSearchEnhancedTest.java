package filesearch.test;

import filesearch.FileSearch;
import filesearch.FileSearchEnhanced;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class FileSearchEnhancedTest {

    private Path tempDir;

    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary directory for testing
        tempDir = Files.createTempDirectory("testDirEnhanced");
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Delete the temporary directory and its contents after each test
        deleteDirectoryRecursively(tempDir);
    }
    
    @Test
    public void testNoFileProvided() throws IOException {
        // Act & Assert
        Map<String, List<String>> searchResults = FileSearchEnhanced.searchFiles(tempDir.toFile(), Collections.emptyList(), true);
        assertTrue(searchResults.isEmpty(), "No files should be found when no file names are provided.");

    }

    @Test
    public void testNullOrInvalidDirectory() {
        // Act & Assert
        Map<String, List<String>> results = FileSearchEnhanced.searchFiles(null, Collections.singletonList("testFile.txt"), true);
        assertTrue(results.get("testFile.txt").isEmpty(), "Null directory should return an empty result.");

        File nonDirFile = new File("notADirectory.txt");
        assertFalse(nonDirFile.isDirectory(), "Ensure it's not a directory.");
        Map<String, List<String>> invalidResults = FileSearchEnhanced.searchFiles(nonDirFile, Collections.singletonList("testFile.txt"), true);
        assertTrue(invalidResults.get("testFile.txt").isEmpty(), "Invalid directory path should return an empty result.");
    }

    @Test
    public void testMultipleFilesInSubdirectory() throws IOException {
        // Arrange
        Path subDir = Files.createDirectory(tempDir.resolve("subDir"));
        Files.createFile(subDir.resolve("file1.txt"));
        Files.createFile(subDir.resolve("file2.txt"));

        // Act
        List<String> fileNames = Arrays.asList("file1.txt", "file2.txt");
        Map<String, List<String>> results = FileSearchEnhanced.searchFiles(tempDir.toFile(), fileNames, true);

        // Assert
        assertTrue(results.containsKey("file1.txt") && !results.get("file1.txt").isEmpty(),
                "file1.txt should be found in the subdirectory.");
        assertTrue(results.containsKey("file2.txt") && !results.get("file2.txt").isEmpty(),
                "file2.txt should be found in the subdirectory.");
    }

    @Test
    public void testFileInFirstSubdirectoryNotTargetFile() throws IOException {
        // Arrange
        Path firstSubDir = Files.createDirectory(tempDir.resolve("firstSubDir"));
        Files.createFile(firstSubDir.resolve("notTheFile.txt"));
        Path secondSubDir = Files.createDirectory(tempDir.resolve("secondSubDir"));
        Files.createFile(secondSubDir.resolve("targetFile.txt"));

        // Act & Assert
        Map<String, List<String>> results = FileSearchEnhanced.searchFiles(tempDir.toFile(), Collections.singletonList("targetFile.txt"), true);

        assertTrue(results.containsKey("targetFile.txt") && !results.get("targetFile.txt").isEmpty(),
                "targetFile.txt should be found in the second subdirectory, despite first subdirectory having a different file.");
    }


    @Test
    public void testSearchMultipleFilesInRootDirectory() throws IOException {
        Files.createFile(tempDir.resolve("testFile1.txt"));
        Files.createFile(tempDir.resolve("testFile2.txt"));

        List<String> fileNames = Arrays.asList("testFile1.txt", "testFile2.txt");

        Map<String, List<String>> results = FileSearchEnhanced.searchFiles(tempDir.toFile(), fileNames, true);

        assertTrue(results.containsKey("testFile1.txt") && !results.get("testFile1.txt").isEmpty(),
                "testFile1.txt should be found in the root directory.");
        assertTrue(results.containsKey("testFile2.txt") && !results.get("testFile2.txt").isEmpty(),
                "testFile2.txt should be found in the root directory.");
    }

    @Test
    public void testFileSearchCaseInsensitive() throws IOException {
        Files.createFile(tempDir.resolve("TestFile.txt"));

        List<String> fileNames = Collections.singletonList("testfile.txt");

        Map<String, List<String>> results = FileSearchEnhanced.searchFiles(tempDir.toFile(), fileNames, false);

        assertTrue(results.containsKey("testfile.txt") && !results.get("testfile.txt").isEmpty(),
                "File search should be case-insensitive and find TestFile.txt.");
    }

    @Test
    public void testFileNotFound() throws IOException {
        Files.createFile(tempDir.resolve("existingFile.txt"));

        List<String> fileNames = Collections.singletonList("nonExistentFile.txt");

        Map<String, List<String>> results = FileSearchEnhanced.searchFiles(tempDir.toFile(), fileNames, true);

        assertTrue(results.get("nonExistentFile.txt").isEmpty(),
                "File should not be found if it does not exist.");
    }

    @Test
    public void testCountFileOccurrences() throws IOException {
        Path subDir = Files.createDirectory(tempDir.resolve("subDir"));
        Files.createFile(tempDir.resolve("testFile.txt"));
        Files.createFile(subDir.resolve("testFile.txt"));

        List<String> fileNames = Collections.singletonList("testFile.txt");

        Map<String, Integer> counts = FileSearchEnhanced.countFileOccurrences(tempDir.toFile(), fileNames, true);

        assertEquals(2, counts.get("testFile.txt"),
                "The count should be 2 as testFile.txt appears twice in the directory structure.");
    }

    @Test
    public void testCountFileOccurrencesCaseInsensitive() throws IOException {
        Path subDir = Files.createDirectory(tempDir.resolve("subDir"));
        Files.createFile(tempDir.resolve("testFile.txt"));
        Files.createFile(subDir.resolve("TestFile.txt"));

        List<String> fileNames = Collections.singletonList("testfile.txt");

        Map<String, Integer> counts = FileSearchEnhanced.countFileOccurrences(tempDir.toFile(), fileNames, false);

        assertEquals(2, counts.get("testfile.txt"),
                "The count should be 2 when case-insensitive for testfile.txt.");
    }

    @Test
    public void testInvalidDirectoryForSearch() {
        File invalidDir = new File("invalidPath");

        List<String> fileNames = Collections.singletonList("anyFile.txt");

        Map<String, List<String>> results = FileSearchEnhanced.searchFiles(invalidDir, fileNames, true);

        assertTrue(results.get("anyFile.txt").isEmpty(),
                "An invalid directory should result in an empty search result.");
    }

    @Test
    public void testInvalidDirectoryForCount() {
        File invalidDir = new File("invalidPath");

        List<String> fileNames = Collections.singletonList("anyFile.txt");

        Map<String, Integer> counts = FileSearchEnhanced.countFileOccurrences(invalidDir, fileNames, true);

        assertEquals(0, counts.get("anyFile.txt"),
                "An invalid directory should return zero count for the file.");
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
