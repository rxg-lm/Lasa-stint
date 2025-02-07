package com.lasa.home.store.user.util;

import java.lang.reflect.InvocationTargetException;

public class AppUtil {

    public static <T> Object createInstanceBySourceAndTargetType(Object source,Class<T> target) {
        Object object = null;
        try {
            object = target.getDeclaredConstructor(source.getClass()).newInstance(source);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return object;
    }
}
