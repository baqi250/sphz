package com.sphz.controller.textdata;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sphz.controller.base.BaseController;
import com.sphz.entity.Page;
import com.sphz.entity.textdata.TextData;
import com.sphz.service.textdata.impl.TextDataServiceImpl;
import com.sphz.util.AppUtil;
import com.sphz.util.FtpUtils;
import com.sphz.util.Jurisdiction;
import com.sphz.util.PageData;

import net.sf.json.JSONArray;

/**
 * @author zd 创建时间:2018-4-23
 */
@Controller
@RequestMapping(value = "/text")
public class TextDataController extends BaseController {
	@Resource(name = "textDataService")
	private TextDataServiceImpl textDataService;
	Map<String, Object> propsMap = FtpUtils.getFtpProperties();// ftp配置
	String hxfwBasepath=(String) propsMap.get("hxfwbasepath");
	String smwsBasepath=(String) propsMap.get("smwsbasepath");
	String dawjBasepath=(String) propsMap.get("dawjbasepath");
	String hostname = (String) propsMap.get("host");
	int port = Integer.parseInt((String) propsMap.get("port"));
	String username = (String) propsMap.get("username");
	String password = (String) propsMap.get("password");
	String menuUrl = "text/list.do"; // 菜单地址(权限用)
	
	//如果点击的ztree不是底层节点 即ISBOTTOMNODE！=0
	@RequestMapping("/list")
	public ModelAndView list(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData(this.getRequest());
		String keywords = pd.getString("keywords");
		if (null != keywords && !"".equals(keywords)) {
			pd.put("keywords", keywords.trim());
		}
		String ID = null == pd.get("ID")?"":pd.get("ID").toString();
		String FTP_URL = null;
		String ISBOTTOMNODE = null;
		if(null != pd.get("id") && !"".equals(pd.get("id").toString())){
			ID = pd.get("id").toString();
		}
		if(null != pd.get("FTP_URL") && !"".equals(pd.get("FTP_URL").toString())){
			FTP_URL = pd.getString("FTP_URL").toString();
		}
		if(null != pd.get("ISBOTTOMNODE") && !"".equals(pd.get("ISBOTTOMNODE").toString())){
			ISBOTTOMNODE = pd.getString("ISBOTTOMNODE").toString();
		}	
		pd.put("ID", ID);//上级ID
		pd.put("FTP_URL", FTP_URL);
		pd.put("ISBOTTOMNODE", ISBOTTOMNODE);
		page.setPd(pd);
		List<PageData> list = textDataService.listData(page);
		mv.addObject("dataList", list);
		mv.addObject("pd", pd);
		mv.addObject("ID", ID);
		mv.addObject("MSG", null == pd.get("MSG")?"'list'".toString():pd.get("MSG").toString()); //MSG=change 则为编辑或删除后跳转过来的
		mv.addObject("QX", Jurisdiction.getHC());
		//mv.addObject("action", "text/list.do");
		mv.setViewName("textdata/textdata");
		return mv;
	}
	
