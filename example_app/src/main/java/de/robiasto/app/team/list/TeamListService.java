package de.robiasto.app.team.list;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
class TeamListService {

    private final TeamListRepository repository;

    public Page<TeamListEntity> getTeams(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
