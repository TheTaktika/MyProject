package com.max.MyProject.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    DEVELOPMENT("Разработка и программирование"),
    HARDWARE("Аппаратное обеспечение"),
    DESIGN("Дизайн"),
    CHAT("Общий чат");

    private final String displayName;

}
