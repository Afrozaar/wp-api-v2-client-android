package com.afrozaar.wp_api_v2_client_android.util;

import android.text.TextUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/08.
 */
public class Validate {

    public static boolean notNull(Object value) {
        return value != null;
    }

    public static void validateMapEntry(String key, Object value, Map<String, Object> map) {
        if (notNull(value)) {
            map.put(key, value);
        }
    }

    public static void validateMapStringEntry(String key, String value, Map<String, String> map) {
        if (!TextUtils.isEmpty(value)) {
            map.put(key, value);
        }
    }

    public static <T> void validateMapListEntry(String key, List<T> list, Map<String, Object> map) {
        if (list != null && list.size() > 0) {
            map.put(key, list);
        }
    }
}
