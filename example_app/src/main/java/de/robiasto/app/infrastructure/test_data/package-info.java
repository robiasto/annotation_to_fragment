@ApplicationModule(
        displayName = "Test Data",
        allowedDependencies = {
                "de.robiasto.app.user.domain",
                "de.robiasto.app.infrastructure.fragment.grid :: link",
                "de.robiasto.app.team.domain",
                "de.robiasto.app.team.detail :: domain",
                "de.robiasto.app.member",
                "de.robiasto.app.member :: domain",
                "de.robiasto.app.user.detail :: domain",
                "de.robiasto.app.infrastructure.utility.id",
               "de.robiasto.app.infrastructure.utility.entity_helper",
        }
)
package de.robiasto.app.infrastructure.test_data;

import org.springframework.modulith.ApplicationModule;
