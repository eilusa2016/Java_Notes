package http.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;

public class TestMent {
	public static void main(String[] args) {
//		Map<String, String> param=new HashMap<String, String>();
//		param.put("a", "100");
		
		Tp t=new Tp("101", "aa", "1567888", "00000");
		Tp t2=new Tp("102", "bb", "1567888", "00000");
		List<Tp> list=Arrays.asList(t,t2);
		String value=JsonUtils.objectToJson(list);
		System.out.println(value);
		Map<String, String> param2=new HashMap<String, String>();
//		param2.put("json", value);
//		String ds=HttpClientUtil.doPost("http://192.168.1.116:7070/HyWebService/tobacco/cstomer", param2);
		String ds=HttpClientUtil.doPostJson("http://192.168.1.116:7070/HyWebService/tobacco/cstomer",value);
		System.out.println(ds);
	}
}
