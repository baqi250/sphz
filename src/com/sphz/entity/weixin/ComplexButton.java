package com.sphz.entity.weixin;

/** 一级菜单
	@author DK
 * 2016.11.1
 */
public class ComplexButton {
	
	private String name;			//菜单名称
	private Button[] sub_button;	//子级菜单
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Button[] getSub_button() {
		return sub_button;
	}
	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
}