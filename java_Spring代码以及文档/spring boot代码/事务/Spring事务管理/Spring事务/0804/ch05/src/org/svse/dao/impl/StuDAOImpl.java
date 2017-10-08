package org.svse.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.svse.dao.StuDAO;
import org.svse.entity.StuInfo;

public class StuDAOImpl extends SimpleJdbcDaoSupport implements StuDAO {

	@Override
	public void addStu(StuInfo stu) {
		
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
