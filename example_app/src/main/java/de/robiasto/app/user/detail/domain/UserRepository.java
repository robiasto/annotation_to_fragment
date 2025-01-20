package de.robiasto.app.user.detail.domain;

import de.robiasto.app.user.domain.UserId;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, UserId> {
    boolean existsByEmail(String email);
}
