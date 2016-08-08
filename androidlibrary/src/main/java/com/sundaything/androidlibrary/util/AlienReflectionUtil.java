package com.sundaything.androidlibrary.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 类描述：反射工具类
 * 创建人：Y
 * 创建时间：2016/8/8
 * 修改人：Y
 * Email:bellyuan.yuan@gmail.com
 * 修改时间：2016/8/8
 * 修改备注：
 */

public class AlienReflectionUtil {

    /**
     * 非法数据
     */
    private static final int INVALID_VALUE = -1;

    /**
     * 通过构造器获取实例
     *
     * @param className 类的名字
     * @param argsClass 构造函数的参数类型
     * @param args      构造函数的参数值
     * @return
     */
    public static Object getObject(String className, Class[] argsClass, Object[] args){
        Object object = null;
        try {
            Class classType = Class.forName(className);
            Constructor constructor = classType.getDeclaredConstructor(argsClass);
            constructor.setAccessible(true);
            object = constructor.newInstance(args);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return object;
    }

    /**
     * 通过反射获取成员变量
     *
     * @param object    指定访问对象
     * @param fieldName 指定访问成员变量
     * @return  指定访问成员变量的值
     */
    public static Object getFieldValue(Object object, String fieldName){
        Object fieldValue = null;
        Class classType = object.getClass();
        try {
            Field field = classType.getDeclaredField(fieldName);
            field.setAccessible(true);
            fieldValue = field.get(object);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fieldValue;
    }

    /**
     * 通过反射获取成员变量 (Int类型)
     *
     * @param object    指定访问对象
     * @param fieldName 指定访问成员变量
     * @return  指定访问成员变量的值
     */
    public static int getIntFieldValue(Object object, String fieldName){
        int fieldValue = INVALID_VALUE;
        Class classType = object.getClass();
        try {
            Field field = classType.getField(fieldName);
            field.setAccessible(true);
            fieldValue = field.getInt(object);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e){
            e.printStackTrace();
        }
        return fieldValue;
    }

    /**
     * 通过反射获取成员变量 (float类型)
     *
     * @param object    指定访问对象
     * @param fieldName 指定访问成员变量
     * @return  指定访问成员变量的值
     */
    public static float getFloatFieldValue(Object object, String fieldName){
        float fieldValue = INVALID_VALUE;
        Class classType = object.getClass();
        try {
            Field field = classType.getField(fieldName);
            field.setAccessible(true);
            fieldValue = field.getFloat(object);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e){
            e.printStackTrace();
        }
        return fieldValue;
    }

    /**
     * 通过反射获取成员变量 (byte类型)
     *
     * @param object    指定访问对象
     * @param fieldName 指定访问成员变量
     * @return  指定访问成员变量的值
     */
    public static float getByteFieldValue(Object object, String fieldName){
        float fieldValue = INVALID_VALUE;
        Class classType = object.getClass();
        try {
            Field field = classType.getField(fieldName);
            field.setAccessible(true);
            fieldValue = field.getByte(object);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e){
            e.printStackTrace();
        }
        return fieldValue;
    }

    /**
     * 通过反射修改成员变量的值
     *
     * @param object        指定修改的对象
     * @param fieldName     指定修改的成员变量
     * @param fieldValue    指定修改的值
     * @return true表示修改成功
     */
    public static boolean setFieldValue(Object object, String fieldName, String fieldValue){
        Class classType = object.getClass();
        try {
            Field field = classType.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, fieldValue);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取父类成员变量的值
     *
     * @param object    指定对象
     * @param fieldName 成员变量
     * @return          成员变量值
     */
    public static Object getSuperClassFieldValue(Object object, String fieldName){
        Field field = null;
        Class<?> superClass = object.getClass().getSuperclass();
        for (; superClass != null;){
            try {
                field = superClass.getDeclaredField(fieldName);
                if (field != null){
                    break;
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        if (field != null){
            field.setAccessible(true);
            try {
                return field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 反射指定对象的方法
     *
     * @param object        指定对象
     * @param methodName    指定方法
     * @return
     */
    public static Object invokeFunction(Object object, String methodName){
        Method method = null;
        try {
            method = object.getClass().getDeclaredMethod(methodName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            try {
                method = object.getClass().getMethod(methodName);
            } catch (NoSuchMethodException e1) {
                e1.printStackTrace();
            }
        }
        if (method != null){
            method.setAccessible(true);
            try {
                return method.invoke(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 调用指定对象的方法 带参数
     *
     * @param object     Object to be use
     * @param methodName method to be invoke
     * @param argsClass  parameter's type
     * @param args       arguments
     *                    by lixl
     */
    public static Object invokeObjectMethod(Object object, String methodName, Class[] argsClass, Object[] args) {
        Object returnValue = null;
        try {
            Class<?> c = object.getClass();
            Method method;
            method = c.getMethod(methodName, argsClass);
            returnValue = method.invoke(object, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnValue;
    }
}
