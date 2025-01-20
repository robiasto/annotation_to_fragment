package de.robiasto.app.user.detail.domain;

import de.robiasto.app.user.domain.Gender;
import de.robiasto.app.user.domain.Position;
import de.robiasto.app.user.domain.UserId;
import de.robiasto.app.user.domain.UserRole;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tt_user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @EmbeddedId
    private UserId id;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private List<UserRole> roles;

    @NotNull
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    private LocalDate birthday;

    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;

    private boolean coach;

    @Enumerated(EnumType.STRING)
    @Nullable
    private Position position;

    public UserEntity(
            UserId id,
            List<UserRole> roles,
            String firstName,
            String lastName,
            String password,
            Gender gender,
            LocalDate birthday,
            String email,
            String phoneNumber,
            boolean coach,
            Position position
    ) {
        this.id = id;
        this.roles = roles;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.coach = coach;
        this.position = position;
    }
}
