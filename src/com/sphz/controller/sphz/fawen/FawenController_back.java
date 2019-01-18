package com.sphz.controller.sphz.fawen;

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
import com.mongodb.client.model.UpdateOptions;
import com.sphz.controller.base.BaseController;
import com.sphz.entity.system.Dictionaries;
import com.sphz.service.sphz.FileManager;
import com.sphz.service.sphz.SphzFileManager;
import com.sphz.service.system.dictionaries.DictionariesManager;
import com.sphz.util.AppUtil;
import com.sphz.util.ConvertUtil;
import com.sphz.util.DateUtil;
import com.sphz.util.DoubleUtils;
import com.sphz.util.FilePageData;
import com.sphz.util.Jurisdiction;
import com.sphz.util.MongoDBUtil;
import com.sphz.util.PageData;
import com.sphz.util.SphzUtils;

@Controller
@RequestMapping("/sphz/fawen_back")
public class FawenController_back extends BaseController {
	@Resource(name="fileService")
	private FileManager fileService;
	@Resource(name="sphzFileService")
	private SphzFileManager sphzFileService;
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	private MongoClient mongoClient;
	
	/**
	 * 去发文列表页面--待补充
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goListTodo")
	public ModelAndView goListTodo() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd=this.getPageData();
		pd.put("BIANMA", "008");
		List<Dictionaries> jbbmList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有发文类型
		mv.addObject("jbbmList", jbbmList);
		mv.setViewName("sphz/fawen/fawen_list_todo");
		return mv;
	}
	
	/**
	 * 去发文列表页面--已补充
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goListDone")
	public ModelAndView goListDone() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd=this.getPageData();
		if(null==pd.getString("XMWYM")||"".equals(pd.getString("XMWYM"))){
			mv.addObject("XMWYM","");
		}else{
			mv.addObject("XMWYM",pd.getString("XMWYM"));
		}
		
		pd.put("BIANMA", "021");
		List<Dictionaries> fwlxList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有发文类型
		mv.addObject("fwlxList", fwlxList);
		
		pd.put("BIANMA", "008");
		List<Dictionaries> jbbmList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有发文类型
		mv.addObject("jbbmList", jbbmList);
		mv.setViewName("sphz/fawen/fawen_list_done");
		return mv;
	}
	
	public Map<String,Object> getListByZT(String zt,PageData pd){
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success"; 
		List<Map<String, Object>> fawenList=new ArrayList<Map<String,Object>>();
		try{
			String jbbm=pd.getString("jbbm");
			String keyword=pd.getString("keyword");
			String beginTime=pd.getString("beginTime");
			String endTime=pd.getString("endTime");
			String beginTimeFz=pd.getString("beginTimeFz");
			String endTimeFz=pd.getString("endTimeFz");
			String fwlx=pd.getString("fwlx");
			Integer sEcho=Integer.parseInt(pd.getString("sEcho"));
			Integer iDisplayStart =Integer.parseInt(pd.getString("iDisplayStart"));
			Integer iDisplayLength =Integer.parseInt(pd.getString("iDisplayLength"));
			mongoClient = MongoDBUtil.initMongo();
			Document document = new Document();
			BasicDBObject basicDBObject = new BasicDBObject();
			//document.append("LRSJ", -1);
			document.append("SUBMIT_TIME", -1);
			BasicDBList endList = new BasicDBList();
			
			BasicDBObject conState = new BasicDBObject();
			conState.put("ZT", zt);
			endList.add(conState);
			
			String REGION_CODE=Jurisdiction.getNumber();
			if(!REGION_CODE.equals("3702")){
				BasicDBObject conRegion = new BasicDBObject();
				conRegion.put("REGION_CODE", REGION_CODE);
				endList.add(conRegion);
				
				//选址条件
				BasicDBList bd1 = new BasicDBList();
				BasicDBObject bd2 = new BasicDBObject();
				
				bd1.add(new BasicDBObject("SXMCJC",  Pattern.compile("^.*"+"条件"+".*$", Pattern.CASE_INSENSITIVE)));
				bd1.add(new BasicDBObject("SXMCJC",  Pattern.compile("^.*"+"选址"+".*$", Pattern.CASE_INSENSITIVE)));
				bd2.put("$or",bd1);
				endList.add(bd2);
				
				//申请时间大于2018年6月
				BasicDBObject conSj = new BasicDBObject();
				conSj.append("$gt", "2018-06-00");
				BasicDBObject conTime= new BasicDBObject();
				conTime.put("SUBMIT_TIME", conSj);
				endList.add(conTime);
				
			}else{//排除外围区域
				/*BasicDBObject bd = new BasicDBObject();
				bd.append("$ne","370283000000");
				BasicDBObject conRegion = new BasicDBObject();
				conRegion.put("REGION_CODE", bd);
				endList.add(conRegion);*/
			}
			
			//经办部门
			BasicDBObject forceEnd = new BasicDBObject();
			if(null!=jbbm&&!"".equals(jbbm)){
				forceEnd.put("REGION_CODE", jbbm);
				endList.add(forceEnd);
			}
			
			//发证类型
			BasicDBObject forceEndFz = new BasicDBObject();
			if(null!=fwlx&&!"".equals(fwlx)){
				forceEndFz.put("FWLX", fwlx);
				endList.add(forceEndFz);
			}
			
			//报建日期
			BasicDBObject conBeginTime = new BasicDBObject();
			BasicDBObject conGtBeginTime = new BasicDBObject();
			if(null!=beginTime&&!"".equals(beginTime)&&"".equals(endTime)){
				conGtBeginTime.append("$gt", beginTime);
				conBeginTime.append("SUBMIT_TIME", conGtBeginTime);
				endList.add(conBeginTime);
			}
			BasicDBObject conEndTime = new BasicDBObject();
			BasicDBObject conLtBeginTime = new BasicDBObject();
			if(null!=endTime&&!"".equals(endTime)&&"".equals(beginTime)){
				conLtBeginTime.append("$lt", endTime);
				conEndTime.append("SUBMIT_TIME", conLtBeginTime);
				endList.add(conEndTime);
			}
			BasicDBObject conTime = new BasicDBObject();
			BasicDBObject conGtLtTime = new BasicDBObject();
			if(!"".equals(beginTime)&&(null!=beginTime)&&!"".equals(endTime)&&(null!=endTime)){
				conGtLtTime.append("$lt", endTime).append("$gt", beginTime);
				conTime.append("SUBMIT_TIME", conGtLtTime);
				endList.add(conTime);
			}
			
			
			//发证日期
			BasicDBObject conBeginTimeFz = new BasicDBObject();
			BasicDBObject conGtBeginTimeFz = new BasicDBObject();
			if(null!=beginTimeFz&&!"".equals(beginTimeFz)&&"".equals(endTimeFz)){
				conGtBeginTimeFz.append("$gt", beginTimeFz);
				conBeginTimeFz.append("FZRQ", conGtBeginTimeFz);
				endList.add(conBeginTimeFz);
			}
			BasicDBObject conEndTimeFz = new BasicDBObject();
			BasicDBObject conLtBeginTimeFz = new BasicDBObject();
			if(null!=endTimeFz&&!"".equals(endTimeFz)&&"".equals(beginTimeFz)){
				conLtBeginTimeFz.append("$lt", endTimeFz);
				conEndTimeFz.append("FZRQ", conLtBeginTimeFz);
				endList.add(conEndTimeFz);
			}
			BasicDBObject conTimeFz = new BasicDBObject();
			BasicDBObject conGtLtTimeFz = new BasicDBObject();
			if(!"".equals(beginTimeFz)&&(null!=beginTimeFz)&&!"".equals(endTimeFz)&&(null!=endTimeFz)){
				conGtLtTimeFz.append("$lt", endTimeFz).append("$gt", beginTimeFz);
				conTimeFz.append("FZRQ", conGtLtTimeFz);
				endList.add(conTimeFz);
			}
			
			
			
			BasicDBList condList = new BasicDBList();
			BasicDBObject autoEnd = new BasicDBObject();
			if(null!=keyword&&!"".equals(keyword)){
				condList.add(new BasicDBObject("PROJECT_NAME",  Pattern.compile("^.*"+keyword+".*$", Pattern.CASE_INSENSITIVE)));
				condList.add(new BasicDBObject("LOCATION",  Pattern.compile("^.*"+keyword+".*$", Pattern.CASE_INSENSITIVE)));
				condList.add(new BasicDBObject("ITEM_NAME",  Pattern.compile("^.*"+keyword+".*$", Pattern.CASE_INSENSITIVE)));
				condList.add(new BasicDBObject("FWWH",  Pattern.compile("^.*"+keyword+".*$", Pattern.CASE_INSENSITIVE)));
				condList.add(new BasicDBObject("APPLICANT",  Pattern.compile("^.*"+keyword+".*$", Pattern.CASE_INSENSITIVE)));
				autoEnd.put("$or",condList);
				endList.add(autoEnd);
			}
			
			basicDBObject.put("$and",endList);
			
			FindIterable<Document> documents  = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("fawen")
					.find(basicDBObject).sort(document).skip(iDisplayStart).limit(iDisplayLength);
			long count = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("fawen")
			.count(basicDBObject);
			
			Integer intValue = new Long(count).intValue();
			//int initEcho=sEcho+1;
			fawenList = MongoDBUtil.documents2MapList(documents);
			
			for(int i=0;i<fawenList.size();i++){
				Map<String, Object> fawen = fawenList.get(i);
				String rc = MongoDBUtil.convertObject2String(fawen.get("REGION_CODE"));
				pd.put("BIANMA", rc);
				PageData dictionary = dictionariesService.findByBianma(pd);
				if(null!=dictionary){
					fawenList.get(i).put("ORG_NAME",dictionary.getString("NAME"));
				}
				String SUBMIT_TIME = MongoDBUtil.convertObject2String(fawen.get("SUBMIT_TIME"));
				fawenList.get(i).put("SQRQ",DateUtil.string2yyyyMMdd(SUBMIT_TIME));
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
		map.put("fawenList", fawenList);
		map.put("result", errInfo);				//返回结果
		return map;
	}
	
	/**
	 * 获取发文列表--待补充
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/listTodo")
	@ResponseBody
	public Object listTodo() throws Exception{
		return AppUtil.returnObject(new PageData(), getListByZT("0",this.getPageData()));
	}
	
	/**
	 * 获取发文列表--已补充
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/listDone")
	@ResponseBody
	public Object listDone() throws Exception{
		return AppUtil.returnObject(new PageData(), getListByZT("1",this.getPageData()));
	}
	
	public void close(){//关闭mongoclient
		if(mongoClient!=null){
			mongoClient.close();
			mongoClient=null;
		}
	}
	/**
	 * 根据文号唯一码和发文类型id获取项目
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	public Object getProjByKey() throws Exception{
		
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success"; 
		PageData pd = new PageData();
		List<Map<String, Object>> fawenList=new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> fawenList1=new ArrayList<Map<String,Object>>();
		try{
			pd = this.getPageData();
	
			mongoClient = MongoDBUtil.initMongo();
			Document document = new Document();
			document.append("WHWYM", pd.get("WHWYM"));
			document.append("FWLXID", pd.get("FWLXID"));
			FindIterable<Document> documents  = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("fawen")
					.find(document).projection(new Document("_id",0).append("FAWEN_ID", 0).append("DRSJ", 0));
			//close();
			fawenList = MongoDBUtil.documents2MapList(documents);
			fawenList1= SphzUtils.fawenLeftJoinDic(dictionariesService, fawenList);
			

		} catch(Exception e){
			errInfo=e.toString();
			logger.error(e.toString(), e);
		}
		
		
		map.put("fawenList", fawenList1);
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 去信息录入界面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goAddInfo")
	public ModelAndView goAddInfo() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd=this.getPageData();
		
		//项目属地
		pd.put("BIANMA", "015");
		List<Dictionaries> xmsdList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有所属区划
		mv.addObject("xmsdList", xmsdList);
		
		//工程类别
		pd.put("BIANMA", "017");
		List<Dictionaries> gclbList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有工程类别
		mv.addObject("gclbList", gclbList);
		
		//申办类型
		pd.put("BIANMA", "013");
		List<Dictionaries> sblxList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有申办类型
		mv.addObject("sblxList", sblxList);
		
		//审批阶段
		pd.put("BIANMA", "018");
		List<Dictionaries> spjdList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有审批阶段
		mv.addObject("spjdList", spjdList);
		
		//发文类型
		pd.put("BIANMA", "021");
		List<Dictionaries> fwlxList = dictionariesService.listAllItemsByCodeValue(pd);
		mv.addObject("fwlxList", fwlxList);
		
		//是否混合用地
		pd.put("BIANMA", "019");
		List<Dictionaries> sfhhydList = dictionariesService.listAllItemsByCodeValue(pd);
		mv.addObject("sfhhydList", sfhhydList);
		
		//配套类型
		pd.put("BIANMA", "029");
		List<Dictionaries> ptlxList = dictionariesService.listAllItemsByCodeValue(pd);
		mv.addObject("ptlxList", ptlxList);
		
		//道路性质
		pd.put("BIANMA", "024");
		List<Dictionaries> dlxzList = dictionariesService.listAllItemsByCodeValue(pd);
		mv.addObject("dlxzList", dlxzList);
		//投资管理类型
		pd.put("BIANMA", "026");
		List<Dictionaries> tzgllxList = dictionariesService.listAllItemsByCodeValue(pd);
		mv.addObject("tzgllxList", tzgllxList);
		
		//投资管理类型
		pd.put("BIANMA", "027");
		List<Dictionaries> tdsylxList = dictionariesService.listAllItemsByCodeValue(pd);
		mv.addObject("tdsylxList", tdsylxList);

		//建筑物使用性质
		pd.put("BIANMA", "009");
		List<Dictionaries> jzwsyxzList = dictionariesService.listAllItemsByCodeValue(pd);
		mv.addObject("jzwsyxzList", jzwsyxzList);
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
		
		//管线类型
		pd.put("BIANMA", "007");
		List<Dictionaries> gxlxList = dictionariesService.listAllItemsByCodeValue(pd);
		mv.addObject("gxlxList", gxlxList);
		mv.setViewName("sphz/fawen/info_add");
		return mv;
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
		pd=this.getPageData();
		mongoClient = MongoDBUtil.initMongo();
		Document document = new Document();
		document.append("WHWYM", pd.getString("WHWYM"));
		String fwlxId=pd.getString("FWLXID");
		FindIterable<Document> documents  = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("fawen")
				.find(document);
		
		List<Map<String, Object>> documents2MapList = MongoDBUtil.documents2MapList(documents);
		Map<String, Object> map = documents2MapList.get(0);
		
		if(fwlxId.equals("建设许可（建筑工程）")){
			Object JZMD = map.get("JZMD");
			Object LDL =map.get("LDL");
			if(!MongoDBUtil.convertObject2String(JZMD).equals("")){
				map.put("JZMD", Double.parseDouble(MongoDBUtil.convertObject2String(JZMD))*100);
			}
			if(!MongoDBUtil.convertObject2String(LDL).equals("")){
				map.put("LDL", Double.parseDouble(MongoDBUtil.convertObject2String(LDL))*100);
			}
		}
		
		//项目红线
		pd.put("FILE_TYPE",1);
		pd.put("BUSINESS_ID",(String)map.get("BSNUM"));
		PageData pdRed = sphzFileService.findByBusinessId(pd);
		if(null!=pdRed){
			map.put("redFileName",pdRed.getString("FILE_NAME"));
		}
		map.put("FWLXID", pd.getString("FWLXID"));
		mv.addObject("pd",map);
		
		//项目属地
		pd.put("BIANMA", "015");
		List<Dictionaries> xmsdList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有所属区划
		mv.addObject("xmsdList", xmsdList);
		
		//工程类别
		pd.put("BIANMA", "017");
		List<Dictionaries> gclbList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有工程类别
		mv.addObject("gclbList", gclbList);
		
		//申办类型
		pd.put("BIANMA", "013");
		List<Dictionaries> sblxList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有申办类型
		mv.addObject("sblxList", sblxList);
		
		//审批阶段
		pd.put("BIANMA", "018");
		List<Dictionaries> spjdList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有审批阶段
		mv.addObject("spjdList", spjdList);
		
		//发文类型
		pd.put("BIANMA", "021");
		List<Dictionaries> fwlxList = dictionariesService.listAllItemsByCodeValue(pd);
		mv.addObject("fwlxList", fwlxList);
		
		//是否混合用地
		pd.put("BIANMA", "019");
		List<Dictionaries> sfhhydList = dictionariesService.listAllItemsByCodeValue(pd);
		mv.addObject("sfhhydList", sfhhydList);
		
		//配套类型
		pd.put("BIANMA", "029");
		List<Dictionaries> ptlxList = dictionariesService.listAllItemsByCodeValue(pd);
		mv.addObject("ptlxList", ptlxList);
		
		//道路性质
		pd.put("BIANMA", "024");
		List<Dictionaries> dlxzList = dictionariesService.listAllItemsByCodeValue(pd);
		mv.addObject("dlxzList", dlxzList);
		//投资管理类型
		pd.put("BIANMA", "026");
		List<Dictionaries> tzgllxList = dictionariesService.listAllItemsByCodeValue(pd);
		mv.addObject("tzgllxList", tzgllxList);
		
		//投资管理类型
		pd.put("BIANMA", "027");
		List<Dictionaries> tdsylxList = dictionariesService.listAllItemsByCodeValue(pd);
		mv.addObject("tdsylxList", tdsylxList);

		//建筑物使用性质
		pd.put("BIANMA", "009");
		List<Dictionaries> jzwsyxzList = dictionariesService.listAllItemsByCodeValue(pd);
		mv.addObject("jzwsyxzList", jzwsyxzList);
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
		
		//管线类型
		pd.put("BIANMA", "007");
		List<Dictionaries> gxlxList = dictionariesService.listAllItemsByCodeValue(pd);
		mv.addObject("gxlxList", gxlxList);
		
		mv.setViewName("sphz/fawen/info_edit");
		return mv;
	}
	
	/**
	 * 编辑
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/edit")
	@ResponseBody
	public Object edit(HttpServletRequest request) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String result="success";
		String uuid="";
		FilePageData pd = new FilePageData(request);
		try {
			pd.put("LRSJ",DateUtil.getTime());
			pd.put("ZT","1");
			
			if(pd.get("FWLXID").equals("建设许可（建筑工程）")){
				String jzmd=pd.getString("JZMD");
				String ldl=pd.getString("LDL");
				if(null!=jzmd&&!"".equals(jzmd)){
					pd.put("JZMD",DoubleUtils.percent2Double(Double.parseDouble(jzmd))+"");
				}
				if(null!=ldl&&!"".equals(ldl)){
					pd.put("LDL",DoubleUtils.percent2Double(Double.parseDouble(ldl))+"");
				}
			}
			//提取项目信息，如果是选址阶段，指定一个项目唯一码，其他阶段通过指定关联项目来获取项目唯一码
			//只需判断项目唯一码是否为""即可
			String xmwym=pd.getString("XMWYM");
			if(xmwym.equals("")){//如果项目唯一码为空，则为选址阶段，生成一个项目唯一码
				xmwym=this.get32UUID();
				pd.put("XMWYM", xmwym);
			}
			uuid = xmwym;
			mongoClient = MongoDBUtil.initMongo();
			Document document = ConvertUtil.convertDocByMap(pd);
			mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("fawen").updateOne(Filters.eq("WHWYM", pd.getString("WHWYM")),new Document("$set",document));
			String fwlxId= pd.getString("FWLXID");
			String spjd="";
			if(fwlxId.indexOf("选址、条件")>-1){
				spjd="条";
			}else if(fwlxId.indexOf("用地")>-1){
				spjd="地";
			}else if(fwlxId.indexOf("建设许可")>-1){
				spjd="建";
			}else if(fwlxId.indexOf("竣工")>-1){
				spjd="核";
			}
			Document documentCon = new Document("XMWYM",pd.getString("XMWYM"));
			Document documentProj = new Document()
					.append("XMWYM", xmwym)
					.append("PROJECT_NAME", pd.getString("PROJECT_NAME"))
					.append("PROJECT_CODE", pd.getString("PROJECT_CODE"))
					.append("LOCATION", pd.getString("LOCATION"))
					.append("INVESTMENT", pd.getString("INVESTMENT"))
					.append("SCALE", pd.getString("SCALE"))
					.append("IS_IMPORTANT", pd.getString("IS_IMPORTANT"))
					.append("APPLICANT", pd.getString("APPLICANT"))
					.append("XMSD", pd.getString("XMSD"))
					.append("SJDW", pd.getString("SJDW"))
					.append("XMLX", pd.getString("XMLX"))
					.append("JSLX", pd.getString("JSLX"))
					.append("REGION_CODE", Jurisdiction.getNumber())
					.append("LRSJ",DateUtil.getTime())
					.append("SPJD", spjd);
			mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("xiangmu")
					.replaceOne(documentCon,documentProj, new UpdateOptions().upsert(true));//根据唯一码判断项目是否存在，如果不存在则新增，存在则更新
			//上传红线文件
			SphzUtils.uploadFile(request, pd.getString("BSNUM"), "redFile", "hxfwbasepath", 1, sphzFileService);//红线范围
			
		} catch (Exception e) {
			result=e.toString();
		}
		map.put("result", result);				//返回结果
		map.put("uuid", uuid);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 保存
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public Object save(HttpServletRequest request) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String result="success";
		String uuid="";
		FilePageData pd = new FilePageData(request);
		try {
			pd.put("LRSJ",DateUtil.getTime());
			pd.put("ZT","1");
			pd.put("BSNUM", this.get32UUID());
			pd.put("WHWYM", this.get32UUID());
			if(pd.get("FWLXID").equals("建设许可（建筑工程）")){
				String jzmd=pd.getString("JZMD");
				String ldl=pd.getString("LDL");
				if(null!=jzmd&&!"".equals(jzmd)){
					pd.put("JZMD",DoubleUtils.percent2Double(Double.parseDouble(jzmd))+"");
				}
				if(null!=ldl&&!"".equals(ldl)){
					pd.put("LDL",DoubleUtils.percent2Double(Double.parseDouble(ldl))+"");
				}
			}
			
			String xmwym=pd.getString("XMWYM");
			pd.put("XMWYM", xmwym);
			uuid = xmwym;
			mongoClient = MongoDBUtil.initMongo();
			Document document = ConvertUtil.convertDocByMap(pd);
			mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("fawen").insertOne(document);
			String fwlxId= pd.getString("FWLXID");
			String spjd="";
			if(fwlxId.indexOf("选址、条件")>-1){
				spjd="条";
			}else if(fwlxId.indexOf("用地")>-1){
				spjd="地";
			}else if(fwlxId.indexOf("建设许可")>-1){
				spjd="建";
			}else if(fwlxId.indexOf("竣工")>-1){
				spjd="核";
			}
			Document documentCon = new Document("XMWYM",pd.getString("XMWYM"));
			Document documentProj = new Document()
					.append("XMWYM", xmwym)
					.append("PROJECT_NAME", pd.getString("PROJECT_NAME"))
					.append("PROJECT_CODE", pd.getString("PROJECT_CODE"))
					.append("LOCATION", pd.getString("LOCATION"))
					.append("INVESTMENT", pd.getString("INVESTMENT"))
					.append("SCALE", pd.getString("SCALE"))
					.append("IS_IMPORTANT", pd.getString("IS_IMPORTANT"))
					.append("APPLICANT", pd.getString("APPLICANT"))
					.append("XMSD", pd.getString("XMSD"))
					.append("SJDW", pd.getString("SJDW"))
					.append("XMLX", pd.getString("XMLX"))
					.append("JSLX", pd.getString("JSLX"))
					.append("REGION_CODE", Jurisdiction.getNumber())
					.append("LRSJ",DateUtil.getTime())
					.append("SPJD", spjd);
			mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("xiangmu")
					.replaceOne(documentCon,documentProj, new UpdateOptions().upsert(true));//根据唯一码判断项目是否存在，如果不存在则新增，存在则更新
			//上传红线文件
			SphzUtils.uploadFile(request, pd.getString("BSNUM"), "redFile", "hxfwbasepath", 1, sphzFileService);//红线范围
			
		} catch (Exception e) {
			result=e.toString();
		}
		map.put("result", result);				//返回结果
		map.put("uuid", uuid);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 去修改发证信息页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goShow")
	public ModelAndView goShow() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd=this.getPageData();
		mongoClient = MongoDBUtil.initMongo();
		Document document = new Document();
		document.append("FAWEN_ID", pd.getString("FAWEN_ID"));
		String fwlxId=pd.getString("FWLXID");
		//document.append("FWLXID", pd.getString("FWLXID"));
		FindIterable<Document> documents  = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("fawen")
				.find(document);
		
		List<Map<String, Object>> documents2MapList = MongoDBUtil.documents2MapList(documents);
		Map<String, Object> map = documents2MapList.get(0);
		if(fwlxId.equals("建设许可（建筑工程）")){
			Object JZMD = map.get("JZMD");
			Object LDL =map.get("LDL");
			if(!MongoDBUtil.convertObject2String(JZMD).equals("")&&fwlxId.equals("建设许可（建筑工程）")){
				map.put("JZMD", Double.parseDouble(MongoDBUtil.convertObject2String(JZMD))*100);
			}
			if(!MongoDBUtil.convertObject2String(LDL).equals("")&&fwlxId.equals("建设许可（建筑工程）")){
				map.put("LDL", Double.parseDouble(MongoDBUtil.convertObject2String(LDL))*100);
			}
		}
		mv.addObject("pd",map);
		//项目属地
		pd.put("BIANMA", "015");
		List<Dictionaries> xmsdList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有所属区划
		mv.addObject("xmsdList", xmsdList);
		
		//工程类别
		pd.put("BIANMA", "017");
		List<Dictionaries> gclbList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有工程类别
		mv.addObject("gclbList", gclbList);
		
		//申办类型
		pd.put("BIANMA", "013");
		List<Dictionaries> sblxList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有申办类型
		mv.addObject("sblxList", sblxList);
		
		//审批阶段
		pd.put("BIANMA", "018");
		List<Dictionaries> spjdList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有审批阶段
		mv.addObject("spjdList", spjdList);
		
		//发文类型
		pd.put("BIANMA", "021");
		List<Dictionaries> fwlxList = dictionariesService.listAllItemsByCodeValue(pd);
		mv.addObject("fwlxList", fwlxList);
		
		//是否混合用地
		pd.put("BIANMA", "019");
		List<Dictionaries> sfhhydList = dictionariesService.listAllItemsByCodeValue(pd);
		mv.addObject("sfhhydList", sfhhydList);
		
		//配套类型
		pd.put("BIANMA", "029");
		List<Dictionaries> ptlxList = dictionariesService.listAllItemsByCodeValue(pd);
		mv.addObject("ptlxList", ptlxList);
		
		//道路性质
		pd.put("BIANMA", "024");
		List<Dictionaries> dlxzList = dictionariesService.listAllItemsByCodeValue(pd);
		mv.addObject("dlxzList", dlxzList);
		
		//建筑物使用性质
		pd.put("BIANMA", "009");
		List<Dictionaries> jzwsyxzList = dictionariesService.listAllItemsByCodeValue(pd);
		mv.addObject("jzwsyxzList", jzwsyxzList);
		
		mv.setViewName("sphz/fawen/info_show");
		return mv;
	}
	
	/**
	 * 去附件列表页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goFiles")
	public ModelAndView goFiles() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd=this.getPageData();
		mv.addObject("businessId",pd.getString("businessId"));
		mv.setViewName("sphz/fawen/file_list");
		return mv;
	}
	
	/**
	 * 去附件列表页面(仅查看)
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goFilesOnlyShow")
	public ModelAndView goFilesOnlyShow() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd=this.getPageData();
		mv.addObject("businessId",pd.getString("businessId"));
		mv.setViewName("sphz/fawen/file_list_only_show");
		return mv;
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
	 * 一书三证发放明细
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/exportYSSZ")
	public ModelAndView exportYSSZ(HttpServletRequest request) throws Exception{
		ModelAndView mv = this.getModelAndView();
		ExportManager  exportManager=(ExportManager)Utils.getApplicationContext().getBean(ExportManager.BEAN_ID);
		Map<String,Object> parameters=new HashMap<String,Object>();
		String p=request.getParameter("param");
		parameters.put("param", request.getParameter("param"));
		HtmlReport htmlReport = exportManager.exportHtml("file:分类报表.ureport.xml",request.getContextPath(),parameters);
		String html="<style type=\"text/css\">";
		html+=htmlReport.getStyle();
		html+="</style>";
		//request.getParameter("param");
		html+=htmlReport.getContent();
		mv.addObject("para", p);
		mv.addObject("html", html);
		mv.setViewName("sphz/fawen/test2");
		return mv;
	}
	
	
}
