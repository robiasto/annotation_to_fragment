@ApplicationModule(
        displayName = "User detail",
        allowedDependencies = {
                "de.robiasto.app.infrastructure.fragment.factory.view.form",
                "de.robiasto.app.infrastructure.fragment.factory.page.redirect_page",
               "de.robiasto.app.infrastructure.utility.entity_helper",
                "de.robiasto.app.infrastructure.utility.id",
                "de.robiasto.app.infrastructure.utility.validation",
                "de.robiasto.app.infrastructure.fragment.grid",
                "de.robiasto.app.infrastructure.fragment.grid :: headline",
                "de.robiasto.app.infrastructure.fragment.grid :: image_text",
                "de.robiasto.app.infrastructure.fragment.form",
                "de.robiasto.app.member",
                "de.robiasto.app.team.domain",
                "de.robiasto.app.user.domain",

        }
)
package de.robiasto.app.user.detail;

import org.springframework.modulith.ApplicationModule;
