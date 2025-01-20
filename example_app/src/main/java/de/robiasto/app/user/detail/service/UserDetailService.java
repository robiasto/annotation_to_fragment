package de.robiasto.app.user.detail.service;

import de.robiasto.app.infrastructure.fragment.form.SelectValueDto;
import de.robiasto.app.infrastructure.fragment.grid.fragments.image_text.ImageTextValueDto;
import de.robiasto.app.user.detail.domain.UserTeamRepository;
import de.robiasto.app.user.domain.UserId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailService {

    private final UserTeamRepository userTeamRepository;

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
}
