package ru.sbt.write_read;

import ru.sbt.zip.Zip;

import java.io.*;
import java.util.List;

public class File_ implements Write_read {

    private final String prefix;
    private final boolean zip;
    File file_name;

    public File_(String s, boolean zip) throws IOException {
        this.prefix = s;
        this.zip = zip;
        file_name = new File(prefix + ".ser");

    }

    private ObjectOutputStream getOutputStream() throws IOException {
        ObjectOutputStream objectOutputStream;

        if (file_name.exists()) {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file_name, true));
        } else {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file_name));
        }
        return objectOutputStream;
    }

    private ObjectInputStream getInputStream() throws IOException {

        return new ObjectInputStream(new FileInputStream(file_name));
    }


    public void write(List<Object> key, Object obj) {
        Init init = new Init(key, obj);
        try (ObjectOutputStream objectOutputStream = getOutputStream()) {
            objectOutputStream.writeObject(init);
            objectOutputStream.flush();
            objectOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Serialization mistake");
        }

        if (zip) {
            Zip.zipFile(file_name);
        }

    }

    public Object read(List<Object> key) {


        if (file_name.length() == 0) {
            return null;
        }
        try (ObjectInputStream objectInputStream = getInputStream()) {
            while (true) {
                Init init = (Init) objectInputStream.readObject();
                if (init.key.equals(key)) {
                    return init.object;
                }
            }

        }  catch (IOException e) {
            throw new RuntimeException("Deserialization mistake");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class was not found");
        }

    }
}
