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
public class KeyValuePair<K, V> extends Tuple<K, V> {
    private final K key;
    private final V value;

    public KeyValuePair(K key, V value) {
        super(key, value);
        this.key = key;
        this.value = value;
    }

    public static <K, V> KeyValuePair<K, V> of(K k, V v) {
        return new KeyValuePair<>(k, v);
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
