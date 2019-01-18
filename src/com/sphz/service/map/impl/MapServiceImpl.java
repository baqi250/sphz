package com.sphz.service.map.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sphz.dao.DaoSupport;
import com.sphz.entity.Page;
import com.sphz.service.map.MapService;
import com.sphz.util.PageData;

@Service("mapService")
public class MapServiceImpl implements MapService {
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Override
	public List<PageData> listData(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("MapMapper.listData", pd);
	}

}
