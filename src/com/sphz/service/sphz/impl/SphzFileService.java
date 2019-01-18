package com.sphz.service.sphz.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.sphz.dao.DaoSupport;
import com.sphz.entity.Page;
import com.sphz.service.sphz.FileManager;
import com.sphz.service.sphz.SphzFileManager;
import com.sphz.util.PageData;

/** 
 * 说明： file
 * 创建人：FH Q313596790
 * 创建时间：2018-07-16
 * @version
 */
@Service("sphzFileService")
public class SphzFileService implements SphzFileManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("SphzFileMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("SphzFileMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("SphzFileMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("SphzFileMapper.datalistPage", page);
	}
	
	/**根据文号唯一码获取文件列表
	 * @param pd
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> listFilesByWhwym(PageData pd) throws Exception {
		List<PageData> listFile = (List<PageData>)dao.findForList("SphzFileMapper.listFilesByWhwym", pd);
		return listFile;
	}
	
	/**<!-- 通过档案盒号和类型获取数据 -->
	 * @param pd
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> findByDahAndType(PageData pd) throws Exception {
		List<PageData> listFile = (List<PageData>)dao.findForList("SphzFileMapper.findByDahAndType", pd);
		return listFile;
	}
	
	/**列表
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> fileList(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("SphzFileMapper.list", pd);
	}
	
	/**列表
	 * @param pd
	 * @throws Exception
	 */
	public int countByDAHH(PageData pd)throws Exception{
		return (Integer)dao.findForObject("SphzFileMapper.countByDAHH", pd);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("SphzFileMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("SphzFileMapper.findById", pd);
	}
	
	/**通过文号唯一码获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByWHWYM(PageData pd)throws Exception{
		return (PageData)dao.findForObject("SphzFileMapper.findByWHWYM", pd);
	}
	
	/**通过文号唯一码获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByBusinessId(PageData pd)throws Exception{
		return (PageData)dao.findForObject("SphzFileMapper.findByBusinessId", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("SphzFileMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

