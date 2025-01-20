package de.robiasto.app.infrastructure.utility.entity_helper;

import java.util.List;
import java.util.function.Function;

public interface EntityQueryUtilityInterface {
      <T, R> List<R> executeMappedQuery(String namedQuery, Class<T> resultClass, Function<T, R> mapper, EntityQueryAttribute param);
    <T> List<T> executeQuery(String namedQuery, Class<T> resultClass, EntityQueryAttribute param);
}
