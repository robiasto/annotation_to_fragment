@ApplicationModule(
        displayName = "Member",
        allowedDependencies = {
                "de.robiasto.app.team.domain",
                "de.robiasto.app.user.domain",
        }
)
package de.robiasto.app.member;

import org.springframework.modulith.ApplicationModule;
