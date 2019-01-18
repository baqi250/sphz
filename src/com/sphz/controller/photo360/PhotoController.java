package com.sphz.controller.photo360;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sphz.controller.base.BaseController;
import com.sphz.entity.Page;
import com.sphz.service.photo360.impl.PhotoServiceImpl;
import com.sphz.util.AppUtil;
import com.sphz.util.PageData;

@Controller
@RequestMapping("/photos")
public class PhotoController extends BaseController {
	@Resource(name="photoService")
	PhotoServiceImpl photoServiceImpl;
	
	@ResponseBody
	@RequestMapping("/pointslist")
	public Object getPoint(Page page){
		Map<String,List> map  = new HashMap<String, List>();
		PageData pd = new PageData();
		page.setPd(pd);
		try {
			List<PageData> list = photoServiceImpl.listData(page);
			map.put("photos", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return AppUtil.returnObject(pd, map);
	}
}