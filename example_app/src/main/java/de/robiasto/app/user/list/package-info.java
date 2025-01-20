@ApplicationModule(
        allowedDependencies = {
                "de.robiasto.app.user.domain",
                "de.robiasto.app.infrastructure.fragment.grid",
                "de.robiasto.app.infrastructure.fragment.plain.pagination",
                "de.robiasto.app.infrastructure.fragment.factory.view.list",
                "de.robiasto.app.infrastructure.advice.menu",
                "de.robiasto.app.infrastructure.utility.id",

        },
        displayName = "User list"
)
package de.robiasto.app.user.list;

import org.springframework.modulith.ApplicationModule;
