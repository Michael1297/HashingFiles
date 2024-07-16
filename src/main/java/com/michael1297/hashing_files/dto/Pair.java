package com.michael1297.hashing_files.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Используется для хранения пары ключ-значение
 * @param <T> ключ
 * @param <U> значение
 */
@Data
@AllArgsConstructor
public class Pair<T, U> {
    private T first;
    private U second;
}
