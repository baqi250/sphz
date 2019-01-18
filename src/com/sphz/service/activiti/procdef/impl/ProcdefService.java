package com.sphz.service.activiti.procdef.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.sphz.dao.DaoSupport;
import com.sphz.entity.Page;
import com.sphz.service.activiti.procdef.ProcdefManager;
import com.sphz.util.PageData;

/** 
 * 说明： 流程管理
 * 创建人：DK
 * 创建时间：2018-01-06
 * @version
 */
@Service("procdefService")
public class ProcdefService implements ProcdefManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ProcdefMapper.datalistPage", page);
	}
	
}

