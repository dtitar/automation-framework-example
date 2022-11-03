package com.github.dtitar.pages.elements.mybets;

/**
 * Временные диапазоны для фильтрации записей на странице Мои ставки
 */
public enum TimePeriod {

    LAST_WEEK("За неделю", 7),
    LAST_MONTH("За месяц", 30),
    LAST_THREE_MONTHS("За три месяца", 90);

    private final String title;
    private final int days;

    TimePeriod(String title, int days) {
        this.title = title;
        this.days = days;
    }

    public String getTitle() {
        return title;
    }
    public int getDays() {
        return days;
    }
}
