package de.robiasto.app.user.login.domain;

import de.robiasto.app.user.infrastructure.UserId;
import de.robiasto.app.user.infrastructure.UserRole;
import de.robiasto.app.user.login.SecurityUserInterface;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@NamedNativeQuery(
        name = "SecurityUserEntity.findByUsername",
        query = "SELECT u.id, u.first_name, u.last_name, u.email, u.password, r.role "
                + "FROM tt_user u JOIN user_roles r ON u.id = r.user_id "
                + "WHERE u.email = :email",
        resultSetMapping = "SecurityUserEntityMapping"
)
@SqlResultSetMapping(
        name = "SecurityUserEntityMapping",
        classes = @ConstructorResult(
                targetClass = SecurityUserEntity.class,
                columns = {
                        @ColumnResult(name = "id", type = UUID.class),
                        @ColumnResult(name = "first_name", type = String.class),
                        @ColumnResult(name = "last_name", type = String.class),
                        @ColumnResult(name = "email", type = String.class),
                        @ColumnResult(name = "password", type = String.class),
                        @ColumnResult(name = "role", type = String.class)
                }
        )
)
@NoArgsConstructor
public class SecurityUserEntity implements SecurityUserInterface {
    /**
     * Constructor for the native query
     */

    public SecurityUserEntity(UUID id, String firstName, String lastName, String email, String password, String role) {
        this.userId = new UserId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = email;
        this.password = password;
        this.roles = List.of(UserRole.valueOf(role));
    }

    public SecurityUserEntity(List<SecurityUserEntity> users) {
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("User could not be found");
        }

        SecurityUserEntity user = users.get(0);

        this.userId = user.userId;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.username = user.username;
        this.password = user.password;
        this.roles = new ArrayList<>();

        users.stream().forEach(userEntity -> this.roles.addAll(userEntity.roles));
    }

    @EmbeddedId
    @Getter
    private UserId userId;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private List<UserRole> roles;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Getter
    private String username;

    @NotNull
    @Getter
    private String password;

    @Override
    public String getDisplayName() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                         .map(userRole -> new SimpleGrantedAuthority(userRole.name()))
                         .collect(Collectors.toSet());
    }
}
