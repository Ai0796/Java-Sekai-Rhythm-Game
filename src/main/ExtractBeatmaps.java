package main;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import java.nio.file.Paths;
import java.nio.file.Files;

public class ExtractBeatmaps {

    private static final String BEATMAP_PATH = "src\\main\\beatmaps";
    private static final String OUTPUT_PATH = "src\\main\\temp_beatmaps";
    private static final String FILE_EXTENSION = ".osz";

    private ExtractBeatmaps() {}

    public static void main() {
        File dir = new File(BEATMAP_PATH);
        try {
            Files.createDirectories(Paths.get(OUTPUT_PATH));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        extractFiles(dir.listFiles());
    }

    public static void extractFiles(File[] files){
        for (File file : files) {
            //We don't want to extract directories or files that aren't osu beatmaps
            if (!file.isDirectory() && file.getName().endsWith(FILE_EXTENSION)) { 

                Main.logger.log(Level.INFO, "File Extraced: " + file.getName());
                try {
                    Files.createDirectories(Paths.get(OUTPUT_PATH + "\\" + file.getName()));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                ZipFile zipFile = new ZipFile(file);
                try {
                    zipFile.extractAll(OUTPUT_PATH + "\\" + file.getName());
                } catch (ZipException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                try {
                    zipFile.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
