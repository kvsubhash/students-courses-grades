package com.example.gradesubmission.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

public class Utility {
    
    public static <T extends RuntimeException> Supplier<T> idNotFoundExceptionSupplier(Long id, Class<T> exClass) {
        return () -> {
            try {
                return exClass.getConstructor(Long.class).newInstance(id);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                throw new RuntimeException("Failed to create exception supplier of type " + exClass.getName(), e.getCause());
            }
        };
    }
}
