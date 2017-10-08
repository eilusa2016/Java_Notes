package web.data.dao;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;

import com.spring.mybatis.mapper.IJTQxData;
import com.spring.mybatis.pojo.JTBean;
import com.spring.mybatis.pojo.ViewJtcawsdata;
import com.spring.tools.XTool;


public class DataDao {
	private final  static Log log=LogFactory.getLog(DataDao.class); 
	@Autowired
	private XTool xTool;
	@Autowired
	private IJTQxData iJTQxData; 
	
	public String  getChangZhanS(){
		JSONObject ob=new JSONObject();
		List<JTBean> list=null;
		List<ViewJtcawsdata> listextr=null;
		try {
			list=iJTQxData.FindChangezhanDatas();
			listextr=iJTQxData.FindChangezhanDatas1501();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("DataDao:getChangZhanS"+e.getMessage(), e);
			list=new ArrayList<JTBean>();
			e.printStackTrace();
		}
		if(list.size()<=0){
			ob.put("mess", "接口未查询得到任何数据");
		}else{
			JSONArray obarr=xTool.ObjectListConvertToJsonarray(list);
			JSONArray obarr2=xTool.ObjectListConvertToJsonarray(listextr);
			obarr.addAll(obarr2);
			ob.put("jtqx", obarr);
			ob.put("size", obarr.size());
		}
		return ob.toString();
	}
	
	
	
	
	
}
