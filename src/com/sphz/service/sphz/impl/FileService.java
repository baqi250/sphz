package com.sphz.service.sphz.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.sphz.dao.DaoSupport;
import com.sphz.entity.Page;
import com.sphz.service.sphz.FileManager;
import com.sphz.util.PageData;

/** 
 * 说明： file
 * 创建人：FH Q313596790
 * 创建时间：2018-07-16
 * @version
 */
@Service("fileService")
public class FileService implements FileManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("FileMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("FileMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("FileMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("FileMapper.datalistPage", page);
	}
	
	/**根据文号唯一码获取文件列表
	 * @param pd
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> listFilesByWhwym(PageData pd) throws Exception {
		List<PageData> listFile = (List<PageData>)dao.findForList("FileMapper.listFilesByWhwym", pd);
		return listFile;
	}
	
	/**<!-- 通过档案盒号和类型获取数据 -->
	 * @param pd
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> findByDahAndType(PageData pd) throws Exception {
		List<PageData> listFile = (List<PageData>)dao.findForList("FileMapper.findByDahAndType", pd);
		return listFile;
	}
	
	/**<!-- 通过BusinessId和类型获取数据 -->
	 * @param pd
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> findByBusinessIdAndType(PageData pd) throws Exception {
		List<PageData> listFile = (List<PageData>)dao.findForList("FileMapper.findByBusinessIdAndType", pd);
		return listFile;
	}
	
	/**列表
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> fileList(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("FileMapper.list", pd);
	}
	
	/**根据BSNUM获取发文列表
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> fileListByBSNUM(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("FileMapper.listByBSNUM", pd);
	}
	
	/**根据BusinessId获取发文列表
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listByBusinessId(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("FileMapper.listByBusinessId", pd);
	}
	
	/**列表
	 * @param pd
	 * @throws Exception
	 */
	public int countByWHWYM(PageData pd)throws Exception{
		return (Integer)dao.findForObject("FileMapper.countByWHWYM", pd);
	}
	
	/**根据BSNUM获取文件个数
	 * @param pd
	 * @throws Exception
	 */
	public int countByBSNUM(PageData pd)throws Exception{
		return (Integer)dao.findForObject("FileMapper.countByBSNUM", pd);
	}
	
	/**根据BusinessId获取文件个数
	 * @param pd
	 * @throws Exception
	 */
	public int countByBusinessId(PageData pd)throws Exception{
		return (Integer)dao.findForObject("FileMapper.countByBusinessId", pd);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("FileMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("FileMapper.findById", pd);
	}
	
	/**通过文号唯一码获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByWHWYM(PageData pd)throws Exception{
		return (PageData)dao.findForObject("FileMapper.findByWHWYM", pd);
	}
	
	/**通过文号唯一码获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByBusinessId(PageData pd)throws Exception{
		return (PageData)dao.findForObject("FileMapper.findByBusinessId", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("FileMapper.deleteAll", ArrayDATA_IDS);
	}

	
}

