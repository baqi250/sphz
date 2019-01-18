package com.sphz.util;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.druid.proxy.jdbc.ClobProxyImpl;
/** 
 * 说明：参数封装Map,解决表单enctype="multipart/form-data"类型取不到参数值的问题
 * 创建人：DK
 * 修改时间：2014年9月20日
 * @version
 */
public class FilePageData extends HashMap implements Map{
	
	private static final long serialVersionUID = 1L;
	
	Map map = null;
	MultipartHttpServletRequest request;
	public FilePageData(HttpServletRequest request){
		this.request = (MultipartHttpServletRequest) request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap(); 
		Iterator entries = properties.entrySet().iterator(); 
		Map.Entry entry; 
		String name = "";  
		String value = "";  
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next(); 
			name = (String) entry.getKey(); 
			Object valueObj = entry.getValue(); 
			if(null == valueObj){ 
				value = ""; 
			}else if(valueObj instanceof String[]){ 
				String[] values = (String[])valueObj;
				for(int i=0;i<values.length;i++){ 
					 value = values[i] + ",";
				}
				value = value.substring(0, value.length()-1); 
			}else{
				value = valueObj.toString(); 
			}
			returnMap.put(name, value); 
		}
		map = returnMap;
	}
	
	public FilePageData() {
		map = new HashMap();
	}
	
	@Override
	public Object get(Object key) {
		Object obj = null;
		if(map.get(key) instanceof Object[]) {
			Object[] arr = (Object[])map.get(key);
			obj = request == null ? arr:(request.getParameter((String)key) == null ? arr:arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}
	
	public String getString(Object key) {
		return (String)get(key);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		if(value instanceof ClobProxyImpl){ 			//读取oracle Clob类型数据
			try {
				ClobProxyImpl cpi = (ClobProxyImpl)value;
				Reader is = cpi.getCharacterStream(); 	//获取流
				BufferedReader br = new BufferedReader(is);
				String str = br.readLine();
				StringBuffer sb = new StringBuffer();
				while(str != null){						//循环读取数据拼接到字符串
					sb.append(str);
					sb.append("\n");
					str = br.readLine();
				}
				value = sb.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map.put(key, value);
	}
	
	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return map.containsValue(value);
	}

	@Override
	public Set entrySet() {
		// TODO Auto-generated method stub
		return map.entrySet();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return map.isEmpty();
	}

	@Override
	public Set keySet() {
		// TODO Auto-generated method stub
		return map.keySet();
	}

	@Override
	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		// TODO Auto-generated method stub
		map.putAll(t);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return map.size();
	}

	@Override
	public Collection values() {
		// TODO Auto-generated method stub
		return map.values();
	}
	
}
