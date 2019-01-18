package com.sphz.controller.information.todo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sphz.controller.base.BaseController;
import com.sphz.entity.Page;
import com.sphz.service.information.todo.TodoManager;
import com.sphz.util.AppUtil;
import com.sphz.util.Jurisdiction;
import com.sphz.util.PageData;


/** 
 * 类名称：图片管理
 * 创建人：DK
 * 创建时间：2015-03-21
 */
@Controller
@RequestMapping(value="/todo")
public class TodoController extends BaseController {
	
	String menuUrl = "todo/list.do"; //菜单地址(权限用)
	@Resource(name="todoService")
	private TodoManager todoService;
	
	
	/**列表
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String KEYW = pd.getString("keyword");	//检索条件
		if(null != KEYW && !"".equals(KEYW)){
			pd.put("KEYW", KEYW.trim());
		}
		pd.put("department_id", Jurisdiction.getDEPARTMENT_ID());//状态
		page.setPd(pd);
		List<PageData>	varList = todoService.list(page);	//列出Pictures列表
		mv.setViewName("information/todo/todo_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**去新增用户页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd()throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		//pd.put("todo_id", this.get32UUID());
		/*pd.put("ROLE_ID", "1");
		List<Role> roleList = noticeService.listAllRolesByPId(pd);//列出所有系统用户角色
*/		mv.setViewName("information/todo/todo_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		//mv.addObject("roleList", roleList);
		return mv;
	}
	
	 /**去修改页面
		 * @param
		 * @throws Exception
		 */
		@RequestMapping(value="/goEdit")
		public ModelAndView goEdit()throws Exception{
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			pd = this.getPageData();
			pd = todoService.findById(pd);	//根据ID读取
			mv.setViewName("information/todo/todo_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
			return mv;
		}	
	
	/**保存用户
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		logBefore(logger, Jurisdiction.getUsername()+"新增todo");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("todo_id", this.get32UUID());	//ID 主键
		pd.put("department_id", Jurisdiction.getDEPARTMENT_ID());
		//SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//pd.put("pub_time", df.format(System.currentTimeMillis()));				//用户默认皮肤
		todoService.save(pd);
		mv.setViewName("save_result");
		return mv;
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Todo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		todoService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object delete() throws Exception {
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {return null;} //校验权限
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		todoService.delete(pd);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Attached");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			
			
			todoService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
}
