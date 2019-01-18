package com.sphz.controller.sphz.report;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.bson.Document;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.sphz.util.DateUtil;
import com.sphz.util.MongoDBUtil;

@Component
public class ReportController  {
	private MongoClient mongoClient;
	public List<Map<String,Object>> loadReportData(String dsName,String datasetName,Map<String,Object>parameters){
		System.out.println(parameters);
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		for(int i=0;i<100;i++){
			Map<String,Object> m=new HashMap<String,Object>();
			m.put("type","选址");
			m.put("id",i);
			m.put("name", RandomStringUtils.random(10,true,false));
			m.put("salary", RandomUtils.nextInt(10000)+i);
			m.put("test", new HashMap().put("test", "2323"));
			list.add(m);
		}
		return list;
	}
	
	public List<Map<String,Object>> loadReportData1(String dsName,String datasetName,Map<String,Object>parameters){
		System.out.println(parameters);
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		for(int i=0;i<100;i++){
			Map<String,Object> m=new HashMap<String,Object>();
			m.put("type","用地");
			m.put("id",i);
			m.put("name", RandomStringUtils.random(10,true,false));
			m.put("salary", RandomUtils.nextInt(10000)+i);
			m.put("age",RandomUtils.nextInt(49));
			list.add(m);
		}
		return list;
	}
	
	
	public List<Map<String, Object>> getDataByFwlxAndRq(Map<String,Object>parameters,String fwlx,String fwlxFull){
		System.out.println(parameters);
		String rq = (String) parameters.get("param");
		String start=rq+"-00";
		String end=rq+"-31";
		List<Map<String, Object>> fawenList=new ArrayList<Map<String,Object>>();
		try{
			if(mongoClient==null){
				mongoClient = MongoDBUtil.initMongo();
			}
			BasicDBObject basicDBObject = new BasicDBObject();
			BasicDBList endList = new BasicDBList();
			BasicDBObject conTime = new BasicDBObject();
			BasicDBObject conGtLtTime = new BasicDBObject();
			conGtLtTime.append("$lt", end).append("$gt", start);
			conTime.append("FZRQ", conGtLtTime);
			endList.add(conTime);
			
			BasicDBObject condEnd = new BasicDBObject();
			condEnd.put("FWLX",fwlx);
			endList.add(condEnd);
			
			BasicDBObject condZT = new BasicDBObject();
			condZT.put("ZT","1");
			endList.add(condZT);
			
			basicDBObject.put("$and",endList);
			
			FindIterable<Document> documents  = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("fawen")
					.find(basicDBObject);
			fawenList = MongoDBUtil.documents2MapList(documents);
			if(null!=fawenList&&fawenList.size()>0){
				for(int i=0;i<fawenList.size();i++){
					fawenList.get(i).put("index", i+1);
					fawenList.get(i).put("LX", fwlxFull);
				}
			}
			
		} catch(Exception e){
			
		}
		return fawenList;
	}
	
	public List<Map<String,Object>> loadReportDataXZ(String dsName,String datasetName,Map<String,Object>parameters){
		return getDataByFwlxAndRq(parameters,"选字","选址意见书");
	}
	public List<Map<String,Object>> loadReportDataDZ(String dsName,String datasetName,Map<String,Object>parameters){
		return getDataByFwlxAndRq(parameters,"地字","建设用地规划许可");
	}
	public List<Map<String,Object>> loadReportDataJZ(String dsName,String datasetName,Map<String,Object>parameters){
		return getDataByFwlxAndRq(parameters,"建字","建设工程规划许可");
	}
	public List<Map<String,Object>> loadReportDataHZ(String dsName,String datasetName,Map<String,Object>parameters){
		return getDataByFwlxAndRq(parameters,"核字","建设工程竣工规划验收合格证");
	}

