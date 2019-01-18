package com.sphz.controller.sphz.files;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.bson.Document;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.sphz.controller.base.BaseController;
import com.sphz.entity.Page;
import com.sphz.service.information.pictures.PicturesManager;
import com.sphz.service.sphz.FileManager;
import com.sphz.util.AppUtil;
import com.sphz.util.Const;
import com.sphz.util.DateUtil;
import com.sphz.util.DelAllFile;
import com.sphz.util.FileUpload;
import com.sphz.util.FtpUtils;
import com.sphz.util.GetWeb;
import com.sphz.util.Jurisdiction;
import com.sphz.util.MongoDBUtil;
import com.sphz.util.PageData;
import com.sphz.util.PathUtil;
import com.sphz.util.Tools;
import com.sphz.util.Watermark;

/** 
 * 类名称：图片管理
 * 创建人：DK
 * 创建时间：2015-03-21
 */
@Controller
@RequestMapping(value="sphz/files")
public class FilesController extends BaseController {
	
	@Resource(name="picturesService")
	private PicturesManager picturesService;
	@Resource(name="fileService")
	private FileManager fileService;
	Map<String, Object> propsMap = FtpUtils.getFtpProperties();// ftp配置
	String hxfwBasepath=(String) propsMap.get("hxfwbasepath");
	String smwsBasepath=(String) propsMap.get("smwsbasepath");
	String dawjBasepath=(String) propsMap.get("dawjbasepath");
	String hostname = (String) propsMap.get("host");
	int port = Integer.parseInt((String) propsMap.get("port"));
	String username = (String) propsMap.get("username");
	String password = (String) propsMap.get("password");
	
	
	/**
	 * 获取发文列表
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/list")
	@ResponseBody
	public Object list() throws Exception{
		
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success"; 
		PageData pd = new PageData();
		List<PageData> fileList=new ArrayList<PageData>();
		try{
			pd = this.getPageData();
			
			
			Integer sEcho=Integer.parseInt(pd.getString("sEcho"));
			Integer iDisplayStart =Integer.parseInt(pd.getString("iDisplayStart"));
			Integer iDisplayLength =Integer.parseInt(pd.getString("iDisplayLength"));
			pd.put("pageIndex",iDisplayStart);
			pd.put("pageSize",iDisplayLength);
			pd.put("startIndex",iDisplayStart);
		    fileList = fileService.listByBusinessId(pd);
			int count=fileService.countByBusinessId(pd);
			
			//Integer intValue = new Long(count).intValue();
			//int initEcho=sEcho+1;
			map.put("iDisplayStart",iDisplayStart);
			map.put("iDisplayLength",iDisplayLength);
			map.put("sEcho", sEcho);
			map.put("iTotalRecords", count);
			map.put("iTotalDisplayRecords", count);//显示的条数 
			
		} catch(Exception e){
			errInfo=e.toString();
			logger.error(e.toString(), e);
		}
		map.put("fileList", fileList);
		map.put("result", errInfo);				//返回结果
		//close();
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 去附件上传页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goFileUpload")
	public ModelAndView goFileUpload() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd=this.getPageData();
		mv.addObject("pd", pd);
		mv.setViewName("sphz/fawen/file_upload");
		return mv;
	}
	
	/**
	 * 新增文件
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveFile")
	@ResponseBody
	public Object saveFile(@RequestParam(required = false) MultipartFile file, HttpServletRequest request)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String fileName = "";
		PageData pd = new PageData();
		String businessId = request.getParameter("businessId");// businessId
		String type = request.getParameter("TYPE");// 文件类型
		InputStream inStream = null;
		inStream = file.getInputStream();
		String basePath = businessId + "/" + type + "/";
		fileName = file.getOriginalFilename();

		pd.put("id", this.get32UUID());
		pd.put("path", basePath+fileName);
		pd.put("business", businessId);
		pd.put("file_name", fileName);
		pd.put("type",type);

		String hostname = (String) propsMap.get("host");
		int port = Integer.parseInt((String) propsMap.get("port"));
		String username = (String) propsMap.get("username");
		String password = (String) propsMap.get("password");
		FtpUtils.uploadFile(hostname, port, username, password, dawjBasepath+basePath, fileName, inStream);
		fileService.save(pd);
		map.put("result", "ok");
		return AppUtil.returnObject(pd, map);
	}
	
	/**根据档案盒号获取文件列表
	 * @return
	 */
	@RequestMapping(value="/getFilesByDAHH")
	@ResponseBody
	public Object getFilesByDAHH(){
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success";
		PageData pd = new PageData();
		List<PageData> BAFilesList = new ArrayList<PageData>();
		List<PageData> BSFilesList = new ArrayList<PageData>();
		List<PageData> GFFilesList = new ArrayList<PageData>();
		List<PageData> LZFilesList = new ArrayList<PageData>();
		List<PageData> ZWFilesList = new ArrayList<PageData>();
		List<PageData> ZXFilesList = new ArrayList<PageData>();
		try{
			pd = this.getPageData();
			//String dahh= pd.getString("dahh");
			pd.put("type", "BA");
			BAFilesList = fileService.findByDahAndType(pd);
			pd.put("type","BS");
			BSFilesList = fileService.findByDahAndType(pd);
			pd.put("type","GF");
			GFFilesList = fileService.findByDahAndType(pd);
			pd.put("type","LZ");
			LZFilesList = fileService.findByDahAndType(pd);
			pd.put("type","LZ");
			LZFilesList = fileService.findByDahAndType(pd);
			pd.put("type","ZW");
			ZWFilesList = fileService.findByDahAndType(pd);
			pd.put("type","ZX");
			ZXFilesList = fileService.findByDahAndType(pd);
			
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("BAFilesList", BAFilesList);
		map.put("BSFilesList", BSFilesList);
		map.put("GFFilesList", GFFilesList);
		map.put("LZFilesList", LZFilesList);
		map.put("ZWFilesList", ZWFilesList);
		map.put("ZXFilesList", ZXFilesList);
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**根据发文唯一号获取文件列表
	 * @return
	 */
	@RequestMapping(value="/listFilesByFwwyh")
	@ResponseBody
	public Object listFilesByFwwyh(){
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success";
		PageData pd = new PageData();
		List<PageData> pdList = new ArrayList<PageData>();
		try{
			pd = this.getPageData();
			pdList = fileService.listFilesByWhwym(pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("filesList", pdList);
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**根据文件路径获取文件
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/getFileByPath")
	public void getFileByPath(HttpServletRequest request,  
            HttpServletResponse response) throws Exception{
		String url= URLDecoder.decode(request.getParameter("url"),"UTF-8");
		int index = url.lastIndexOf("/");
		String path= url.substring(0, index);
		path=dawjBasepath+path;
		String filename=url.substring(index+1, url.length());
		 System.out.println(filename);
		response.addHeader("Content-Disposition", "inline;filename=" + new String(filename.getBytes("UTF-8"),"ISO-8859-1")); //attachment下载  inline文件名问题 
		 FTPClient ftpClient = new FTPClient();
		// byte[] return_arraybyte=null;  
		
		 //连接FTP服务器
			 ftpClient.connect(hostname, port);
		   	 ftpClient.login(username, password);
		   	ftpClient.setControlEncoding("UTF-8");
		 //登录FTP服务器
		// ftpClient.login(username, password);
		 //验证FTP服务器是否登录成功
		 int replyCode = ftpClient.getReplyCode();
		 if(!FTPReply.isPositiveCompletion(replyCode)){
			 
		 }
		 //切换FTP目录
		 ftpClient.changeWorkingDirectory(new String(path.getBytes("UTF-8"),"ISO-8859-1"));
		 ftpClient.enterLocalPassiveMode();
		 OutputStream os = response.getOutputStream();
		/* String fileName1=new String(filename.getBytes("UTF-8"),"ISO-8859-1");
		 System.out.println(fileName1);*/
		 boolean b=ftpClient.retrieveFile(new String(filename.getBytes("UTF-8"),"ISO-8859-1"), os);
		 System.out.println(b);
		 os.close();
		 ftpClient.logout();
		
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
		String result="success";
		pd = this.getPageData();
		try {
			fileService.delete(pd);
			String path= pd.getString("path");
			int index= path.lastIndexOf("/");
			String basePath=path.substring(0,index);
			String fileName=path.substring(index+1,path.length());
			FtpUtils.deleteFile(hostname, port, username, password, dawjBasepath+basePath, fileName);
		} catch (Exception e) {
			result=e.getMessage();
		}
		map.put("result", result);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
