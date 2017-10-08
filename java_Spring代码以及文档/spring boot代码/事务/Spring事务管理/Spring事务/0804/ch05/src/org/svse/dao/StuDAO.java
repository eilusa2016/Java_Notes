package org.svse.dao;

import java.util.List;

import org.svse.entity.StuInfo;

public interface StuDAO {

	
	
	public void addStu(StuInfo stu);
	
	
	public void updateStu(StuInfo stu);
	
	
	public void deleteStu(Integer stuid);
	
	public StuInfo findById(Integer stuid);
	
	
	public List<StuInfo> findAll();
	
	
	
	
	
	
	
}
