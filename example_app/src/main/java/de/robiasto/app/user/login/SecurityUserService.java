package de.robiasto.app.user.login;

import de.robiasto.app.user.login.domain.SecurityUserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class SecurityUserService implements UserDetailsService {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TypedQuery<SecurityUserEntity> query = entityManager.createNamedQuery("SecurityUserEntity.findByUsername", SecurityUserEntity.class);
        query.setParameter("email", username);

        return new SecurityUserEntity(query.getResultList());
    }
}
