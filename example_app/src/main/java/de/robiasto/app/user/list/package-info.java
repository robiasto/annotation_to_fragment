@ApplicationModule(
        allowedDependencies = {
                "de.robiasto.app.user.infrastructure",
                "de.robiasto.app.user.domain",
                "de.robiasto.app.infrastructure.fragment.grid",
                "de.robiasto.app.infrastructure.fragment.plain.pagination",
                "de.robiasto.app.infrastructure.fragment.view.list",
                "de.robiasto.app.infrastructure.advice.menu",
                "de.robiasto.app.infrastructure.utility.id",

        },
        displayName = "User web list"
)
package de.robiasto.app.user.list;

import org.springframework.modulith.ApplicationModule;
