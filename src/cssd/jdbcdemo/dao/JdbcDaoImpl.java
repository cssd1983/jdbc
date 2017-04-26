package cssd.jdbcdemo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;

import cssd.jdbcdemo.model.Circle;
@Component
public class JdbcDaoImpl {
	private DataSource datasource;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private SimpleJdbcTemplate simpleJdbcTemplate;
	/*public Circle getCircle(int circleId){

		Connection conn = null;
		try {

			String driver="org.apache.derby.jdbc.ClientDriver";
			Class.forName(driver).newInstance();
			conn=datasource.getConnection();
			PreparedStatement ps=conn.prepareStatement("select * from circle where id=?");
			ps.setInt(1,circleId);
			Circle circle=null;
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{circle=new Circle(circleId,rs.getString("name"));
			}
			rs.close();
			ps.close();
			return circle;
		}
		catch(Exception e)
		{throw new RuntimeException(e);
		}
		finally{
			try{
				conn.close();
			}
			catch(SQLException e){}
		}
	}	
	*/
	public int getCircleCount()
	{
		String sql="select count(*) from Circle";
		//jdbcTemplate.setDataSource(getDatasource());
		return jdbcTemplate.queryForInt(sql);
	}
	public String getCircleName(int circleId){
		String sql="SELECT NAME FROM CIRCLE WHERE ID=?";
		//jdbcTemplate.queryForObject(sql, String.class);
		return jdbcTemplate.queryForObject(sql,new Object[]{circleId},String.class);
	}
	public Circle getCircleForId(int circleId){
		String sql="SELECT * FROM CIRCLE WHERE ID=?";
		//jdbcTemplate.queryForObject(sql, String.class);
		return jdbcTemplate.queryForObject(sql,new Object[] {circleId}, new CircleMapper());
	}
	
	
	public List<Circle> getAllCircles(){
		String sql="select *from Circle";
		return  jdbcTemplate.query(sql,new CircleMapper());
	}
	public DataSource getDatasource() {
		return datasource;
	}
	@Autowired
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate=new JdbcTemplate(datasource);
		this.namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(datasource);
	}
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	/*public void insertCircle(Circle circle)
	{
		String sql="INSERT INTO CIRCLE(ID,NAME)VALUES(?,?)";
	jdbcTemplate.update(sql,new Object[]{circle.getId(),circle.getName()});
	}*/
	public void insertCircle(Circle circle)
	{
		String sql="INSERT INTO CIRCLE(ID,NAME)VALUES(:id,:name)";
	//jdbcTemplate.update(sql,new Object[]{circle.getId(),circle.getName()});
	SqlParameterSource namedParameters=new MapSqlParameterSource("id",circle.getId()).addValue("name", circle.getName());
	namedParameterJdbcTemplate.update(sql, namedParameters);
	}
	public void createTriangleTable()
	{
		String sql="CREATE TABLE TRIANGLE(ID INTEGER,NAME VARCHAR(50))";
		jdbcTemplate.execute(sql);
	}
	public static final class CircleMapper implements RowMapper<Circle>{

		@Override
		public Circle mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Circle circle=new Circle();
			circle.setId(resultSet.getInt("ID"));
			circle.setName(resultSet.getString("Name"));
			return circle;
			
		}
	
	}
}

