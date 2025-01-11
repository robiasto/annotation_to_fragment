@ApplicationModule(
        displayName = "Team detail web",
        allowedDependencies = {
                "de.robiasto.app.infrastructure.fragment.view.form",
                "de.robiasto.app.infrastructure.fragment.page.redirect_page",
                "de.robiasto.app.infrastructure.utility.security",
                "de.robiasto.app.infrastructure.utility.id",
                "de.robiasto.app.infrastructure.utility.validation",
                "de.robiasto.app.infrastructure.utility.file",
                "de.robiasto.app.team.detail.domain",
                "de.robiasto.app.team.detail.service",
                "de.robiasto.app.team.domain",
                "de.robiasto.app.team.infrastructure",
        }
)
package de.robiasto.app.team.detail.web;

import org.springframework.modulith.ApplicationModule;
