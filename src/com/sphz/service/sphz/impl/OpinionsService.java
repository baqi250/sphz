package com.sphz.service.sphz.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.sphz.dao.DaoSupport;
import com.sphz.entity.Page;
import com.sphz.service.sphz.OpinionsManager;
import com.sphz.util.PageData;

/** 
 * 说明： opinions
 * 创建人：FH Q313596790
 * 创建时间：2018-07-13
 * @version
 */
@Service("opinionsService")
public class OpinionsService implements OpinionsManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("OpinionsMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("OpinionsMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("OpinionsMapper.edit", pd);
	}
	/**修改状态
	 * @param pd
	 * @throws Exception
	 */
	public void editStatus(PageData pd)throws Exception{
		dao.update("OpinionsMapper.editStatus", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("OpinionsMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("OpinionsMapper.listAll", pd);
	}
	
	/**列表(筛选条件)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> searchList(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("OpinionsMapper.searchList", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("OpinionsMapper.findById", pd);
	}
	
	/**通过proj_no获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByProjNo(PageData pd)throws Exception{
		return (PageData)dao.findForObject("OpinionsMapper.findByProjNo", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("OpinionsMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/**获取总数
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public PageData getCountByRegion(PageData pd)throws Exception{
		return (PageData)dao.findForObject("OpinionsMapper.getCountByRegion", pd);
	}
	
}

