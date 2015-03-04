package webapp.service;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.hamcrest.core.Is;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import webapp.exception.DeptNotFoundException;
import webapp.model.Dept;
import webapp.model.Emp;
import webapp.service.DeptInfoService;
import webapp.util.EmployeeDataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/webapp/spring/beans.xml")
public class DeptInfoServiceTest {

	static GenericXmlApplicationContext factory;	
	static Logger log = Logger.getLogger(DeptInfoServiceTest.class);
	
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
		
		log.info("classNmae=" + ds.getClassName() );
		log.info("url=" + ds.getUrl() );
		log.info("user=" + ds.getUser() );
		log.info("password=" + ds.getPassword() );
	}
	
	@Test
	public void testGetDeptInfo() {
		log.info("###@@@ testGetDeptInfo()");
		
		DeptInfoService service = factory.getBean(DeptInfoService.class);
		
		try {
			Dept dept = service.getDeptInfo(20);
			assertNotNull(dept);
			log.info("deptno=" + dept.getDeptno());
			log.info("dname=" + dept.getDname());
			log.info("loc=" + dept.getLoc());
		} catch (DeptNotFoundException e) {
			log.info("Dept Not Found...", e);
		}
		
	}
	
	@Test
	public void testGetDeptInfoWithEmps() {
		log.info("###@@@ testGetDeptInfoWithEmps()");
		
		DeptInfoService service = factory.getBean(DeptInfoService.class);
		
		try {
			Dept dept = service.getDeptInfoWithEmps(50);
			
			log.info(dept.getDeptno() + " " + dept.getDname());
			
			List<Emp> emps =  dept.getEmps();
			if (emps != null) {
				for (Emp e : emps) {
					log.info(e.getEmpno() + " " + e.getEname());
				}
			}
		} catch (DeptNotFoundException e) {
			log.info("Dept Not Found", e);
		}
		
	}
	
	
	

}



























