package ru.sbt.cacheProxy;

import ru.sbt.annotations.Cache;
import ru.sbt.cache_types.Cache_types;
import ru.sbt.write_read.File_;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class CacheProxy implements InvocationHandler {

    private Object delegate;

    private Cache_types cacheType = Cache_types.IN_MEMORY;
    private boolean zip = false;
    private List<Class> identityBy;
    private Map<Integer, File_> map = new HashMap<>();
    private String file_name = "";

    public CacheProxy(Object delegate) {
        this.delegate = delegate;

    }

    public static <T> T cache(Object delegate) {
        return (T) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(), new CacheProxy(delegate));
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Cache.class))
        {
            return method.invoke(delegate, args);
        }
        Cache cache = method.getAnnotation(Cache.class);
        Field(cache, method.getParameterTypes());
        if (map == null) {
            Proxy(method.getModifiers());
        }

        File_ cacheWork = map.get(method.hashCode());
        List<Object> key = calculateKey(args);

        Object obj = cacheWork.read(key);
        if (obj == null) {
            obj = invokeMethod(method, args);
            cacheWork.write(key, obj);

        } else {
            return obj;
        }
        return obj;
    }


    private List<Object> calculateKey(Object[] args) {
        List<Object> objects = new ArrayList<>();
        ListIterator<Class> iterator = identityBy.listIterator();
        for (int i = 0; i < args.length; i++) {
            while (iterator.hasNext()) {
                Class clazz = iterator.next();
                if (clazz == args[i].getClass()) {
                    objects.add(args[i]);
                    i++;
                } else {
                    i++;
                }
            }
        }
        return objects;
    }

    private Object invokeMethod(Method method, Object[] args) throws Throwable {
        Object obj;
        int size_return = method.getAnnotation(Cache.class).list_size();
        try {
            obj = method.invoke(delegate, args);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error during call the method");
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
        if (method.getReturnType() == List.class) {
            List list = (List) obj;
            obj = list.subList(0, size_return == 0 ? list.size() : (size_return > list.size() ? list.size() : size_return));
        }
        return obj;
    }

    private void Proxy(Integer methodHash) throws IOException {
        if (cacheType == Cache_types.IN_FILE) {
            map.put(methodHash, new File_(String.valueOf(methodHash) + file_name, zip));
        }

    }

    private void Field(Cache cache, Class<?>[] parameterTypes) {
        cacheType = cache.cache_type();
        zip = cache.zip();
        String Prefix = cache.fileNamePrefix();
        file_name = Prefix + file_name;

    }

}
