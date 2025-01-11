@ApplicationModule(
        displayName = "User Login",
        allowedDependencies = {
                "de.robiasto.app.user.infrastructure",
                "de.robiasto.app.infrastructure.fragment.plain.alert",
                "de.robiasto.app.infrastructure.fragment.grid",
                "de.robiasto.app.infrastructure.fragment.grid :: form",
                "de.robiasto.app.infrastructure.fragment.grid :: link",
                "de.robiasto.app.infrastructure.fragment.form",
                "de.robiasto.app.infrastructure.utility.id",
        }
)
package de.robiasto.app.user.login;

import org.springframework.modulith.ApplicationModule;
