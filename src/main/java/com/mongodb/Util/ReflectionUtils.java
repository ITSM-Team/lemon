package com.mongodb.Util;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Update;

public class ReflectionUtils {
	 private static Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);  
	 
	 /**  
	     * 根据对象获得mongodb Update语句 除id字段以外，所有被赋值的字段都会成为修改项  
	     */  
	    public static Update getUpdateObj(final Object obj) {  
	        if (obj == null)  
	            return null;  
	        Field[] fields = obj.getClass().getDeclaredFields();  
	        Update update = null;  
	        boolean isFirst = true;  
	        for (Field field : fields) {  
	            field.setAccessible(true);  
	            try {  
	                Object value = field.get(obj);  
	                if (value != null) {  
	                    if ("id".equals(field.getName().toLowerCase())  
	                            || "serialversionuid".equals(field.getName()  
	                                    .toLowerCase()))  
	                        continue;  
	                    if (isFirst) {  
	                        update = Update.update(field.getName(), value);  
	                        isFirst = false;  
	                    } else {  
	                        update = update.set(field.getName(), value);  
	                    }  
	                }  
	  
	            } catch (IllegalArgumentException e) {  
	                e.printStackTrace();  
	            } catch (IllegalAccessException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	        return update;  
	    }  

}
