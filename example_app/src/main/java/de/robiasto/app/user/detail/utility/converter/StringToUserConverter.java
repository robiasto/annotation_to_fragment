package de.robiasto.app.user.detail.utility.converter;


import de.robiasto.app.user.detail.domain.UserEntity;
import de.robiasto.app.user.infrastructure.UserId;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
@Profile("!mvcTest")
class StringToUserConverter implements Converter<String, UserEntity> {

    private final ConverterService repository;

    @Override
    public UserEntity convert(@NonNull String s) {
        return repository.getUserById(new UserId(UUID.fromString(s)));
    }
}
