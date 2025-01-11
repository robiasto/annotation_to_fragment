@ApplicationModule(
        displayName = "Security utility",
        allowedDependencies = {
                "de.robiasto.app.user.infrastructure",
                "de.robiasto.app.user.login",
        }
)
package de.robiasto.app.infrastructure.utility.security;

import org.springframework.modulith.ApplicationModule;
