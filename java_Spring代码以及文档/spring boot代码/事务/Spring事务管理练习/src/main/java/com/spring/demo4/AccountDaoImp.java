package com.spring.demo4;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Created by zhuxinquan on 17-4-27.
 */
public class AccountDaoImp extends JdbcDaoSupport implements AccountDao {
    public void outMoney(String out, Double money) {
        String sql = "update account set money = money - ? where name = ?";
        this.getJdbcTemplate().update(sql, money, out);
    }

    public void inMoney(String in, Double money) {
        String sql = "update account set money = money + ? where name = ?";
        this.getJdbcTemplate().update(sql, money, in);
    }
}
