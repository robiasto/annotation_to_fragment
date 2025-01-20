@ApplicationModule(
        displayName = "App configuration",
        allowedDependencies = {
                "de.robiasto.app.user.login",
                "de.robiasto.app.user.domain",
                "de.robiasto.app.infrastructure.utility.id"
        }
)

package de.robiasto.app.infrastructure.configuration;

import org.springframework.modulith.ApplicationModule;
