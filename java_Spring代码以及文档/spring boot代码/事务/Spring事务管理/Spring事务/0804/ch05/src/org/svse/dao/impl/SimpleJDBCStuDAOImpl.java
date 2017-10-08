package org.svse.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.svse.dao.StuDAO;
import org.svse.entity.StuInfo;

public class SimpleJDBCStuDAOImpl implements StuDAO {

	private SimpleJdbcTemplate simpleJdbcTemplate;
	 public void setDataSource(DataSource dataSource) {
		 	this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	 }
	
	
	@Override
	public void addStu(StuInfo stu) {

		String sql = "insert into stuinfo values(:stuname,:stuage,:stubirthday)";
		 SqlParameterSource sqlParameter = new BeanPropertySqlParameterSource(stu);
		 this.simpleJdbcTemplate.update(sql, sqlParameter);
		 
	}

	@Override
	public void deleteStu(Integer stuid) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<StuInfo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StuInfo findById(Integer stuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStu(StuInfo stu) {
		// TODO Auto-generated method stub

	}

}
