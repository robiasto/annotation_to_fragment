package de.robiasto.app.user.detail.web.update;

import de.robiasto.app.infrastructure.utility.entity_helper.FileUtilityInterface;
import de.robiasto.app.infrastructure.utility.id.RouteConfiguration;
import de.robiasto.app.member.MemberDto;
import de.robiasto.app.member.TeamMemberServiceInterface;
import de.robiasto.app.team.domain.TeamId;
import de.robiasto.app.user.detail.domain.UserEntity;
import de.robiasto.app.user.detail.domain.UserRepository;
import de.robiasto.app.user.domain.Position;
import de.robiasto.app.user.domain.UserId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
class UserUpdateService {

    private final UserRepository repository;

    private final FileUtilityInterface fileUtility;

    private final TeamMemberServiceInterface memberService;

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

        this.updateTeam(
                userUpdateResponse.getTeamId(),
                user.getId(),
                userUpdateResponse.getPosition(),
                userUpdateResponse.isCoach()
        );
    }

    private void updateTeam(TeamId teamId, UserId userId, Position position, boolean coach) {
        MemberDto member = new MemberDto(userId, teamId, position, coach);
        if (coach) {
            memberService.updateCoach(member);

            return;
        }

        memberService.updatePlayer(member);
    }
}
