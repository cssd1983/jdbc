package cssd.jdbcdemo.dao;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class SimpleJdbcDaoImpl extends SimpleJdbcDaoSupport {
	public int getCircleCount()
	{
		String sql="select count(*) from Circle";
		//jdbcTemplate.setDataSource(getDatasource());
		return this.getJdbcTemplate().queryForInt(sql);
	}


}
