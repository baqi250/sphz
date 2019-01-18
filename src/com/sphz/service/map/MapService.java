package com.sphz.service.map;

import java.util.List;

import com.sphz.entity.Page;
import com.sphz.util.PageData;

public interface MapService {
	public List<PageData> listData(PageData pd)throws Exception;
}
