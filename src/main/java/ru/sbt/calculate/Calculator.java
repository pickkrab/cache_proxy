package ru.sbt.calculate;

import ru.sbt.annotations.Cache;
import ru.sbt.cache_types.Cache_types;

import java.util.List;

public interface Calculator {

    @Cache(cache_type = Cache_types.IN_MEMORY, identityBy = String.class)
    int doHardWork(Integer a, int b, String c);

    @Cache(cache_type = Cache_types.IN_FILE, zip = true)
    int doHardWork(Integer a, int b);

    @Cache(cache_type = Cache_types.IN_FILE, zip = true, list_size = 10)
    List<String> doList(int a);

    @Cache(cache_type = Cache_types.IN_MEMORY, identityBy = Integer.class, list_size = 1000_000_000)
    List<Integer> doList1(int a, String b);
}
