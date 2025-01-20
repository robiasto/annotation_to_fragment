package de.robiasto.app.infrastructure.test_data;

import com.github.javafaker.Faker;
import de.robiasto.app.infrastructure.utility.entity_helper.FileUtilityInterface;
import de.robiasto.app.infrastructure.utility.id.RouteConfiguration;
import de.robiasto.app.team.detail.domain.TeamEntity;
import de.robiasto.app.team.domain.TeamId;
import de.robiasto.app.user.detail.domain.UserEntity;
import de.robiasto.app.user.domain.Gender;
import de.robiasto.app.user.domain.Position;
import de.robiasto.app.user.domain.UserId;
import de.robiasto.app.user.domain.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Profile("init-db")
@AllArgsConstructor
class CreateTestData implements CommandLineRunner {
    private final Faker faker = new Faker();
    private final CreateTestDataService userService;
    private final PasswordEncoder passwordEncoder;
    private final ResourceLoader resourceLoader;
    private final FileUtilityInterface fileUtility;

    private final Random random = new SecureRandom();

    private static final int CREATED_USERS = 20;
    private static final int CREATE_TEAMS = 2;


    @Override
    public void run(String... args) {
        this.userService.deleteAll();
        this.fileUtility.deleteFolder(RouteConfiguration.IMG + (new UserId()).getRouteBasePath());
        this.fileUtility.deleteFolder(RouteConfiguration.IMG + (new TeamId()).getRouteBasePath());

        this.creatTeamMembers(this.createUser(), this.createTeams());
    }

    private void creatTeamMembers(List<UserEntity> userEntities, List<TeamEntity> teamEntities) {
        userEntities.forEach(userEntity -> userService.createTeamMember(
                teamEntities.get(this.random.nextInt(CreateTestData.CREATE_TEAMS)), userEntity
        ));

    }

    private UserEntity newRandomUserParameters() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        Gender gender =faker.bool().bool() ? Gender.MALE : Gender.FEMALE;
        UserId userId = new UserId();

        this.createProfileImage(gender, userId);

        return new UserEntity(
                userId,
                List.of(UserRole.ROLE_USER),
                firstName,
                lastName,
                this.passwordEncoder.encode(lastName),
                gender,
                LocalDate.ofInstant(faker.date().birthday(10, 40).toInstant(), ZoneId.systemDefault()),
                faker.internet().emailAddress(
                        String.format("%s.%s",
                                      firstName.toLowerCase(),
                                      lastName.toLowerCase()
                        )
                ),
                faker.phoneNumber().phoneNumber(),
                false,
                Position.CENTER
        );
    }

    private void createTeamImage(TeamId id) {
        String resourcePath = "classpath:test_data_img/team/img1.jpg";
        String fileName = RouteConfiguration.getAvatar(id);
        Resource resource = resourceLoader.getResource(resourcePath);

        try (InputStream inputStream =  resource.getInputStream()){
            this.fileUtility.saveFile(inputStream.readAllBytes(), fileName);
        } catch (IOException e) {
            throw new TestDateException("Error accessing resource: " +  resourcePath);
        }
    }

    private void createProfileImage(Gender gender, UserId userId) {
        String resourcePath = "classpath:test_data_img/profile/" + gender.toString() + "_0.jpeg";
        String fileName = RouteConfiguration.getAvatar(userId);
        Resource resource = resourceLoader.getResource(resourcePath);

        try (InputStream inputStream =  resource.getInputStream()){
            this.fileUtility.saveFile(inputStream.readAllBytes(), fileName);
        } catch (IOException e) {
            throw new TestDateException("Error accessing resource: " +  resourcePath);
        }
    }
    @SuppressWarnings("java:S6437")
    private List<UserEntity> createUser() {
        List<UserEntity> userEntities = new ArrayList<>();
        UserId userIdAdmin = new UserId();
        this.createProfileImage(Gender.MALE, userIdAdmin);

        userEntities.add(
                userService.createUser(
                        new UserEntity(
                                userIdAdmin,
                                List.of(UserRole.ROLE_ADMIN),
                                "Anthony",
                                "Admin",
                                this.passwordEncoder.encode("Admin"),
                                Gender.MALE,
                                LocalDate.of(1997, 11, 2),
                                "anthony.admin@gmail.com",
                                "0123456789",
                                true,
                                Position.CENTER

                        )
                )
        );

        UserId userIdUser = new UserId();
        this.createProfileImage(Gender.MALE, userIdUser);

        userEntities.add(
                userService.createUser(
                        new UserEntity(
                                userIdUser,
                                List.of(UserRole.ROLE_USER),
                                "Anthony",
                                "User",
                                this.passwordEncoder.encode("User"),
                                Gender.FEMALE,
                                LocalDate.of(1977, 2, 20),
                                "anthony.user@gmail.com",
                                "9876543210",
                                true,
                                Position.CENTER
                        )
                )
        );

        for (int i = 0; i < CreateTestData.CREATED_USERS; i++) {
            userEntities.add(this.userService.createUser(this.newRandomUserParameters()));
        }

        return userEntities;
    }

    private List<TeamEntity> createTeams() {
        List<TeamEntity> teamEntities = new ArrayList<>();

        for (int i = 0; i < CreateTestData.CREATE_TEAMS; i++) {
            TeamId teamId = new TeamId();
            this.createTeamImage(teamId);

            teamEntities.add(this.userService.createTeam(new TeamEntity(teamId, faker.team().name())));
        }

        return teamEntities;
    }
}
