package com.sphz.controller.sphz;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.util.JSON;
import com.sphz.controller.base.BaseController;
import com.sphz.entity.Page;
import com.sphz.entity.system.Dictionaries;
import com.sphz.entity.system.User;
import com.sphz.service.information.todo.TodoManager;
import com.sphz.service.sphz.BuildingManager;
import com.sphz.service.sphz.ChangeManager;
import com.sphz.service.sphz.FileManager;
import com.sphz.service.sphz.MessageManager;
import com.sphz.service.sphz.OpinionsManager;
import com.sphz.service.sphz.PcompletionManager;
import com.sphz.service.sphz.PpermitionManager;
import com.sphz.service.sphz.ProjManager;
import com.sphz.service.sphz.TrafficManager;
import com.sphz.service.system.dictionaries.DictionariesManager;
import com.sphz.util.AppUtil;
import com.sphz.util.Const;
import com.sphz.util.ConvertUtil;
import com.sphz.util.DateUtil;
import com.sphz.util.FilePageData;
import com.sphz.util.FtpUtils;
import com.sphz.util.Jurisdiction;
import com.sphz.util.MongoDBUtil;
import com.sphz.util.PageData;
import com.sphz.util.SphzUtils;
import com.sphz.util.Tools;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/sphz")
public class SphzController extends BaseController {
	@Resource(name="projService")
	private ProjManager projService;
	@Resource(name="opinionsService")
	private OpinionsManager opinionsService;
	@Resource(name="changeService")
	private ChangeManager changeService;
	@Resource(name="ppermitionService")
	private PpermitionManager ppermitionService;
	@Resource(name="pcompletionService")
	private PcompletionManager pcompletionService;
	@Resource(name="buildingService")
	private BuildingManager buildingService;
	@Resource(name="trafficService")
	private TrafficManager trafficService;
	@Resource(name="messageService")
	private MessageManager messageService;
	@Resource(name="fileService")
	private FileManager fileService;
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	
	private MongoClient mongoClient;
	Map<String, Object> propsMap = FtpUtils.getFtpProperties();
	String hostname = (String) propsMap.get("host");
	int port = Integer.parseInt((String) propsMap.get("port"));
	String username = (String) propsMap.get("username");
	String password = (String) propsMap.get("password");
	String approval="行政审批处";
	String[] fawenLists={"建设项目选址意见书","建设项目规划条件变更","建设用地规划许可证","建设工程规划许可证（建筑）","建设工程规划许可证（市政交通）","建设工程竣工规划验收合格证"};
	
