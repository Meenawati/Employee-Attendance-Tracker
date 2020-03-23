package com.divinisoft.project;

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
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.divinisoft.project.hibernate.entity.EmployeeDTO;
import com.divinisoft.project.hibernate.entity.VacationDetailDTO;
import com.divinisoft.project.hibernate.entity.VacationTypeDTO;
import com.divinisoft.project.repository.EmployeeRepository;

public class AppTest {
	private static SessionFactory factory;

	@Autowired
	EmployeeRepository employeeRepo;

	@BeforeClass
	public static void hibernateSetup() {
		try {
			factory = new Configuration().configure("hibernate.cnfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			System.out.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	@Test
	public void testApplication() throws ParseException {
		Session session = factory.openSession();
		Transaction tsTransaction = null;
		try {
			tsTransaction = session.beginTransaction();

			VacationTypeDTO v1 = new VacationTypeDTO("Vacation", 10);
			VacationTypeDTO v2 = new VacationTypeDTO("Sick Leave", 10);

			session.save(v1);
			session.save(v2);

			VacationDetailDTO vacationDetail1 = new VacationDetailDTO();
			vacationDetail1.setVacationType(v1);
			String dateString1 = "18/03/2020";
			Date dateVal1 = new SimpleDateFormat("dd/MM/yyyy").parse(dateString1);
			vacationDetail1.setDate(dateVal1);
			vacationDetail1.setVacationType(v1);

			VacationDetailDTO vacationDetail2 = new VacationDetailDTO();
			vacationDetail2.setVacationType(v2);
			String dateString2 = "19/03/2020";
			Date dateVal2 = new SimpleDateFormat("dd/MM/yyyy").parse(dateString2);
			vacationDetail2.setDate(dateVal2);
			vacationDetail2.setVacationType(v2);

			EmployeeDTO emp1 = new EmployeeDTO("John", "Android Developer");
			emp1.getVacationDetails().add(vacationDetail1);
			emp1.getVacationDetails().add(vacationDetail2);

			Integer id = (Integer) session.save(emp1);

			EmployeeDTO emp2 = session.get(EmployeeDTO.class, id);
			List<VacationDetailDTO> vDetails = emp2.getVacationDetails();
			VacationDetailDTO vd1 = vDetails.get(0);
			VacationDetailDTO vd2 = vDetails.get(1);

			assertEquals("John", emp2.getName());
			assertEquals("Android Developer", emp2.getDepartment());
			assertEquals(dateVal1, vd1.getDate());
			assertEquals(v1, vd1.getVacationType());
			assertEquals(dateVal2, vd2.getDate());
			assertEquals(v2, vd2.getVacationType());

			tsTransaction.commit();
		} catch (HibernateException e) {
			if (tsTransaction != null)
				;
			tsTransaction.rollback();

		} finally {
			session.close();
		}
	}
}
