package de.robiasto.app.team.list;

import de.robiasto.app.team.infrastructure.TeamId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
interface TeamListRepository extends PagingAndSortingRepository<TeamListEntity, TeamId> {

}