	/**
	 * 去新增发证信息页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd.put("DEPARTMENT",Jurisdiction.getUsername());
		
		//用地性质树形选择
		List<PageData> zdictPdList = new ArrayList<PageData>();
		JSONArray arr = JSONArray.fromObject(dictionariesService.listAllDictsToSelect("c7fa42b047ef49bca1100d3fc636f533",zdictPdList));
		mv.addObject("zTreeNodes", (null == arr ?"":arr.toString()));
		
		//管线类别
		pd.put("BIANMA", "007");
		List<Dictionaries> pipeCategoryList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有管线类别
		mv.addObject("pipeCategoryList", pipeCategoryList);
		
		pd.put("BIANMA", "009");
		List<Dictionaries> jzwsyxzList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有建筑物使用性质
		mv.addObject("jzwsyxzList", jzwsyxzList);
		
		pd.put("BIANMA", "014");
		List<Dictionaries> fwlxList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有发文类型
		mv.addObject("fwlxList", fwlxList);
		
		pd.put("BIANMA","015");
		List<Dictionaries> ssqhList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有所属区划
		mv.addObject("ssqhList", ssqhList);
		
		
		mv.addObject("pd",pd);
		mv.addObject("msg", "add");
		mv.addObject("title", "信息登记");
		mv.setViewName("sphz/info_add");
		return mv;
	}
	
	@RequestMapping(value="/add")
	@ResponseBody
	public Object save(HttpServletRequest request) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success";
		String FAWEN_ID=this.get32UUID();
		FilePageData pd = new FilePageData(request);
		pd.put("FAWEN_ID",FAWEN_ID);
		pd.put("FURNISH",Jurisdiction.getUsername());
		pd.put("DEPARTMENT",Jurisdiction.getUsername());
		SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pd.put("ADD_TIME",df1.format(System.currentTimeMillis()));
		//STAGE  报建类型  1：选址意见书   2：规划条件变更  3：建设用地规划许可  4：建设工程规划许可（建筑） 5：建设工程规划许可（交通）  6：建设工程竣工规划验收合格证
		//STATUS 填报信息状态  1：待完善   2：待提交  3： 待审核  4：已通过  5:未通过
		//action 0:补充  1：暂存  2：提交
		pd=SphzUtils.addStatusInfo(pd);
		try {
			mongoClient = MongoDBUtil.initMongo();
			Document document = ConvertUtil.convertDocByMap(pd);
			System.out.println(MongoDBUtil.getDbName());
			mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("fawen")
					.insertOne(document);
			PageData pdProj=SphzUtils.extractProjInfo(pd);//存放项目信息表
			Document documentProj = new Document("PROJ_KEY",pd.getString("PROJ_KEY"));
			mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("xiangmu")
					.replaceOne(documentProj,ConvertUtil.convertDocByMap(pdProj), new UpdateOptions().upsert(true));//根据唯一码判断项目是否存在，如果不存在则新增，存在则更新
			//添加文件
			//SphzUtils.uploadFile(request, FAWEN_ID, "planFile", "planbasepath", 1, fileService);//平面图纸
			//SphzUtils.uploadFile(request, FAWEN_ID, "redFile", "redbasepath", 2, fileService);//平面图纸
		} catch (Exception e) {
			errInfo=e.toString();
		}
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	
	@RequestMapping(value="/add1")
	@ResponseBody
	public Object save1(HttpServletRequest request,
			@RequestParam(value="planFile",required=false) MultipartFile planFile,
			@RequestParam(value="redFile",required=false) MultipartFile redFile,
			@RequestParam(value="PROJ_NO",required=false) String PROJ_NO,
			@RequestParam(value="PROJ_NAME",required=false) String PROJ_NAME,
			@RequestParam(value="PROJ_LOCATION",required=false) String PROJ_LOCATION,
			@RequestParam(value="PROJ_UNIT",required=false) String PROJ_UNIT,
			@RequestParam(value="ORG_CODE",required=false) String ORG_CODE,
			@RequestParam(value="APPLICANT_NAME",required=false) String APPLICANT_NAME,
			@RequestParam(value="APPLICANT_PHONE",required=false) String APPLICANT_PHONE,
			@RequestParam(value="DEPARTMENT",required=false) String DEPARTMENT,
			@RequestParam(value="OPERATOR",required=false) String OPERATOR,
			@RequestParam(value="OPINIONS_NO",required=false) String OPINIONS_NO,
			@RequestParam(value="OPINIONS_DATE",required=false) String OPINIONS_DATE,
			@RequestParam(value="LAND_USE",required=false) String LAND_USE,
			@RequestParam(value="LAND_AREA",required=false) String LAND_AREA,
			@RequestParam(value="MIX_RATIO",required=false) String MIX_RATIO,
			@RequestParam(value="PLOT_RATIO",required=false) String PLOT_RATIO,
			@RequestParam(value="CONTROL_HEIGHT",required=false) String CONTROL_HEIGHT,
			@RequestParam(value="GREEN_RATIO",required=false) String GREEN_RATIO,
			
			@RequestParam(value="BUILDING_DENSITY",required=false) String BUILDING_DENSITY,
			@RequestParam(value="PARKING",required=false) String PARKING,
			@RequestParam(value="SPONGE_REQUIRE",required=false) String SPONGE_REQUIRE,
			
			@RequestParam(value="BUILDING_AREA",required=false) String BUILDING_AREA,
			@RequestParam(value="GROUND_AREA",required=false) String GROUND_AREA,
			@RequestParam(value="UNDERGROUND_AREA",required=false) String UNDERGROUND_AREA,
			@RequestParam(value="BUILDING_PROPERTY",required=false) String BUILDING_PROPERTY,
			@RequestParam(value="GROUND_PARKING",required=false) String GROUND_PARKING,
			@RequestParam(value="UNDERGROUND_PARKING",required=false) String UNDERGROUND_PARKING,
			@RequestParam(value="HOUSE_NUM",required=false) String HOUSE_NUM,
			@RequestParam(value="PIEP_CATEGORY",required=false) String PIEP_CATEGORY,
			@RequestParam(value="PIEP_LENGTH",required=false) String PIEP_LENGTH,
			@RequestParam(value="ROAD_LEVEL",required=false) String ROAD_LEVEL,
			@RequestParam(value="ROAD_LENGTH",required=false) String ROAD_LENGTH,
			@RequestParam(value="CONVERT_AREA",required=false) String CONVERT_AREA,
			@RequestParam(value="ACTION",required=false) String ACTION,
			@RequestParam(value="STAGE",required=false) String STAGE) throws Exception{
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		logBefore(logger, Jurisdiction.getUsername()+"新增报建信息");
		//ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		pd.put("PROJ_NO",PROJ_NO);
		pd.put("PROJ_NAME",PROJ_NAME);
		pd.put("PROJ_LOCATION",PROJ_LOCATION);
		pd.put("PROJ_UNIT",PROJ_UNIT);
		pd.put("ORG_CODE",ORG_CODE);
		pd.put("APPLICANT_NAME",APPLICANT_NAME);
		pd.put("APPLICANT_PHONE",APPLICANT_PHONE);
		pd.put("DEPARTMENT",DEPARTMENT);
		pd.put("OPERATOR",OPERATOR);
		pd.put("OPINIONS_NO",OPINIONS_NO);
		pd.put("OPINIONS_DATE",OPINIONS_DATE);
		pd.put("LAND_USE",LAND_USE);
		pd.put("DEPARTMENT",DEPARTMENT);
		pd.put("OPERATOR",OPERATOR);
		pd.put("OPINIONS_NO",OPINIONS_NO);
		pd.put("OPINIONS_DATE",OPINIONS_DATE);
		pd.put("LAND_USE",LAND_USE);
		if(LAND_AREA==null||LAND_AREA.equals("")){
			LAND_AREA="0";
		}
		pd.put("LAND_AREA",Double.valueOf(LAND_AREA.toString()));
		if(MIX_RATIO==null||MIX_RATIO.equals("")){
			MIX_RATIO="0";
		}
		pd.put("MIX_RATIO",Double.valueOf(MIX_RATIO.toString()));
		if(PLOT_RATIO==null||PLOT_RATIO.equals("")){
			PLOT_RATIO="0";
		}
		pd.put("PLOT_RATIO",Double.valueOf(PLOT_RATIO.toString()));
		if(CONTROL_HEIGHT==null||CONTROL_HEIGHT.equals("")){
			CONTROL_HEIGHT="0";
		}
		pd.put("CONTROL_HEIGHT",Double.valueOf(CONTROL_HEIGHT.toString()));
		if(GREEN_RATIO==null||GREEN_RATIO.equals("")){
			GREEN_RATIO="0";
		}
		pd.put("GREEN_RATIO",Double.valueOf(GREEN_RATIO.toString()));
		if(BUILDING_DENSITY==null||BUILDING_DENSITY.equals("")){
			BUILDING_DENSITY="0";
		}
		pd.put("BUILDING_DENSITY",Double.valueOf(BUILDING_DENSITY.toString()));
		if(PARKING==null||PARKING.equals("")){
			PARKING="0";
		}
		pd.put("PARKING",Integer.parseInt(PARKING.toString()));
		pd.put("SPONGE_REQUIRE",SPONGE_REQUIRE);
		
		
		if(BUILDING_AREA==null||BUILDING_AREA.equals("")){
			BUILDING_AREA="0";
		}
		pd.put("BUILDING_AREA",Double.valueOf(BUILDING_AREA.toString()));
		
		if(GROUND_AREA==null||GROUND_AREA.equals("")){
			GROUND_AREA="0";
		}
		pd.put("GROUND_AREA",Double.valueOf(GROUND_AREA.toString()));
		
		if(UNDERGROUND_AREA==null||UNDERGROUND_AREA.equals("")){
			UNDERGROUND_AREA="0";
		}
		pd.put("UNDERGROUND_AREA",Double.valueOf(UNDERGROUND_AREA.toString()));
		
		pd.put("BUILDING_PROPERTY",BUILDING_PROPERTY);
		
		if(GROUND_PARKING==null||GROUND_PARKING.equals("")){
			GROUND_PARKING="0";
		}
		pd.put("GROUND_PARKING",Integer.parseInt(GROUND_PARKING.toString()));
		
		if(UNDERGROUND_PARKING==null||UNDERGROUND_PARKING.equals("")){
			UNDERGROUND_PARKING="0";
		}
		pd.put("UNDERGROUND_PARKING",Integer.parseInt(UNDERGROUND_PARKING.toString()));
		
		if(HOUSE_NUM==null||HOUSE_NUM.equals("")){
			HOUSE_NUM="0";
		}
		pd.put("HOUSE_NUM",Integer.parseInt(HOUSE_NUM.toString()));
		
		pd.put("PIEP_CATEGORY",PIEP_CATEGORY);
		if(PIEP_LENGTH==null||PIEP_LENGTH.equals("")){
			PIEP_LENGTH="0";
		}
		pd.put("PIEP_LENGTH",Double.valueOf(PIEP_LENGTH.toString()));
		pd.put("ROAD_LEVEL",ROAD_LEVEL);
		if(ROAD_LENGTH==null||ROAD_LENGTH.equals("")){
			ROAD_LENGTH="0";
		}
		pd.put("ROAD_LENGTH",Double.valueOf(ROAD_LENGTH.toString()));
		if(CONVERT_AREA==null||CONVERT_AREA.equals("")){
			CONVERT_AREA="0";
		}
		pd.put("CONVERT_AREA",Double.valueOf(CONVERT_AREA.toString()));
		
		
		String errInfo = "success";
		Map<String,Object> map = new HashMap<String,Object>();
		String id=this.get32UUID();
		//pd.put("ID", id);	//ID 主键
		//pd.put("department_id", Jurisdiction.getDEPARTMENT_ID());
		//在新增项目之前需要校验是否存在该项目，存在则不操作，不存在则新增
		try{
			/*List<PageData> projList= projService.findByNo(pd);
			if(projList.size()>0){
				
			}else{
				projService.save(pd);//新增项目信息
			}*/
			
		//添加文书相关信息
		pd.put("FURNISH",Jurisdiction.getUsername());
		pd.put("DEPARTMENT",Jurisdiction.getUsername());
		mongoClient = MongoDBUtil.initMongo();
		Document document = ConvertUtil.convertDocByMap(pd);
		System.out.println(MongoDBUtil.getDbName());
		mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("ghsp")
				.insertOne(document);
		mongoClient.close();
		
		SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pd.put("ADD_TIME",df1.format(System.currentTimeMillis()));
		//STAGE  报建类型  1：选址意见书   2：规划条件变更  3：建设用地规划许可  4：建设工程规划许可（建筑） 5：建设工程规划许可（交通）  6：建设工程竣工规划验收合格证
		//STATUS 填报信息状态  1：待完善   2：待提交  3： 待审核  4：已通过  5:未通过
		//action 0:补充  1：暂存  2：提交
		if(STAGE.equals("1")){
			if(ACTION.equals("0")){
				pd.put("STATUS",1);
				pd.put("MESSAGE_READER",Jurisdiction.getUsername());
				pd.put("MESSAGE_BUSINESS_STATUS",3);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"保存了关于"+pd.getString("PROJ_NAME")+"的《建设项目选址意见书》相关内容,请尽快补充！");
			}
			
