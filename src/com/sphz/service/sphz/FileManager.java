package com.sphz.service.sphz;

import java.util.List;

import com.sphz.entity.Page;
import com.sphz.util.PageData;

/** 
 * 说明： file接口
 * 创建人：FH Q313596790
 * 创建时间：2018-07-16
 * @version
 */
public interface FileManager{

	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception;
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
	
	/**根据文号唯一码获取文件列表
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listFilesByWhwym(PageData pd)throws Exception;
	
	/**<!-- 通过档案盒号和类型获取数据 -->
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> findByDahAndType(PageData pd)throws Exception;
	/**<!-- 通过businessId和类型获取数据 -->
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> findByBusinessIdAndType(PageData pd)throws Exception;
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listAll(PageData pd)throws Exception;
	
	/**列表
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> fileList(PageData pd)throws Exception;
	
	/**根据BSNUM获取文件列表
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> fileListByBSNUM(PageData page)throws Exception;
	
	/**根据BusinessId获取文件列表
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listByBusinessId(PageData page)throws Exception;
	
	/**根据文号唯一码获取文件列表数量
	 * @param pd
	 * @throws Exception
	 */
	public int countByWHWYM(PageData pd)throws Exception;
	
	/**根据BSNUM获取文件列表数量
	 * @param pd
	 * @throws Exception
	 */
	public int countByBSNUM(PageData pd)throws Exception;
	
	/**根据BusinessId获取文件列表数量
	 * @param pd
	 * @throws Exception
	 */
	public int countByBusinessId(PageData pd)throws Exception;
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	
	/**通过文号唯一码获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByWHWYM(PageData pd)throws Exception;
	
	/**通过文号唯一码获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByBusinessId(PageData pd)throws Exception;
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;
	
}

