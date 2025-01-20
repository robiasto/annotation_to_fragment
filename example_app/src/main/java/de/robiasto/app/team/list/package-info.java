@ApplicationModule(
        displayName = "Team list",
        allowedDependencies = {
                "de.robiasto.app.infrastructure.utility.id",
                "de.robiasto.app.team.domain",
                "de.robiasto.app.infrastructure.fragment.factory.view.list",
                "de.robiasto.app.infrastructure.advice.menu",
                "de.robiasto.app.infrastructure.fragment.grid",
        }
)
package de.robiasto.app.team.list;

import org.springframework.modulith.ApplicationModule;
