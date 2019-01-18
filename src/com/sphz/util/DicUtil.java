package com.sphz.util;

import com.sphz.service.system.dictionaries.DictionariesManager;

/**
 * @描述 : 数据字典工具类
 * @创建者：liushengsong @创建时间： 2017年2月20日下午3:00:17
 *
 */
public class DicUtil {

	
	public static String convertBianma2Name(String BIANMA,DictionariesManager dictionariesService){
		PageData pd=new PageData();
		pd.put("BIANMA", BIANMA);
		PageData pdResult;
		try {
			pdResult = dictionariesService.findByBianma(pd);
			if(null!=pdResult){
				return pdResult.getString("NAME");
			}else{
				return BIANMA;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			return "";
		}
		
	}
}
