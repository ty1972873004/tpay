package com.tpay.payment.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @desc 
 * @author Trazen
 * @since 2018-04-09
 * @version 1.0
 */
public class BeanToMapUtils {

	/**
	 * Converts a map to a JavaBean.
	 * 
	 * @param type
	 *            type to convert
	 * @param map
	 *            map to convert
	 * @return JavaBean converted
	 * @throws IntrospectionException
	 *             failed to get class fields
	 * @throws IllegalAccessException
	 *             failed to instant JavaBean
	 * @throws InstantiationException
	 *             failed to instant JavaBean
	 * @throws InvocationTargetException
	 *             failed to call setters
	 */
	public static final Object toBean(Class<?> type,
			Map<String, ? extends Object> map) throws IntrospectionException,
			IllegalAccessException, InstantiationException,
			InvocationTargetException {
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		Object obj = type.newInstance();
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (map.containsKey(propertyName)) {
				Object value = map.get(propertyName);
				Object[] args = new Object[1];
				args[0] = value;
				descriptor.getWriteMethod().invoke(obj, args);
			}
		}
		return obj;
	}

	public static List<Field> getAllFields(Object obj,Class fieldType){
		List<Field> fields = new ArrayList<Field>();
		List<Field> fieldsResult = new ArrayList<Field>();
		if(obj!=null){
			Class clazz = obj.getClass();
			fields.addAll(getBeanField(clazz));
			Class clazzSuper = clazz.getSuperclass();
			if(clazzSuper!=null){
				fields.addAll(getBeanField(clazzSuper));
			}
		}
		for(Field f:fields){
			if(f.getType().equals(fieldType)){
				fieldsResult.add(f);
			}
		}
		return fields;
	}
	public static List<Field> getAllFields(Object obj){
		List<Field> fields = new ArrayList<Field>();
		if(obj!=null){
			Class clazz = obj.getClass();
			fields.addAll(getBeanField(clazz));
			Class clazzSuper = clazz.getSuperclass();
			if(clazzSuper!=null){
				fields.addAll(getBeanField(clazzSuper));
			}
		}
		return fields;
	}
	
	public static List<Field> getBeanField(Class clazz){
		List<Field> fields = new ArrayList<Field>();
		if(clazz != null){
			fields.addAll(new ArrayList(Arrays.asList(clazz.getDeclaredFields())));
		}
		return fields;
	}
	/**
	 * * 将一个 JavaBean 对象转化为一个 Map * @param bean 要转化的JavaBean 对象 * @return 转化出来的
	 * Map 对象 * @throws IntrospectionException 如果分析类属性失败 * @throws
	 * IllegalAccessException 如果实例化 JavaBean 失败 * @throws
	 * InvocationTargetException 如果调用属性的 setter 方法失败
	 */
	public static Map convertBean(Object bean) throws IntrospectionException,
			IllegalAccessException, InvocationTargetException {
		Class type = bean.getClass();
		Map returnMap = new HashMap();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null) {
					returnMap.put(propertyName, result);
				} else {
					returnMap.put(propertyName, "");
				}
			}
		}
		return returnMap;
	}
	
	/**
	 * 利用java反射机制将任意对象的转换为map存储
	 * @param obj
	 * @return
	 * @author wmchen
	 * 2015-10-17
	 */
	public static Map<String, Object> getValueMap(Object obj) {  
		if(obj instanceof List){
			Object[] array = new Object[((List) obj).size()];
			Map<String, Object> map = new HashMap<String, Object>();
			int i=0;
    		for(Iterator it=((List) obj).iterator(); it.hasNext();){
    			array[i++] = getValueMap(it.next());
    		}
    		map.put("list", array);
    		return map;
    	}  
        Map<String, Object> map = new HashMap<String, Object>();  
        // System.out.println(obj.getClass());  
        // 获取f对象对应类中的所有属性域  
        Field[] fields = obj.getClass().getDeclaredFields();  
        for (int i = 0, len = fields.length; i < len; i++) {  
            String varName = fields[i].getName();  
            try {  
                // 获取原来的访问控制权限  
                boolean accessFlag = fields[i].isAccessible();  
                // 修改访问控制权限  
                fields[i].setAccessible(true);  
                // 获取在对象f中属性fields[i]对应的对象中的变量  
                Object o = fields[i].get(obj);  
                if(!varName.equals("serialVersionUID")){
                	if (o != null) {
                    	if(o instanceof List){
                    		Object[] array = new Object[((List) o).size()];
                    		int j=0;
                    		for(Iterator it=((List) o).iterator(); it.hasNext();){
                    			array[j++] = getValueMap(it.next());
                    		}
                    		map.put(varName, array); 
                    	}else if(o instanceof Class){
                    		map.put(varName, getValueMap(o)); 
                    	}else{
                    		map.put(varName, o);  
                    	}
                    } else{
                    	map.put(varName, o);  
                    }
                }
                
                    
                // System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);  
                // 恢复访问控制权限  
                fields[i].setAccessible(accessFlag);  
            } catch (IllegalArgumentException ex) {  
                ex.printStackTrace();  
            } catch (IllegalAccessException ex) {  
                ex.printStackTrace();  
            }  
        }  
        return map;  
  
    }
	

	/**
	 * 利用java反射机制将任意对象的转换为map存储
	 * @param obj
	 * @param jsonName 返回的json对象名称
	 * @return
	 * @author yuwm
	 * 2016-1-8
	 */
	public static Map<String, Object> getValueMap(Object obj,String jsonName) {  
		if(obj instanceof List){
			Object[] array = new Object[((List) obj).size()];
			Map<String, Object> map = new HashMap<String, Object>();
			int i=0;
    		for(Iterator it=((List) obj).iterator(); it.hasNext();){
    			array[i++] = getValueMap(it.next());
    		}
    		map.put(jsonName, array);
    		return map;
    	}  
        Map<String, Object> map = new HashMap<String, Object>();  
        // System.out.println(obj.getClass());  
        // 获取f对象对应类中的所有属性域  
        Field[] fields = obj.getClass().getDeclaredFields();  
        for (int i = 0, len = fields.length; i < len; i++) {  
            String varName = fields[i].getName();  
            try {  
                // 获取原来的访问控制权限  
                boolean accessFlag = fields[i].isAccessible();  
                // 修改访问控制权限  
                fields[i].setAccessible(true);  
                // 获取在对象f中属性fields[i]对应的对象中的变量  
                Object o = fields[i].get(obj);  
                if(!varName.equals("serialVersionUID")){
                	if (o != null) {
                    	if(o instanceof List){
                    		Object[] array = new Object[((List) o).size()];
                    		int j=0;
                    		for(Iterator it=((List) o).iterator(); it.hasNext();){
                    			array[j++] = getValueMap(it.next());
                    		}
                    		map.put(varName, array); 
                    	}else if(o instanceof Class){
                    		map.put(varName, getValueMap(o)); 
                    	}else{
                    		map.put(varName, o);  
                    	}
                    } else{
                    	map.put(varName, o);  
                    }
                }
                
                    
                // System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);  
                // 恢复访问控制权限  
                fields[i].setAccessible(accessFlag);  
            } catch (IllegalArgumentException ex) {  
                ex.printStackTrace();  
            } catch (IllegalAccessException ex) {  
                ex.printStackTrace();  
            }  
        }  
        return map;  
  
    }
}
