@ApplicationModule(
        displayName = "User detail",
        allowedDependencies = {
                "de.robiasto.app.infrastructure.fragment.view.form",
                "de.robiasto.app.infrastructure.fragment.page.redirect_page",
                "de.robiasto.app.infrastructure.utility.entity_manager_utility",
                "de.robiasto.app.infrastructure.fragment.grid",
                "de.robiasto.app.infrastructure.fragment.grid :: headline",
                "de.robiasto.app.infrastructure.fragment.grid :: image_text",
                "de.robiasto.app.infrastructure.fragment.form",
                "de.robiasto.app.infrastructure.utility.security",
                "de.robiasto.app.infrastructure.utility.id",
                "de.robiasto.app.infrastructure.utility.validation",
                "de.robiasto.app.infrastructure.utility.file",
                "de.robiasto.app.member",
                "de.robiasto.app.team.infrastructure",
                "de.robiasto.app.user.infrastructure",
                "de.robiasto.app.user.domain",

        }
)
package de.robiasto.app.user.detail;

import org.springframework.modulith.ApplicationModule;
