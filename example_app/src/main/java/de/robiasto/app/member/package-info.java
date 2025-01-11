@ApplicationModule(
        displayName = "Member",
        allowedDependencies = {
                "de.robiasto.app.team.infrastructure",
                "de.robiasto.app.user.infrastructure",
        }
)
package de.robiasto.app.member;

import org.springframework.modulith.ApplicationModule;
