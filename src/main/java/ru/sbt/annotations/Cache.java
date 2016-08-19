package ru.sbt.annotations;

import ru.sbt.cache_types.Cache_types;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

public @interface Cache {

    Cache_types cache_type() default Cache_types.IN_MEMORY;
    int list_size() default 0;
    boolean zip() default false;
    String fileNamePrefix() default "";
    Class[] identityBy() default {};

}
