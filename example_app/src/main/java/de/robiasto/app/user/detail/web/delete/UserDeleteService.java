package de.robiasto.app.user.detail.web.delete;

import de.robiasto.app.member.TeamMemberServiceInterface;
import de.robiasto.app.user.detail.domain.UserEntity;
import de.robiasto.app.user.detail.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class UserDeleteService {

    private final UserRepository repository;
    private final TeamMemberServiceInterface teamMemberService;

    public void deleteUser(UserEntity user) {
        this.teamMemberService.deleteAllByUserId(user.getId());
        repository.delete(user);
    }
}
