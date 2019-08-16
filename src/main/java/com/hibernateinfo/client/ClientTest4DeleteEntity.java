package com.hibernateinfo.client;

import org.hibernate.Session;

import com.hibernateinfo.entities.Employee;
import com.hibernateinfo.util.HibernateUtil;

/**
 * @author Pasha
 *
 */
public class ClientTest4DeleteEntity {

	public static void main(String[] args) 
	{
		//Session object implements autoCloseable and -after java1.7- every object implements 
		//autoCloseable can be used as private resources in try catch block
		//you Don't need to close session in finally block 
		try(Session session = HibernateUtil.getSessionFactory().openSession()) 
		{
			deleteEmployeeById(session);			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void deleteEmployeeById(Session session) 
	{
		Employee employee = session.get(Employee.class, 28);
		if(employee != null)
		{	
			session.beginTransaction();					
			session.delete(employee);
			session.getTransaction().commit();
		}else
		{
			System.out.println("Employee Does Not Exist In Database!");
		}	
	}
}
/*
<property name="hibernate.hbm2ddl.auto">update</property>
Hibernate: 
    select
        employee0_.employee_id as employee_id1_0_0_,
        employee0_.date_of_join as date_of_join2_0_0_,
        employee0_.email as email3_0_0_,
        employee0_.employee_name as employee_name4_0_0_,
        employee0_.salary as salary5_0_0_ 
    from
        employee_table employee0_ 
    where
        employee0_.employee_id=?
Hibernate: 
    delete 
    from
        employee_table 
    where
        employee_id=?
*/	 

