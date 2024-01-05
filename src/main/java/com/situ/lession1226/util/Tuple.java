package com.situ.lession1226.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 功能：
 *
 * @author 千堆雪
 * @version 1.0.0
 * @since 2024/1/4
 * <p>
 * created by 千堆雪 on 2024/1/4, last modified by 千堆雪 on 2024/1/4
 */
public class Tuple<K, V> {
    private final K first;
    private final V second;

    public Tuple(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }
}
