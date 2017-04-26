package cssd.jdbcdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cssd.jdbcdemo.dao.JdbcDaoImpl;
import cssd.jdbcdemo.dao.SimpleJdbcDaoImpl;
import cssd.jdbcdemo.model.Circle;

public class JdbcDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

ApplicationContext ctx=new ClassPathXmlApplicationContext("spring.xml");
SimpleJdbcDaoImpl dao= ctx.getBean("simpleJdbcDaoImpl",SimpleJdbcDaoImpl.class);
//Circle circle=dao.getCircle(1);
//System.out.println(circle.getName());
//dao.insertCircle(new Circle(5,"fifth Circle"));
System.out.println(dao.getCircleCount());
//dao.createTriangleTable();
}

}
