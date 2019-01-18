package com.sphz.service.information.knowledge.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sphz.dao.DaoSupport;
import com.sphz.entity.Page;
import com.sphz.service.information.knowledge.KnowledgeManager;
import com.sphz.util.PageData;

/** 
 * 说明： 明细表
 * 创建人：DK
 * 创建时间：2016-04-17
 * @version
 */
@Service("knowledgeService")
public class KnowledgeService implements KnowledgeManager{  

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void save(PageData pd)throws Exception{
		dao.save("KnowledgeMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void delete(PageData pd)throws Exception{
		dao.delete("KnowledgeMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void edit(PageData pd)throws Exception{
		dao.update("KnowledgeMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("KnowledgeMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("KnowledgeMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("KnowledgeMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	@Override
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("KnowledgeMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/**查询明细总数
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public PageData findCount(PageData pd)throws Exception{
		return (PageData)dao.findForObject("KnowledgeMapper.findCount", pd);
	}

	/**根据通知id获取文件列表
	 * @param pd
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> listFileByNoiceId(PageData pd) throws Exception {
		List<PageData> listFile = (List<PageData>)dao.findForList("KnowledgeMapper.listFileByNoiceId", pd);
		return listFile;
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void updateFile(PageData pd)throws Exception{
		dao.delete("KnowledgeMapper.updateFileByKnowId", pd);
	}
	
	/**通过id获取文件
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public PageData findFileByKnowId(PageData pd)throws Exception{
		return (PageData)dao.findForObject("KnowledgeMapper.findFileByKnowId", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void deleteFileByKnowId(PageData pd)throws Exception{
		dao.delete("KnowledgeMapper.deleteFileByKnowId", pd);
	}
	
	
}
