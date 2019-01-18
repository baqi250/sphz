package com.sphz.controller.information.notice;

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
import com.sphz.entity.system.Dictionaries;
import com.sphz.service.information.notice.NoticeManager;
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
@RequestMapping(value="/notice")
public class NoticeController extends BaseController {
	
	String menuUrl = "notice/list.do"; //菜单地址(权限用)
	@Resource(name="noticeService")
	private NoticeManager noticeService;
	
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	
	@Resource(name="picturesService")
	private PicturesManager picturesService;
	
	@Resource(name="panoramaService")
	private PanoramaManager panoramaService;
	
	Map<String, Object> propsMap = FtpUtils.getFtpProperties();
	String hostname = (String) propsMap.get("host");
	int port = Integer.parseInt((String) propsMap.get("port"));
	String username = (String) propsMap.get("username");
	String password = (String) propsMap.get("password");
	
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
		List<PageData>	varList = noticeService.list(page);	//列出Pictures列表
		mv.setViewName("information/notice/notice_list");
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
		pd.put("BIANMA", "005");
		pd.put("notice_id", this.get32UUID());
		List<Dictionaries> notice_forumList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有通讯录群组
		mv.addObject("notice_forumList", notice_forumList);
		/*pd.put("ROLE_ID", "1");
		List<Role> roleList = noticeService.listAllRolesByPId(pd);//列出所有系统用户角色
*/		mv.setViewName("information/notice/notice_edit");
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
			pd = noticeService.findById(pd);	//根据ID读取
			pd.put("BIANMA", "005");
			List<Dictionaries> notice_forumList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有通讯录群组
			mv.addObject("notice_forumList", notice_forumList);
			mv.setViewName("information/notice/notice_edit");
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
			@RequestParam(value="notice_id",required=false) String notice_id,
			@RequestParam(value="pub_time",required=false) String pub_time,
			@RequestParam(value="content",required=false) String content,
			@RequestParam(value="notice_forum",required=false) String notice_forum) throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		logBefore(logger, Jurisdiction.getUsername()+"新增notice");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String fileName = "";
		//pd.put("notice_id", this.get32UUID());	//ID 主键
		//pd.put("title", pd.getString("title"));				//最后登录时间
		//pd.put("content", pd.getString("content"));						//IP
		pd.put("title", title);				//最后登录时间
		pd.put("content", content);						//IP
		pd.put("notice_forum", notice_forum);	
		pd.put("pub_time", pub_time);
		pd.put("publisher", Jurisdiction.getUsername());//状态
		pd.put("department_id", Jurisdiction.getDEPARTMENT_ID());
		//SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//pd.put("pub_time", df.format(System.currentTimeMillis()));				//用户默认皮肤
		//pd.put("notice_forum", pd.getString("notice_forum"));	
		
		InputStream inStream = null;  
        inStream = file.getInputStream();  
        //String basePath="message";//设置服务器中文件保存的根目录  
        String basePath=(String)propsMap.get("panoramabasepath");//设置服务器中文件保存的根目录  
        Date now = new Date();   
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        String filePath = dateFormat.format(now); //根据当前时间设置文件保存的子目录  
        basePath+="/"+filePath+"/"+notice_id;
        fileName= file.getOriginalFilename();    
        
        pd.put("file_id",this.get32UUID());
        pd.put("name",fileName);
        pd.put("path",basePath);
        pd.put("notice_id","news"+notice_id);
        pd.put("create_time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
       
        FtpUtils.uploadFile(hostname, port, username, password, basePath, fileName, inStream);
	
	 	picturesService.saveFile(pd);	
		
		pd.put("picture_url",basePath+"/"+fileName);
		pd.put("notice_id",notice_id);
		noticeService.save(pd);
		mv.setViewName("save_result");
		return mv;
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(HttpServletRequest request,
			@RequestParam(value="file",required=false) MultipartFile file,
			@RequestParam(value="title",required=false) String title,
			@RequestParam(value="notice_id",required=false) String notice_id,
			@RequestParam(value="pub_time",required=false) String pub_time,
			@RequestParam(value="picture_url",required=false) String picture_url,
			@RequestParam(value="content",required=false) String content) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Notice");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		
		String fileName = "";
		pd.put("title", title);
		pd.put("notice_id", notice_id);
		pd.put("pub_time", pub_time);
		pd.put("content", content);
		pd.put("picture_url",picture_url);
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
				basePath += "/" + filePath + "/" + notice_id;
				fileName = file.getOriginalFilename();

				pd.put("file_id", this.get32UUID());
				pd.put("name", fileName);
				pd.put("path", basePath);
				pd.put("panorama_id", "news"+notice_id);
				pd.put("create_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pd.put("picture_url", basePath + "/" + fileName);
				FtpUtils.uploadFile(hostname, port, username, password, basePath, fileName, inStream);

				PageData pd1 = panoramaService.findFileByPanoramaId(pd);
				FtpUtils.deleteFile(hostname, port, username, password, pd1.getString("path"), pd1.getString("name"));

				panoramaService.updateFile(pd);// update
			}
			
		pd.put("notice_id", notice_id);
		noticeService.edit(pd);
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
		//删除ftp文件
		List<PageData> pd1= noticeService.findFileByNoticeId(pd);
		if(null!=pd1&&pd1.size()>0){
			for(int i=0;i<pd1.size();i++){
				FtpUtils.deleteFileFolder(hostname, port, username, password, pd1.get(i).getString("path"));
			}
		}
		
		//删除potal-file中的记录
		noticeService.deleteFileByNoticeId(pd);
		pd.put("panorama_id","news"+pd.getString("notice_id"));
		PageData pd2= panoramaService.findFileByPanoramaId(pd);
		if(null!=pd2){
			FtpUtils.deleteFileFolder(hostname, port, username, password, pd2.getString("path"));
		}
		panoramaService.deleteFileByPanoramaId(pd);
		
		noticeService.delete(pd);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**根据noticeid获取文件
	 * @return
	 */
	@RequestMapping(value="/listFileByNoiceId")
	@ResponseBody
	public Object listFileByNoiceId(){
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success";
		PageData pd = new PageData();
		List<PageData> pdList = new ArrayList<PageData>();
		try{
			pd = this.getPageData();
			pdList = noticeService.listFileByNoiceId(pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("list", pdList);
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 删除文件
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteFile")
	@ResponseBody
	public Object deleteFile() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = noticeService.findFileById(pd);

	

		FtpUtils.deleteFile(hostname, port, username, password, pd.getString("path"), pd.getString("name"));

		noticeService.deleteFile(pd);
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
				pd.put("notice_id",ArrayDATA_IDS[i]);
				
				List<PageData> pd1= noticeService.findFileByNoticeId(pd);
				if(null!=pd1&&pd1.size()>0){
					for(int j=0;j<pd1.size();j++){
						FtpUtils.deleteFileFolder(hostname, port, username, password, pd1.get(j).getString("path"));
					}
				}
				noticeService.deleteFileByNoticeId(pd);
				pd.put("panorama_id","news"+pd.getString("notice_id"));
				PageData pd2= panoramaService.findFileByPanoramaId(pd);
				if(null!=pd2){
					FtpUtils.deleteFileFolder(hostname, port, username, password, pd2.getString("path"));
				}
				panoramaService.deleteFileByPanoramaId(pd);
			}
			
			noticeService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
}
