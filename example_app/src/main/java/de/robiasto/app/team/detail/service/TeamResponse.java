package de.robiasto.app.team.detail.service;

import de.robiasto.app.user.domain.UserId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class TeamResponse {
    @NotBlank
    @Size(min = 1, max = 200)
    private String name;

    private UUID coach = null;

    @NotNull
    private List<UUID> players;

    protected MultipartFile avatar;

    public UserId getCoach() {
        return this.coach == null ? null :new UserId(this.coach);
    }



    public List<UserId> getPlayers() {
        return this.players.stream().map(UserId::new).toList();
    }
}
