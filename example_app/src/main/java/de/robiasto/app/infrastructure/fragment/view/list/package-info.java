@ApplicationModule(
        displayName = "View List fragments",
        allowedDependencies = {
                "de.robiasto.app.infrastructure.fragment.grid",
               "de.robiasto.app.infrastructure.fragment.grid :: link",
                "de.robiasto.app.infrastructure.fragment.page.default_page",
        }
)
package de.robiasto.app.infrastructure.fragment.view.list;

import org.springframework.modulith.ApplicationModule;
