package com.sphz.controller.sphz.project;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bstek.ureport.Utils;
import com.bstek.ureport.export.ExportManager;
import com.bstek.ureport.export.html.HtmlReport;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.sphz.controller.base.BaseController;
import com.sphz.entity.system.Dictionaries;
import com.sphz.service.sphz.FileManager;
import com.sphz.service.system.dictionaries.DictionariesManager;
import com.sphz.util.AppUtil;
import com.sphz.util.ConvertUtil;
import com.sphz.util.DateUtil;
import com.sphz.util.DicUtil;
import com.sphz.util.DoubleUtils;
import com.sphz.util.FilePageData;
import com.sphz.util.Jurisdiction;
import com.sphz.util.MongoDBUtil;
import com.sphz.util.PageData;

@Controller
@RequestMapping("/sphz/project")
public class ProjectController extends BaseController {
	@Resource(name="fileService")
	private FileManager fileService;
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	private MongoClient mongoClient;
	
	/**
	 * 去项目列表页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goList")
	public ModelAndView goList() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		/*pd.put("BIANMA", "021");
		List<Dictionaries> fwlxList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有发文类型
		mv.addObject("fwlxList", fwlxList);*/
		pd.put("BIANMA", "008");
		List<Dictionaries> jbbmList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有经办部门
		mv.addObject("jbbmList", jbbmList);
		
