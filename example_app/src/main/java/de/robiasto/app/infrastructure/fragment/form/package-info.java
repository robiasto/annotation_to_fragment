@ApplicationModule(
        displayName = "Form fragments",
        allowedDependencies = {
                "de.robiasto.app.infrastructure.fragment.grid :: image",
                "de.robiasto.app.infrastructure.fragment.grid :: image_text",
                "de.robiasto.app.infrastructure.fragment.grid :: link",
        }
)
package de.robiasto.app.infrastructure.fragment.form;

import org.springframework.modulith.ApplicationModule;
