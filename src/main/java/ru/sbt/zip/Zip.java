package ru.sbt.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Zip {
    public static void zipFile(File file_name) {
        try {
            FileInputStream fileOutputStream = new FileInputStream(file_name);
            //здесь должен быть алгоритм по записи байтов из файла в архив
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to compress");
        }
    }

}
