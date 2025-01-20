package de.robiasto.app.user.detail.web.create;

import de.robiasto.app.user.detail.domain.UserEntity;
import de.robiasto.app.user.detail.domain.UserRepository;
import de.robiasto.app.user.domain.UserId;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class UserCreateService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserCreateService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity createUser(UserCreateResponse createUserFormResponse) {
        return repository.save(
                new UserEntity(
                        new UserId(),
                        createUserFormResponse.getUserRole(),
                        createUserFormResponse.getFirstName(),
                        createUserFormResponse.getLastName(),
                        passwordEncoder.encode(createUserFormResponse.getPassword()),
                        createUserFormResponse.getGender(),
                        createUserFormResponse.getBirthday(),
                        createUserFormResponse.getEmail(),
                        createUserFormResponse.getPhoneNumber(),
                        false,
                        createUserFormResponse.getPosition()
                )
        );
    }
}
