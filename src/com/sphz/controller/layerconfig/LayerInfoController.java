package com.sphz.controller.layerconfig;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sphz.controller.base.BaseController;
import com.sphz.util.AppUtil;
import com.sphz.util.Const;
import com.sphz.util.PageData;
import com.sphz.util.Tools;

@Controller
@RequestMapping("/layer")
public class LayerInfoController extends BaseController {
	
	@ResponseBody
	@RequestMapping("/layerinfos")
	public Object getConfig(){
		Map<String,String> map  = new HashMap<String, String>();
		PageData pd = new PageData();
		map.put("layerinfos", Tools.readTxtFileAll(Const.LayerInfo, "utf-8"));
		return AppUtil.returnObject(pd, map);
	}
}
