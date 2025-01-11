@ApplicationModule(
        displayName = "Team detail domain",
        allowedDependencies = {
                "de.robiasto.app.member",
                "de.robiasto.app.team.infrastructure",
                "de.robiasto.app.team.detail.domain",
                "de.robiasto.app.user.infrastructure",
        }
)
package de.robiasto.app.team.detail.domain;

import org.springframework.modulith.ApplicationModule;
