package de.robiasto.fragment_annotation_core.integration;

import de.robiasto.fragment_annotation_core.utilities.UtilityException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
class MapperFactory {
    private static final Map<Class<?>, Constructor<?>> cache = new ConcurrentHashMap<>();

    public Object getMapperInstance(Object mapperClass) {
        Assert.isInstanceOf(Class.class, mapperClass, "Mapper class must be provided.");

        try {
            Constructor<?> constructor = MapperFactory.cache.computeIfAbsent(
                    (Class<?>) mapperClass,
                    cls -> {
                        try {
                            return cls.getDeclaredConstructor();
                        } catch (NoSuchMethodException e) {
                            throw new UtilityException(e.getMessage());
                        }
                    }
            );

            return constructor.newInstance();
        } catch (Exception e) {
            throw new UtilityException(e.getMessage());
        }
    }
}
