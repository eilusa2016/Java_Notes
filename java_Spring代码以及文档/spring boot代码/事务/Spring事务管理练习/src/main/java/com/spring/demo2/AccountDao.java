package com.spring.demo2;

/**
 * Created by zhuxinquan on 17-4-27.
 */
public interface AccountDao {
    public void outMoney(String out, Double money);
    public void inMoney(String in, Double money);
}
