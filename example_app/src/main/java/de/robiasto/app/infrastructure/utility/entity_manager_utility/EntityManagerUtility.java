package de.robiasto.app.infrastructure.utility.entity_manager_utility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Transactional
@AllArgsConstructor
public class EntityManagerUtility {
    @PersistenceContext
    private EntityManager entityManager;

    public  <T, R> List<R> executeMappedQuery(String namedQuery, Class<T> resultClass, Function<T, R> mapper, Param param) {
        TypedQuery<T> query = entityManager.createNamedQuery(namedQuery, resultClass);
        Optional.ofNullable(param).ifPresent(id -> query.setParameter(param.name, param.value));

        return query.getResultStream().map(mapper).toList();
    }

    public  <T> List<T> executeQuery(String namedQuery, Class<T> resultClass, Param param) {
        TypedQuery<T> query = entityManager.createNamedQuery(namedQuery, resultClass);
        Optional.ofNullable(param).ifPresent(id -> query.setParameter(param.name, param.value));

        return query.getResultList();
    }

    public record Param(String name, Object value) {}
}
