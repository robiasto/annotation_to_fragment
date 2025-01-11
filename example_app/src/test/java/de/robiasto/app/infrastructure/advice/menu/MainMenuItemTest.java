package de.robiasto.app.infrastructure.advice.menu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainMenuItemTest {

    @Test
    void testMainMenuItemCreation() {
        // Given
        String title = "Home";
        boolean translated = true;
        String link = "/home";
        String icon = "home-icon";
        int sorting = 1;

        // When
        MainMenuItem menuItem = new MainMenuItem(title, translated, link, icon, sorting);

        // Then
        assertEquals(title, menuItem.getTitle());
        assertEquals(link, menuItem.getLink());
        assertEquals(icon, menuItem.getIcon());
        assertTrue(menuItem.isTranslated());
        assertEquals(sorting, menuItem.getSorting());
    }
} 