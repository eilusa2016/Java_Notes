package org.svse.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.svse.dao.StuDAO;
import org.svse.entity.StuInfo;

public class JDBCStuDAOImpl implements StuDAO {

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void addStu(StuInfo stu) {
		String sql = "insert into stuinfo values(?,?,?)";
		this.jdbcTemplate.update(sql, new Object[] { stu.getStuname(),
				stu.getStuage(), stu.getStubirthday() });

	}

	@Override
	public void deleteStu(Integer stuid) {
		String sql = "delete from stuinfo where stuid=?";
		this.jdbcTemplate.update(sql, new Object[] { stuid });
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StuInfo> findAll() {

		String sql = "select * from stuinfo";
		RowMapper mapper = new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				StuInfo stu = new StuInfo();
				stu.setStuid(rs.getInt("stuid"));
				stu.setStuname(rs.getString("stuname"));
				stu.setStuage(rs.getInt("stuage"));
				stu.setStubirthday(rs.getDate("stubirthday"));

				return stu;
			}

		};

		return this.jdbcTemplate.query(sql, mapper);
	}

	@Override
	public StuInfo findById(Integer stuid) {

		String sql = "select * from stuinfo where stuid=?";

		return (StuInfo) this.jdbcTemplate.queryForObject(sql,
				new Object[] { stuid }, new RowMapper() {

					@Override
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						StuInfo stu = new StuInfo();
						stu.setStuid(rs.getInt("stuid"));
						stu.setStuname(rs.getString("stuname"));
						stu.setStuage(rs.getInt("stuage"));
						stu.setStubirthday(rs.getDate("stubirthday"));

						return stu;
					}

				});
	}

	@Override
	public void updateStu(StuInfo stu) {
		// TODO Auto-generated method stub

	}

}
