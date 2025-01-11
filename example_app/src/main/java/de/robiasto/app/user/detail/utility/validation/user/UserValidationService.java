package de.robiasto.app.user.detail.utility.validation.user;

import de.robiasto.app.user.detail.domain.UserEntity;
import de.robiasto.app.user.detail.domain.UserRepository;
import de.robiasto.app.user.infrastructure.UserId;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Service
@Transactional
class UserValidationService implements UserValidationServiceInterface {

    private final UserRepository repository;

    public UserValidationService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean userWithEmailExists(String email) {

        return repository.existsByEmail(email);
    }

    @Override
    @SuppressWarnings("java:S2201")
    public UserEntity getUserWithRoles(UserId userId) {
        return repository.findById(userId)
                         .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}
