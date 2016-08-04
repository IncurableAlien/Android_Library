package com.sundaything.androidlibrary.util;

import java.util.List;

/**
 * @Title: Helloio
 * @Package com.sundaything.androidlibrary.util
 * @Description:
 * @Author Y
 * @Date 16/8/4 17:02
 */
public class AlienCollectionUtil {

    /**
     * 判断集合是否为空
     * @param list
     * @return
     */
    public static boolean isListEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    /**
     * 判断集合是否非空
     * @param list
     * @return
     */
    public static boolean isListNotEmpty(List<?> list) {
        return !isListEmpty(list);
    }

    /**
     * 判断集合大小是否小于传入参数
     * @param list 集合
     * @param size 目标值
     * @return true说明集合大小小于目标值
     */
    public static boolean isListSizeLessThan(List<?> list, int size) {
        if (isListEmpty(list)) {
            return true;
        } else {
            return list.size() < size;
        }
    }

    /**
     * 判断集合大小是否大于传入参数
     * @param list 集合
     * @param size 目标值
     * @return true说明集合大于目标值
     */
    public static boolean isListSizeGreaterEqual(List<?> list, int size) {
        if (isListEmpty(list)) {
            return false;
        } else {
            return list.size() >= size;
        }
    }

}
