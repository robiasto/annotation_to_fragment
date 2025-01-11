@ApplicationModule(
        displayName = "Menu Profile",
        allowedDependencies = {
                "de.robiasto.app.infrastructure.configuration",
                "de.robiasto.app.user.login"
        }
)
package de.robiasto.app.infrastructure.advice.profile;

import org.springframework.modulith.ApplicationModule;
