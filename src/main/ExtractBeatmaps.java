package main;

import java.io.File;
import java.io.IOException;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import java.nio.file.Paths;
import java.util.Objects;
import java.nio.file.Files;

public class ExtractBeatmaps {
    private final String BEATMAP_PATH = "src\\main\\beatmaps";
    private final String OUTPUT_PATH = "src\\main\\temp_beatmaps";
    private final String FILE_EXTENSION = "osz";

    public ExtractBeatmaps(){
        main(BEATMAP_PATH);
    }

    public void main(String startPath) {
        File dir = new File(startPath);
        try {
            Files.createDirectories(Paths.get(OUTPUT_PATH));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        extractFiles(dir.listFiles());
    }

    public void extractFiles(File[] files){
        for (File file : files) {
            //We don't want to extract directories or files that aren't osu beatmaps
            if (!file.isDirectory() && Objects.equals(file.getName().substring(file.getName().lastIndexOf(".") + 1), FILE_EXTENSION)) { 

                System.out.println("File: " + file.getAbsolutePath());
                ZipFile zipFile = new ZipFile(file);
                try {
                    zipFile.extractAll(OUTPUT_PATH);
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
