package com.sphz.controller.ureport;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.bstek.ureport.definition.datasource.BuildinDatasource;

@Component("sphzBuildinDataSource")
public class SphzBuildinDataSource implements BuildinDatasource {
	@Resource(name="dataSource")
	private DataSource datasource;
	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		try {
			return datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "规划审批汇总数据源";
	}
	
	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

}
