package de.robiasto.app.user.detail.domain;

import de.robiasto.app.infrastructure.fragment.form.SelectValueDto;
import de.robiasto.app.infrastructure.fragment.grid.fragments.image_text.ImageTextValueDto;
import de.robiasto.app.infrastructure.utility.entity_manager_utility.EntityManagerUtility;
import de.robiasto.app.user.detail.domain.team.UserTeamCareerEntity;
import de.robiasto.app.user.detail.domain.team.UserTeamEntity;
import de.robiasto.app.user.infrastructure.UserId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserTeamRepository {

    private EntityManagerUtility entityManager;

    private static final String USER_ID = "userId";

    public List<ImageTextValueDto> findAllUserTeamCareer(UserId userId) {
        return this.entityManager.executeMappedQuery("UserTeamCareer.findAll", UserTeamCareerEntity.class, UserTeamCareerEntity::toImageTextDto, new EntityManagerUtility.Param(USER_ID, userId.id));
    }

    public List<SelectValueDto> findAllPlayerTeams() {
        return  this.entityManager.executeMappedQuery("UserTeam.findAllPlayer", UserTeamEntity.class, UserTeamEntity::toSelectValueDto, null);
    }

    public List<SelectValueDto> findAllPlayerTeams(UserId userId) {
        return  this.entityManager.executeMappedQuery("UserTeam.findAllPlayerWithId", UserTeamEntity.class, UserTeamEntity::toSelectValueDto, new EntityManagerUtility.Param(USER_ID, userId.id));
    }

    public List<SelectValueDto> findAllTeamsCoaches() {
        return  this.entityManager.executeMappedQuery("UserTeam.findAllCoaches", UserTeamEntity.class, UserTeamEntity::toSelectValueDto, null);
    }

    public List<SelectValueDto> findAllTeamsCoaches(UserId userId) {
        return  this.entityManager.executeMappedQuery("UserTeam.findAllCoachesWithId", UserTeamEntity.class, UserTeamEntity::toSelectValueDto, new EntityManagerUtility.Param(USER_ID, userId.id));
    }

}
