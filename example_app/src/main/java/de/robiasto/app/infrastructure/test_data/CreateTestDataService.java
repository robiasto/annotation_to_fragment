package de.robiasto.app.infrastructure.test_data;

import de.robiasto.app.member.domain.MemberEntity;
import de.robiasto.app.member.domain.MemberRepository;
import de.robiasto.app.team.detail.domain.TeamEntity;
import de.robiasto.app.team.detail.domain.TeamRepository;
import de.robiasto.app.user.detail.domain.UserEntity;
import de.robiasto.app.user.detail.domain.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
class CreateTestDataService {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final MemberRepository teamMemberRepository;

    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    public TeamEntity createTeam(TeamEntity team) {
        return teamRepository.save(team);
    }

    public void createTeamMember(TeamEntity team, UserEntity user) {
        teamMemberRepository.save(new MemberEntity(
                team.getId(),
                user.getId(),
                user.getPosition(),
                user.isCoach()
        ));
    }

    public void deleteAll() {
        this.teamMemberRepository.deleteAll();
        this.userRepository.deleteAll();
        this.teamRepository.deleteAll();
    }
}
