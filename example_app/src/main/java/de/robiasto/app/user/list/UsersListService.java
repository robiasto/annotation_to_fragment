package de.robiasto.app.user.list;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class UsersListService {

    @PersistenceContext
    private EntityManager entityManager;

    public Page<ListUserEntity> getUsers(Pageable pageable) {
        String queryString =
                "SELECT id, first_name as firstName, last_name as lastName, gender, birthday, email "
                        + "FROM tt_user"
                        + " ORDER BY " + this.getSortString(pageable.getSort());

        Query query = entityManager.createNativeQuery(queryString, "ListUserEntityMapping");
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        @SuppressWarnings("unchecked")
        List<ListUserEntity> userEntityList = query.getResultList();

        Query countQuery = entityManager.createNativeQuery("SELECT COUNT(*) FROM tt_user");

        return new PageImpl<>(userEntityList, pageable, ((Number) countQuery.getSingleResult()).longValue());
    }

    private String getSortString(Sort sort) {
        return sort.stream()
                   .map(order -> order.getProperty() + " " + order.getDirection())
                   .collect(Collectors.joining(", "));
    }
}
