package unlam.analisisdesoftware.CustomComponents;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CustomFileUtils {
	public static String STORAGE_FOLDER = "stg";
	public static String IMAGE_FOLDER = "img";
	
	public static String copyFileToStorage(String path) {
		
		if (path.equals("")) {
			return "";
		}
		
		// construyo los paths.
		Path soucePath = Paths.get(path);
		Path destPath = Paths.get(STORAGE_FOLDER, IMAGE_FOLDER,soucePath.getFileName().toString());
		
		try {
			Files.copy(soucePath, destPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// no pude guardar la imagen.
			return "";
		}
		return destPath.toString();
	}
	
	public static boolean deleteFileFromStorage(String path) {
		
		if (path.equals("")) {
			return false;
		}
		
		Path soucePath = Paths.get(path);
		try {
			Files.deleteIfExists(soucePath);
		} catch (IOException e) {
			// no pude guardar la imagen.
			return false;
		}
		return true;
	}
	
	public static String renameFileInStorage(String path, String newFileName) {
		if (path.equals("")) {
			return "";
		}
		
		Path soucePath = Paths.get(path);
		Path destPath = Paths.get(soucePath.getParent().toString(), newFileName);
		try {
			Files.move(soucePath, destPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// no pude guardar la imagen.
			return "";
		}		
		
		return destPath.toString();
	}
}
