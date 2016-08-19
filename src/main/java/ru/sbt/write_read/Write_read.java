package ru.sbt.write_read;

import java.util.List;

public interface Write_read {
    void write(List<Object> key, Object obj);
    Object read(List<Object> key);
}
