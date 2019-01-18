package com.sphz.service.photo360;

import java.util.List;

import com.sphz.entity.Page;
import com.sphz.util.PageData;

public interface PhotoService {
	public List<PageData> listData(Page page)throws Exception;
}
