package com.sphz.service.system.role.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sphz.dao.DaoSupport;
import com.sphz.entity.Page;
import com.sphz.entity.system.Role;
import com.sphz.service.system.role.RoleManager;
import com.sphz.util.PageData;

/**	角色
 * @author DK
 * 修改日期：2015.11.6
 */
@Service("roleService")
public class RoleService implements RoleManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**列出此组下级角色
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Role> listAllRolesByPId(PageData pd) throws Exception {
		return (List<Role>) dao.findForList("RoleMapper.listAllRolesByPId", pd);
	}
	
	/**通过id查找
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageData findObjectById(PageData pd) throws Exception {
		return (PageData)dao.findForObject("RoleMapper.findObjectById", pd);
	}
	
	/**通过角色编码查找
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageData getRoleByRnumber(PageData pd) throws Exception{
		return (PageData)dao.findForObject("RoleMapper.getRoleByRnumber", pd);
	}
	
	/**添加
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void add(PageData pd) throws Exception {
		dao.save("RoleMapper.insert", pd);
	}
	
	/**保存修改
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void edit(PageData pd) throws Exception {
		dao.update("RoleMapper.edit", pd);
	}
	
	/**删除角色
	 * @param ROLE_ID
	 * @throws Exception
	 */
	@Override
	public void deleteRoleById(String ROLE_ID) throws Exception {
		dao.delete("RoleMapper.deleteRoleById", ROLE_ID);
	}
	
	/**给当前角色附加菜单权限
	 * @param role
	 * @throws Exception
	 */
	@Override
	public void updateRoleRights(Role role) throws Exception {
		dao.update("RoleMapper.updateRoleRights", role);
	}
	
	/**通过id查找
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@Override
	public Role getRoleById(String ROLE_ID) throws Exception {
		return (Role) dao.findForObject("RoleMapper.getRoleById", ROLE_ID);
	}
	
	/**给全部子角色加菜单权限
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void setAllRights(PageData pd) throws Exception {
		dao.update("RoleMapper.setAllRights", pd);
	}
	
	/**权限(增删改查)
	 * @param msg 区分增删改查
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void saveB4Button(String msg,PageData pd) throws Exception {
		dao.update("RoleMapper."+msg, pd);
	}

	/**通过角色ID数组获取角色列表
	 * @param arryROLE_ID
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Role> getRoleByArryROLE_ID(String[] arryROLE_ID)throws Exception{
		return (List<Role>) dao.findForList("RoleMapper.listAllRolesByArryROLE_ID", arryROLE_ID);
	}
	
	/**角色列表(弹窗选择用)
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> roleListWindow(Page page)throws Exception{
		return (List<PageData>) dao.findForList("RoleMapper.roleWindowlistPage", page);
	}
}