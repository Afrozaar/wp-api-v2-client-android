package com.afrozaar.wp_api_v2_client_android.util;

import com.google.common.collect.ImmutableMap;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/08.
 */
public class Validate {

    public static boolean notNull(Object value) {
        return value != null;
    }

    public static void validateMapEntry(String key, Object value, ImmutableMap.Builder<String, Object> map) {
        if (notNull(value)) {
            map.put(key, value);
        }
    }
}