		mv.setViewName("sphz/project/project_list_all");
		return mv;
	}
	/**
	 * 去项目列表页面（功能全面）
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goListAll")
	public ModelAndView goListAll() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		String REGION_CODE=Jurisdiction.getNumber();
		if(!REGION_CODE.equals("3702")){
			pd.put("BIANMA", REGION_CODE);
		}else{
			pd.put("BIANMA", "008");
		}
		List<Dictionaries> jbbmList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有经办部门
		mv.addObject("jbbmList", jbbmList);
		mv.setViewName("sphz/project/project_list_all");
		return mv;
	}
	
	/**
	 * 去项目空间页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goSpatial")
	public ModelAndView goSpatial() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		mv.addObject("pd", pd);
		//mv.setViewName("sphz/project/project_map");
		mv.setViewName("sphz/project/map");
		return mv;
	}
	
	/**
	 * 去选择项目页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goProjSelect")
	public ModelAndView goProjSelect() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		mv.addObject("pd", pd);
		mv.setViewName("sphz/project/proj_select_map");
		return mv;
	}
	/**
	 * 去项目空间定位页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goMapDetail")
	public ModelAndView goMapDetail() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		mv.addObject("pd", pd);
		mv.setViewName("sphz/project/map_detail");
		return mv;
	}
	
	
	/**
	 * 去项目空间详情页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goSpatialDetail")
	public ModelAndView goSpatialDetail() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		mv.addObject("pd", pd);
		mv.setViewName("sphz/project/project_map");
		return mv;
	}
	
	/**
	 * 去发文信息详情页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goFawenDetail")
	public ModelAndView goFawenDetail() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd=this.getPageData();
		mv.addObject("pd", pd);
		mv.setViewName("sphz/project/fawen_detail");
		return mv;
	}
	
	/**
	 * 去项目详情页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goProjectDetail")
	public ModelAndView goPrjectDetail() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd=this.getPageData();
		mv.addObject("pd",pd);
		mv.setViewName("sphz/project/project_detail");
		return mv;
	}
	
	/**
	 * 去项目Pop页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goPopInfo")
	public ModelAndView goPopInfo() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd=this.getPageData();
		mv.addObject("pd",pd);
		mv.setViewName("sphz/project/project_pop_info");
		return mv;
	}
	
	
	/**打开上传EXCEL页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goUploadExcel")
	public ModelAndView goUploadExcel()throws Exception{
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("sphz/project/file_upload");
		return mv;
	}
	/**
	 * 获取项目列表
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/list")
	@ResponseBody
	public Object list() throws Exception{
		
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success"; 
		PageData pd = new PageData();
		List<Map<String, Object>> xiangmuList=new ArrayList<Map<String,Object>>();
		try{
			pd = this.getPageData();
			Integer sEcho=Integer.parseInt(pd.getString("sEcho"));
			Integer iDisplayStart =Integer.parseInt(pd.getString("iDisplayStart"));
			Integer iDisplayLength =Integer.parseInt(pd.getString("iDisplayLength"));
			String keyword=pd.getString("keyword");
			String spjd=pd.getString("spjd");
			String jbbm=pd.getString("jbbm");
			mongoClient = MongoDBUtil.initMongo();
			
			BasicDBObject basicDBObject = new BasicDBObject();
			BasicDBList endList = new BasicDBList();
			BasicDBList condList = new BasicDBList();
			BasicDBObject autoEnd = new BasicDBObject();
			if(null!=keyword&&!"".equals(keyword)){
				condList.add(new BasicDBObject("PROJECT_NAME",  Pattern.compile("^.*"+keyword+".*$", Pattern.CASE_INSENSITIVE)));
				condList.add(new BasicDBObject("LOCATION",  Pattern.compile("^.*"+keyword+".*$", Pattern.CASE_INSENSITIVE)));
				condList.add(new BasicDBObject("APPLICANT",  Pattern.compile("^.*"+keyword+".*$", Pattern.CASE_INSENSITIVE)));
				autoEnd.put("$or",condList);
				endList.add(autoEnd);
			}
			
			
			//经办部门
			String REGION_CODE=Jurisdiction.getNumber();
			if(!REGION_CODE.equals("3702")){
				BasicDBObject conRegion = new BasicDBObject();
				conRegion.put("REGION_CODE", REGION_CODE);
				endList.add(conRegion);
			}
			
			//经办部门
			BasicDBObject forceEnd = new BasicDBObject();
			if(null!=jbbm&&!"".equals(jbbm)){
				forceEnd.put("REGION_CODE", jbbm);
				endList.add(forceEnd);
			}
			
			//审批阶段
			BasicDBObject conSpjd = new BasicDBObject();
			if(null!=spjd&&!"".equals(spjd)){
				conSpjd.put("SPJD", spjd);
				endList.add(conSpjd);
			}
			if((null!=jbbm&&!"".equals(jbbm))||(null!=spjd&&!"".equals(spjd))||(null!=keyword&&!"".equals(keyword))||(!REGION_CODE.equals("3702"))){
				basicDBObject.put("$and",endList);
			}
			//basicDBObject.put("$and",endList);
			
			FindIterable<Document> documents  = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("xiangmu")
					.find(basicDBObject).skip(iDisplayStart).limit(iDisplayLength);
			long count = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("xiangmu")
			.count(basicDBObject);
			
			Integer intValue = new Long(count).intValue();
			//int initEcho=sEcho+1;
			xiangmuList = MongoDBUtil.documents2MapList(documents);
			
			for(int i=0;i<xiangmuList.size();i++){
				Map<String, Object> xiangmu = xiangmuList.get(i);
				String rc = MongoDBUtil.convertObject2String(xiangmu.get("REGION_CODE"));
				pd.put("BIANMA", rc);
				PageData dictionary = dictionariesService.findByBianma(pd);
				if(null!=dictionary){
					xiangmuList.get(i).put("ORG_NAME",dictionary.getString("NAME"));
				}
			}
			
			map.put("iDisplayStart",iDisplayStart);
			map.put("iDisplayLength",iDisplayLength);
			map.put("sEcho", sEcho);
			map.put("iTotalRecords", intValue);
			map.put("iTotalDisplayRecords", intValue);//显示的条数 
			
		} catch(Exception e){
			errInfo=e.toString();
			logger.error(e.toString(), e);
		}
		map.put("xiangmuList", xiangmuList);
		map.put("result", errInfo);		
		return AppUtil.returnObject(new PageData(), map);
	}
	
	
	/**
	 * 根据项目唯一码获取项目
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/getXiangmuByXmwym")
	@ResponseBody
	public Object getXiangmuByXmwym() throws Exception{
		
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "success"; 
		PageData pd = new PageData();
		List<Map<String, Object>> xiangmuList=new ArrayList<Map<String,Object>>();
		Map<String,Object> xiangmu=new HashMap<String,Object>();
		try{
			pd = this.getPageData();
			mongoClient = MongoDBUtil.initMongo();
			Document document = new Document();
			document.append("XMWYM", pd.get("XMWYM"));
			FindIterable<Document> documents  = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("xiangmu")
					.find(document);
			xiangmuList = MongoDBUtil.documents2MapList(documents);
			if(null!=xiangmuList&&xiangmuList.size()>0){
				xiangmu=xiangmuList.get(0);
			}
		} catch(Exception e){
			result=e.toString();
			logger.error(e.toString(), e);
		}
		
		map.put("xiangmu", xiangmu);
		map.put("result", result);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	public List<Map<String, Object>> getBaseProjectDataByXmwym(String xmwym) throws Exception{
		List<Map<String, Object>> baseDataList=new ArrayList<Map<String,Object>>();
		Map<String, Object> baseMap=new HashMap<String, Object>();
		String[]keyArray={"申请单位","项目编码","项目地址","项目属地","设计单位","是否重点项目","项目总投资","项目规模"};
		String[]keyMapArray={"APPLICANT","PROJECT_CODE","LOCATION","XMSD","SJDW","IS_IMPORTANT","INVESTMENT","SCALE"};//需要计算
		//1、从项目表中直接查询项目基本信息
			List<Map<String, Object>> xmList=new ArrayList<Map<String,Object>>();
			FindIterable<Document> xmDs = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("xiangmu")
					.find(new Document("XMWYM", xmwym));
			xmList = MongoDBUtil.documents2MapList(xmDs);
			
			if(null!=xmList&&xmList.size()>0){
				//1、获取项目基本信息
				Map<String, Object> xm=xmList.get(0);
				baseMap.put("APPLICANT",MongoDBUtil.convertObject2String(xm.get("APPLICANT"))+"&#12288;");
				baseMap.put("PROJECT_CODE",MongoDBUtil.convertObject2String(xm.get("PROJECT_CODE"))+"&#12288;");
				baseMap.put("LOCATION",MongoDBUtil.convertObject2String(xm.get("LOCATION"))+"&#12288;");
				baseMap.put("XMSD",MongoDBUtil.convertObject2String(xm.get("XMSD"))+"&#12288;");
				baseMap.put("SJDW",MongoDBUtil.convertObject2String(xm.get("SJDW"))+"&#12288;");
				baseMap.put("IS_IMPORTANT", DicUtil.convertBianma2Name(MongoDBUtil.convertObject2String(xm.get("IS_IMPORTANT")), dictionariesService)+"&#12288;");
				baseMap.put("INVESTMENT",MongoDBUtil.convertObject2String(xm.get("INVESTMENT"))+"万元&#12288;");
				baseMap.put("SCALE",MongoDBUtil.convertObject2String(xm.get("SCALE"))+"&#12288;");
			}
			
			for(int i=0;i<keyArray.length;i++){
				Map<String,Object>map=new HashMap<String,Object>();
				map.put("id", i);
				map.put("name",keyArray[i]);
				map.put("value",baseMap.get(keyMapArray[i]));
				baseDataList.add(map);
			}
		
		return baseDataList;
	}
	
	
	//根据项目唯一码获取大项目、分期项目所有文件
	public List<Map<String, Object>> getFilesByXmwym(String xmwym) throws Exception{//根据项目唯一码获取大项目、分期项目所有文件
		List<Map<String, Object>> childFilesList=new ArrayList<Map<String,Object>>();
		String[]nameArray={"建设单位提报方案","建设单位提报申请","规划局发文","局内流转文件","其他政府文件","第三方咨询文件"};	
		List<List<PageData>>filesList=new ArrayList<List<PageData>>();
		List<Map<String, Object>> fwList=new ArrayList<Map<String,Object>>();
		//mongoClient = MongoDBUtil.initMongo();
		FindIterable<Document> doc  = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("fawen")
				.find(new Document("XMWYM", xmwym)).sort(Sorts.descending("FZRQ"));//查询大项目相关发文
		fwList=MongoDBUtil.documents2MapList(doc);
		List<PageData> bAFilesList = new ArrayList<PageData>();
		List<PageData> bSFilesList = new ArrayList<PageData>();
		List<PageData> gFFilesList = new ArrayList<PageData>();
		List<PageData> lZFilesList = new ArrayList<PageData>();
		List<PageData> zWFilesList = new ArrayList<PageData>();
		List<PageData> zXFilesList = new ArrayList<PageData>();
		if(null!=fwList&&fwList.size()>0){
			for(int i=0;i<fwList.size();i++){
				Map<String,Object> fw=fwList.get(i);//根据发文获取所有附件
				String bsnum=MongoDBUtil.convertObject2String(fw.get("BSNUM"));
				String fwwh=MongoDBUtil.convertObject2String(fw.get("FWWH"));
				PageData pd = new PageData();
				pd.put("bsnum",bsnum);
				pd.put("fwwh",fwwh);
				pd.put("type", "BA");
				bAFilesList.addAll(fileService.findByBusinessIdAndType(pd));
				pd.put("type","BS");
				bSFilesList.addAll(fileService.findByBusinessIdAndType(pd));
				pd.put("type","GF");
				gFFilesList.addAll(fileService.findByBusinessIdAndType(pd));
				pd.put("type","LZ");
				lZFilesList.addAll(fileService.findByBusinessIdAndType(pd));
				pd.put("type","ZW");
				zWFilesList.addAll(fileService.findByBusinessIdAndType(pd));
				pd.put("type","ZX");
				zXFilesList.addAll(fileService.findByBusinessIdAndType(pd));
			}
		}
		filesList.add(bAFilesList);
		filesList.add(bSFilesList);
		filesList.add(gFFilesList);
		filesList.add(lZFilesList);
		filesList.add(zWFilesList);
		filesList.add(zXFilesList);
		for(int i=0;i<6;i++){
			Map<String, Object> fileMap=new HashMap<String,Object>();
			fileMap.put("id",i);
			fileMap.put("name",nameArray[i]);
			fileMap.put("sub",filesList.get(i));
			childFilesList.add(fileMap);
		}
		
		return childFilesList;
	}
	
	
		
	//根据项目唯一码获取所有分期项目
	public List<Map<String, Object>> getDocumentDataByXmwym(String xmwym) throws Exception{
		List<Map<String, Object>> fawenList=new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> documentDataList=new ArrayList<Map<String,Object>>();
		//根据项目唯一码获取该项目的发文
		FindIterable<Document> documents  = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("fawen")
				.find(new Document("XMWYM",xmwym)).sort(Sorts.descending("FZRQ"));
		fawenList = MongoDBUtil.documents2MapList(documents);
		getFawen(fawenList,documentDataList,false);
		return documentDataList;
	}
	
	//根据档案盒号获取文件列表
	public List<Map<String, Object>> getChildFilesList(String bsnum,String fwwh) throws Exception{
		List<Map<String, Object>> childFilesList=new ArrayList<Map<String,Object>>();
		//String dahh=MongoDBUtil.convertObject2String(fw.get("DAHH"));
		PageData pd1 = new PageData();
		List<PageData> BAFilesList = new ArrayList<PageData>();
		List<PageData> BSFilesList = new ArrayList<PageData>();
		List<PageData> GFFilesList = new ArrayList<PageData>();
		List<PageData> LZFilesList = new ArrayList<PageData>();
		List<PageData> ZWFilesList = new ArrayList<PageData>();
		List<PageData> ZXFilesList = new ArrayList<PageData>();
		pd1.put("bsnum",bsnum);
		pd1.put("fwwh",fwwh);
		
		pd1.put("type", "BA");
		BAFilesList = fileService.findByBusinessIdAndType(pd1);
		pd1.put("type","BS");
		BSFilesList = fileService.findByBusinessIdAndType(pd1);
		pd1.put("type","GF");
		GFFilesList = fileService.findByBusinessIdAndType(pd1);
		pd1.put("type","LZ");
		LZFilesList = fileService.findByBusinessIdAndType(pd1);
		pd1.put("type","ZW");
		ZWFilesList = fileService.findByBusinessIdAndType(pd1);
		pd1.put("type","ZX");
		ZXFilesList = fileService.findByBusinessIdAndType(pd1);
		String[]nameArray={"建设单位提报方案","建设单位提报申请","规划局发文","局内流转文件","其他政府文件","第三方咨询文件"};	
		List<List<PageData>>filesList=new ArrayList<List<PageData>>();
		filesList.add(BAFilesList);
		filesList.add(BSFilesList);
		filesList.add(GFFilesList);
		filesList.add(LZFilesList);
		filesList.add(ZWFilesList);
		filesList.add(ZXFilesList);
			
		for(int j=0;j<6;j++){
			Map<String, Object> fileMap=new HashMap<String,Object>();
			fileMap.put("id",j);
			fileMap.put("name",nameArray[j]);
			fileMap.put("sub",filesList.get(j));
			childFilesList.add(fileMap);
		}
		return childFilesList;
	}
	
	public void getFawen(List<Map<String, Object>> fawenList,List<Map<String, Object>> documentDataList,boolean isSub) throws Exception{
		if(null!=fawenList&&fawenList.size()>0){
			for(int i=0;i<fawenList.size();i++){
				List<Map<String, Object>> childFilesList=new ArrayList<Map<String,Object>>();
				Map<String, Object> fawen=new HashMap<String,Object>();
				Map<String, Object> fw=fawenList.get(i);
				fawen.put("id",MongoDBUtil.convertObject2String(fw.get("WHWYM")));
				fawen.put("stage", "");
				fawen.put("url","");
				fawen.put("time",MongoDBUtil.convertObject2String(fw.get("FZRQ")));
				fawen.put("name",MongoDBUtil.convertObject2String(fw.get("FWWH")));
				String fwlxId=MongoDBUtil.convertObject2String(fw.get("FWLXID"));
				String fwlx=MongoDBUtil.convertObject2String(fw.get("FWLX"));
				if(fwlxId.indexOf("选址、条件")>-1){
					fawen.put("type","TJXZ");
					fawen.put("short","选");
				}else if(fwlxId.indexOf("用地")>-1){
					fawen.put("type","YDXK");
					fawen.put("short","地");
				}else if(fwlxId.indexOf("建设许可")>-1){
					fawen.put("type","JSXK");
					fawen.put("short","建");
				}else if(fwlxId.indexOf("竣工")>-1){
					fawen.put("type","JGXK");
					fawen.put("short","核");
				}else if(fwlx.equals("变审字")||fwlx.equals("规审字")||fwlx.equals("建审字")||fwlx.equals("临字")||fwlx.equals("外环字")){
					fawen.put("type","JSXK");
					fawen.put("short","");
				}else if(fwlx.equals("验线字")||fwlx.equals("单验字")){
					fawen.put("type","JGXK");
					fawen.put("short","");
				}else{
					fawen.put("type","");
					fawen.put("short","");
				}
				
				String bsnum=MongoDBUtil.convertObject2String(fw.get("BSNUM"));
				String fwwh=MongoDBUtil.convertObject2String(fw.get("FWWH"));
				childFilesList=getChildFilesList(bsnum,fwwh);
				Map<String, Object>childAttachmentListOptions=new HashMap<String,Object>();
				childAttachmentListOptions.put("data", childFilesList);
				fawen.put("childAttachmentlistOptions", childAttachmentListOptions);
				documentDataList.add(fawen);
			}
		}
	}
	
	
	/**
	 * 获取项目详情
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/getProjectDetails")
	@ResponseBody
	public Object getProjectDetails() throws Exception{
		
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success"; 
		PageData pd = new PageData();
		Map<String, Object> returnMap=new HashMap<String,Object>();
		List<Map<String, Object>> attachmentList=new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> baseData=new ArrayList<Map<String,Object>>();
		Map<String, Object> stageData=new HashMap<String,Object>();
		List<Map<String, Object>> documentData=new ArrayList<Map<String,Object>>();
		try{
			pd = this.getPageData();
			String xmwym=pd.getString("XMWYM").trim();
			String project_name=pd.getString("XMMC").trim();
			returnMap.put("title", project_name);
			returnMap.put("analysisUrl", "");
			mongoClient = MongoDBUtil.initMongo();
			
			returnMap.put("stageFlag", false);
			documentData=getDocumentDataByXmwym(xmwym);
			//stageData=getStageDataByXmwym(xmwym);
			baseData=getBaseProjectDataByXmwym(xmwym);
			attachmentList=getFilesByXmwym(xmwym);
			
			Map<String,Object>attachmentlistOptions=new HashMap<String,Object>();
			attachmentlistOptions.put("data", attachmentList);
			returnMap.put("attachmentlistOptions", attachmentlistOptions);
			returnMap.put("baseData", baseData);
			returnMap.put("stageData", stageData);
			returnMap.put("documentData", documentData);
			
		} catch(Exception e){
			errInfo=e.toString();
			logger.error(e.toString(), e);
		}
		map.put("returnMap", returnMap);
		map.put("result", errInfo);		
		return AppUtil.returnObject(new PageData(), map);
	}
	
	
	/**
	 * 根据发文文号获取发文详情
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/getFawenDetails")
	@ResponseBody
	public Object getFawenDetails() throws Exception{
		
		PageData pd = new PageData();
		Map<String, Object> returnMap=new HashMap<String,Object>();
		List<String>BaojianData=new ArrayList<String>();
		List<String>FawenData=new ArrayList<String>();
		List<String>ZhibiaoData=new ArrayList<String>();
		String title="";
		String ZhibiaoType="";
		String[]keyArrayBaojian={"ITEM_NAME","APPLICANT","TYSHXYDM","SUBMIT_TIME","AGENT_PERSON","AGENT_PHONE","PROJECT_NAME","PROJECT_CODE","LOCATION","XMSD","SJDW","IS_IMPORTANT","INVESTMENT","SCALE"};
		String[]keyArrayFawen={"FWLX","FWWH","FZRQ","ORG_NAME","JBR","QPR","ZSGYH"};
		String[]keyArrayZb_XZTJ_JZGC={"SFHHYD","HHYDBL","LAND_USE_NAME","GHYDXZDM","YDMJ","RJL","JZMD","LDL","TCW","KZGD","BZ"};
		String[]keyArrayZb_XZTJ_SZDQ={"DLXZ","DLCD","HXKD","DMXS","SJBZ","HMCSYQ","BZ"};
		String[]keyArrayZb_XZTJ_SZGX={"GXLX","GXCD","SJBZ","HMCSYQ","BZ"};
		String[]keyArrayZb_YD={"SFHHYD","HHYDBL","LAND_USE_NAME","GHYDXZDM","YDMJ","TZGLLX","TDSYLX","HMCSYQ","BZ"};
		String[]keyArrayZb_JZJZ={"SFHHYD","HHYDBL","LAND_USE_NAME","GHYDXZDM","YDMJ","RJL","JZMD","LDL","ZJZMJ","TCW","DSJZMJ","DSTCW","DXJZMJ","DXTCW","ZZJZMJ","BGJZMJ","PTJZMJ","JZDS","HS","JZWSYXZ","HMCSYQ","BZ"};
		String[]keyArrayZb_JZDQ={"LAND_USE_NAME","GHYDXZDM","YDMJ","DLZCD","DLKD","DLJB","DMXS","SJBZ","ZSJZMJ","HMCSYQ","BZ"};
		String[]keyArrayZb_JZGX={"LAND_USE_NAME","GHYDXZDM","YDMJ","GXLX","GXZCD","ZSJZMJ","HMCSYQ","BZ"};
		String[]keyArrayZb_HZJZ={"YDMJ","ZJZMJ","DSJZMJ","DXJZMJ","JZZDMJ","JZGD","CS","BZ"};
		String[]keyArrayZb_HZDQ={"YDMJ","DLZCD","DLKD","DMXS","ZSJZMJ","BZ"};
		String[]keyArrayZb_HZGX={"YDMJ","GXLX","GXZCD","BZ"};
		String[]keyArrayZb_QT={"SPNR"};
		
		List<Map<String, Object>> fawenList=new ArrayList<Map<String,Object>>();
		try{
			
			pd = this.getPageData();
			String whwym=pd.getString("whwym").trim();
			//title=fwwh;
			mongoClient = MongoDBUtil.initMongo();
			FindIterable<Document> doc  = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("fawen")
					.find(new Document("WHWYM", whwym)).sort(Sorts.descending("FZRQ"));//查询大项目相关发文
			fawenList=MongoDBUtil.documents2MapList(doc);
			if(null!=fawenList&&fawenList.size()>0){
				Map<String,Object>fawen=fawenList.get(0);
				title=(String)fawen.get("FWWH");
				for(int j=0;j<keyArrayBaojian.length;j++){
					String key=keyArrayBaojian[j];
					if(key.equals("IS_IMPORTANT")){
						BaojianData.add(DicUtil.convertBianma2Name(MongoDBUtil.convertObject2String(fawen.get(key)), dictionariesService));
					}else{
						BaojianData.add(MongoDBUtil.convertObject2String(fawen.get(key)));
					}
					
				}
				for(int j=0;j<keyArrayFawen.length;j++){
					FawenData.add(MongoDBUtil.convertObject2String(fawen.get(keyArrayFawen[j])));
				}
				
				String fwlxId= MongoDBUtil.convertObject2String(fawen.get("FWLXID"));
				switch(fwlxId){
					case "选址、条件（建筑工程）":
						ZhibiaoType="ZBXX-XZTJ-JZGC";
						for(int j=0;j<keyArrayZb_XZTJ_JZGC.length;j++){
							ZhibiaoData.add(MongoDBUtil.convertObject2String(fawen.get(keyArrayZb_XZTJ_JZGC[j])));
						}
						break;
					case "选址、条件（市政道桥）":
						ZhibiaoType="ZBXX-XZTJ-SZDQ";
						for(int j=0;j<keyArrayZb_XZTJ_SZDQ.length;j++){
							ZhibiaoData.add(MongoDBUtil.convertObject2String(fawen.get(keyArrayZb_XZTJ_SZDQ[j])));
						}
						break;
					case "选址、条件（市政管线）":
						ZhibiaoType="ZBXX-XZTJ-SZGX";
						for(int j=0;j<keyArrayZb_XZTJ_SZGX.length;j++){
							ZhibiaoData.add(MongoDBUtil.convertObject2String(fawen.get(keyArrayZb_XZTJ_SZGX[j])));
						}
						break;
					case "用地许可":
						ZhibiaoType="ZBXX-YDXK";
						for(int j=0;j<keyArrayZb_YD.length;j++){
							ZhibiaoData.add(MongoDBUtil.convertObject2String(fawen.get(keyArrayZb_YD[j])));
						}
						break;
					case "建设许可（建筑工程）":
						ZhibiaoType="ZBXX-JSXK-JZGC";
						for(int j=0;j<keyArrayZb_JZJZ.length;j++){
							if(keyArrayZb_JZJZ[j].equals("RJL")){
								String rjl=MongoDBUtil.convertObject2String(fawen.get(keyArrayZb_JZJZ[j]));
								if(!"".equals(rjl)){
									BigDecimal bd=new BigDecimal(rjl);
									rjl=bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+"";
								}
								ZhibiaoData.add(rjl);
							}else if(keyArrayZb_JZJZ[j].equals("JZMD")){
								String jzmd=MongoDBUtil.convertObject2String(fawen.get(keyArrayZb_JZJZ[j]));
								if(!"".equals(jzmd)){
									NumberFormat nt1=NumberFormat.getPercentInstance();
									nt1.setMaximumFractionDigits(2);
									nt1.setRoundingMode(RoundingMode.HALF_UP);
									jzmd=nt1.format(Double.parseDouble(jzmd))+"";
								}
								ZhibiaoData.add(jzmd);
							}else if(keyArrayZb_JZJZ[j].equals("LDL")){
								String ldl=MongoDBUtil.convertObject2String(fawen.get(keyArrayZb_JZJZ[j]));
								if(!"".equals(ldl)){
									NumberFormat nt1=NumberFormat.getPercentInstance();
									nt1.setMaximumFractionDigits(2);
									nt1.setRoundingMode(RoundingMode.HALF_UP);
									ldl=nt1.format(Double.parseDouble(ldl))+"";
								}
								ZhibiaoData.add(ldl);
							}else{
								ZhibiaoData.add(MongoDBUtil.convertObject2String(fawen.get(keyArrayZb_JZJZ[j])));
							}
						}
						
						break;
					case "建设许可（市政道桥）":
						ZhibiaoType="ZBXX-JSXK-SZDQ";
						for(int j=0;j<keyArrayZb_JZDQ.length;j++){
							ZhibiaoData.add(MongoDBUtil.convertObject2String(fawen.get(keyArrayZb_JZDQ[j])));
						}
						break;
					case "建设许可（市政管线）":
						ZhibiaoType="ZBXX-JSXK-SZGX";
						for(int j=0;j<keyArrayZb_JZGX.length;j++){
							ZhibiaoData.add(MongoDBUtil.convertObject2String(fawen.get(keyArrayZb_JZGX[j])));
						}
						break;
					case "竣工（建筑工程）":
						ZhibiaoType="ZBXX-JGXK-JZGC";
						for(int j=0;j<keyArrayZb_HZJZ.length;j++){
							ZhibiaoData.add(MongoDBUtil.convertObject2String(fawen.get(keyArrayZb_HZJZ[j])));
						}
						break;
					case "竣工（市政道桥）":
						ZhibiaoType="ZBXX-JGXK-SZDQ";
						for(int j=0;j<keyArrayZb_HZDQ.length;j++){
							ZhibiaoData.add(MongoDBUtil.convertObject2String(fawen.get(keyArrayZb_HZDQ[j])));
						}
						break;
					case "竣工（市政管线）":
						ZhibiaoType="ZBXX-JGXK-SZGX";
						for(int j=0;j<keyArrayZb_HZGX.length;j++){
							ZhibiaoData.add(MongoDBUtil.convertObject2String(fawen.get(keyArrayZb_HZGX[j])));
						}
						break;
					case "其它":
						ZhibiaoType="ZBXX-QT";
						for(int j=0;j<keyArrayZb_QT.length;j++){
							ZhibiaoData.add(MongoDBUtil.convertObject2String(fawen.get(keyArrayZb_QT[j])));
						}
						break;
					default:
						System.out.println("没有匹配的"+fwlxId);
				}
			}
			
			returnMap.put("title", title);
			returnMap.put("ZhibiaoType", ZhibiaoType);
			returnMap.put("BaojianData", BaojianData);
			returnMap.put("FawenData", FawenData);
			returnMap.put("ZhibiaoData", ZhibiaoData);
			
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), returnMap);
	}
	
	/**
	 * 根据项目名称搜索项目列表
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/searchByXmmc")
	@ResponseBody
	public Object searchByXmmc() throws Exception{
		
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success"; 
		PageData pd = new PageData();
		List<Map<String, Object>> projectList=new ArrayList<Map<String,Object>>();
		try{
			pd = this.getPageData();
			String xmmc=pd.getString("xmmc");
			mongoClient = MongoDBUtil.initMongo();
			BasicDBObject con= new BasicDBObject("XMMC",  Pattern.compile("^.*"+xmmc+".*$", Pattern.CASE_INSENSITIVE));
			FindIterable<Document> documents  = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("xiangmu")
					.find(con);
			projectList = MongoDBUtil.documents2MapList(documents);
		} catch(Exception e){
			errInfo=e.toString();
			logger.error(e.toString(), e);
		}
		map.put("projectList", projectList);
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 根据项目唯一码搜索项目
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/searchByXmwym")
	@ResponseBody
	public Object searchByXmwym() throws Exception{
		
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success"; 
		PageData pd = new PageData();
		List<Map<String, Object>> projectList=new ArrayList<Map<String,Object>>();
		Map<String, Object> proj=new HashMap<String,Object>();
		try{
			pd = this.getPageData();
			String xmwym=pd.getString("xmwym");
			mongoClient = MongoDBUtil.initMongo();
			/*FindIterable<Document> documents  = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("xiangmu")
					.find(new Document().append("XMWYM",xmwym));
			projectList = MongoDBUtil.documents2MapList(documents);*/
			FindIterable<Document> documents = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("fawen")
					.find(new Document().append("XMWYM",xmwym)).sort(new Document().append("FZRQ", -1));
			projectList = MongoDBUtil.documents2MapList(documents);
			if(null!=projectList&&projectList.size()>0){
				proj = projectList.get(0);
				System.out.println(proj.get("LAND_USE"));
			}
		} catch(Exception e){
			errInfo=e.toString();
			logger.error(e.toString(), e);
		}
		map.put("proj", proj);
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 根据项目名称/项目位置、建设单位搜索项目列表
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/searchByKeyWord")
	@ResponseBody
	public Object searchByKeyWord() throws Exception{
		
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "success"; 
		PageData pd = new PageData();
		List<Map<String, Object>> projectList=new ArrayList<Map<String,Object>>();
		try{
			pd = this.getPageData();
			String keyword=pd.getString("keyword");
			mongoClient = MongoDBUtil.initMongo();
			String xmwym="";
			
			BasicDBObject basicDBObject = new BasicDBObject();
			BasicDBList endList = new BasicDBList();
			BasicDBList condList = new BasicDBList();
			BasicDBObject autoEnd = new BasicDBObject();
			if(null!=keyword&&!"".equals(keyword)){
				Document d=new Document();
				d.append("FWWH", keyword);
				FindIterable<Document> documentsFw = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("fawen").find(d);
				List<Map<String, Object>> fawenList = MongoDBUtil.documents2MapList(documentsFw);
				if(null!=fawenList&&fawenList.size()>0){
					Map<String, Object> fawen = fawenList.get(0);
					xmwym=(String)fawen.get("XMWYM");
				}
				
				condList.add(new BasicDBObject("PROJECT_NAME",  Pattern.compile("^.*"+keyword+".*$", Pattern.CASE_INSENSITIVE)));
				condList.add(new BasicDBObject("LOCATION",  Pattern.compile("^.*"+keyword+".*$", Pattern.CASE_INSENSITIVE)));
				condList.add(new BasicDBObject("APPLICANT",  Pattern.compile("^.*"+keyword+".*$", Pattern.CASE_INSENSITIVE)));
				if(!"".equals(xmwym)){
					condList.add(new BasicDBObject("XMWYM", xmwym));
				}
				autoEnd.put("$or",condList);
				endList.add(autoEnd);
				
				String REGION_CODE=Jurisdiction.getNumber();
				if(!REGION_CODE.equals("3702")){
					BasicDBObject conRegion = new BasicDBObject();
					conRegion.put("REGION_CODE", REGION_CODE);
					endList.add(conRegion);
				}
				
				
				basicDBObject.put("$and",endList);
				FindIterable<Document> documents  = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("xiangmu")
						.find(basicDBObject);
				projectList = MongoDBUtil.documents2MapList(documents);
			}else{
				
			}
		} catch(Exception e){
			result=e.toString();
			logger.error(e.toString(), e);
		}
		map.put("projectList", projectList);
		map.put("result", result);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 去修改发证信息页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		//pd.put("DEPARTMENT",Jurisdiction.getUsername());
		pd=this.getPageData();
		
		mongoClient = MongoDBUtil.initMongo();
		Document document = new Document();
		document.append("XMWYM", pd.getString("XMWYM"));
		FindIterable<Document> documents  = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("xiangmu")
				.find(document);
		
		List<Map<String, Object>> documents2MapList = MongoDBUtil.documents2MapList(documents);

		Map<String, Object> map = documents2MapList.get(0);
		
		pd.put("FILE_TYPE",1);
		pd.put("BUSINESS_ID",pd.getString("XMWYM"));
		PageData pdRed = fileService.findByBusinessId(pd);
		if(null!=pdRed){
			map.put("redFileName",pdRed.getString("FILE_NAME"));
		}
		
		mv.addObject("pd",map);
		mv.addObject("msg", "edit");
		mv.addObject("title", "信息编辑");
		mv.setViewName("sphz/project/project_edit");
		//close();
		return mv;
	}
	
	@RequestMapping(value="/edit")
	@ResponseBody
	public Object edit(HttpServletRequest request) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success";
		FilePageData pd = new FilePageData(request);
		System.out.println(pd);
		try {
			mongoClient = MongoDBUtil.initMongo();
			Document document = ConvertUtil.convertDocByMap(pd);
			mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("xiangmu").updateOne(Filters.eq("XIANGMU_ID", pd.getString("XIANGMU_ID")),new Document("$set",document));
			
			//上传红线文件
			//SphzUtils.uploadFile(request, pd.getString("XMWYM"), "redFile", "hxfwbasepath", 1, fileService);//红线范围
		} catch (Exception e) {
			errInfo=e.toString();
		}
		
		map.put("result", errInfo);				//返回结果
		//close();
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 去规划许可建筑面积报表页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/exportGhxkjzmj")
	public ModelAndView exportGhxkjzmj(HttpServletRequest request) throws Exception{
		ModelAndView mv = this.getModelAndView();
		ExportManager  exportManager=(ExportManager)Utils.getApplicationContext().getBean(ExportManager.BEAN_ID);
		Map<String,Object> parameters=new HashMap<String,Object>();
		parameters.put("param", request.getParameter("param"));
		HtmlReport htmlReport = exportManager.exportHtml("file:城乡规划许可管理情况表.ureport.xml",request.getContextPath(),parameters);
		String html="<style type=\"text/css\">";
		html+=htmlReport.getStyle();
		html+="</style>";
		html+=htmlReport.getContent();
		mv.addObject("param", "gagaga");
		mv.addObject("html", html);
		mv.setViewName("sphz/fawen/test");
		return mv;
	}
	
	/**
	 * 根据项目唯一码获取项目信息
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/getProjectByXmwym")
	@ResponseBody
	public Object getProjectByXmwym() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String result="success";
		PageData pd = new PageData();
		try {
			pd=this.getPageData();
			mongoClient = MongoDBUtil.initMongo();
			Document dc=new Document();
			dc.append("XMWYM", pd.getString("XMWYM"));
			FindIterable<Document> documents = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("xiangmu").find(dc);
			List<Map<String, Object>> projList = MongoDBUtil.documents2MapList(documents);
			if(null!=projList&&projList.size()>0){
				map.put("proj",projList.get(0));
			}else{
				result="fail";
			}
		} catch (Exception e) {
			result=e.toString();
		}
		
		map.put("result", result);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 根据发文文号获取项目
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/getProjectByFwwh")
	@ResponseBody
	public Object getProjectByFwwh() throws Exception{
		//步骤：应该归属到按关键字查询里面，查询时，根据key查询项目唯一码，根据项目唯一码查询项目编号
		Map<String,Object> map = new HashMap<String,Object>();
		String result="success";
		PageData pd = new PageData();
		try {
			pd=this.getPageData();
			mongoClient = MongoDBUtil.initMongo();
			Document d=new Document();
			d.append("FWWH", pd.getString("fwwh"));
			FindIterable<Document> documents = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("fawen").find(d);
			List<Map<String, Object>> fawenList = MongoDBUtil.documents2MapList(documents);
			if(null!=fawenList&&fawenList.size()>0){
				Map<String, Object> fawen = fawenList.get(0);
				String xmwym=(String)fawen.get("XMWYM");
				
				
			}else{
				result="fail";
			}
		} catch (Exception e) {
			result=e.toString();
		}
		
		map.put("result", result);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 去新增项目页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		
		//项目属地
		pd.put("BIANMA", "015");
		List<Dictionaries> xmsdList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有所属区划
		mv.addObject("xmsdList", xmsdList);
		
		//是否重点项目
		pd.put("BIANMA", "030");
		List<Dictionaries> isImportantList = dictionariesService.listAllItemsByCodeValue(pd);
		mv.addObject("isImportantList", isImportantList);
		
		//项目类型
		pd.put("BIANMA", "011");
		List<Dictionaries> xmlxList = dictionariesService.listAllItemsByCodeValue(pd);
		mv.addObject("xmlxList", xmlxList);
		
		//建设类型
		pd.put("BIANMA", "031");
		List<Dictionaries> jslxList = dictionariesService.listAllItemsByCodeValue(pd);
		mv.addObject("jslxList", jslxList);
		pd.put("XMWYM", this.get32UUID());
		mv.addObject("pd",pd);
		mv.setViewName("sphz/project/project_add");
		return mv;
	}
	
	@RequestMapping(value="/checkHas")
	@ResponseBody
	public Object checkHas(HttpServletRequest request) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "success";
		PageData pd=this.getPageData();
		try {
			mongoClient = MongoDBUtil.initMongo();
			Document dc=new Document().append("PROJECT_NAME", pd.getString("PROJECT_NAME"));
			long count = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("xiangmu")
					.count(dc);
			if(count>0){
				result="has";
			}
		} catch (Exception e) {
			result=e.toString();
		}
		map.put("result", result);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@RequestMapping(value="/add")
	@ResponseBody
	public Object save(HttpServletRequest request) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success";
		PageData pd=this.getPageData();
		//pd.put("XMWYM",this.get32UUID());
		pd.put("LRSJ",DateUtil.getTime());
		pd.put("REGION_CODE", Jurisdiction.getNumber());
		try {
			mongoClient = MongoDBUtil.initMongo();
			Document document = ConvertUtil.convertDocByMap(pd);
			document.append("SPJD", "");
			System.out.println(MongoDBUtil.getDbName());
			mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("xiangmu")
					.insertOne(document);
		} catch (Exception e) {
			errInfo=e.toString();
		}
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	
}
