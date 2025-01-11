@ApplicationModule(
        displayName = "Team Member domain",
        allowedDependencies = {
                "de.robiasto.app.team.infrastructure",
                "de.robiasto.app.user.infrastructure",
                "de.robiasto.app.infrastructure.fragment.form",
                "de.robiasto.app.infrastructure.utility.id",
                "de.robiasto.app.infrastructure.utility.security",
                "de.robiasto.app.infrastructure.fragment.grid :: image_text"
        }
)
package de.robiasto.app.team.domain;

import org.springframework.modulith.ApplicationModule;
