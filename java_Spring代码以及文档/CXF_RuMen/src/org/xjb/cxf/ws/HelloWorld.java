package org.xjb.cxf.ws;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.xjb.cxf.ws.domain.Cat;
import org.xjb.cxf.ws.domain.User;
import org.xjb.cxf.ws.util.MapXMlAdapter;

@WebService
public interface HelloWorld {
    public java.lang.String sayHi(java.lang.String name);
    
    public List<Cat> getCatByUser(User user);
    /**
     * cxf不能处理map集合，所以用MapXMlAdapter来处理适配
     * @return
     */
    public @XmlJavaTypeAdapter(MapXMlAdapter.class)  Map<String, Cat>   getAllCats();
}
