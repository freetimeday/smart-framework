package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public final class PropsUtil {

    private static  final Logger LOGGER= LoggerFactory.getLogger(PropsUtil.class);


    public static Properties loadProps(String fileName){

        Properties props=null;
        InputStream inputStream=null;
        try{
            inputStream=Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if(inputStream==null){
                throw new FileNotFoundException(fileName+"file is not found");
            }
            props=new Properties();
            props.load(inputStream);
        }catch (IOException e){
                LOGGER.error("load properties file failure",e);
        }finally {
            if(inputStream!=null){
                try{
                    inputStream.close();
                }catch (IOException e){
                    LOGGER.error("close inputStream failure",e);
                }
            }
        }
            return  props;
    }


    public static String getString(Properties props,String key,String defaultValue){
        String value=defaultValue;
        if(props.contains(key)){
            value= props.getProperty(key);
        }
        return value;
    }


    /**
     * 获取字符型属性（默认值为空字符串）
     */
    public static String getString(Properties props,String key){
        return getString(props,key,"");
    }


    public static int getInt(Properties props,String key,int defaultValue){
        int value=defaultValue;
        if(props.contains(key)){
            value= CastUtil.castInt(props.getProperty(key));
        }
        return value;
    }

    public static int getInt(Properties props,String key){
        return getInt(props,key,0);
    }


    public static boolean getBoolean(Properties props,String key,Boolean defaultValue){
        Boolean value=defaultValue;
        if(props.contains(key)){
            value= CastUtil.castBoolean(props.getProperty(key));
        }
        return value;
    }

    public static boolean getBoolean(Properties props,String key){
        return getBoolean(props,key,false);
    }

}
