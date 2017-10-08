package com.spring.demo4;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhuxinquan on 17-4-27.
 * propagation  :事物的传播行为
 * isolation    :事物的隔离级别
 * readOnly     :只读
 * rollbackFor  :发生哪些异常回滚
 */
@Transactional(propagation = Propagation.REQUIRED)
public class AccountServiceImp implements AccountService {

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void transfer(String out, String in, Double money) {
        accountDao.outMoney(out, money);
        int i = 1 / 0;
        accountDao.inMoney(in, money);
    }
}
