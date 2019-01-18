package com.sphz.service.system.dictionaries;

import java.util.List;

import com.sphz.entity.Page;
import com.sphz.entity.system.DicTreeSelect;
import com.sphz.entity.system.Dictionaries;
import com.sphz.util.PageData;


/** 
 * 说明： 数据字典接口类
 * 创建人：DK
 * 创建时间：2015-12-16
 * @version
 */
public interface DictionariesManager{

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
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;

	/**通过编码获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByBianma(PageData pd)throws Exception;
	
	/**
	 * 通过ID获取其子级列表
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<Dictionaries> listSubDictByParentId(String parentId) throws Exception;
	
	/**
	 * 获取所有数据并填充每条数据的子级列表(递归处理)
	 * @param MENU_ID
	 * @return
	 * @throws Exception
	 */
	public List<Dictionaries> listAllDict(String parentId) throws Exception;
	
	/**
	 * 获取所有数据并填充每条数据的子级列表(递归处理)用于代码生成器引用数据字典
	 * @param MENU_ID
	 * @return
	 * @throws Exception
	 */
	public List<Dictionaries> listAllDictToCreateCode(String parentId) throws Exception;
	
	/**排查表检查是否被占用
	 * @param pd
	 * @throws Exception
	 */
	public PageData findFromTbs(PageData pd)throws Exception;
	
	/**
	 * 通过编码获取其子级列表
	 * @param pd
	 * @return
	 */
	public List<Dictionaries> listAllItemsByCodeValue(PageData pd)throws Exception;
	
	/**
	 * 通过编码获取列表
	 * @param pd
	 * @return
	 */
	public List<Dictionaries> listItemsByBianma(PageData pd)throws Exception;
	
	/**
	 * 获取所有数据并填充每条数据的子级列表(递归处理)下拉ztree用
	 * @param MENU_ID
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listAllDictsToSelect(String parentId, List<PageData> zdictPdList) throws Exception;
	
	/**
	 * 根据字典项id获取子数据字典
	 * @param parent_id
	 * @return
	 * @throws Exception
	 */
	public List<DicTreeSelect> listSubDicTreeByParentId(String parentId) throws Exception;
	
	/**
	 * 获取字典项的所有子字典项，用于下拉树选择框（递归）
	 * @param parent_id
	 * @return
	 * @throws Exception
	 */
	public List<DicTreeSelect> listAllDicTreeSelect(String parentId) throws Exception;
	
	/**通过ids获取英文名称
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> getNameEnByIds(String[] ids)throws Exception;
}

