package de.robiasto.app;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ModulithTest {

    @Test
    void writeDocumentationSnippets() {
        var modules = ApplicationModules.of(Application.class);

        new Documenter(modules.verify())
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml();
    }

    @Test
    void verifyModules() {
        var modules = ApplicationModules.of(Application.class);

        new Documenter(modules.verify());
    }
}
