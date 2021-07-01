package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;

public final class ClassHelper {

    private static  final Set<Class<?>> CLASS_SET;

    static{
        String appBasePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(appBasePackage);
    }

    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }
    public static Set<Class<?>> getServiceClassSet(){
        Set<Class<?>> classSet=new HashSet<Class<?>>();
        for(Class<?> cls:CLASS_SET){
            if(cls.isAnnotationPresent(Service.class)){
                    classSet.add(cls);
            }
        }
        return classSet;
    }

    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> classSet=new HashSet<Class<?>>();
        for(Class<?> cls:CLASS_SET){
            if(cls.isAnnotationPresent(Controller.class)){
                classSet.add(cls);
            }
        }
        return classSet;
    }

    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> classSet=new HashSet<Class<?>>();
        classSet.addAll(getServiceClassSet());
        classSet.addAll(getControllerClassSet());
        return classSet;
    }
}
