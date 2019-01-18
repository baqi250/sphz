package com.sphz.service.activiti.procdef;

import java.util.List;

import com.sphz.entity.Page;
import com.sphz.util.PageData;

/** 
 * 说明： 流程管理接口
 * 创建人：DK
 * 创建时间：2018-01-06
 * @version
 */
public interface ProcdefManager{

	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
}

