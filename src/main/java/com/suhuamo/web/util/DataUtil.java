package com.suhuamo.web.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author suhuamo
 * @slogan 想和喜欢的人睡在冬日的暖阳里
 * @date 2023/03/11 数据处理工具类
 */
public class DataUtil {

    /**
     *
     * 将data对象中的数据赋值到result对象对应的字段中，即令 result.x=data.x
     * 
     * @param data 传入的数据对象
     * @param result 返回的结果对象
     */
    public static void pojoTransfer(Object data, Object result) {
        // 获取数据类class和method，使用get方法
        Class<?> dataClass = data.getClass();
        Method[] methods = dataClass.getMethods();
        // 获取结果类class和字段
        Class<?> resultClass = result.getClass();
        Field[] declaredFields = resultClass.getDeclaredFields();
        // 遍历数据类的每一个get方法，使之赋值到对应的结果类的字段中
        for (Method method : methods) {
            if (method.getName().startsWith("get")) {
                // 遍历结果类中的每一个字段，找到对应get方法的对应的字段
                for (Field declaredField : declaredFields) {
                    declaredField.setAccessible(true);
                    if (declaredField.getName().equalsIgnoreCase(method.getName().substring(3))) {
                        try {
                            declaredField.set(result, method.invoke(data, null));
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        } catch (InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }
}
