package com.sphz.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class ConfigUtils extends PropertyPlaceholderConfigurer{

	private static Map<String,Object>propMap;
	public static Object getPropMap(String name) {
		return propMap.get(name);
	}
	public static Object setPropMap(String name,Object value) {
		return propMap.put(name,value);
	}
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		// TODO Auto-generated method stub
		super.processProperties(beanFactoryToProcess, props);
		propMap=new HashMap<String,Object>();
		for(Object key:props.keySet()){
			String keyStr=key.toString();
			String value=props.getProperty(keyStr);
			propMap.put(keyStr,value);
		}
	}
	
	
}
