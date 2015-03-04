package webapp.dao;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import webapp.exception.DeptNotFoundException;
import webapp.model.Dept;
import webapp.service.DeptInfoService;
import webapp.util.EmployeeDataSource;

public class DeptDaoTest {
	
	static GenericXmlApplicationContext factory;
	static Logger log = Logger.getLogger(DeptDaoTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.info("### setUpBeforeClass()");
		factory = new GenericXmlApplicationContext("classpath:/webapp/resource/beans.xml");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		log.info("### tearDownAfterClass()");
		factory.close();
	}

	@Test
	public void testEmployeeDataSource() {
		log.info("###@@@ testEmployeeDataSource()");
		
//		EmployeeDataSource ds = factory.getBean(EmployeeDataSource.class);
//		EmployeeDataSource ds = (EmployeeDataSource)factory.getBean("dataSource");
		EmployeeDataSource ds = factory.getBean("dataSource", EmployeeDataSource.class);
		
		assertNotNull(ds.getClassName());
		assertNotNull(ds.getUrl());
		assertNotNull(ds.getUser());
		assertNotNull(ds.getPassword());
		
		log.info("className = " + ds.getClassName());
		log.info("url = " + ds.getUrl());
		log.info("user = " + ds.getUser());
		log.info("password = " + ds.getPassword());
	}
	
	@Test
	public void testDeptInfoService()  {
		log.info("###@@@ testDeptInfoService()");
		
		DeptInfoService service = factory.getBean(DeptInfoService.class);
		
		try{
			Dept dept = service.getDeptInfo(200);
			assertNotNull(dept);
			log.info("deptno = " + dept.getDeptno());
			log.info("dname = " + dept.getDname());
			log.info("loc = " + dept.getLoc());
		} catch (DeptNotFoundException e) {
			log.info("Dept Not Found...");
		}
		
	}

}

















