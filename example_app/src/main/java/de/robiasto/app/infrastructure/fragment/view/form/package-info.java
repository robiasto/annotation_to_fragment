@ApplicationModule(
        allowedDependencies = {
                "de.robiasto.app.infrastructure.fragment.form",
                "de.robiasto.app.infrastructure.fragment.grid",
                "de.robiasto.app.infrastructure.fragment.grid :: link",
                "de.robiasto.app.infrastructure.fragment.grid :: form",
                "de.robiasto.app.infrastructure.fragment.page.default_page",
        }
)
package de.robiasto.app.infrastructure.fragment.view.form;

import org.springframework.modulith.ApplicationModule;
