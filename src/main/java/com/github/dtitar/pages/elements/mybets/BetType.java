package com.github.dtitar.pages.elements.mybets;

/**
 * Значения фильтра по типу пари на странице Мои ставки
 */
public enum BetType {

    ALL("Все типы пари"),
    WON("Выигравшие"),
    LOST("Проигравшие"),
    CALCULATED("Рассчитанные"),
    WAITING_FOR_CALCULATION("Ожидающие расчета");

    private String title;

    BetType(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

}
