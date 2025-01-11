package de.robiasto.app.user.detail.utility;

import de.robiasto.app.team.infrastructure.TeamId;
import de.robiasto.app.user.domain.Gender;
import de.robiasto.app.user.infrastructure.Position;
import de.robiasto.app.user.infrastructure.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public abstract class AbstractUserResponse {
    @NotNull
    private List<UserRole> userRole;

    @NotBlank
    @Size(min = 1, max = 200)
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 200)
    private String lastName;

    @NotNull
    private Gender gender;

    @NotNull
    private String email;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @NotBlank
    @Pattern(regexp = "[0-9.\\-() x/+]+")
    private String phoneNumber;

    protected MultipartFile avatar;

    private boolean coach;

    private Position position;

    private UUID teamCoachId;

    private UUID teamPlayerId;

    public TeamId getTeamId() {
        return coach ? new TeamId(teamCoachId) : new TeamId(teamPlayerId);
    }

}