	//如果点击的ztree是底层节点 即ISBOTTOMNODE=0
	@RequestMapping("/listdetail")
	public void listdetail(Page page,HttpServletRequest request,  
            HttpServletResponse response) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData(this.getRequest());			
		String ID = null == pd.get("ID")?"":pd.get("ID").toString();
		String FTP_URL = null;
		String ISBOTTOMNODE = null;
		if(null != pd.get("id") && !"".equals(pd.get("id").toString())){
			ID = pd.get("id").toString();
		}
		if(null != pd.get("FTP_URL") && !"".equals(pd.get("FTP_URL").toString())){
			FTP_URL = pd.getString("FTP_URL").toString();
		}
		if(null != pd.get("ISBOTTOMNODE") && !"".equals(pd.get("ISBOTTOMNODE").toString())){
			ISBOTTOMNODE = pd.getString("ISBOTTOMNODE").toString();
		}	
		/*pd.put("ID", ID);//上级ID
		pd.put("FTP_URL", FTP_URL);
		pd.put("ISBOTTOMNODE", ISBOTTOMNODE);
		mv.addObject("pd", pd);
		mv.setViewName("textdata/textdata_detail");*/
		 FTPClient ftpClient = new FTPClient();
 		 byte[] return_arraybyte=null;  
 		 //连接FTP服务器
 	/*	 ftpClient.connect("192.168.109.44", 21);
 		 ftpClient.login("lsgg", "lsgg");*/
 		 ftpClient.connect(hostname, port);
 		 ftpClient.login(username, password);
 		 ftpClient.setControlEncoding("UTF-8");
 		 //验证FTP服务器是否登录成功
 		 int replyCode = ftpClient.getReplyCode();
 		 if(!FTPReply.isPositiveCompletion(replyCode)){
 			 
 		 }
 		 //System.out.println(FTP_URL);
 		 String[] strs = FTP_URL.split("/");
 		//System.out.println(new String(new String(strs[5].getBytes("UTF-8"),"ISO-8859-1")));
 		response.addHeader("Content-Disposition", "inline;filename=" + new String(new String(strs[5].getBytes("UTF-8"),"ISO-8859-1"))); //attachment下载  inline文件名问题 
 		 //切换FTP目录
 		// ftpClient.changeWorkingDirectory("/fs_lgghpt/regulation");
 		ftpClient.changeWorkingDirectory("/"+strs[3]+"/"+strs[4]);
 		 ftpClient.enterLocalPassiveMode();
 		 OutputStream os = response.getOutputStream();
 		 boolean b = ftpClient.retrieveFile(new String(strs[5].getBytes("UTF-8"),"ISO-8859-1"), os);
 		 //System.out.println(b);
 		 os.close();
		//return mv;
	}
	
	
	/**
	 * 请求新增菜单页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toAdd")
	public ModelAndView toAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		String ISBOTTOMNODE = null;
		try{
			PageData pd = new PageData();
			pd = this.getPageData();
			String ID = (null == pd.get("ID") || "".equals(pd.get("ID").toString()))?"0":pd.get("ID").toString();//接收传过来的上级菜单ID,如果上级为顶级就取值“0”
			if(ID.equals("-1")){
				ID = "0";
			}
			pd.put("ID",ID);
			mv.addObject("ID", ID);	
			mv.addObject("pds", textDataService.getTreeById(pd));	//传入父菜单所有信息
			if((textDataService.getTreeById(pd))!=null){            //新增子节点时候
				ISBOTTOMNODE = "0";
			}else{
				ISBOTTOMNODE = "1";
			}
			mv.addObject("ISBOTTOMNODE", ISBOTTOMNODE);	
			mv.addObject("MSG", "add");							//执行状态 add 为添加
			mv.setViewName("textdata/textdata_edit");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}	
	
	/**
	 * 保存菜单信息
	 * @param menu
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(TextData text)throws Exception{
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
	//	logBefore(logger, Jurisdiction.getUsername()+"保存文本数据树");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			text.setID(String.valueOf(Integer.parseInt(textDataService.findMaxId(pd).get("MID").toString())+1));
			text.setSORT(Integer.parseInt(textDataService.findMaxId(pd).get("MID").toString())+1);
			
			text.setDATA_TYPE(pd.getString("DATA_TYPE"));
					
			textDataService.saveMenu(text); //保存文本数据
		} catch(Exception e){
			logger.error(e.toString(), e);
			mv.addObject("msg","failed");
		}
		if("0".equals(text.getPID())){
			mv.setViewName("redirect:/text/list.do?MSG='change'&ID="+text.getID());//保存成功跳转到列表页面
		}else{
			mv.setViewName("redirect:/text/list.do?MSG='change'&ID="+text.getPID());//保存成功跳转到列表页面
		}
		return mv;
	}
	
	/**
	 * 请求编辑菜单页面
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(String id)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			pd.put("ID",id);				//接收过来的要修改的ID
			pd = textDataService.getTreeById(pd);	//读取此ID的菜单数据
			mv.addObject("pd", pd);				//放入视图容器
			pd.put("ID",pd.get("PID").toString());			//用作读取父菜单信息
			mv.addObject("pds", textDataService.getTreeById(pd));			//传入父菜单所有信息
			mv.addObject("ID", pd.get("PID").toString());	//传入父菜单ID，作为子菜单的父菜单ID用
			mv.addObject("MSG", "edit");
			mv.addObject("ISBOTTOMNODE", "0");
			pd.put("ID",id);			//复原本菜单ID
			mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
			mv.setViewName("textdata/textdata_edit");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 保存编辑
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(TextData text)throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		logBefore(logger, Jurisdiction.getUsername()+"修改分类tree");
		ModelAndView mv = this.getModelAndView();
		int sort = Integer.parseInt(text.getID());
		text.setSORT(sort);
		try{
			textDataService.edit(text);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		mv.setViewName("redirect:/text/list.do?MSG='change'&ID="+text.getPID()); //保存成功跳转到列表页面
		return mv;
	}
	
	/**
	 * 显示列表ztree
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listAllText")
	public ModelAndView listAllDict(Model model, String ID) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			JSONArray arr = JSONArray.fromObject(textDataService.listAllText("0"));
			String json = arr.toString();
			json = json.replaceAll("ID", "id").replaceAll("PID", "pId").replaceAll("NAME", "name")
					.replaceAll("subText", "nodes").replaceAll("hasText", "checked").replaceAll("treeurl", "url");
			model.addAttribute("zTreeNodes", json);
			mv.addObject("ID", ID);
			mv.addObject("pd", pd);
			mv.setViewName("textdata/textdata_ztree");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 根据pid删除tree
	 * @throws Exception 
	 */
	@RequestMapping(value="/deleteByPid")
	@ResponseBody
	public void deleteByPID(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername() + "删除PID下的子节点");
		PageData pd = new PageData();
		pd = this.getPageData();
		textDataService.deleteByPID(pd);
		String str = new String("分类删除成功！".getBytes(),"ISO-8859-1");
		out.write(str);
		out.close();
	}
	
	/**
	 * 删除
	 * 
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete")
	public void delete(PrintWriter out) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "删除textdata");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
			return;
		} // 校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		textDataService.delete(pd);
		out.write("success");
		out.close();
	}

	/**
	 * 批量删除
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "批量删除textdata");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
			return null;
		} // 校验权限
		PageData pd = new PageData();
		Map<String, Object> map = new HashMap<String, Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if (null != DATA_IDS && !"".equals(DATA_IDS)) {
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			textDataService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		} else {
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
}
