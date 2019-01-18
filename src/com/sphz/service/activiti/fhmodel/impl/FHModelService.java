package com.sphz.service.activiti.fhmodel.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.sphz.dao.DaoSupport;
import com.sphz.entity.Page;
import com.sphz.service.activiti.fhmodel.FHModelManager;
import com.sphz.util.PageData;

/** 
 * 说明： 工作流模型管理
 * 创建人：DK
 * 创建时间：2017-12-26
 * @version
 */
@Service("fhmodelService")
public class FHModelService implements FHModelManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void edit(PageData pd)throws Exception{
		dao.update("FHModelMapper.edit", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("FHModelMapper.findById", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("FHModelMapper.datalistPage", page);
	}
	
}

