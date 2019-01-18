package com.sphz.controller.information.panorama;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sphz.controller.base.BaseController;
import com.sphz.entity.Page;
import com.sphz.service.information.panorama.PanoramaManager;
import com.sphz.service.information.pictures.PicturesManager;
import com.sphz.service.system.dictionaries.DictionariesManager;
import com.sphz.util.AppUtil;
import com.sphz.util.FtpUtils;
import com.sphz.util.Jurisdiction;
import com.sphz.util.PageData;


/** 
 * 类名称：图片管理
 * 创建人：DK
 * 创建时间：2015-03-21
 */
@Controller
@RequestMapping(value="/panorama")
public class PanoramaController extends BaseController {
	
	String menuUrl = "panorama/list.do"; //菜单地址(权限用)
	@Resource(name="panoramaService")
	private PanoramaManager panoramaService;
	
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	
	@Resource(name="picturesService")
	private PicturesManager picturesService;
	
	 Map<String,Object>propsMap=FtpUtils.getFtpProperties();
	 String hostname = (String)propsMap.get("host");
	 int port = Integer.parseInt((String)propsMap.get("port"));
	 String username = (String)propsMap.get("username");
	 String password = (String)propsMap.get("password");
	
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
		pd.put("user_name", Jurisdiction.getUsername());//状态
		page.setPd(pd);
		List<PageData>	varList = panoramaService.list(page);	//列出Pictures列表
		mv.setViewName("information/panorama/panorama_list");
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
		//pd.put("BIANMA", "00506");
		//pd.put("notice_id", this.get32UUID());
		//List<Dictionaries> notice_forumList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有通讯录群组
		//mv.addObject("knowledge_forumList", notice_forumList);
		/*pd.put("ROLE_ID", "1");
		List<Role> roleList = noticeService.listAllRolesByPId(pd);//列出所有系统用户角色
*/		mv.setViewName("information/panorama/panorama_edit");
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
			pd = panoramaService.findById(pd);	//根据ID读取
			/*pd.put("BIANMA", "00506");
			List<Dictionaries> knowledge_forumList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有通讯录群组
			mv.addObject("knowledge_forumList", knowledge_forumList);*/
			mv.setViewName("information/panorama/panorama_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
			return mv;
		}	
	
	/**保存用户
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(HttpServletRequest request,
			@RequestParam(value="file",required=false) MultipartFile file,
			@RequestParam(value="title",required=false) String title,
			@RequestParam(value="photo_date",required=false) String photo_date,
			@RequestParam(value="panorama_url",required=false) String panorama_url) throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		logBefore(logger, Jurisdiction.getUsername()+"新增panorama");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String fileName = "";
		pd.put("title", title);		//图片ID
		pd.put("photo_date", photo_date);		//图片ID
		//pd.put("dictionaries_id", dictionaries_id);			//属于ID
		String panorama_id=this.get32UUID();
		pd.put("panorama_id", panorama_id);	//ID 主键
		pd.put("panorama_url",panorama_url);
		pd.put("publisher", Jurisdiction.getUsername());//状态
		pd.put("department_id", Jurisdiction.getDEPARTMENT_ID());
		//SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//pd.put("photo_date", df.format(System.currentTimeMillis()));	
		
			
			
			InputStream inStream = null;  
	        inStream = file.getInputStream();  
	        //String basePath="message";//设置服务器中文件保存的根目录  
	        String basePath=(String)propsMap.get("panoramabasepath");//设置服务器中文件保存的根目录  
	        Date now = new Date();   
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	        String filePath = dateFormat.format(now); //根据当前时间设置文件保存的子目录  
	        basePath+="/"+filePath+"/"+panorama_id;
	        fileName= file.getOriginalFilename();    
	        
	        pd.put("file_id",this.get32UUID());
	        pd.put("name",fileName);
	        pd.put("path",basePath);
	        pd.put("notice_id",panorama_id);
	        pd.put("create_time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	       
	        FtpUtils.uploadFile(hostname, port, username, password, basePath, fileName, inStream);
		
		 	picturesService.saveFile(pd);	
			
			pd.put("picture_url",basePath+"/"+fileName);
		
		
	
		//pd.put("file_name",fileName);
		//用户默认皮肤
		panoramaService.save(pd);
		mv.setViewName("save_result");
		return mv;
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/edit")
	public ModelAndView edit(HttpServletRequest request,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "panorama_id", required = false) String panorama_id,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value="photo_date",required=false) String photo_date,
			@RequestParam(value="picture_url",required=false) String picture_url,
			@RequestParam(value = "panorama_url", required = false) String panorama_url) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "修改Panorama");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
			return null;
		} // 校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String fileName = "";
		pd.put("title", title);
		pd.put("photo_date", photo_date);
		pd.put("panorama_id", panorama_id);
		pd.put("panorama_url", panorama_url);
		pd.put("picture_url", picture_url);
		// pd.put("dictionaries_id",dictionaries_id1);
	
			if (file.getSize() == 0) {

			} else {
				InputStream inStream = null;
				inStream = file.getInputStream();
				// String basePath="message";//设置服务器中文件保存的根目录
				String basePath = (String) propsMap.get("panoramabasepath");// 设置服务器中文件保存的根目录
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String filePath = dateFormat.format(now); // 根据当前时间设置文件保存的子目录
				basePath += "/" + filePath + "/" + panorama_id;
				fileName = file.getOriginalFilename();

				pd.put("file_id", this.get32UUID());
				pd.put("name", fileName);
				pd.put("path", basePath);
				pd.put("notice_id", panorama_id);
				pd.put("create_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pd.put("picture_url", basePath + "/" + fileName);
				FtpUtils.uploadFile(hostname, port, username, password, basePath, fileName, inStream);

				PageData pd1 = panoramaService.findFileByPanoramaId(pd);
				FtpUtils.deleteFile(hostname, port, username, password, pd1.getString("path"), pd1.getString("name"));

				panoramaService.updateFile(pd);// update
			}
			
		panoramaService.edit(pd);
		mv.addObject("msg", "success");
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
		PageData pd1= panoramaService.findFileByPanoramaId(pd);
		if(null!=pd1){
			FtpUtils.deleteFileFolder(hostname, port, username, password, pd1.getString("path"));
		}
		panoramaService.deleteFileByPanoramaId(pd);
		panoramaService.delete(pd);
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
			for(int i=0;i<ArrayDATA_IDS.length;i++){
				pd.put("panorama_id",ArrayDATA_IDS[i]);
				PageData pd1= panoramaService.findFileByPanoramaId(pd);
				if(null!=pd1){
					FtpUtils.deleteFileFolder(hostname, port, username, password, pd1.getString("path"));
				}
				panoramaService.deleteFileByPanoramaId(pd);
			}
			
			panoramaService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	
}
