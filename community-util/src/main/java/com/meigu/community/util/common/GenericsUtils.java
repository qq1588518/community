package com.meigu.community.util.common;


import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Generics的util类.
 * @author
 */
@SuppressWarnings({"rawtypes"})
public class GenericsUtils{
	private static final Log	log	= LogFactory.getLog(GenericsUtils.class);
	
	private GenericsUtils()
	{
	}
	
	/**
	 * 通过反射,获得定义Class时声明的父类的范型参数的类型. 如public BookManager extends GenricManager<Book>
	 * 
	 * @param clazz
	 *            The class to introspect
	 * @return the first generic declaration, or <code>Object.class</code> if
	 *         cannot be determined
	 */
	public static Class getSuperClassGenricType(Class clazz)
	{
		return getSuperClassGenricType(clazz, 0);
	}
	
	/**
	 * 通过反射,获得定义Class时声明的父类的范型参数的类型. 如public BookManager extends GenricManager<Book>
	 * 
	 * @param clazz
	 *            clazz The class to introspect
	 * @param index
	 *            the Index of the generic declaration,start from 0.
	 * @return the index generic declaration, or <code>Object.class</code> if
	 *         cannot be determined
	 */
	public static Class getSuperClassGenricType(Class clazz, int index)
	{
		
		Type genType = clazz.getGenericSuperclass();
		/* 判断是否继承自此类 */
		if(!(genType instanceof ParameterizedType))
		{
			log.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
			return Object.class;
		}
		
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		/* 判断传的参数：index 是否合法 */
		if(index >= params.length || index < 0)
		{
			log.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);
			return Object.class;
		}
		/* 判断是否是真正的类型，因为可能是 泛化类型 T */
		if(!(params[index] instanceof Class))
		{
			log.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
			return Object.class;
		}
		return (Class) params[index];
	}
	
	/**获取类的所有属性*/
	public static PropertyDescriptor[]  getProperties(Class clazz) {
		PropertyDescriptor[]  properties= new PropertyDescriptor[]{};
		try {
			properties=Introspector.getBeanInfo(clazz).getPropertyDescriptors();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	/**获取对象属性->值*/
	public static Map<String, Object> getBeanKeyValue(Object bean) {
		Map<String, Object>  keyValues=new HashMap<String, Object>();
		try {
			PropertyDescriptor[]  pds=getProperties(bean.getClass());
			for (PropertyDescriptor property : pds) {
				Method  method=property.getReadMethod();
				Object  value =method.invoke(bean);
				keyValues.put(property.getName(), value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return keyValues;
	}
	
	/**获取对象指定属性值*/
	public static Object getPropertyValue(Object bean,String fieldName) {
		Object  value=null;
		try {
			if (bean!=null) {
				PropertyDescriptor 	pd 		= new PropertyDescriptor(fieldName,bean.getClass());
				Method 				method 	= pd.getReadMethod();
									value	= method.invoke(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  value;
	}
	
}
