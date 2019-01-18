package com.sphz.service.textdata;

import java.util.List;

import com.sphz.entity.Page;
import com.sphz.entity.textdata.TextData;
import com.sphz.util.PageData;

public interface TextDataService {
	
	public List<PageData> listData(Page page)throws Exception;
	/**
	 * 通过ID获取其子级列表
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<TextData> listSubTextByParentId(String parentId) throws Exception;
	/**
	 * 获取所有数据并填充每条数据的子级列表(递归处理)
	 * @param MENU_ID
	 * @return
	 * @throws Exception
	 */
	public List<TextData> listAllText(String parentId) throws Exception;
	
	/**
	 * @param menu
	 * @throws Exception
	 */
	public void saveMenu(TextData text) throws Exception;
	
	/**
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getTreeById(PageData pd) throws Exception;
	
	/**
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findMaxId(PageData pd) throws Exception;
	
	/**根据PID删除tree
	 * @param pd
	 * @throws Exception
	 */
	public void deleteByPID(PageData pd)throws Exception;
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception;
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;
	/**
	 * 修改分类子节点内容
	 * @param text
	 * @throws Exception
	 */
	public void edit(TextData text) throws Exception;	
}
