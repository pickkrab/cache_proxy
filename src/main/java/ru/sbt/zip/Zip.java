package ru.sbt.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public static void zipFile(File file_name) {
        try {
            FileInputStream fileOutputStream = new FileInputStream(file_name);
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file_name.getName()));
            ZipEntry pair = new ZipEntry(file_name.getName());
            zipOutputStream.putNextEntry(pair);
            byte[] list = new byte[1024];
            int count;
            while ((count = fileOutputStream.read(list)) > 0) {
                zipOutputStream.write(list, 0, count);
            }
            fileOutputStream.close();
            zipOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to compress");
        }
    }

}
