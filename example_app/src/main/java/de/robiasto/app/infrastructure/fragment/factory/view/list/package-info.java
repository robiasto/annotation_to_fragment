@ApplicationModule(
        displayName = "Factory: View List fragments",
        allowedDependencies = {
                "de.robiasto.app.infrastructure.fragment.grid",
               "de.robiasto.app.infrastructure.fragment.grid :: link",
                "de.robiasto.app.infrastructure.fragment.factory.page.default_page",
        }
)
package de.robiasto.app.infrastructure.fragment.factory.view.list;

import org.springframework.modulith.ApplicationModule;