	//规划许可建筑面积表
	public List<Map<String,Object>>loadGhxkjzmDataj (String dsName,String datasetName,Map<String,Object>parameters){
		System.out.println(parameters);
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> fawenList=new ArrayList<Map<String,Object>>();
		Document document = new Document();
		document.append("FWLXID", "f727c923cfb94566841812b4fb78e4b8");
		document.append("FWRQ", new Document("$gte",DateUtil.getBeginDataOfMonth()).append("$lte", DateUtil.getEndDataOfMonth()));
		fawenList = MongoDBUtil.getFawenList(document);
		double zjzmj=MongoDBUtil.jsjzl(fawenList);
		//System.out.println(zjzmj);
		document.put("FWLXID","424008dcbf0b4d80938883d31feafc93");
		fawenList = MongoDBUtil.getFawenList(document);
		double[] gxArray= MongoDBUtil.jsgxl(fawenList);
		double gxcd =gxArray[0] ;
		double gxmj = gxArray[1];
		
		document.put("FWLXID","af1cef1c384f4b5387987f0bdc830724");
		fawenList = MongoDBUtil.getFawenList(document);
		double[] dlArray= MongoDBUtil.jsdll(fawenList);
		double dlcd = dlArray[0];
		double dlmj= dlArray[1];
		
		document.put("FWRQ", new Document("$gte",DateUtil.getBeginDataOfYear()).append("$lte", DateUtil.getEndDataOfYear()));
		document.put("FWLXID", "f727c923cfb94566841812b4fb78e4b8");
		fawenList = MongoDBUtil.getFawenList(document);
		double zjzmj_year = MongoDBUtil.jsjzl(fawenList);
		
		document.put("FWLXID","424008dcbf0b4d80938883d31feafc93");
		fawenList = MongoDBUtil.getFawenList(document);
		gxArray = MongoDBUtil.jsgxl(fawenList);
		double gxcd_year = gxArray[0];
		double gxmj_year = gxArray[1];
		
		document.put("FWLXID","af1cef1c384f4b5387987f0bdc830724");
		fawenList = MongoDBUtil.getFawenList(document);
		dlArray= MongoDBUtil.jsdll(fawenList);
		double dlcd_year = dlArray[0];
		double dlmj_year = dlArray[1];
		
		Map<String,Object> m=new HashMap<String,Object>();
		m.put("zjzmj",zjzmj);
		m.put("gxmj",gxmj);
		m.put("gxcd",gxcd);
		m.put("dlcd",dlcd);
		m.put("total_month",zjzmj+gxmj+dlcd);
		
		m.put("zjzmj_year",zjzmj_year);
		m.put("gxmj_year",gxmj_year);
		m.put("gxcd_year",gxcd_year);
		m.put("dlcd_year",dlcd_year);
		m.put("total_year",new BigDecimal(zjzmj_year+gxmj_year+dlcd_year+""));
		list.add(m);
		return list;
	}
	
	//规划许可建筑面积表
		public List<Map<String,Object>>loadCxghxkglData1 (String dsName,String datasetName,Map<String,Object>parameters){
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			List<Map<String, Object>> fawenList=new ArrayList<Map<String,Object>>();
			Document document = new Document();
			document.append("FWLXID", "f727c923cfb94566841812b4fb78e4b8");
			document.append("FWRQ", new Document("$gte",DateUtil.getBeginDataOfMonth()).append("$lte", DateUtil.getEndDataOfMonth()));
			fawenList = MongoDBUtil.getFawenList(document);
			double zjzmj=MongoDBUtil.jsjzl(fawenList);
			//System.out.println(zjzmj);
			document.put("FWLXID","424008dcbf0b4d80938883d31feafc93");
			fawenList = MongoDBUtil.getFawenList(document);
			double[] gxArray= MongoDBUtil.jsgxl(fawenList);
			double gxcd =gxArray[0] ;
			double gxmj = gxArray[1];
			
			document.put("FWLXID","af1cef1c384f4b5387987f0bdc830724");
			fawenList = MongoDBUtil.getFawenList(document);
			double[] dlArray= MongoDBUtil.jsdll(fawenList);
			double dlcd = dlArray[0];
			double dlmj= dlArray[1];
			
			document.put("FWRQ", new Document("$gte",DateUtil.getBeginDataOfYear()).append("$lte", DateUtil.getEndDataOfYear()));
			document.put("FWLXID", "f727c923cfb94566841812b4fb78e4b8");
			fawenList = MongoDBUtil.getFawenList(document);
			double zjzmj_year = MongoDBUtil.jsjzl(fawenList);
			
			document.put("FWLXID","424008dcbf0b4d80938883d31feafc93");
			fawenList = MongoDBUtil.getFawenList(document);
			gxArray = MongoDBUtil.jsgxl(fawenList);
			double gxcd_year = gxArray[0];
			double gxmj_year = gxArray[1];
			
			document.put("FWLXID","af1cef1c384f4b5387987f0bdc830724");
			fawenList = MongoDBUtil.getFawenList(document);
			dlArray= MongoDBUtil.jsdll(fawenList);
			double dlcd_year = dlArray[0];
			double dlmj_year = dlArray[1];
			
			Map<String,Object> m=new HashMap<String,Object>();
			m.put("zjzmj",zjzmj);
			m.put("gxmj",gxmj);
			m.put("gxcd",gxcd);
			m.put("dlcd",dlcd);
			m.put("total_month",zjzmj+gxmj+dlcd);
			
			m.put("zjzmj_year",zjzmj_year);
			m.put("gxmj_year",gxmj_year);
			m.put("gxcd_year",gxcd_year);
			m.put("dlcd_year",dlcd_year);
			m.put("total_year",new BigDecimal(zjzmj_year+gxmj_year+dlcd_year+""));
			list.add(m);
			return list;
		}
		
