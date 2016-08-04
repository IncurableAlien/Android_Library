package com.sundaything.androidlibrary.util;

/**
 * @Title: Helloio
 * @Package com.sundaything.androidlibrary.util
 * @Description:
 * @Author Y
 * @Date 16/8/4 16:55
 */
public class AlienMathUtil {
    /**
     * 计算两点间的距离
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return 两点间距离 double
     */
    public static double distance(double x1,double y1,double x2,double y2)
    {
        return Math.sqrt(Math.abs(x1-x2)*Math.abs(x1-x2)+Math.abs(y1-y2)*Math.abs(y1-y2));
    }

    /**
     * 计算点a(x,y)的角度
     * @param x
     * @param y
     * @return 点a(x,y)的角度 double
     */
    public static double pointTotoDegrees(double x,double y)
    {
        return Math.toDegrees(Math.atan2(x,y));
    }

    /**
     * 点在圆肉
     * @param sx
     * @param sy
     * @param r
     * @param x
     * @param y
     * @return true 点在圆内；
     */
    public static boolean checkInRound(float sx, float sy, float r, float x, float y)
    {
        return Math.sqrt((sx - x) * (sx - x) + (sy - y) * (sy - y)) < r;
    }
}
