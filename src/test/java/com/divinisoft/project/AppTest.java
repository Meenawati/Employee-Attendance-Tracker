package com.divinisoft.project;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.divinisoft.project.hibernate.entity.Employee;
import com.divinisoft.project.hibernate.entity.VacationDetail;
import com.divinisoft.project.hibernate.entity.VacationType;

public class AppTest {
	private static SessionFactory factory;
	private Transaction tsTransaction;
	private Session session;
	private static VacationType v1, v2;
	private static Integer vt1;
	private static Integer vt2;
	private static Integer vt3;
	private static Integer vt4;

	@BeforeClass
	public static void hibernateSetup() {
		try {
			factory = new Configuration().configure("hibernate.cnfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			System.out.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		Session session2 = factory.openSession();
		Transaction tsTransaction2 = null;
		try {
			tsTransaction2 = session2.beginTransaction();

			VacationType v1 = new VacationType("Vacation", 10);
			VacationType v2 = new VacationType("Sick Leave", 10);
			VacationType v3 = new VacationType("Parental Leave", 5);
			VacationType v4 = new VacationType("Marriage Leave", 5);

			vt1 = (Integer) session2.save(v1);
			vt2 = (Integer) session2.save(v2);
			vt3 = (Integer) session2.save(v3);
			vt4 = (Integer) session2.save(v4);
			tsTransaction2.commit();
		} catch (HibernateException e) {
			if (tsTransaction2 != null)
				;
			tsTransaction2.rollback();

		} finally {
			session2.close();
		}
	}

	@Before
	public void Setup() {
		session = factory.openSession();
		tsTransaction = session.beginTransaction();
	}

	@Test
	public void testAddAndGetEmployee() {
		Employee emp1 = new Employee("Jhon", 23, "Android Developer", 20000.00);
		Integer id = (Integer) session.save(emp1);
		Employee emp2 = session.get(Employee.class, id);
		assertEquals("Jhon", emp2.getName());
		assertEquals(23, emp2.getAge());
		assertEquals("Android Developer", emp2.getDepartment());
		assertEquals(20000.00, emp2.getSalary());
	}

	@Test
	public void testAddAndGetVacations() throws ParseException {
		Employee emp1 = new Employee("John", 23, "Android Developer", 20000.00);

		VacationDetail vacationDetail1 = new VacationDetail();
		vacationDetail1.setVacationType(v1);
		String dateString1 = "18/03/2020";
		Date dateVal1 = new SimpleDateFormat("dd/MM/yyyy").parse(dateString1);
		vacationDetail1.setDate(dateVal1);

		VacationDetail vacationDetail2 = new VacationDetail();
		vacationDetail2.setVacationType(v2);
		String dateString2 = "19/03/2020";
		Date dateVal2 = new SimpleDateFormat("dd/MM/yyyy").parse(dateString2);
		vacationDetail2.setDate(dateVal2);

		emp1.getVacationDetails().add(vacationDetail1);
		emp1.getVacationDetails().add(vacationDetail2);

		Integer id = (Integer) session.save(emp1);

		Employee emp2 = session.get(Employee.class, id);
		List<VacationDetail> vDetails = emp2.getVacationDetails();
		VacationDetail vd1 = vDetails.get(0);
		VacationDetail vd2 = vDetails.get(1);

		assertEquals("John", emp2.getName());
		assertEquals(23, emp2.getAge());
		assertEquals("Android Developer", emp2.getDepartment());
		assertEquals(20000.00, emp2.getSalary());
		assertEquals(dateVal1, vd1.getDate());
		assertEquals(v1, vd1.getVacationType());
		assertEquals(dateVal2, vd2.getDate());
		assertEquals(v2, vd2.getVacationType());
	}

	@Test
	public void testUpdateEmployee() {
		Employee emp1 = new Employee("Jhon", 23, "Android Developer", 20000.00);
		Integer id = (Integer) session.save(emp1);
		Employee emp2 = (Employee) session.get(Employee.class, id);
		assertEquals(23, emp2.getAge());

		emp1.setName("Jhon Rick");
		emp1.setAge(24);
		emp1.setDepartment("QA");
		emp1.setSalary(50000);

		Integer id2 = (Integer) session.save(emp1);

		Employee emp3 = session.get(Employee.class, id2);
		assertEquals("Jhon Rick", emp3.getName());
		assertEquals(24, emp3.getAge());
		assertEquals("QA", emp3.getDepartment());
		assertEquals(50000, emp3.getSalary());
	}

	@Test
	public void testDeleteEmployee() {
		Employee emp1 = new Employee("Jhon", 23, "Android Developer", 20000.00);
		Integer id = (Integer) session.save(emp1);
		Employee emp2 = (Employee) session.load(Employee.class, id);
		session.delete(emp2);

		assertNull(session.get(Employee.class, id));
	}

	@After
	public void tearDone() {
		if (tsTransaction != null) {
			tsTransaction.rollback();
		}
		if (session != null) {
			session.close();
		}
	}

	@AfterClass
	public static void deleteVacationType() {
		Session session3 = factory.openSession();
		Transaction tsTransaction = null;
		try {
			tsTransaction = session3.beginTransaction();

			VacationType v1 = (VacationType) session3.get(VacationType.class, vt1);
			session3.delete(v1);
			VacationType v2 = (VacationType) session3.get(VacationType.class, vt2);
			session3.delete(v2);
			VacationType v3 = (VacationType) session3.get(VacationType.class, vt3);
			session3.delete(v3);
			VacationType v4 = (VacationType) session3.get(VacationType.class, vt4);
			session3.delete(v4);

		} catch (HibernateException e) {
			if (tsTransaction != null)
				;
			tsTransaction.rollback();

		} finally {
			session3.close();
		}

	}
}
