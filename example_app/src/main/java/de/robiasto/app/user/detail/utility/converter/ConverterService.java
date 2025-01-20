package de.robiasto.app.user.detail.utility.converter;

import de.robiasto.app.user.detail.domain.UserEntity;
import de.robiasto.app.user.detail.domain.UserRepository;
import de.robiasto.app.user.detail.utility.UserNotFoundException;
import de.robiasto.app.user.domain.UserId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConverterService {

    private final UserRepository repository;

    public UserEntity getUserById(UserId userId) {
        return repository.findById(userId)
                         .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
