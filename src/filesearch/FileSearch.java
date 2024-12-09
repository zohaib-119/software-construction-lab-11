package filesearch;
import java.io.File;

public class FileSearch {

    /**
     * Recursively searches for the specified file within the directory and its subdirectories.
     *
     * @param directory The starting directory to search within.
     * @param fileName  The name of the file to search for.
     * @return true if the file is found, false otherwise.
     */
    public static boolean searchFile(File directory, String fileName) {
        File[] files = directory.listFiles();

        if (files == null) return false;

        for (File file : files) {
            if (file.isDirectory()) {
                // Recursive call to search within subdirectories
                if (searchFile(file, fileName)) {
                    return true;
                }
            } else if (file.getName().equals(fileName)) {
                System.out.println("File found at: " + file.getAbsolutePath());
                return true;
            }
        }
        System.out.println("File not found!");
        return false;
    }
}
