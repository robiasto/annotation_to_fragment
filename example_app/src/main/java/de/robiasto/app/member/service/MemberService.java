package de.robiasto.app.member.service;

import de.robiasto.app.member.MemberDto;
import de.robiasto.app.member.TeamMemberServiceInterface;
import de.robiasto.app.member.domain.MemberEntity;
import de.robiasto.app.member.domain.MemberRepository;
import de.robiasto.app.team.infrastructure.TeamId;
import de.robiasto.app.user.infrastructure.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements TeamMemberServiceInterface {
    private final MemberRepository teamMemberRepository;

    @Override
    public void updateTeam(TeamId teamId, List<MemberDto> memberDtos) {
        this.teamMemberRepository.findAllByTeamIdAndRemovedDateIsNullAndCoachIsFalse(teamId).forEach(
                oldMemberDto -> {
                    if (memberDtos.stream().anyMatch(memberDto -> memberDto.userId().equals(oldMemberDto.getUserId()))) {
                        return;
                    }

                    oldMemberDto.setRemoved();
                    this.teamMemberRepository.save(oldMemberDto);
                }
        );

        memberDtos.forEach(member -> {
            this.validateCoach(member, false);


            if (this.teamMemberRepository.existsByRemovedDateIsNullAndTeamIdAndUserIdAndCoach(teamId, member.userId(), false)) {
                return;
            }

            this.add(new MemberDto(teamId, member));
        });
    }

    @Override
    public void updateCoach(MemberDto memberDto) {
        this.validateCoach(memberDto, true);

        if (this.teamMemberRepository.existsByRemovedDateIsNullAndTeamIdAndUserIdAndCoach(memberDto.teamId(), memberDto.userId(), true)) {
            return;
        }

        this.removeMember(memberDto);
        this.add(memberDto);
    }

    @Override
    public void deleteAllByUserId(UserId userId) {
        this.teamMemberRepository.deleteAllByUserId(userId);
    }

    @Override
    public void updatePlayer(MemberDto memberDto) {
        this.validateCoach(memberDto, false);

        if (this.teamMemberRepository.existsByRemovedDateIsNullAndTeamIdAndUserIdAndPositionAndCoachIsFalse(
                memberDto.teamId(),
                memberDto.userId(),
                memberDto.position())
        ) {
            return;
        }

        this.removeMember(memberDto);
        this.add(memberDto);
    }

    private void validateCoach(MemberDto memberDto, boolean validValue) {
        if (memberDto.coach() != validValue) {
            throw new IllegalArgumentException("Coach value is invalid. Expected: " + validValue + " but was: " + memberDto.coach());
        }
    }

    private void removeMember(MemberDto memberDto) {
        this.teamMemberRepository.findByUserIdAndRemovedDateIsNull(memberDto.userId()).ifPresent(
                oldMemberDto -> {
                    oldMemberDto.setRemoved();
                    this.teamMemberRepository.save(oldMemberDto);
                }
        );
    }

    private void add(MemberDto memberDto) {
        this.teamMemberRepository.save(
                new MemberEntity(
                        memberDto.teamId(),
                        memberDto.userId(),
                        memberDto.position(),
                        memberDto.coach()
                )
        );
    }
}
