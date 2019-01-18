package com.sphz.controller.sphz.statistic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
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
import com.sphz.util.Jurisdiction;
import com.sphz.util.MongoDBUtil;
import com.sphz.util.ObjectExcelView;
import com.sphz.util.ObjectExcelView1;
import com.sphz.util.PageData;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/sphz/statistics_back")
public class SphzStatisticController_back extends BaseController {
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	
	private MongoClient mongoClient;
	
	
	/**去发文明细页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goFwmx")
	public ModelAndView goFwmx() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd=this.getPageData();
		String REGION_CODE=Jurisdiction.getNumber();
		if(!REGION_CODE.equals("3702")){
			pd.put("BIANMA", REGION_CODE);
		}else{
			pd.put("BIANMA", "");
		}
		mv.addObject("pd",pd);
		mv.setViewName("sphz/statistic/fwmx");
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
		pd=this.getPageData();
		String bianma= (String)pd.get("bianma");
		
		
		List<Dictionaries> cbbmList = new ArrayList<Dictionaries>();
		try {
			if(bianma.equals("")){
				pd.put("BIANMA", "008");
				cbbmList = dictionariesService.listAllItemsByCodeValue(pd);
			}else{
				pd.put("BIANMA",bianma);
				cbbmList = dictionariesService.listItemsByBianma(pd);
			}
			
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
		int year=Integer.parseInt(pd.getString("year"));
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
			document.append("FZRQ", new Document("$gte",DateUtil.getBeginDataOfYear()).append("$lte", DateUtil.getDay()));
			if(null!=cbbmId&&!cbbmId.equals("")){
				document.append("REGION_CODE", cbbmId);
			}
			document.append("ZT", "1");
			for(int i=0;i<fwlxList.size();i++){
				fwlxNameList.add(fwlxList.get(i).getNAME());
				document.append("FWLX",fwlxList.get(i).getNAME());
				//根据发文文号进行去重，因为在发证过程中，有些证有多个地块，解决方法是将每个地块单独拎出来进行保存，这样就会出现一个证出现多条记录的情况，这要求录入人员保证
				//发文文号的唯一性，在统计过程中将这些记录统计为一个，满足一证多地块需求
				Long c=0l;
				DistinctIterable<String> distinct = collection.distinct("FWWH",document,String.class);
				for(String s :distinct){
					c++;
				}
				Long count = c;
				//Long count = collection.count(document);
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
			document.append("FZRQ", new Document("$gte",DateUtil.getBeginDataOfYear()).append("$lte", DateUtil.getDay()));
			document.append("ZT", "1");
			for(int i=0;i<fwlxList.size();i++){
				fwlxNameList.add(fwlxList.get(i).getNAME());
				document.append("FWLX",fwlxList.get(i).getNAME());
				
				Long c=0l;
				DistinctIterable<String> distinct = collection.distinct("FWWH",document,String.class);
				for(String s :distinct){
					c++;
				}
				Long count = c;
				//Long count = collection.count(document);
				totalYearCount+=count;
				fwlxYearCountList.add(count);
			}
			fwlxYearCountList.set(0, totalYearCount);
			
			Document document1=new Document();
			document1.append("FZRQ", new Document("$gte",DateUtil.getBeginDataOfYearByInterval(1)).append("$lte", DateUtil.getDataByIntervalYear(1)));
			document1.append("ZT", "1");
			for(int i=0;i<fwlxList.size();i++){
				document1.append("FWLX",fwlxList.get(i).getNAME());
				//Long count = collection.count(document1);
				
				Long c1=0l;
				DistinctIterable<String> distinct1 = collection.distinct("FWWH",document1,String.class);
				for(String s :distinct1){
					c1++;
				}
				Long count = c1;
				
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
					document.append("FZRQ", new Document("$gte",DateUtil.getBeginDataOfMonthByInterval(j)).append("$lte", DateUtil.getEndDataOfLastMonthByInterval(j)));
					document.append("ZT", "1");
					if(j==0){
						fwlxNameList.add(fwlxList.get(i).getNAME());
					}
					document.append("FWLX",fwlxList.get(i).getNAME());
					
					Long c=0l;
					DistinctIterable<String> distinct = collection.distinct("FWWH",document,String.class);
					for(String s :distinct){
						c++;
					}
					Long count = c;
					
					//Long count = collection.count(document);
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
				document.append("FZRQ", new Document("$gte",DateUtil.getBeginDataOfMonthByInterval(j)).append("$lte", DateUtil.getEndDataOfLastMonthByInterval(j)));
				document.append("ZT", "1");
				
				Long c=0l;
				DistinctIterable<String> distinct = collection.distinct("FWWH",document,String.class);
				for(String s :distinct){
					c++;
				}
				Long count = c;
				
				//Long count = collection.count(document);
				fwlxHalfThisYearCountList.add(count);
				
				Document document1=new Document();
				document1.append("FZRQ", new Document("$gte",DateUtil.getBeginDataOfMonthByInterval(j+12)).append("$lte", DateUtil.getEndDataOfLastMonthByInterval(j+12)));
				document1.append("ZT", "1");
				
				Long c1=0l;
				DistinctIterable<String> distinct1 = collection.distinct("FWWH",document1,String.class);
				for(String s :distinct1){
					c1++;
				}
				Long count1 = c1;
				
				//Long count1 = collection.count(document1);
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
	

	
}