		public List<Map<String, Object>>getListByXmwymAndFwlxId(String xmwym,String fwlxId){
			BasicDBObject basicDBObject = new BasicDBObject();
			BasicDBList endList = new BasicDBList();
			BasicDBObject conXmwym = new BasicDBObject();
			conXmwym.put("XMWYM", xmwym);
			endList.add(conXmwym);
			BasicDBList condList = new BasicDBList();
			BasicDBObject autoEnd = new BasicDBObject();
			condList.add(new BasicDBObject("FWLXID",  Pattern.compile("^.*"+fwlxId+".*$", Pattern.CASE_INSENSITIVE)));
			autoEnd.put("$or",condList);
			endList.add(autoEnd);
			basicDBObject.put("$and",endList);
			FindIterable<Document> documentsXZ=mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("fawen")
					.find(basicDBObject).sort(new Document().append("FZRQ", -1));
			return MongoDBUtil.documents2MapList(documentsXZ);
		}
		
		//导出项目列表带项目所有阶段指标信息
		public List<Map<String,Object>>loadProjectTotalData (String dsName,String datasetName,Map<String,Object>parameters){
			List<Map<String,Object>> projTotalDataList=new ArrayList<Map<String,Object>>();
			List<Map<String, Object>> projList=new ArrayList<Map<String,Object>>(); 
				try {
					mongoClient = MongoDBUtil.initMongo();
					FindIterable<Document> documents  = mongoClient.getDatabase(MongoDBUtil.getDbName()).getCollection("xiangmu")
							.find();
					projList = MongoDBUtil.documents2MapList(documents);
					if(projList!=null&&projList.size()>0){
						for(int i=0;i<projList.size();i++){
							Map<String,Object> proj=projList.get(i);
							String xmwym=(String)proj.get("XMWYM");
							Map<String,Object> m=new HashMap<String,Object>();
							m.put("PROJECT_NAME", proj.get("PROJECT_NAME"));
							m.put("XMWYM", proj.get("XMWYM"));
							m.put("REGION_CODE", proj.get("REGION_CODE"));
							m.put("LOCATION", proj.get("LOCATION"));
							m.put("INVESTMENT", proj.get("INVESTMENT"));
							m.put("SCALE", proj.get("SCALE"));
							m.put("IS_IMPORTANT", proj.get("IS_IMPORTANT"));
							m.put("APPLICANT", proj.get("APPLICANT"));
							m.put("XMSD", proj.get("XMSD"));
							m.put("SJDW", proj.get("SJDW"));
							m.put("XMLX", proj.get("XMLX"));
							m.put("JSLX", proj.get("JSLX"));
							m.put("SPJD", proj.get("SPJD"));
							
							//1、提取最新选址指标字段
							List<Map<String, Object>> listXZ = getListByXmwymAndFwlxId(xmwym,"选址、条件");
							if(listXZ!=null&&listXZ.size()>0){
								Map<String,Object> xz=listXZ.get(0);
								m.put("SFHHYD_XZ", xz.get("SFHHYD"));
								m.put("HHYDBL_XZ", xz.get("HHYDBL"));
								m.put("GHYDXZ_XZ", xz.get("LAND_USE_NAME"));
								m.put("GHYDXZDM_XZ", xz.get("GHYDXZDM"));
								m.put("YDMJ_XZ", xz.get("YDMJ"));
								m.put("RJL_XZ", xz.get("RJL"));
								m.put("JZMD_XZ", xz.get("JZMD"));
								m.put("LDL_XZ", xz.get("LDL"));
								m.put("TCW_XZ", xz.get("TCW"));
								m.put("KZGD_XZ", xz.get("KZGD"));
								m.put("HMCSYQ_XZ", xz.get("HMCSYQ"));
								m.put("BZ_XZ", xz.get("BZ"));
								m.put("DLXZ_XZ", xz.get("DLXZ"));
								m.put("DLCD_XZ", xz.get("DLCD"));
								m.put("HXKD_XZ", xz.get("HXKD"));
								m.put("DMXS_XZ", xz.get("DMXS"));
								m.put("SJBZ_XZ", xz.get("SJBZ"));
								m.put("GXLX_XZ", xz.get("GXLX"));
								m.put("GXCD_XZ", xz.get("GXCD"));
							}
							//2、提取最新用地指标字段
							List<Map<String, Object>> listDZ = getListByXmwymAndFwlxId(xmwym,"用地");
							if(listDZ!=null&&listDZ.size()>0){
								Map<String,Object> yd=listDZ.get(0);
								m.put("SFHHYD_YD", yd.get("SFHHYD"));
								m.put("HHYDBL_YD", yd.get("HHYDBL"));
								m.put("GHYDXZ_YD", yd.get("LAND_USE_NAME"));
								m.put("GHYDXZDM_YD", yd.get("GHYDXZDM"));
								m.put("YDMJ_YD", yd.get("YDMJ"));
								m.put("TZGLLX_YD", yd.get("TZGLLX"));
								m.put("TDSYLX_YD", yd.get("TDSYLX"));
								m.put("HMCSYQ_YD", yd.get("HMCSYQ"));
								m.put("BZ_YD", yd.get("BZ"));
							}
							//3、提取最新绿证指标字段
							List<Map<String, Object>> listJZ = getListByXmwymAndFwlxId(xmwym,"建设许可");
							if(listJZ!=null&&listJZ.size()>0){
								Map<String,Object> jz=listJZ.get(0);
								m.put("SFHHYD_JZ", jz.get("SFHHYD"));
								m.put("HHYDBL_JZ", jz.get("HHYDBL"));
								m.put("GHYDXZ_JZ", jz.get("LAND_USE_NAME"));
								m.put("GHYDXZDM_JZ", jz.get("GHYDXZDM"));
								m.put("YDMJ_JZ", jz.get("YDMJ"));
								m.put("RJL_JZ", jz.get("RJL"));
								m.put("JZMD_JZ", jz.get("JZMD"));
								m.put("LDL_JZ", jz.get("LDL"));
								m.put("ZJZMJ_JZ", jz.get("ZJZMJ"));
								m.put("DSJZMJ_JZ", jz.get("DSJZMJ"));
								m.put("DXJZMJ_JZ", jz.get("DXJZMJ"));
								m.put("TCW_JZ", jz.get("TCW"));
								m.put("DSTCW_JZ", jz.get("DSTCW"));
								m.put("DXTCW_JZ", jz.get("DXTCW"));
								m.put("ZZJZMJ_JZ", jz.get("ZZJZMJ"));
								m.put("BGJZMJ_JZ", jz.get("BGJZMJ"));
								m.put("PTJZMJ_JZ", jz.get("PTJZMJ"));
								m.put("JZDS_JZ", jz.get("JZDS"));
								m.put("HS_JZ", jz.get("HS"));
								m.put("JZWSYXZ_JZ", jz.get("JZWSYXZ"));
								m.put("HMCSYQ_JZ", jz.get("HMCSYQ"));
								m.put("BZ_JZ", jz.get("BZ"));
								
								m.put("DLZCD_JZ", jz.get("DLZCD"));
								m.put("DLKD_JZ", jz.get("DLKD"));
								m.put("DLJB_JZ", jz.get("DLJB"));
								m.put("DMXS_JZ", jz.get("DMXS"));
								m.put("SJBZ_JZ", jz.get("SJBZ"));
								m.put("ZSJZMJ_JZ", jz.get("ZSJZMJ"));
								
								m.put("GXLX_JZ", jz.get("GXLX"));
								m.put("GXZCD_JZ", jz.get("GXZCD"));
								
								//3、提取最新竣工指标字段
								List<Map<String, Object>> listHZ = getListByXmwymAndFwlxId(xmwym,"竣工");
								if(listHZ!=null&&listHZ.size()>0){
									Map<String,Object> hz=listHZ.get(0);
									m.put("YDMJ_HZ", jz.get("YDMJ"));
									m.put("ZJZMJ_HZ", jz.get("ZJZMJ"));
									m.put("JZZDMJ_HZ", jz.get("JZZDMJ"));
									m.put("DSJZMJ_HZ", jz.get("DSJZMJ"));
									m.put("LHMJ_HZ", jz.get("LHMJ"));
									m.put("DXJZMJ_HZ", jz.get("DXJZMJ"));
									m.put("JZGD_HZ", jz.get("JZGD"));
									m.put("CS_HZ", jz.get("CS"));
									m.put("BZ_HZ", jz.get("BZ"));
									
									m.put("DLZCD_HZ", jz.get("DLZCD"));
									m.put("DLKD_HZ", jz.get("DLKD"));
									m.put("DMXS_HZ", jz.get("DMXS"));
									m.put("ZSJZMJ_HZ", jz.get("ZSJZMJ"));
									
									m.put("GXLX_HZ", jz.get("GXLX"));
									m.put("GXZCD_HZ", jz.get("GXZCD"));
								}	
							}
							projTotalDataList.add(m);
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			return projTotalDataList;
		}
	
	
}
