@ApplicationModule(
        displayName = "Factory: View Form fragments",
        allowedDependencies = {
                "de.robiasto.app.infrastructure.fragment.form",
                "de.robiasto.app.infrastructure.fragment.grid",
                "de.robiasto.app.infrastructure.fragment.grid :: link",
                "de.robiasto.app.infrastructure.fragment.grid :: form",
                "de.robiasto.app.infrastructure.fragment.factory.page.default_page",
        }
)
package de.robiasto.app.infrastructure.fragment.factory.view.form;

import org.springframework.modulith.ApplicationModule;
