package de.robiasto.app.user.detail.service;

import de.robiasto.app.infrastructure.fragment.form.SelectValueDto;
import de.robiasto.app.infrastructure.fragment.grid.fragments.image_text.ImageTextValueDto;
import de.robiasto.app.member.MemberDto;
import de.robiasto.app.member.TeamMemberServiceInterface;
import de.robiasto.app.team.infrastructure.TeamId;
import de.robiasto.app.user.detail.domain.UserTeamRepository;
import de.robiasto.app.user.infrastructure.Position;
import de.robiasto.app.user.infrastructure.UserId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailService {

    private final UserTeamRepository userTeamRepository;

    private final TeamMemberServiceInterface memberService;


    public List<ImageTextValueDto> getCareerList(UserId userId) {
        return userTeamRepository.findAllUserTeamCareer(userId);
    }

    public List<SelectValueDto> getSelectableTeamPlayers(UserId userId) {
        return userTeamRepository.findAllPlayerTeams(userId);
    }

    public List<SelectValueDto> getSelectableTeamPlayers() {
        return userTeamRepository.findAllPlayerTeams();
    }

    public List<SelectValueDto> getSelectableTeamCoaches(UserId userId) {
        return userTeamRepository.findAllTeamsCoaches(userId);
    }

    public List<SelectValueDto> getSelectableTeamCoaches() {
        return userTeamRepository.findAllTeamsCoaches();
    }

    public void updateTeam(TeamId teamId, UserId userId, Position position, boolean coach) {
        MemberDto member = new MemberDto(userId, teamId, position, coach);
        if (coach) {
            memberService.updateCoach(member);

            return;
        }

        memberService.updatePlayer(member);
    }
}
