package com.xjb.controller;

import com.xjb.entity.Girl;
import com.xjb.entity.ResultMsg;
import com.xjb.exhandler.PersonException;
import com.xjb.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    private List<Girl> list;

    @Autowired
    private PersonService personService;

    /**
     * 返回一个列表
     *
     * @return
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public List<Girl> getGirlList() {
        list = personService.getallgirls();
        if (list == null) {
            list = new ArrayList<Girl>();
        }
        return list;
    }


    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public List<Girl> addGirlList() throws Exception {
        list = new ArrayList<Girl>();
        for (int i = 0; i < 20; i++) {
            Girl g = new Girl();
            g.setAge(20);
            g.setCupSize(i * 2 + "");
            list.add(g);
        }

        return personService.saveGirls(list);
    }

    /**
     *  增加一个对象
     *   @Valid 注解表示  对于这个对象进行验证
     *          被验证的对象需要在字段上添加验证注解
     * @param girl
     * @param result  验证的结果
     * @return
     */
    @PostMapping(value = "/addgirl")
    public ResultMsg<Girl> addGirlList(@Valid  Girl girl, BindingResult result) throws Exception {
        ResultMsg<Girl> resMsg= new ResultMsg<>();
        if(girl.getAge()<15){

            throw new Exception("自定义的年龄太小的异常");
        }
        if(result.hasErrors()){

           // throw new Exception(result.getFieldError().getDefaultMessage());
            resMsg.setMessage(result.getFieldError().getDefaultMessage());
            resMsg.setList(new ArrayList<Girl>());
          // System.out.print( result.getFieldError().getDefaultMessage());
            return resMsg;
        }
        list = new ArrayList<Girl>();
        list.add(girl);

        resMsg.setMessage("加入成功");
        resMsg.setList(personService.saveGirls(list));
        return resMsg;
    }


    @PostMapping(value = "/tex")
    public ResultMsg<Girl>  Test(@Valid  Girl girl, BindingResult result) throws PersonException {
        if(girl.getAge()<15){
            throw new PersonException("小学异常",100);

        }else if(girl.getAge()>=15&&girl.getAge()<18){
            throw new PersonException("中学异常",110);
        }
        return  null;
    }


}
