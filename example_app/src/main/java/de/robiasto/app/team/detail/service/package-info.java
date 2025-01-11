@ApplicationModule(
        displayName = "Team converter",
        allowedDependencies = {
                "de.robiasto.app.infrastructure.utility.entity_manager_utility",
                "de.robiasto.app.infrastructure.utility.id",
                "de.robiasto.app.infrastructure.fragment.form",
                "de.robiasto.app.member",
                "de.robiasto.app.team.detail.domain",
                "de.robiasto.app.team.domain",
                "de.robiasto.app.team.detail.service",
                "de.robiasto.app.team.infrastructure",
                "de.robiasto.app.user.infrastructure",
        }
)
package de.robiasto.app.team.detail.service;

import org.springframework.modulith.ApplicationModule;
