package com.shop.common.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean;

public class NullAwareBeanUtilBean extends BeanUtilsBean {
    @Override
    public void copyProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException {
        if (value == null) {
            return ;
        }
        super.copyProperty(bean, name, value);
    }
}
