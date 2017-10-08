package com.xjb.service;

import com.xjb.entity.Girl;
import com.xjb.service.inter.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 *
 */
@Service
public class PersonService {

    @Autowired
    private IPersonService iPersonService;

    public List<Girl> getallgirls(){
        return iPersonService.findAll();
    }

    @Transactional//事务注解
    public List<Girl>  saveGirls(List<Girl> list) throws Exception{

        Iterable<Girl> it=new Iterable<Girl>() {
            @Override
            public Iterator<Girl> iterator() {
                return list.iterator();
            }
        };
       iPersonService.save(it);
       return list;
    }

    /**
     * 删除的操作
     * @param id
     */
    public void  RemoveGir(int id){
        iPersonService.delete(id);
    }

}