			if(ACTION.equals("1")){
				pd.put("STATUS",3);
				pd.put("MESSAGE_READER",approval);
				pd.put("MESSAGE_BUSINESS_STATUS",3);
				
				
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"提交了关于"+pd.getString("PROJ_NAME")+"的《建设项目选址意见书》相关内容,请尽快审核！");
			}
			if(ACTION.equals("2")){
				pd.put("STATUS",2);
				pd.put("MESSAGE_READER",Jurisdiction.getUsername());
				pd.put("MESSAGE_BUSINESS_STATUS",2);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"暂存了关于"+pd.getString("PROJ_NAME")+"的《建设项目选址意见书》相关内容,请尽快提交！");
			}
			pd.put("OPINIONS_ID",id);
			opinionsService.save(pd);
		}
		if(STAGE.equals("2")){
			if(ACTION.equals("0")){
				pd.put("STATUS",1);
				pd.put("MESSAGE_READER",Jurisdiction.getUsername());
				pd.put("MESSAGE_BUSINESS_STATUS",3);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"保存了关于"+pd.getString("PROJ_NAME")+"的《建设项目规划条件变更》相关内容,请尽快补充！");
			}
			if(ACTION.equals("1")){
				pd.put("STATUS",3);
				pd.put("MESSAGE_READER",approval);
				pd.put("MESSAGE_BUSINESS_STATUS",3);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"提交了关于"+pd.getString("PROJ_NAME")+"的《建设项目规划条件变更》相关内容,请尽快审核！");
			}
			if(ACTION.equals("2")){
				pd.put("STATUS",2);
				pd.put("MESSAGE_READER",Jurisdiction.getUsername());
				pd.put("MESSAGE_BUSINESS_STATUS",2);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"暂存了关于"+pd.getString("PROJ_NAME")+"的《建设项目规划条件变更》相关内容,请尽快提交！");
			}
			pd.put("PCHANGE_ID",id);
			changeService.save(pd);
		}
		if(STAGE.equals("3")){
			if(ACTION.equals("0")){
				pd.put("STATUS",1);
				pd.put("MESSAGE_READER",Jurisdiction.getUsername());
				pd.put("MESSAGE_BUSINESS_STATUS",3);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"保存了关于"+pd.getString("PROJ_NAME")+"的《建设用地规划许可》相关内容,请尽快补充！");
			}
			if(ACTION.equals("1")){
				pd.put("STATUS",3);
				pd.put("MESSAGE_READER",approval);
				pd.put("MESSAGE_BUSINESS_STATUS",3);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"提交了关于"+pd.getString("PROJ_NAME")+"的《建设用地规划许可》相关内容,请尽快审核！");
			}
			if(ACTION.equals("2")){
				pd.put("STATUS",2);
				pd.put("MESSAGE_READER",Jurisdiction.getUsername());
				pd.put("MESSAGE_BUSINESS_STATUS",2);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"暂存了关于"+pd.getString("PROJ_NAME")+"的《建设用地规划许可》相关内容,请尽快提交！");
			}
			pd.put("PPERMITION_ID",id);
			ppermitionService.save(pd);
		}
		if(STAGE.equals("4")){
			if(ACTION.equals("0")){
				pd.put("STATUS",1);
				pd.put("MESSAGE_READER",Jurisdiction.getUsername());
				pd.put("MESSAGE_BUSINESS_STATUS",3);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"保存了关于"+pd.getString("PROJ_NAME")+"的《建设工程规划许可（新办建筑类）》相关内容,请尽快补充！");
			}
			if(ACTION.equals("1")){
				pd.put("STATUS",3);
				pd.put("MESSAGE_READER",approval);
				pd.put("MESSAGE_BUSINESS_STATUS",3);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"提交了关于"+pd.getString("PROJ_NAME")+"的《建设工程规划许可（新办建筑类）》相关内容,请尽快审核！");
			}
			if(ACTION.equals("2")){
				pd.put("STATUS",2);
				pd.put("MESSAGE_READER",Jurisdiction.getUsername());
				pd.put("MESSAGE_BUSINESS_STATUS",2);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"暂存了关于"+pd.getString("PROJ_NAME")+"的《建设工程规划许可（新办建筑类）》相关内容,请尽快提交！");
			}
			pd.put("BP_ID",id);
			buildingService.save(pd);
		}
		if(STAGE.equals("5")){
			if(ACTION.equals("0")){
				pd.put("STATUS",1);
				pd.put("MESSAGE_READER",Jurisdiction.getUsername());
				pd.put("MESSAGE_BUSINESS_STATUS",3);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"保存了关于"+pd.getString("PROJ_NAME")+"的《建设工程规划许可（新办市政交通类）》相关内容,请尽快补充！");
			}
			if(ACTION.equals("1")){
				pd.put("STATUS",3);
				pd.put("MESSAGE_READER",approval);
				pd.put("MESSAGE_BUSINESS_STATUS",3);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"提交了关于"+pd.getString("PROJ_NAME")+"的《建设工程规划许可（新办市政交通类）》相关内容,请尽快审核！");
			}
			if(ACTION.equals("2")){
				pd.put("STATUS",2);
				pd.put("MESSAGE_READER",Jurisdiction.getUsername());
				pd.put("MESSAGE_BUSINESS_STATUS",2);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"暂存了关于"+pd.getString("PROJ_NAME")+"的《建设工程规划许可（新办市政交通类）》相关内容,请尽快提交！");
			}
			pd.put("TPERM_ID",id);
			trafficService.save(pd);
		}
		if(STAGE.equals("6")){
			if(ACTION.equals("0")){
				pd.put("STATUS",1);
				pd.put("MESSAGE_READER",Jurisdiction.getUsername());
				pd.put("MESSAGE_BUSINESS_STATUS",3);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"保存了关于"+pd.getString("PROJ_NAME")+"的《建设工程竣工规划核实》相关内容,请尽快补充！");
			}
			if(ACTION.equals("1")){
				pd.put("STATUS",3);
				pd.put("MESSAGE_READER",approval);
				pd.put("MESSAGE_BUSINESS_STATUS",3);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"提交了关于"+pd.getString("PROJ_NAME")+"的《建设工程竣工规划核实》相关内容,请尽快审核！");
			}
			if(ACTION.equals("2")){
				pd.put("STATUS",2);
				pd.put("MESSAGE_READER",Jurisdiction.getUsername());
				pd.put("MESSAGE_BUSINESS_STATUS",2);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"暂存了关于"+pd.getString("PROJ_NAME")+"的《建设工程竣工规划核实》相关内容,请尽快提交！");
			}
			pd.put("PCOMP_ID",id);
			pcompletionService.save(pd);
		}
		
		//项目表,假设项目编号唯一，先判断项目编号是否存在，存在则修改，不存在则新增
		//projNo
		
		PageData projData= projService.findByNo(pd);
		if(projData!=null){
			projService.edit(pd);
		}else{
			pd.put("ID",this.get32UUID());
			projService.save(pd);//新增项目信息
		}
		
		//添加消息
		pd.put("MESSAGE_ID",this.get32UUID());
		pd.put("MESSAGE_TYPE",1);//消息类型（1：提醒 2：催办”）
		pd.put("MESSAGE_BUSINESS_ID",id);//消息关联报建id
		//pd.put("MESSAGE_READER","Admin");
		pd.put("MESSAGE_STATUS",1);//消息状态（1：未读  2：已读）
		pd.put("MESSAGE_STAGES",STAGE);//消息关联报建阶段（ 1：选址意见书   2：规划条件变更  3：建设用地规划许可  4：建设工程规划许可（建筑） 5：建设工程规划许可（交通）  6：建设工程竣工规划验收合格证）
		//pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"提交了关于"+pd.getString("PROJ_NAME")+"的《建设项目选址意见书》相关内容,请尽快审核！");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pd.put("MESSAGE_SEND_TIME",df.format(System.currentTimeMillis()));
		messageService.save(pd);
		
		//添加文件
		String planFileName = "";
		if (planFile==null||planFile.getSize() == 0) {

		} else {
			InputStream inStream = null;
			inStream = planFile.getInputStream();
			// String basePath="message";//设置服务器中文件保存的根目录
			String basePath = (String) propsMap.get("planbasepath");// 设置服务器中文件保存的根目录
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String filePath = dateFormat.format(now); // 根据当前时间设置文件保存的子目录
			basePath += "/" + filePath + "/" + id;
			planFileName = planFile.getOriginalFilename();

			pd.put("FILE_ID", this.get32UUID());
			pd.put("FILE_NAME", planFileName);
			pd.put("FILE_PATH", basePath);
			pd.put("BUSINESS_ID", id);
			pd.put("FILE_TYPE", "平面图纸");
			pd.put("CREATE_TIME", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			//pd.put("picture_url", basePath + "/" + fileName);
			FtpUtils.uploadFile(hostname, port, username, password, basePath, planFileName, inStream);
			fileService.save(pd);
		}
		
		//添加文件
				String redFileName = "";
				if (redFile==null||redFile.getSize() == 0) {

				} else {
					InputStream inStream = null;
					inStream = redFile.getInputStream();
					// String basePath="message";//设置服务器中文件保存的根目录
					String basePath = (String) propsMap.get("redbasepath");// 设置服务器中文件保存的根目录
					Date now = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String filePath = dateFormat.format(now); // 根据当前时间设置文件保存的子目录
					basePath += "/" + filePath + "/" + id;
					redFileName = redFile.getOriginalFilename();

					pd.put("FILE_ID", this.get32UUID());
					pd.put("FILE_NAME", redFileName);
					pd.put("FILE_PATH", basePath);
					pd.put("BUSINESS_ID", id);
					pd.put("FILE_TYPE", "红线范围");
					pd.put("CREATE_TIME", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					//pd.put("picture_url", basePath + "/" + fileName);
					FtpUtils.uploadFile(hostname, port, username, password, basePath, redFileName, inStream);
					fileService.save(pd);
				}
		
		}catch(Exception e){
			errInfo=e.toString();
		}
		map.put("result", errInfo);				//返回结果
		//mv.setViewName("save_result");
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
		int STAGE_TYPE=Integer.parseInt(pd.getString("STAGE_TYPE"));
		String PKID=pd.getString("PKID");
		String EDIT_TYPE=pd.getString("EDIT_TYPE");//用于记录当前编辑数据的状态，1 待提交  2 被驳回
		
		mongoClient = MongoDBUtil.initMongo();
		Document document = new Document();
		FindIterable<Document> documents  = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("fawen")
				.find();
		List<Map<String, Object>> documents2MapList = MongoDBUtil.documents2MapList(documents);

		mv.addObject("pd1", documents2MapList.get(0));
		
		if(STAGE_TYPE==1){
			pd = opinionsService.findById(pd);	//根据ID读取
		}
		if(STAGE_TYPE==2){
			pd = changeService.findById(pd);	//根据ID读取
		}
		if(STAGE_TYPE==3){
			pd = ppermitionService.findById(pd);	//根据ID读取
		}
		if(STAGE_TYPE==4){
			pd = buildingService.findById(pd);	//根据ID读取
		}
		if(STAGE_TYPE==5){
			pd = trafficService.findById(pd);	//根据ID读取
		}
		if(STAGE_TYPE==6){
			pd = pcompletionService.findById(pd);	//根据ID读取
		}
		
		pd.put("STAGE_TYPE",STAGE_TYPE);
		pd.put("PKID",PKID);
		
		pd.put("FILE_TYPE","平面图纸");
		PageData pdPlan = fileService.findById(pd);
		if(null!=pdPlan){
			pd.put("planFileName",pdPlan.getString("FILE_NAME"));
		}
		pd.put("FILE_TYPE","红线范围");
		PageData pdRed = fileService.findById(pd);
		if(null!=pdRed){
			pd.put("redFileName",pdRed.getString("FILE_NAME"));
		}
		
		//用地性质树形选择
		List<PageData> zdictPdList = new ArrayList<PageData>();
		JSONArray arr = JSONArray.fromObject(dictionariesService.listAllDictsToSelect("c7fa42b047ef49bca1100d3fc636f533",zdictPdList));
		mv.addObject("zTreeNodes", (null == arr ?"":arr.toString()));
		
		//管线类别
		pd.put("BIANMA", "007");
		List<Dictionaries> pipeCategoryList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有管线类别
		mv.addObject("pipeCategoryList", pipeCategoryList);
		
		mv.addObject("pd",pd);
		mv.addObject("msg", "edit");
		mv.addObject("EDIT_TYPE",EDIT_TYPE);
		mv.addObject("title", "信息编辑");
		mv.setViewName("sphz/info_add");
		return mv;
	}
	
	
	@RequestMapping(value="/edit")
	@ResponseBody
	public Object edit(HttpServletRequest request,
			@RequestParam(value="planFile",required=false) MultipartFile planFile,
			@RequestParam(value="redFile",required=false) MultipartFile redFile,
			@RequestParam(value="PROJ_NO",required=false) String PROJ_NO,
			@RequestParam(value="PROJ_NAME",required=false) String PROJ_NAME,
			@RequestParam(value="PROJ_LOCATION",required=false) String PROJ_LOCATION,
			@RequestParam(value="PROJ_UNIT",required=false) String PROJ_UNIT,
			@RequestParam(value="ORG_CODE",required=false) String ORG_CODE,
			@RequestParam(value="APPLICANT_NAME",required=false) String APPLICANT_NAME,
			@RequestParam(value="APPLICANT_PHONE",required=false) String APPLICANT_PHONE,
			@RequestParam(value="DEPARTMENT",required=false) String DEPARTMENT,
			@RequestParam(value="OPERATOR",required=false) String OPERATOR,
			@RequestParam(value="OPINIONS_NO",required=false) String OPINIONS_NO,
			@RequestParam(value="OPINIONS_DATE",required=false) String OPINIONS_DATE,
			@RequestParam(value="LAND_USE",required=false) String LAND_USE,
			@RequestParam(value="LAND_AREA",required=false) String LAND_AREA,
			@RequestParam(value="MIX_RATIO",required=false) String MIX_RATIO,
			@RequestParam(value="PLOT_RATIO",required=false) String PLOT_RATIO,
			@RequestParam(value="CONTROL_HEIGHT",required=false) String CONTROL_HEIGHT,
			@RequestParam(value="GREEN_RATIO",required=false) String GREEN_RATIO,
			
			@RequestParam(value="BUILDING_DENSITY",required=false) String BUILDING_DENSITY,
			@RequestParam(value="PARKING",required=false) String PARKING,
			@RequestParam(value="SPONGE_REQUIRE",required=false) String SPONGE_REQUIRE,
			
			@RequestParam(value="BUILDING_AREA",required=false) String BUILDING_AREA,
			@RequestParam(value="GROUND_AREA",required=false) String GROUND_AREA,
			@RequestParam(value="UNDERGROUND_AREA",required=false) String UNDERGROUND_AREA,
			@RequestParam(value="BUILDING_PROPERTY",required=false) String BUILDING_PROPERTY,
			@RequestParam(value="GROUND_PARKING",required=false) String GROUND_PARKING,
			@RequestParam(value="UNDERGROUND_PARKING",required=false) String UNDERGROUND_PARKING,
			@RequestParam(value="HOUSE_NUM",required=false) String HOUSE_NUM,
			@RequestParam(value="PIEP_CATEGORY",required=false) String PIEP_CATEGORY,
			@RequestParam(value="PIEP_LENGTH",required=false) String PIEP_LENGTH,
			@RequestParam(value="ROAD_LEVEL",required=false) String ROAD_LEVEL,
			@RequestParam(value="ROAD_LENGTH",required=false) String ROAD_LENGTH,
			@RequestParam(value="CONVERT_AREA",required=false) String CONVERT_AREA,
			@RequestParam(value="ACTION",required=false) String ACTION,
			@RequestParam(value="STAGE",required=false) String STAGE,
			@RequestParam(value="PKID",required=false) String PKID,
			@RequestParam(value="EDIT_TYPE",required=false) String EDIT_TYPE) throws Exception{
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		logBefore(logger, Jurisdiction.getUsername()+"新增报建信息");
		//ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("PKID",PKID);
		pd.put("PROJ_NO",PROJ_NO);
		pd.put("PROJ_NAME",PROJ_NAME);
		pd.put("PROJ_LOCATION",PROJ_LOCATION);
		pd.put("PROJ_UNIT",PROJ_UNIT);
		pd.put("ORG_CODE",ORG_CODE);
		pd.put("APPLICANT_NAME",APPLICANT_NAME);
		pd.put("APPLICANT_PHONE",APPLICANT_PHONE);
		pd.put("DEPARTMENT",DEPARTMENT);
		pd.put("OPERATOR",OPERATOR);
		pd.put("OPINIONS_NO",OPINIONS_NO);
		pd.put("OPINIONS_DATE",OPINIONS_DATE);
		pd.put("LAND_USE",LAND_USE);
		pd.put("DEPARTMENT",DEPARTMENT);
		pd.put("OPERATOR",OPERATOR);
		pd.put("OPINIONS_NO",OPINIONS_NO);
		pd.put("OPINIONS_DATE",OPINIONS_DATE);
		pd.put("LAND_USE",LAND_USE);
		if(LAND_AREA==null||LAND_AREA.equals("")){
			LAND_AREA="0";
		}
		pd.put("LAND_AREA",Double.valueOf(LAND_AREA.toString()));
		if(MIX_RATIO==null||MIX_RATIO.equals("")){
			MIX_RATIO="0";
		}
		pd.put("MIX_RATIO",Double.valueOf(MIX_RATIO.toString()));
		if(PLOT_RATIO==null||PLOT_RATIO.equals("")){
			PLOT_RATIO="0";
		}
		pd.put("PLOT_RATIO",Double.valueOf(PLOT_RATIO.toString()));
		if(CONTROL_HEIGHT==null||CONTROL_HEIGHT.equals("")){
			CONTROL_HEIGHT="0";
		}
		pd.put("CONTROL_HEIGHT",Double.valueOf(CONTROL_HEIGHT.toString()));
		if(GREEN_RATIO==null||GREEN_RATIO.equals("")){
			GREEN_RATIO="0";
		}
		pd.put("GREEN_RATIO",Double.valueOf(GREEN_RATIO.toString()));
		if(BUILDING_DENSITY==null||BUILDING_DENSITY.equals("")){
			BUILDING_DENSITY="0";
		}
		pd.put("BUILDING_DENSITY",Double.valueOf(BUILDING_DENSITY.toString()));
		if(PARKING==null||PARKING.equals("")){
			PARKING="0";
		}
		pd.put("PARKING",Integer.parseInt(PARKING.toString()));
		pd.put("SPONGE_REQUIRE",SPONGE_REQUIRE);
		
		
		if(BUILDING_AREA==null||BUILDING_AREA.equals("")){
			BUILDING_AREA="0";
		}
		pd.put("BUILDING_AREA",Double.valueOf(BUILDING_AREA.toString()));
		
		if(GROUND_AREA==null||GROUND_AREA.equals("")){
			GROUND_AREA="0";
		}
		pd.put("GROUND_AREA",Double.valueOf(GROUND_AREA.toString()));
		
		if(UNDERGROUND_AREA==null||UNDERGROUND_AREA.equals("")){
			UNDERGROUND_AREA="0";
		}
		pd.put("UNDERGROUND_AREA",Double.valueOf(UNDERGROUND_AREA.toString()));
		
		pd.put("BUILDING_PROPERTY",BUILDING_PROPERTY);
		
		if(GROUND_PARKING==null||GROUND_PARKING.equals("")){
			GROUND_PARKING="0";
		}
		pd.put("GROUND_PARKING",Integer.parseInt(GROUND_PARKING.toString()));
		
		if(UNDERGROUND_PARKING==null||UNDERGROUND_PARKING.equals("")){
			UNDERGROUND_PARKING="0";
		}
		pd.put("UNDERGROUND_PARKING",Integer.parseInt(UNDERGROUND_PARKING.toString()));
		
		if(HOUSE_NUM==null||HOUSE_NUM.equals("")){
			HOUSE_NUM="0";
		}
		pd.put("HOUSE_NUM",Integer.parseInt(HOUSE_NUM.toString()));
		
		pd.put("PIEP_CATEGORY",PIEP_CATEGORY);
		if(PIEP_LENGTH==null||PIEP_LENGTH.equals("")){
			PIEP_LENGTH="0";
		}
		pd.put("PIEP_LENGTH",Double.valueOf(PIEP_LENGTH.toString()));
		pd.put("ROAD_LEVEL",ROAD_LEVEL);
		if(ROAD_LENGTH==null||ROAD_LENGTH.equals("")){
			ROAD_LENGTH="0";
		}
		pd.put("ROAD_LENGTH",Double.valueOf(ROAD_LENGTH.toString()));
		if(CONVERT_AREA==null||CONVERT_AREA.equals("")){
			CONVERT_AREA="0";
		}
		pd.put("CONVERT_AREA",Double.valueOf(CONVERT_AREA.toString()));
		
		
		String errInfo = "success";
		Map<String,Object> map = new HashMap<String,Object>();
		String id=this.get32UUID();
		//pd.put("ID", id);	//ID 主键
		//pd.put("department_id", Jurisdiction.getDEPARTMENT_ID());
		//在新增项目之前需要校验是否存在该项目，存在则不操作，不存在则新增
		try{
			/*List<PageData> projList= projService.findByNo(pd);
			if(projList.size()>0){
				
			}else{
				projService.save(pd);//新增项目信息
			}*/
			
		//添加文书相关信息
		pd.put("FURNISH",Jurisdiction.getUsername());
		pd.put("DEPARTMENT",Jurisdiction.getUsername());

		SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pd.put("UPDATE_TIME",df1.format(System.currentTimeMillis()));
		//STAGE  报建类型  1：选址意见书   2：规划条件变更  3：建设用地规划许可  4：建设工程规划许可（建筑） 5：建设工程规划许可（交通）  6：建设工程竣工规划验收合格证
		//STATUS 填报信息状态  1：待完善   2：待提交  3： 待审核  4：已通过  5:未通过
		//action 0:补充  1：提交  2：暂存
		//MESSAGE_BUSINESS_STATUS消息关联报建信息状态（1：待完善   2：待提交  3： 待审核  4：已通过  5:未通过）
		if(ACTION.equals("0")){
			if("1".equals(EDIT_TYPE)){
				pd.put("STATUS",1);
			}else{
				pd.put("STATUS",5);
			}
			pd.put("MESSAGE_READER",Jurisdiction.getUsername());
			pd.put("MESSAGE_BUSINESS_STATUS",1);
		}
		if(ACTION.equals("1")){
			pd.put("STATUS",3);
			pd.put("MESSAGE_READER",approval);
			pd.put("MESSAGE_BUSINESS_STATUS",3);
		}
		if(ACTION.equals("2")){
			if("1".equals(EDIT_TYPE)){
				pd.put("STATUS",1);
			}else{
				pd.put("STATUS",5);
			}
			pd.put("MESSAGE_READER",Jurisdiction.getUsername());
			pd.put("MESSAGE_BUSINESS_STATUS",2);
		}
		
		if(STAGE.equals("1")){
			if(ACTION.equals("0")){
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"保存了关于"+pd.getString("PROJ_NAME")+"的《建设项目选址意见书》相关内容,请尽快补充！");
			}
			if(ACTION.equals("1")){
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"提交了关于"+pd.getString("PROJ_NAME")+"的《建设项目选址意见书》相关内容,请尽快审核！");
			}
			if(ACTION.equals("2")){
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"暂存了关于"+pd.getString("PROJ_NAME")+"的《建设项目选址意见书》相关内容,请尽快提交！");
			}
			//pd.put("OPINIONS_ID",id);
			opinionsService.edit(pd);
		}
		if(STAGE.equals("2")){
			if(ACTION.equals("0")){
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"保存了关于"+pd.getString("PROJ_NAME")+"的《建设项目规划条件变更》相关内容,请尽快补充！");
			}
			if(ACTION.equals("1")){
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"提交了关于"+pd.getString("PROJ_NAME")+"的《建设项目规划条件变更》相关内容,请尽快审核！");
			}
			if(ACTION.equals("2")){
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"暂存了关于"+pd.getString("PROJ_NAME")+"的《建设项目规划条件变更》相关内容,请尽快提交！");
			}
			//pd.put("PCHANGE_ID",id);
			changeService.edit(pd);
		}
		if(STAGE.equals("3")){
			if(ACTION.equals("0")){
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"保存了关于"+pd.getString("PROJ_NAME")+"的《建设用地规划许可》相关内容,请尽快补充！");
			}
			if(ACTION.equals("1")){
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"提交了关于"+pd.getString("PROJ_NAME")+"的《建设用地规划许可》相关内容,请尽快审核！");
			}
			if(ACTION.equals("2")){
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"暂存了关于"+pd.getString("PROJ_NAME")+"的《建设用地规划许可》相关内容,请尽快提交！");
			}
			//pd.put("PPERMITION_ID",id);
			ppermitionService.edit(pd);
		}
		if(STAGE.equals("4")){
			if(ACTION.equals("0")){
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"保存了关于"+pd.getString("PROJ_NAME")+"的《建设工程规划许可（新办建筑类）》相关内容,请尽快补充！");
			}
			if(ACTION.equals("1")){
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"提交了关于"+pd.getString("PROJ_NAME")+"的《建设工程规划许可（新办建筑类）》相关内容,请尽快审核！");
			}
			if(ACTION.equals("2")){
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"暂存了关于"+pd.getString("PROJ_NAME")+"的《建设工程规划许可（新办建筑类）》相关内容,请尽快提交！");
			}
			//pd.put("BP_ID",id);
			buildingService.edit(pd);
		}
		if(STAGE.equals("5")){
			if(ACTION.equals("0")){
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"保存了关于"+pd.getString("PROJ_NAME")+"的《建设工程规划许可（新办市政交通类）》相关内容,请尽快补充！");
			}
			if(ACTION.equals("1")){
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"提交了关于"+pd.getString("PROJ_NAME")+"的《建设工程规划许可（新办市政交通类）》相关内容,请尽快审核！");
			}
			if(ACTION.equals("2")){
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"暂存了关于"+pd.getString("PROJ_NAME")+"的《建设工程规划许可（新办市政交通类）》相关内容,请尽快提交！");
			}
			//pd.put("TPERM_ID",id);
			trafficService.edit(pd);
		}
		if(STAGE.equals("6")){
			if(ACTION.equals("0")){
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"保存了关于"+pd.getString("PROJ_NAME")+"的《建设工程竣工规划核实》相关内容,请尽快补充！");
			}
			if(ACTION.equals("1")){
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"提交了关于"+pd.getString("PROJ_NAME")+"的《建设工程竣工规划核实》相关内容,请尽快审核！");
			}
			if(ACTION.equals("2")){
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"暂存了关于"+pd.getString("PROJ_NAME")+"的《建设工程竣工规划核实》相关内容,请尽快提交！");
			}
			//pd.put("PCOMP_ID",id);
			pcompletionService.edit(pd);
		}
		
		//项目表,假设项目编号唯一，先判断项目编号是否存在，存在则修改，不存在则新增
		//projNo
		
		PageData projData= projService.findByNo(pd);
		if(projData!=null){
			projService.edit(pd);
		}else{
			pd.put("ID",this.get32UUID());
			projService.save(pd);//新增项目信息
		}
		
		//添加消息
		pd.put("MESSAGE_ID",this.get32UUID());
		pd.put("MESSAGE_TYPE",1);//消息类型（1：提醒 2：催办”）
		pd.put("MESSAGE_BUSINESS_ID",id);//消息关联报建id
		//pd.put("MESSAGE_READER","Admin");
		pd.put("MESSAGE_STATUS",1);//消息状态（1：未读  2：已读）
		pd.put("MESSAGE_STAGES",STAGE);//消息关联报建阶段（ 1：选址意见书   2：规划条件变更  3：建设用地规划许可  4：建设工程规划许可（建筑） 5：建设工程规划许可（交通）  6：建设工程竣工规划验收合格证）
		//pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"提交了关于"+pd.getString("PROJ_NAME")+"的《建设项目选址意见书》相关内容,请尽快审核！");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pd.put("MESSAGE_SEND_TIME",df.format(System.currentTimeMillis()));
		messageService.save(pd);
		
		//添加文件
		String planFileName = "";
		if (planFile==null||planFile.getSize() == 0) {

		} else {
			InputStream inStream = null;
			inStream = planFile.getInputStream();
			// String basePath="message";//设置服务器中文件保存的根目录
			String basePath = (String) propsMap.get("planbasepath");// 设置服务器中文件保存的根目录
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String filePath = dateFormat.format(now); // 根据当前时间设置文件保存的子目录
			basePath += "/" + filePath + "/" + PKID;
			planFileName = planFile.getOriginalFilename();

			pd.put("FILE_ID", this.get32UUID());
			pd.put("FILE_NAME", planFileName);
			pd.put("FILE_PATH", basePath);
			pd.put("BUSINESS_ID", PKID);
			pd.put("FILE_TYPE", "平面图纸");
			pd.put("CREATE_TIME", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			//pd.put("picture_url", basePath + "/" + fileName);
			
			PageData pd1=fileService.findById(pd);
			if(null!=pd1){
				FtpUtils.deleteFile(hostname, port, username, password, pd1.getString("FILE_PATH"), pd1.getString("FILE_NAME"));
				fileService.edit(pd);
			}else{
				fileService.save(pd);
			}
			FtpUtils.uploadFile(hostname, port, username, password, basePath, planFileName, inStream);
		}
		
		//添加文件
			String redFileName = "";
			if (redFile==null||redFile.getSize() == 0) {

			} else {
				InputStream inStream = null;
				inStream = redFile.getInputStream();
				// String basePath="message";//设置服务器中文件保存的根目录
				String basePath = (String) propsMap.get("redbasepath");// 设置服务器中文件保存的根目录
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String filePath = dateFormat.format(now); // 根据当前时间设置文件保存的子目录
				basePath += "/" + filePath + "/" + PKID;
				redFileName = redFile.getOriginalFilename();

				pd.put("FILE_ID", this.get32UUID());
				pd.put("FILE_NAME", redFileName);
				pd.put("FILE_PATH", basePath);
				pd.put("BUSINESS_ID", PKID);
				pd.put("FILE_TYPE", "红线范围");
				pd.put("CREATE_TIME", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				//pd.put("picture_url", basePath + "/" + fileName);
				
				PageData pd1=fileService.findById(pd);
				if(null!=pd1){
					FtpUtils.deleteFile(hostname, port, username, password, pd1.getString("FILE_PATH"), pd1.getString("FILE_NAME"));
					fileService.edit(pd);
				}else{
					fileService.save(pd);
				}
				FtpUtils.uploadFile(hostname, port, username, password, basePath, redFileName, inStream);
				
			}
			
		
		}catch(Exception e){
			errInfo=e.toString();
		}
		map.put("result", errInfo);				//返回结果
		//mv.setViewName("save_result");
		return AppUtil.returnObject(new PageData(), map);
	}
	
	//项目选择页面
	@RequestMapping(value="/projSelect")
	public ModelAndView projSelect(Page page) throws Exception{
		
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		//List<PageData>projList= projService.listAll(pd);
		List<PageData>projList=projService.list(page);
		//List<PageData>projList= opinionsService.list(page);
		mv.addObject("projList",projList);
		mv.setViewName("sphz/proj_select");
		return mv;
	}
	
	//根据项目项目编号获取项目基本信息
	@RequestMapping(value="/getProjByNo")
	@ResponseBody
	public Object getProjById() throws Exception{
		
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success";
		PageData pd = new PageData();
		//List<PageData> pdList = new ArrayList<PageData>();
		try{
			pd = this.getPageData();
			pd= projService.findByNo(pd);
			//pd=opinionsService.findByProjNo(pd);
			//pdList = noticeService.listFileByNoiceId(pd);
		} catch(Exception e){
			errInfo=e.toString();
			logger.error(e.toString(), e);
		}
		map.put("proj", pd);
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 去审核信息页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goCheck")
	public ModelAndView goCheck() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd.put("DEPARTMENT",Jurisdiction.getUsername());
		pd.put("BIANMA", "008");
		List<Dictionaries> departmentList = dictionariesService.listAllItemsByCodeValue(pd);//列出所有承办部门
		mv.addObject("departmentList", departmentList);
		mv.addObject("pd",pd);
		mv.setViewName("sphz/info_check");
		return mv;
	}
	
	/**
	 * 获取审批数据列表
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/getCheckList")
	@ResponseBody
	public Object getCheckList() throws Exception{
		
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success"; 
		PageData pd = new PageData();
		List<PageData> pdList = new ArrayList<PageData>();
		try{
			pd = this.getPageData();
			pdList.addAll(opinionsService.listAll(pd));
			pdList.addAll(changeService.listAll(pd));
			pdList.addAll(ppermitionService.listAll(pd));
			pdList.addAll(buildingService.listAll(pd));
			pdList.addAll(trafficService.listAll(pd));
			pdList.addAll(pcompletionService.listAll(pd));
		} catch(Exception e){
			errInfo=e.toString();
			logger.error(e.toString(), e);
		}
		map.put("checkList", pdList);
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 获取审批数据列表，筛选条件报建类型，填报部门
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/search")
	@ResponseBody
	public Object search() throws Exception{
		
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success"; 
		PageData pd = new PageData();
		List<PageData> pdList = new ArrayList<PageData>();
		try{
			pd = this.getPageData();
			String STAGE = pd.getString("STAGE");
			//String DEPARTMENT = pd.getString("DEPARTMENT");
			if(STAGE==null||STAGE.equals("")){
			
				pdList.addAll(opinionsService.listAll(pd));
				pdList.addAll(changeService.listAll(pd));
				pdList.addAll(ppermitionService.listAll(pd));
				pdList.addAll(buildingService.listAll(pd));
				pdList.addAll(trafficService.listAll(pd));
				pdList.addAll(pcompletionService.listAll(pd));
			}else{
				if(STAGE.equals("1")){
					pdList.addAll(opinionsService.listAll(pd));
				}
				if(STAGE.equals("2")){
					pdList.addAll(changeService.listAll(pd));	
				}
				if(STAGE.equals("3")){
					pdList.addAll(ppermitionService.listAll(pd));
				}
				if(STAGE.equals("4")){
					pdList.addAll(buildingService.listAll(pd));
				}
				if(STAGE.equals("5")){
					pdList.addAll(trafficService.listAll(pd));
				}
				if(STAGE.equals("6")){
					pdList.addAll(pcompletionService.listAll(pd));
				}
			}
			
			
		} catch(Exception e){
			errInfo=e.toString();
			logger.error(e.toString(), e);
		}
		map.put("checkList", pdList);
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	//审批通过
	@RequestMapping(value="/pass")
	@ResponseBody
	public Object pass() throws Exception{
		
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success";
		PageData pd = new PageData();
		//List<PageData> pdList = new ArrayList<PageData>();
		try{
			pd = this.getPageData();
			String STAGE_TYPE = pd.getString("STAGE_TYPE");
			String PKID = pd.getString("PKID");
			String DEPARTMENT=pd.getString("DEPARTMENT");
		    String PROJ_NAME=pd.getString("PROJ_NAME");
			SimpleDateFormat passdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pd.put("PASS_TIME",passdf.format(System.currentTimeMillis()));
			if(STAGE_TYPE.equals("1")){
				pd.put("OPINIONS_ID",PKID);
				pd.put("STATUS",4);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"通过了您关于"+PROJ_NAME+"的《建设项目选址意见书核发》报建内容。");
				opinionsService.editStatus(pd);
			}
			if(STAGE_TYPE.equals("2")){
				pd.put("PCHANGE_ID",PKID);
				pd.put("STATUS",4);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"通过了您关于"+PROJ_NAME+"的《建设项目规划条件变更》报建内容。");
				changeService.editStatus(pd);
			}
			if(STAGE_TYPE.equals("3")){
				pd.put("PPERMITION_ID",PKID);
				pd.put("STATUS",4);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"通过了您关于"+PROJ_NAME+"的《建设用地规划许可》报建内容。");
				ppermitionService.editStatus(pd);
			}
			if(STAGE_TYPE.equals("4")){
				pd.put("BP_ID",PKID);
				pd.put("STATUS",4);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"通过了您关于"+PROJ_NAME+"的《建设工程规划许可（新办建筑类）》报建内容。");
				buildingService.editStatus(pd);
			}
			if(STAGE_TYPE.equals("5")){
				pd.put("TPERM_ID",PKID);
				pd.put("STATUS",4);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"通过了您关于"+PROJ_NAME+"的《建设工程规划许可（新办市政交通类）》报建内容。");
				trafficService.editStatus(pd);
			}
			if(STAGE_TYPE.equals("6")){
				pd.put("PCOMP_ID",PKID);
				pd.put("STATUS",4);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"通过了您关于"+PROJ_NAME+"的《建设工程竣工规划核实》报建内容。");
				pcompletionService.editStatus(pd);
			}
			pd.put("MESSAGE_ID",this.get32UUID());
			pd.put("MESSAGE_TYPE",1);
			pd.put("MESSAGE_READER",DEPARTMENT);
			pd.put("MESSAGE_STATUS",1);
			
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pd.put("MESSAGE_SEND_TIME",df.format(System.currentTimeMillis()));
			pd.put("MESSAGE_BUSINESS_ID",PKID);
			pd.put("MESSAGE_STAGES",STAGE_TYPE);
			pd.put("MESSAGE_BUSINESS_STATUS",4);
			messageService.save(pd);
			//pd=opinionsService.findByProjNo(pd);
			//pdList = noticeService.listFileByNoiceId(pd);
		} catch(Exception e){
			errInfo=e.toString();
			logger.error(e.toString(), e);
		}
		//map.put("proj", pd);
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	//驳回
	@RequestMapping(value="/reject")
	@ResponseBody
	public Object reject() throws Exception{
		
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success";
		PageData pd = new PageData();
		//List<PageData> pdList = new ArrayList<PageData>();
		try{
			pd = this.getPageData();
			String STAGE_TYPE = pd.getString("STAGE_TYPE");
			String PKID = pd.getString("PKID");
			String DEPARTMENT=pd.getString("DEPARTMENT");
		    String PROJ_NAME=pd.getString("PROJ_NAME");
			SimpleDateFormat passdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pd.put("PASS_TIME",passdf.format(System.currentTimeMillis()));
			if(STAGE_TYPE.equals("1")){
				pd.put("OPINIONS_ID",PKID);
				pd.put("STATUS",5);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"驳回了您关于"+PROJ_NAME+"的《建设项目选址意见书核发》报建内容。");
				opinionsService.editStatus(pd);
			}
			if(STAGE_TYPE.equals("2")){
				pd.put("PCHANGE_ID",PKID);
				pd.put("STATUS",5);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"驳回了您关于"+PROJ_NAME+"的《建设项目规划条件变更》报建内容。");
				changeService.editStatus(pd);
			}
			if(STAGE_TYPE.equals("3")){
				pd.put("PPERMITION_ID",PKID);
				pd.put("STATUS",5);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"驳回了您关于"+PROJ_NAME+"的《建设用地规划许可》报建内容。");
				ppermitionService.editStatus(pd);
			}
			if(STAGE_TYPE.equals("4")){
				pd.put("BP_ID",PKID);
				pd.put("STATUS",5);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"驳回了您关于"+PROJ_NAME+"的《建设工程规划许可（新办建筑类）》报建内容。");
				buildingService.editStatus(pd);
			}
			if(STAGE_TYPE.equals("5")){
				pd.put("TPERM_ID",PKID);
				pd.put("STATUS",5);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"驳回了您关于"+PROJ_NAME+"的《建设工程规划许可（新办市政交通类）》报建内容。");
				trafficService.editStatus(pd);
			}
			if(STAGE_TYPE.equals("6")){
				pd.put("PCOMP_ID",PKID);
				pd.put("STATUS",5);
				pd.put("MESSAGE_CONTENT",Jurisdiction.getUsername()+"驳回了您关于"+PROJ_NAME+"的《建设工程竣工规划核实》报建内容。");
				pcompletionService.editStatus(pd);
			}
			
			pd.put("MESSAGE_ID",this.get32UUID());
			pd.put("MESSAGE_TYPE",1);
			pd.put("MESSAGE_READER",DEPARTMENT);
			pd.put("MESSAGE_STATUS",1);
			
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pd.put("MESSAGE_SEND_TIME",df.format(System.currentTimeMillis()));
			pd.put("MESSAGE_BUSINESS_ID",PKID);
			pd.put("MESSAGE_STAGES",STAGE_TYPE);
			pd.put("MESSAGE_BUSINESS_STATUS",5);
			messageService.save(pd);
			//pd=opinionsService.findByProjNo(pd);
			//pdList = noticeService.listFileByNoiceId(pd);
		} catch(Exception e){
			errInfo=e.toString();
			logger.error(e.toString(), e);
		}
		//map.put("proj", pd);
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	
	/**
	 * 去登记管理页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goAddList")
	public ModelAndView goAddList() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		mv.addObject("pd",pd);
		mv.setViewName("sphz/add_list");
		return mv;
	}
	
	/**
	 * 获取登记管理页面数据列表
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/getAddList")
	@ResponseBody
	public Object getAddList() throws Exception{
		
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success"; 
		PageData pd = new PageData();
		List<PageData> noSubmitList = new ArrayList<PageData>();
		List<PageData> noCheckList = new ArrayList<PageData>();
		List<PageData> passList = new ArrayList<PageData>();
		List<PageData> rejectList = new ArrayList<PageData>();
		//STATUS 填报信息状态  1：待完善   2：待提交  3： 待审核  4：已通过  5:未通过
		try{
			pd = this.getPageData();
			String STAGE = pd.getString("STAGE");
			pd.put("DEPARTMENT",Jurisdiction.getUsername());
			if(STAGE==null||STAGE.equals("")){
				pd.put("STATUS",1);
				noSubmitList.addAll(opinionsService.listAll(pd));
				noSubmitList.addAll(changeService.listAll(pd));
				noSubmitList.addAll(ppermitionService.listAll(pd));
				noSubmitList.addAll(buildingService.listAll(pd));
				noSubmitList.addAll(trafficService.listAll(pd));
				noSubmitList.addAll(pcompletionService.listAll(pd));
				pd.put("STATUS",2);
				noSubmitList.addAll(opinionsService.listAll(pd));
				noSubmitList.addAll(changeService.listAll(pd));
				noSubmitList.addAll(ppermitionService.listAll(pd));
				noSubmitList.addAll(buildingService.listAll(pd));
				noSubmitList.addAll(trafficService.listAll(pd));
				noSubmitList.addAll(pcompletionService.listAll(pd));
				pd.put("STATUS",3);
				noCheckList.addAll(opinionsService.listAll(pd));
				noCheckList.addAll(changeService.listAll(pd));
				noCheckList.addAll(ppermitionService.listAll(pd));
				noCheckList.addAll(buildingService.listAll(pd));
				noCheckList.addAll(trafficService.listAll(pd));
				noCheckList.addAll(pcompletionService.listAll(pd));
				pd.put("STATUS",4);
				passList.addAll(opinionsService.listAll(pd));
				passList.addAll(changeService.listAll(pd));
				passList.addAll(ppermitionService.listAll(pd));
				passList.addAll(buildingService.listAll(pd));
				passList.addAll(trafficService.listAll(pd));
				passList.addAll(pcompletionService.listAll(pd));
				pd.put("STATUS",5);
				rejectList.addAll(opinionsService.listAll(pd));
				rejectList.addAll(changeService.listAll(pd));
				rejectList.addAll(ppermitionService.listAll(pd));
				rejectList.addAll(buildingService.listAll(pd));
				rejectList.addAll(trafficService.listAll(pd));
				rejectList.addAll(pcompletionService.listAll(pd));
			}else{
				if(STAGE.equals("1")){
					pd.put("STATUS",1);
					noSubmitList.addAll(opinionsService.listAll(pd));
					pd.put("STATUS",2);
					noSubmitList.addAll(opinionsService.listAll(pd));
					pd.put("STATUS",3);
					noCheckList.addAll(opinionsService.listAll(pd));
					pd.put("STATUS",4);
					passList.addAll(opinionsService.listAll(pd));
					pd.put("STATUS",5);
					rejectList.addAll(opinionsService.listAll(pd));
				}
				if(STAGE.equals("2")){
					pd.put("STATUS",1);
					noSubmitList.addAll(changeService.listAll(pd));
					pd.put("STATUS",2);
					noSubmitList.addAll(changeService.listAll(pd));
					pd.put("STATUS",3);
					noCheckList.addAll(changeService.listAll(pd));
					pd.put("STATUS",4);
					passList.addAll(changeService.listAll(pd));
					pd.put("STATUS",5);
					rejectList.addAll(changeService.listAll(pd));
				}
				if(STAGE.equals("3")){
					pd.put("STATUS",1);
					noSubmitList.addAll(ppermitionService.listAll(pd));
					pd.put("STATUS",2);
					noSubmitList.addAll(ppermitionService.listAll(pd));
					pd.put("STATUS",3);
					noCheckList.addAll(ppermitionService.listAll(pd));
					pd.put("STATUS",4);
					passList.addAll(ppermitionService.listAll(pd));
					pd.put("STATUS",5);
					rejectList.addAll(ppermitionService.listAll(pd));
				}
				if(STAGE.equals("4")){
					pd.put("STATUS",1);
					noSubmitList.addAll(buildingService.listAll(pd));
					pd.put("STATUS",2);
					noSubmitList.addAll(buildingService.listAll(pd));
					pd.put("STATUS",3);
					noCheckList.addAll(buildingService.listAll(pd));
					pd.put("STATUS",4);
					passList.addAll(buildingService.listAll(pd));
					pd.put("STATUS",5);
					rejectList.addAll(buildingService.listAll(pd));
				}
				if(STAGE.equals("5")){
					pd.put("STATUS",1);
					noSubmitList.addAll(trafficService.listAll(pd));
					pd.put("STATUS",2);
					noSubmitList.addAll(trafficService.listAll(pd));
					pd.put("STATUS",3);
					noCheckList.addAll(trafficService.listAll(pd));
					pd.put("STATUS",4);
					passList.addAll(trafficService.listAll(pd));
					pd.put("STATUS",5);
					rejectList.addAll(trafficService.listAll(pd));
				}
				if(STAGE.equals("6")){
					pd.put("STATUS",1);
					noSubmitList.addAll(pcompletionService.listAll(pd));
					pd.put("STATUS",2);
					noSubmitList.addAll(pcompletionService.listAll(pd));
					pd.put("STATUS",3);
					noCheckList.addAll(pcompletionService.listAll(pd));
					pd.put("STATUS",4);
					passList.addAll(pcompletionService.listAll(pd));
					pd.put("STATUS",5);
					rejectList.addAll(pcompletionService.listAll(pd));
				}
			}
		} catch(Exception e){
			errInfo=e.toString();
			logger.error(e.toString(), e);
		}
		map.put("noSubmitList", noSubmitList);
		map.put("noCheckList", noCheckList);
		map.put("passList", passList);
		map.put("rejectList", rejectList);
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	
	
}
