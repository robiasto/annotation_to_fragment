package de.robiasto.app.user.detail.web.update;

import de.robiasto.app.infrastructure.utility.file.FileUtility;
import de.robiasto.app.infrastructure.utility.id.RouteConfiguration;
import de.robiasto.app.user.detail.domain.UserEntity;
import de.robiasto.app.user.detail.domain.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
class UserUpdateService {

    private final UserRepository repository;

    private final FileUtility fileUtility;

    public void update(UserEntity user, UserUpdateResponse userUpdateResponse) {
        user.setRoles(userUpdateResponse.getUserRole());
        user.setFirstName(userUpdateResponse.getFirstName());
        user.setLastName(userUpdateResponse.getLastName());
        user.setGender(userUpdateResponse.getGender());
        user.setBirthday(userUpdateResponse.getBirthday());
        user.setEmail(userUpdateResponse.getEmail());
        user.setPhoneNumber(userUpdateResponse.getPhoneNumber());
        user.setCoach(userUpdateResponse.isCoach());
        user.setPosition(userUpdateResponse.getPosition());

        fileUtility.saveFile(userUpdateResponse.getAvatar(), RouteConfiguration.getAvatar(user.getId()));
        this.repository.save(user);
    }
}
