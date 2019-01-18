package com.sphz.service.photo360.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sphz.dao.DaoSupport;
import com.sphz.entity.Page;
import com.sphz.service.photo360.PhotoService;
import com.sphz.util.PageData;

@Service("photoService")
public class PhotoServiceImpl implements PhotoService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> listData(Page page) throws Exception {
		return (List<PageData>)dao.findForList("PhotosMapper.datalistPage", page);
	}

}
