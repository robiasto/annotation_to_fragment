package de.robiasto.app.user.list;

import de.robiasto.app.user.domain.Gender;
import de.robiasto.app.user.domain.UserId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Immutable
@SqlResultSetMapping(
        name = "ListUserEntityMapping",
        classes = @ConstructorResult(
                targetClass = ListUserEntity.class,
                columns = {
                        @ColumnResult(name = "id", type = UUID.class),
                        @ColumnResult(name = "firstName", type = String.class),
                        @ColumnResult(name = "lastName", type = String.class),
                        @ColumnResult(name = "gender", type = String.class),
                        @ColumnResult(name = "birthday", type = LocalDate.class),
                        @ColumnResult(name = "email", type = String.class)
                }
        )
)
@Getter
@NoArgsConstructor
public class ListUserEntity {

    @EmbeddedId
    private UserId id;

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
    /*
        * Constructor for the ListUserEntity
     */
    public ListUserEntity(UUID id, String firstName, String lastName, String gender, LocalDate birthday, String email) {
        this.id = new UserId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = Gender.valueOf(gender);
        this.birthday = birthday;
        this.email = email;
    }

    public String getDisplayName() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
