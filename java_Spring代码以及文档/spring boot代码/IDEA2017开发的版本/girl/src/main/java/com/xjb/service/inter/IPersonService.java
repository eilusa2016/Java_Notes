package com.xjb.service.inter;

import com.xjb.entity.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 第一个参数是 泛型的类型
 * 第二个参数是主键的类型
 * 这个接口不需要注解
 */
public interface IPersonService extends JpaRepository<Girl,Integer> {

}
