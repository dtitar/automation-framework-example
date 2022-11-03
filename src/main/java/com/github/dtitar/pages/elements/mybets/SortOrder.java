package com.github.dtitar.pages.elements.mybets;

import lombok.Getter;

/**
 * Порядок сортировки записей на странице Мои ставки
 */
@Getter
public enum SortOrder {

    ASCENDING ("По возрастанию"),
    DESCENDING ("По убыванию");

    private String title;

    SortOrder(String title) {
        this.title = title;
    }
}
