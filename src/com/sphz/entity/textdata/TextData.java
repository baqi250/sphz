package com.sphz.entity.textdata;

import java.util.List;

import com.sphz.entity.system.Dictionaries;

public class TextData {

	private String ID;
	private String PROFESSION_TYPE;
	private String DATA_TYPE;
	private String DATA_SOURCE;
	private String IN_TIME;
	private String COMPANY;
	private String PRINT_TIME;
	private String NAME;
	private String FTP_URL;
	private List<TextData> subText;
	private String treeurl;
	private String ISBOTTOMNODE;
	private String target;
	private boolean hasText = false;
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}

	
	public String getISBOTTOMNODE() {
		return ISBOTTOMNODE;
	}
	public void setISBOTTOMNODE(String iSBOTTOMNODE) {
		ISBOTTOMNODE = iSBOTTOMNODE;
	}
	public String getFTP_URL() {
		return FTP_URL;
	}
	public void setFTP_URL(String fTP_URL) {
		FTP_URL = fTP_URL;
	}
	public String getTreeurl() {
		return treeurl;
	}
	public void setTreeurl(String treeurl) {
		this.treeurl = treeurl;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public List<TextData> getSubText() {
		return subText;
	}
	public void setSubText(List<TextData> subText) {
		this.subText = subText;
	}
	private String PID;
	private int SORT;
	
	public boolean isHasText() {
		return hasText;
	}
	public void setHasText(boolean hasText) {
		this.hasText = hasText;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPROFESSION_TYPE() {
		return PROFESSION_TYPE;
	}
	public void setPROFESSION_TYPE(String pROFESSION_TYPE) {
		PROFESSION_TYPE = pROFESSION_TYPE;
	}
	public String getDATA_TYPE() {
		return DATA_TYPE;
	}
	public void setDATA_TYPE(String dATA_TYPE) {
		DATA_TYPE = dATA_TYPE;
	}
	public String getDATA_SOURCE() {
		return DATA_SOURCE;
	}
	public void setDATA_SOURCE(String dATA_SOURCE) {
		DATA_SOURCE = dATA_SOURCE;
	}
	public String getIN_TIME() {
		return IN_TIME;
	}
	public void setIN_TIME(String iN_TIME) {
		IN_TIME = iN_TIME;
	}
	public String getCOMPANY() {
		return COMPANY;
	}
	public void setCOMPANY(String cOMPANY) {
		COMPANY = cOMPANY;
	}
	public String getPRINT_TIME() {
		return PRINT_TIME;
	}
	public void setPRINT_TIME(String pRINT_TIME) {
		PRINT_TIME = pRINT_TIME;
	}
	public String getPID() {
		return PID;
	}
	public void setPID(String pID) {
		PID = pID;
	}
	public int getSORT() {
		return SORT;
	}
	public void setSORT(int sORT) {
		SORT = sORT;
	}


}
