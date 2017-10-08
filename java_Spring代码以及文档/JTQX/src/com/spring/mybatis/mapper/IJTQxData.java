package com.spring.mybatis.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.mybatis.pojo.JTBean;
import com.spring.mybatis.pojo.ViewJtcawsdata;

@Service("iJTQxData")
public interface IJTQxData {
	
	public abstract List<JTBean> FindChangezhanDatas();
	public abstract List<ViewJtcawsdata> FindChangezhanDatas1501();
}
