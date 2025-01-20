package de.robiasto.app.infrastructure.utility.entity_helper.entity_query;

import de.robiasto.app.infrastructure.utility.entity_helper.EntityQueryAttribute;
import de.robiasto.app.infrastructure.utility.entity_helper.EntityQueryUtilityInterface;
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
public class EntityQueryUtility implements EntityQueryUtilityInterface {
    @PersistenceContext
    private EntityManager entityManager;

    public  <T, R> List<R> executeMappedQuery(String namedQuery, Class<T> resultClass, Function<T, R> mapper, EntityQueryAttribute param) {
        TypedQuery<T> query = entityManager.createNamedQuery(namedQuery, resultClass);
        Optional.ofNullable(param).ifPresent(id -> query.setParameter(param.name(), param.value()));

        return query.getResultStream().map(mapper).toList();
    }

    public  <T> List<T> executeQuery(String namedQuery, Class<T> resultClass, EntityQueryAttribute param) {
        TypedQuery<T> query = entityManager.createNamedQuery(namedQuery, resultClass);
        Optional.ofNullable(param).ifPresent(id -> query.setParameter(param.name(), param.value()));

        return query.getResultList();
    }

}
