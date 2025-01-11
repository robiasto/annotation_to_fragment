package de.robiasto.app.infrastructure.advice.menu;

import lombok.Getter;

@Getter
public class MainMenuItem {
    private final String title;

    private final String link;

    private final String icon;

    private final boolean translated;

    private final int sorting;

    public MainMenuItem(String title, boolean translated, String link, String icon, int sorting) {
        this.title = title;
        this.translated = translated;
        this.link = link;
        this.icon = icon;
        this.sorting = sorting;
    }
}
