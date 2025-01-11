@ApplicationModule(
        displayName = "Team detail converter",
        allowedDependencies = {
                "de.robiasto.app.team.detail.domain",
                "de.robiasto.app.team.infrastructure"
        }
)
package de.robiasto.app.team.detail.converter;

import org.springframework.modulith.ApplicationModule;
