package com.sphz.service.textdata.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sphz.dao.DaoSupport;
import com.sphz.entity.Page;
import com.sphz.entity.system.Dictionaries;
import com.sphz.entity.system.Menu;
import com.sphz.entity.textdata.TextData;
import com.sphz.service.textdata.TextDataService;
import com.sphz.util.PageData;

@Service("textDataService")
public class TextDataServiceImpl implements TextDataService {
	
	@Resource(name="daoSupport")
	private DaoSupport dao;
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> listData(Page page) throws Exception {
		return (List<PageData>)dao.findForList("TextDataMapper.datalistPage", page);
	}
	@Override
	public void delete(PageData pd) throws Exception {
		dao.delete("TextDataMapper.delete", pd);
		
	}
	@Override
	public void deleteAll(String[] ArrayDATA_IDS) throws Exception {
		dao.delete("TextDataMapper.deleteAll", ArrayDATA_IDS);
		
	}
	@Override
	public List<TextData> listAllText(String parentId) throws Exception {
		List<TextData> textList = this.listSubTextByParentId(parentId);
		for(TextData text : textList){
			if(text.getISBOTTOMNODE().equals("0")){
				text.setTreeurl("text/listdetail.do?ID="+text.getID()+"&FTP_URL="+text.getFTP_URL()+"&ISBOTTOMNODE="+text.getISBOTTOMNODE());
			}else{
				text.setTreeurl("text/list.do?ID="+text.getID()+"&FTP_URL="+text.getFTP_URL()+"&ISBOTTOMNODE="+text.getISBOTTOMNODE());	
			}
			//text.setTreeurl("text/listdetail.do?ID="+text.getID()+"&FTP_URL="+text.getFTP_URL()+"&ISBOTTOMNODE="+text.getISBOTTOMNODE());	
			text.setSubText(this.listAllText(text.getID()));
			text.setTarget("treeFrame");
		}
		return textList;
	}
	@Override
	public List<TextData> listSubTextByParentId(String parentId) throws Exception {
		return (List<TextData>) dao.findForList("TextDataMapper.listSubTextByParentId", parentId);
	}
	@Override
	public PageData getTreeById(PageData pd) throws Exception {
		return (PageData) dao.findForObject("TextDataMapper.getTreeById", pd);
	}
	@Override
	public PageData findMaxId(PageData pd) throws Exception {
		return (PageData) dao.findForObject("TextDataMapper.findMaxId", pd);
	}
	@Override
	public void saveMenu(TextData text) throws Exception {
		dao.save("TextDataMapper.insertText", text);		
	}
	@Override
	public void deleteByPID(PageData pd) throws Exception {
		dao.delete("TextDataMapper.deleteByPID", pd);		
	}
	@Override
	public void edit(TextData text) throws Exception {
		dao.update("TextDataMapper.updateTree", text);
		
	}

}
