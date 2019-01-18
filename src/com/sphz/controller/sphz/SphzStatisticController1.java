package com.sphz.controller.sphz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.sphz.controller.base.BaseController;
import com.sphz.entity.system.Dictionaries;
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
import com.sphz.service.system.user.UserManager;
import com.sphz.util.AppUtil;
import com.sphz.util.DateUtil;
import com.sphz.util.FtpUtils;
import com.sphz.util.MongoDBUtil;
import com.sphz.util.ObjectExcelView;
import com.sphz.util.ObjectExcelView1;
import com.sphz.util.PageData;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/sphz/statistics1")
public class SphzStatisticController1 extends BaseController {
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
	
	@Resource(name="userService")
	private UserManager userService;
	
	private MongoClient mongoClient;
	/**导出用户信息到EXCEL
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
				Map<String,Object> dataMap = new HashMap<String,Object>();
				ObjectExcelView erv = new ObjectExcelView();					//执行excel操作
				mv = new ModelAndView(erv,dataMap);
			}
		 catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**导出用户信息到EXCEL
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goFwmx")
	public ModelAndView goFwmx() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd=this.getPageData();
		mv.addObject("pd",pd);
		mv.setViewName("sphz/fwmx");
		return mv;
	}
	
	/**导出用户信息到EXCEL
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/excel1")
	public ModelAndView exportExcel1() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
				Map<String,Object> dataMap = new HashMap<String,Object>();
				ObjectExcelView1 erv = new ObjectExcelView1();					//执行excel操作
				mv = new ModelAndView(erv,dataMap);
			}
		 catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 获取承办部门列表
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/getCbbmList")
	@ResponseBody
	public Object getCbbmList(){
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success"; 
		PageData pd = new PageData();
		pd.put("BIANMA", "008");
		List<Dictionaries> cbbmList = new ArrayList<Dictionaries>();
		try {
			cbbmList = dictionariesService.listAllItemsByCodeValue(pd);
		} catch (Exception e) {
			errInfo=e.toString();
		}//列出所有发文类型
		map.put("cbbmList", cbbmList);
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 获取承办部门发文数量情况统计信息
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/StaticCountByCbbm")
	@ResponseBody
	public Object StaticCountByCbbm() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String code = "success"; 
		PageData pd = new PageData();
		pd=this.getPageData();
		String cbbmId=pd.getString("cbbmId");
		String cbbmName=pd.getString("cbbmName");
		List<Dictionaries> fwlxList = new ArrayList<Dictionaries>();
		List<String> fwlxNameList=new ArrayList<String>();
		List<Long> fwlxCountList=new ArrayList<Long>();
		List<Long> gapList=new ArrayList<Long>();
		Long totalCount=0l;
		fwlxNameList.add("总数");
		fwlxCountList.add(totalCount);
		try {
			pd.put("BIANMA", "021");
			fwlxList = dictionariesService.listAllItemsByCodeValue(pd);
			mongoClient = MongoDBUtil.initMongo();
			MongoCollection<Document> collection = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("fawen");
			Document document=new Document();
			document.append("FWRQ", new Document("$gte",DateUtil.getBeginDataOfYear()).append("$lte", DateUtil.getDay()));
			if(null!=cbbmId&&!cbbmId.equals("")){
				document.append("CBBM", cbbmId);
			}
			for(int i=0;i<fwlxList.size();i++){
				fwlxNameList.add(fwlxList.get(i).getNAME());
				document.append("FWLX",fwlxList.get(i).getDICTIONARIES_ID());
				Long count = collection.count(document);
				totalCount+=count;
				fwlxCountList.add(count);
			}
			fwlxCountList.set(0, totalCount);
			Long gap=totalCount;
			gapList.add(0l);
			for(int i=1;i<fwlxCountList.size();i++){
				gap= gap-fwlxCountList.get(i);
				gapList.add(gap);
			}
			String html="";
			if(null!=cbbmName&&!cbbmName.equals("")){
				html=" <p>"+DateUtil.getBeginDataCnOfYear()+"至今，"+cbbmName+"核发各类规划审批证书共<u><b> "+totalCount+" </b></u>件，其中";
			}else{
				html=" <p>"+DateUtil.getBeginDataCnOfYear()+"至今，青岛市共核发各类规划审批证书共<u><b> "+totalCount+" </b></u>件，其中";
			}
			
			for(int i=1;i<fwlxNameList.size();i++){
				html+="，"+fwlxNameList.get(i)+"<u><b>"+fwlxCountList.get(i)+"</b></u>件";
			}
			html+=".</p>";
			map.put("html",html);
			
		} catch (IOException e) {
			code=e.getMessage();
		}
		map.put("fwlxNameList", fwlxNameList);
		map.put("fwlxCountList", fwlxCountList);
		map.put("gapList", gapList);
		map.put("result", code);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 规划审核证书核发同期对比
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/StaticCountByYear")
	@ResponseBody
	public Object StaticCountByYear() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String code = "success"; 
		PageData pd = new PageData();
		List<Dictionaries> fwlxList = new ArrayList<Dictionaries>();
		List<String> fwlxNameList=new ArrayList<String>();
		List<Long> fwlxYearCountList=new ArrayList<Long>();
		List<Long> fwlxNextYearCountList=new ArrayList<Long>();
		List<Long> riseCountList=new ArrayList<Long>();
		Long totalYearCount=0l;
		Long totalNextYearCount=0l;
		fwlxNameList.add("总数");
		fwlxYearCountList.add(totalYearCount);
		fwlxNextYearCountList.add(totalNextYearCount);
		try {
			pd.put("BIANMA", "021");
			fwlxList = dictionariesService.listAllItemsByCodeValue(pd);
			mongoClient = MongoDBUtil.initMongo();
			MongoCollection<Document> collection = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("fawen");
			Document document=new Document();
			document.append("FWRQ", new Document("$gte",DateUtil.getBeginDataOfYear()).append("$lte", DateUtil.getDay()));
			for(int i=0;i<fwlxList.size();i++){
				fwlxNameList.add(fwlxList.get(i).getNAME());
				document.append("FWLX",fwlxList.get(i).getDICTIONARIES_ID());
				Long count = collection.count(document);
				totalYearCount+=count;
				fwlxYearCountList.add(count);
			}
			fwlxYearCountList.set(0, totalYearCount);
			
			Document document1=new Document();
			document1.append("FWRQ", new Document("$gte",DateUtil.getBeginDataOfYearByInterval(1)).append("$lte", DateUtil.getDataByIntervalYear(1)));
			for(int i=0;i<fwlxList.size();i++){
				document1.append("FWLX",fwlxList.get(i).getDICTIONARIES_ID());
				Long count = collection.count(document1);
				totalNextYearCount+=count;
				fwlxNextYearCountList.add(count);
			}
			fwlxNextYearCountList.set(0, totalNextYearCount);
			
			for(int i=0;i<fwlxYearCountList.size();i++){
				riseCountList.add(fwlxYearCountList.get(i)-fwlxNextYearCountList.get(i));
			}
			
			String html="";
			if(riseCountList.get(0)>=0){
				html=" <p>与去年同期相比，核发各类规划审批证书增加<u><b> "+riseCountList.get(0)+" </b></u>件，其中";
			}else{
				html=" <p>与去年同期相比，核发各类规划审批证书减少<u><b> "+Math.abs(riseCountList.get(0))+" </b></u>件，其中";
			}
			for(int i=1;i<fwlxNameList.size();i++){
				if(riseCountList.get(i)>=0){
					html+="，"+fwlxNameList.get(i)+"增加<u><b>"+riseCountList.get(i)+"</b></u>件";
				}else{
					html+="，"+fwlxNameList.get(i)+"减少<u><b>"+Math.abs(riseCountList.get(i))+"</b></u>件";
				}
			}
			html+=".</p>";
			map.put("html",html);
			
		} catch (IOException e) {
			code=e.getMessage();
		}
		map.put("fwlxNameList", fwlxNameList);
		map.put("fwlxYearCountList", fwlxYearCountList);
		map.put("fwlxNextYearCountList", fwlxNextYearCountList);
		map.put("riseCountList", riseCountList);
		List<String>yearList=new ArrayList<String>();
		yearList.add(DateUtil.getYearByInterval(1)+"年");
		yearList.add(DateUtil.getYearByInterval(0)+"年");
		map.put("yearList",yearList);
		map.put("result", code);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 近6月各月核发情况对比
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/StaticCountByHalfYear")
	@ResponseBody
	public Object StaticCountByHalfYear() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String code = "success"; 
		PageData pd = new PageData();
		List<Dictionaries> fwlxList = new ArrayList<Dictionaries>();
		List<String> fwlxNameList=new ArrayList<String>();
		List fwlxCountList=new ArrayList();
		List<String> monthList=new ArrayList<String>();
		try {
			pd.put("BIANMA", "021");
			fwlxList = dictionariesService.listAllItemsByCodeValue(pd);
			mongoClient = MongoDBUtil.initMongo();
			MongoCollection<Document> collection = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("fawen");
			for(int i=0;i<fwlxList.size();i++){
				List<Long> fwlxHalfYearCountList=new ArrayList<Long>();
				for(int j=5;j>=0;j--){
					if(i==0){
						monthList.add(DateUtil.getMonthOfYearByInterval(j));
					}
					Document document=new Document();
					document.append("FWRQ", new Document("$gte",DateUtil.getBeginDataOfMonthByInterval(j)).append("$lte", DateUtil.getEndDataOfLastMonthByInterval(j)));
					if(j==0){
						fwlxNameList.add(fwlxList.get(i).getNAME());
					}
					document.append("FWLX",fwlxList.get(i).getDICTIONARIES_ID());
					Long count = collection.count(document);
					fwlxHalfYearCountList.add(count);
				}
				fwlxCountList.add(fwlxHalfYearCountList);
			}
			 //<p>近6个月（2018年1月-2018年6月）核发情况为：1月总数<b> 12 </b>件，2月总数<b> 5 </b>件，3月总数<b> 10 </b>件，4月总数<b> 15 </b>件，5月总数<b> 8 </b>件，6月总数<b> 10 </b>件。</p>
			String html="";
			html=" <p>近6个月（"+monthList.get(0)+"-"+monthList.get(monthList.size()-1)+"）核发情况为： ";
			for(int i=0;i<monthList.size();i++){
				Long count=0l;
				for(int j=0;j<fwlxCountList.size();j++){
					List<Long> list = (List<Long>) fwlxCountList.get(j);
					count+= list.get(i);
				}
				html+=","+monthList.get(i)+"总数<b>"+count+"</b>件";
			}
			html+=".</p>";
			map.put("html",html);
			
		} catch (IOException e) {
			code=e.getMessage();
		}
		map.put("fwlxNameList", fwlxNameList);
		map.put("monthList", monthList);
		map.put("fwlxCountList", fwlxCountList);
		map.put("result", code);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 近6月各月核发情况同期对比
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/compareCountByHalfYear")
	@ResponseBody
	public Object compareCountByHalfYear() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String code = "success"; 
		PageData pd = new PageData();
		List<Long> fwlxHalfThisYearCountList=new ArrayList<Long>();
		List<Long> fwlxHalfLastYearCountList=new ArrayList<Long>();
		List<String> monthList=new ArrayList<String>();
		try {
			mongoClient = MongoDBUtil.initMongo();
			MongoCollection<Document> collection = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("fawen");
			for(int j=5;j>=0;j--){
				monthList.add(DateUtil.getMonthByInterval(j));
				Document document=new Document();
				document.append("FWRQ", new Document("$gte",DateUtil.getBeginDataOfMonthByInterval(j)).append("$lte", DateUtil.getEndDataOfLastMonthByInterval(j)));
				Long count = collection.count(document);
				fwlxHalfThisYearCountList.add(count);
				
				Document document1=new Document();
				document1.append("FWRQ", new Document("$gte",DateUtil.getBeginDataOfMonthByInterval(j+12)).append("$lte", DateUtil.getEndDataOfLastMonthByInterval(j+12)));
				Long count1 = collection.count(document1);
				fwlxHalfLastYearCountList.add(count1);
				
				//<p>2017年1月-6月 与 2018年1月-6月 相比，核发变化情况：1月增加<b> 3 </b>件，2月减少<b> 1 </b>件，3月增加<b> 0 </b>件，4月增加<b> 5 </b>件，5月增加<b> 0 </b>件，6月增加<b> 2 </b>件。</p>
				String html="";
				html+="<p>"+DateUtil.getMonthOfYearByInterval(17)+"-"+DateUtil.getMonthOfYearByInterval(12)+"与"+DateUtil.getMonthOfYearByInterval(5)+"-"+DateUtil.getMonthOfYearByInterval(0)+"相比，核发变化情况：";
				for(int i=0;i<monthList.size();i++){
					Long gap=fwlxHalfThisYearCountList.get(i)-fwlxHalfLastYearCountList.get(i);
					if(gap>=0){
						html+=","+monthList.get(i)+"增加<b>"+gap+"</b>件";
					}else{
						html+=","+monthList.get(i)+"减少<b>"+Math.abs(gap)+"</b>件";
					}
				}
				html+=".</p>";
				map.put("html", html);
			}
		} catch (IOException e) {
			code=e.getMessage();
		}
		map.put("fwlxHalfThisYearCountList", fwlxHalfThisYearCountList);
		map.put("fwlxHalfLastYearCountList", fwlxHalfLastYearCountList);
		map.put("monthList", monthList);
		List<String> yearList=new ArrayList<String>();
		yearList.add(DateUtil.getYearByInterval(1)+"年");
		yearList.add(DateUtil.getYearByInterval(0)+"年");
		map.put("yearList", yearList);
		map.put("result", code);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 获取分区发文数量
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/getTotalCountByRegion")
	@ResponseBody
	public Object getTotalCountByRegion(){
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success"; 
		PageData pd = new PageData();
		List<String> regionList = new ArrayList<String>();
		//var regions = ["青岛市市区", "市南区", "市北区", "李沧区", "崂山区", "城阳区", "西海岸", "高新区", "保税区"];
		regionList.add("全部");
		regionList.add("市南分局");
		regionList.add("市北分局");
		regionList.add("李沧分局");
		regionList.add("崂山分局");
		regionList.add("城阳分局");
		regionList.add("西海岸分局");
		regionList.add("高新区分局");
		regionList.add("保税港区分局");
		List chart1Data1=new ArrayList();
		List chart1Data2=new ArrayList();
		 
		try{
			pd = this.getPageData();
			//String STAGE = pd.getString("Region");
			for(int i=0;i<regionList.size();i++){
				pd.put("REGION",regionList.get(i));
				PageData pdOpinions = opinionsService.getCountByRegion(pd);
				Long opinionsCount = (Long) pdOpinions.get("fwCount");
				
				PageData pdPermition = ppermitionService.getCountByRegion(pd);
				Long permitionCount = (Long)pdPermition.get("fwCount");
				
				PageData pdBuilding = buildingService.getCountByRegion(pd);
				Long buildingCount = (Long)pdBuilding.get("fwCount");
				
				PageData pdTraffic = trafficService.getCountByRegion(pd);
				Long trafficCount = (Long)pdTraffic.get("fwCount");
				
				PageData pdCompletion = pcompletionService.getCountByRegion(pd);
				Long completionCount = (Long)pdCompletion.get("fwCount");
				
				Long total=opinionsCount+permitionCount+buildingCount+trafficCount+completionCount;
				Long gap1=(long) 0;
				Long gap2=total-opinionsCount;
				Long gap3=gap2-permitionCount;
				Long gap4=gap3-buildingCount-trafficCount;
				Long gap5= (long) 0;
				List data=new ArrayList();
				data.add(total);
				data.add(opinionsCount);
				data.add(permitionCount);
				data.add(buildingCount+trafficCount);
				data.add(completionCount);
				List data1=new ArrayList();
				data1.add(gap1);
				data1.add(gap2);
				data1.add(gap3);
				data1.add(gap4);
				data1.add(gap5);
				
				
				chart1Data1.add(data);
				
				chart1Data2.add(data1);
				if(i==0){
				 String spzlHtml=" <p>2018年1月1日至今，青岛市市区核发各类规划审批证书共<u><b> "+total+" </b></u>件，"
				 		+ "其中选址意见书（规划设计条件）<u><b>"+opinionsCount+"</b></u>件，"
				 		+ "建设用地规划许可证<u><b> "+permitionCount+"</b></u>件，"
				 		+ "建设工程规划许可证<u><b> "+(buildingCount+trafficCount)+" </b></u>件，"
				 		+ "建设工程竣工规划验收合格证<u><b> "+completionCount+" </b></u>件。</p>";
				 map.put("spzlHtml",spzlHtml);
				 
				}
			}
			
		} catch(Exception e){
			errInfo=e.toString();
			logger.error(e.toString(), e);
		}
		map.put("chart1Data1", chart1Data1);
		map.put("chart1Data2", chart1Data2);
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 获取近六个月发文数量
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/getTotalCountByDate")
	@ResponseBody
	public Object getTotalCountByDate(){
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success"; 
		PageData pd = new PageData();
		List countList1=new ArrayList();
		List countList2=new ArrayList();
		List countList3=new ArrayList();
		List countList4=new ArrayList();
		//List countList5=new ArrayList();
		//List countList6=new ArrayList();
		Long opinionsCount=(long) 0;
		Long permitionCount=(long) 0;
		Long buildingCount=(long) 0;
		Long trafficCount=(long) 0;
		Long brCount=(long) 0;
		Long completionCount=(long) 0;
		
		try{
			pd = this.getPageData();
			for(int i=0;i<6;i++){
				pd.put("DATACONDITION",i);
				PageData pdOpinions = opinionsService.getCountByRegion(pd);
				
				opinionsCount = (Long) pdOpinions.get("fwCount");
				for(int j=0;j<i;j++){
					opinionsCount=opinionsCount-(Long)countList1.get(j);
				}
				countList1.add(opinionsCount);
				
				PageData pdPermition = ppermitionService.getCountByRegion(pd);
				permitionCount = (Long)pdPermition.get("fwCount");
				for(int j=0;j<i;j++){
					permitionCount=permitionCount-(Long)countList2.get(j);
				}
				countList2.add(permitionCount);
				
				PageData pdBuilding = buildingService.getCountByRegion(pd);
				buildingCount = (Long)pdBuilding.get("fwCount");
				PageData pdTraffic = trafficService.getCountByRegion(pd);
				trafficCount = (Long)pdTraffic.get("fwCount");
				brCount=trafficCount+buildingCount;
				for(int j=0;j<i;j++){
					brCount=brCount-(Long)countList3.get(j);
				}
				
				countList3.add(brCount);
				
				PageData pdCompletion = pcompletionService.getCountByRegion(pd);
				completionCount = (Long)pdCompletion.get("fwCount");
				for(int j=0;j<i;j++){
					completionCount=completionCount-(Long)countList4.get(j);
				}
				countList4.add(completionCount);
				
			}
			
		} catch(Exception e){
			errInfo=e.toString();
			logger.error(e.toString(), e);
		}
		map.put("countList1",countList1);
		map.put("countList2",countList2);
		map.put("countList3",countList3);
		map.put("countList4",countList4);
		map.put("result", errInfo);//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
}