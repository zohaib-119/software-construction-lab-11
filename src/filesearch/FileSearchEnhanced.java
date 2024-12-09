
package filesearch;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileSearchEnhanced {

    /**
     * Searches for multiple files within a given directory and its subdirectories.
     *
     * @param directory      The root directory to search in.
     * @param fileNames      The list of file names to search for.
     * @param caseSensitive  Specifies if the search should be case-sensitive.
     * @return A map where keys are file names and values are lists of full paths for each occurrence.
     */
    public static Map<String, List<String>> searchFiles(File directory, List<String> fileNames, boolean caseSensitive) {
        Map<String, List<String>> foundFiles = new HashMap<>();
        for (String fileName : fileNames) {
            foundFiles.put(fileName, new ArrayList<>());
        }
        searchFilesRecursive(directory, fileNames, caseSensitive, foundFiles);
        return foundFiles;
    }

    /**
     * Recursively searches for the specified files in the directory and subdirectories.
     *
     * @param directory      The current directory to search in.
     * @param fileNames      The list of file names to search for.
     * @param caseSensitive  Specifies if the search should be case-sensitive.
     * @param foundFiles     The map that stores the found file paths.
     */
    private static void searchFilesRecursive(File directory, List<String> fileNames, boolean caseSensitive,
                                             Map<String, List<String>> foundFiles) {
        if (directory == null || !directory.isDirectory()) {
            return;
        }

        File[] files = directory.listFiles();


        for (File file : files) {
            if (file.isDirectory()) {
                searchFilesRecursive(file, fileNames, caseSensitive, foundFiles);
            } else {
                for (String fileName : fileNames) {
                    if (matches(file.getName(), fileName, caseSensitive)) {
                        foundFiles.get(fileName).add(file.getAbsolutePath());
                    }
                }
            }
        }
    }

    /**
     * Counts the occurrences of each specified file within the directory and subdirectories.
     *
     * @param directory      The root directory to search in.
     * @param fileNames      The list of file names to search for.
     * @param caseSensitive  Specifies if the search should be case-sensitive.
     * @return A map where keys are file names and values are the count of occurrences.
     */
    public static Map<String, Integer> countFileOccurrences(File directory, List<String> fileNames, boolean caseSensitive) {
        Map<String, Integer> fileCounts = new HashMap<>();
        for (String fileName : fileNames) {
            fileCounts.put(fileName, 0);
        }
        countOccurrencesRecursive(directory, fileNames, caseSensitive, fileCounts);
        return fileCounts;
    }

    /**
     * Recursively counts occurrences of each specified file within the directory and subdirectories.
     *
     * @param directory      The current directory to search in.
     * @param fileNames      The list of file names to search for.
     * @param caseSensitive  Specifies if the search should be case-sensitive.
     * @param fileCounts     The map that stores the count of occurrences.
     */
    private static void countOccurrencesRecursive(File directory, List<String> fileNames, boolean caseSensitive,
                                                  Map<String, Integer> fileCounts) {
        if (directory == null || !directory.isDirectory()) {
            return;
        }

        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                countOccurrencesRecursive(file, fileNames, caseSensitive, fileCounts);
            } else {
                for (String fileName : fileNames) {
                    if (matches(file.getName(), fileName, caseSensitive)) {
                        fileCounts.put(fileName, fileCounts.get(fileName) + 1);
                    }
                }
            }
        }
    }

    /**
     * Checks if a file name matches a target name, with optional case sensitivity.
     *
     * @param fileName       The name of the file found in the directory.
     * @param targetName     The target file name to match.
     * @param caseSensitive  Specifies if the comparison should be case-sensitive.
     * @return True if the file names match, otherwise false.
     */
    private static boolean matches(String fileName, String targetName, boolean caseSensitive) {
        return caseSensitive ? fileName.equals(targetName) : fileName.equalsIgnoreCase(targetName);
    }
}
