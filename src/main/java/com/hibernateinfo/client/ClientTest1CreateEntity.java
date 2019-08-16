package com.hibernateinfo.client;

import java.util.Date;

import org.hibernate.Session;

import com.hibernateinfo.entities.Employee;
import com.hibernateinfo.util.HibernateUtil;

/**
 * @author Pasha
 *
 */
public class ClientTest1CreateEntity {

	public static void main(String[] args) 
	{
		//Session object implements autoCloseable and -after java1.7- every object implements 
		//autoCloseable can be used as private resources in try catch block
		//you Don't need to close session in finally block 
		try(Session session = HibernateUtil.getSessionFactory().openSession()) 
		{
			createEmployee(session);			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void createEmployee(Session session) {
		session.beginTransaction();
		
		/*
		 save() -> This method is used to save an entity object into database and 
		 return generated primaryKey. Integer id = (Integer)session.save(employee);
		 It will throw an exception if an entity 
		 already exists in the database.
		 you can open declaration for more info.
		 */	 
		Integer id =(Integer) session.save(getEmployee());
		System.out.println("Employee is created  with Id::" + id);
		
		session.getTransaction().commit();
	}
	private static Employee getEmployee(){
		Employee employee= new Employee();
		employee.setEmployeeName("Pasha Sadi");
		employee.setEmail("pasha@hibernate.info");
		employee.setSalary(65000.00);
		employee.setDoj(new Date());
		return employee;
	}
}
/*
<property name="hibernate.hbm2ddl.auto">update</property>
Hibernate: 
    
    create table employee_table (
       employee_id number(10,0) not null,
        date_of_join timestamp,
        email varchar2(255 char),
        employee_name varchar2(100 char) not null,
        salary double precision,
        primary key (employee_id)
    )
Hibernate: 
    
    alter table employee_table 
       drop constraint UK_2casspobvavvi9s23312f9mhm
Hibernate: 
    
    alter table employee_table 
       add constraint UK_2casspobvavvi9s23312f9mhm unique (email)
Hibernate: 
    select
        hibernate_sequence.nextval 
    from
        dual
Employee is created  with Id::27
Hibernate: 
    insert 
    into
        employee_table
        (date_of_join, email, employee_name, salary, employee_id) 
    values
        (?, ?, ?, ?, ?)
*/	 

