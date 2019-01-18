package com.sphz.controller.map;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.kabeja.dxf.DXFConstants;
import org.kabeja.dxf.DXFDocument;
import org.kabeja.dxf.DXFLWPolyline;
import org.kabeja.dxf.DXFLayer;
import org.kabeja.dxf.DXFVertex;
import org.kabeja.dxf.helpers.Point;
import org.kabeja.parser.Parser;
import org.kabeja.parser.ParserBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sphz.controller.base.BaseController;
import com.sphz.entity.Page;
import com.sphz.service.map.impl.MapServiceImpl;
import com.sphz.util.AppUtil;
import com.sphz.util.DateUtil;
import com.sphz.util.PageData;

@Controller
@RequestMapping("/map")
public class MapController extends BaseController {
	@Resource(name="mapService")
	MapServiceImpl mapServiceImpl;
	
	@RequestMapping("/showmap")
	public ModelAndView showmap(Page page){
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("map/map");
		return mv;
	}
	
	@RequestMapping("/show3d")
	public ModelAndView show3DMap(Page page){
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("map/3dmap");
		return mv;
	}
	
	@RequestMapping("/saveresults")
	public ModelAndView saveResults(Page page){
		/*PageData pd = new PageData(this.getRequest());
		String keywords = pd.getString("zd");*/
		
		ModelAndView mv = this.getModelAndView();		
		mv.setViewName("map/aa");
		return mv;
	}
	
	@RequestMapping("/addcad")
	public ModelAndView statics(Page page){
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("map/addcad");
		return mv;
	}
	
	@RequestMapping("/cadjoin")
	public ModelAndView compare(Page page){
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("map/cadjoin");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/maplist")
	public Object list(Page page){
		Map<String,List> map  = new HashMap<String, List>();
		PageData pd = new PageData();
		page.setPd(pd);
		try {
			List<PageData> list = mapServiceImpl.listData(pd);
			map.put("maps", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return AppUtil.returnObject(pd, map);
	}
	
	@RequestMapping("/proxy")
	public Object proxy(){
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("map/proxy");
		return mv;
	}
	
	/**cad转json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/convert")
	@ResponseBody
	public Object convert(HttpServletRequest request,
			@RequestParam(value="file",required=true) MultipartFile file) throws Exception{
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		//logBefore(logger, Jurisdiction.getUsername()+"新增notice");
		//ModelAndView mv = this.getModelAndView();
		//PageData pd = new PageData();
		//pd = this.getPageData();
		InputStream in = null;  
        in = file.getInputStream(); 
        //InputStream in = new FileInputStream("E:\\"+sourceFile);
	    // Parser dxfParser = DXFParserBuilder.createDefaultParser();
	    Parser dxfParser = ParserBuilder.createDefaultParser();
	    dxfParser.parse(in, "UTF-8");
	    DXFDocument doc = dxfParser.getDocument();
	    //DXFHeader dxfHeader = doc.getDXFHeader();
	    //dxfHeader.getVarialbeIterator();
	    Iterator dxfLayerIterator = doc.getDXFLayerIterator();
	    DXFLayer layer = (DXFLayer)dxfLayerIterator.next();
	    List<DXFLWPolyline> dxfEntities = layer.getDXFEntities(DXFConstants.ENTITY_TYPE_LWPOLYLINE);
	    
		
//		Record result = new Record();
//		Record r_rings = new Record();
//		List list = new ArrayList();
//		List list_childer1 = new ArrayList();
//		double[] d = new double[2];
//		d[0] = -115.3125;
//		d[1] = -37.96875;
//		double[] d1 = new double[2];
//		d1[0] = -115.3125;
//		d1[1] = -37.96875;
//		list_childer1.add(d);
//		list_childer1.add(d1);
//		list.add(list_childer1);
//		r_rings.set("rings", list);
//		result.set("geometry", r_rings);
//		renderJson("data", result);
	    Map<String,Object> result=new HashMap<String,Object>();
	    Map<String,Object> r_rings=new HashMap<String,Object>();
	    Map<String,Object> sr=new HashMap<String,Object>();
	    Map<String,Object> wk=new HashMap<String,Object>();
	    List list=new ArrayList();
	   
	    //double[]d=new double[2];
	    
	   // pd.put("data",result);
	    
	    for(int i=0;i<dxfEntities.size();i++){
	    	DXFLWPolyline dxflwPolyline = dxfEntities.get(i);
	    	int c= dxflwPolyline.getVertexCount();
	    	List list_children=new ArrayList();
	    	for(int j=0;j<c;j++){
	    		DXFVertex vertex = dxflwPolyline.getVertex(j);
	    		Point point = vertex.getPoint();
	    		double x = point.getX();
	    		double y = point.getY();
	    		double[]d=new double[2];
	    		d[0]=x;
	    		d[1]=y;
	    		list_children.add(d);
	    		System.out.println("第"+i+"个LWPOLYLINE的第"+j+"个节点的X坐标为："+x+";Y坐标为："+y);
	    	}
	    	
	    	DXFVertex vertex1 = dxflwPolyline.getVertex(0);
    		Point point1 = vertex1.getPoint();
    		double x1 = point1.getX();
    		double y1 = point1.getY();
    		double[]d1=new double[2];
    		d1[0]=x1;
    		d1[1]=y1;
    		list_children.add(d1);
	    	
	    	list.add(list_children);
	    }
	    r_rings.put("rings",list);
	    wk.put("wkid",3853);
	    r_rings.put("spatialReference",wk);
	    //sr.put("spatialReference",wk);
	    result.put("geometry",r_rings);
	    result.put("layerId",file.getOriginalFilename().split("\\.")[0]+new Date().getTime());
	    result.put("fileName",file.getOriginalFilename().split("\\.")[0]);
       // pd.put("geodata",result);
		//.setViewName("save_result");
		//return mv;
	    //map.put("list", pdList);
		//.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), result);
	}
}
