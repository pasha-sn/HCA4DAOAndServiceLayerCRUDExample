package com.hibernateinfo.client;

import org.hibernate.Session;

import com.hibernateinfo.entities.Employee;
import com.hibernateinfo.util.HibernateUtil;

/**
 * @author Pasha
 *
 */
public class ClientTest5DirtyCheckingMechanism {

	public static void main(String[] args) 
	{
		Session session = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Employee employee = session.get(Employee.class, 27);
			if(employee != null) {
				session.beginTransaction();
				
				//https://stackoverflow.com/questions/5268466/how-does-hibernate-detect-dirty-state-of-an-entity-object
				//with dirty checking, hibernate will update employee state changes
				//while the changes has been made within transaction boundary 
				//which is salary in this case
				//salary of employee will be updated even if session's update method not used
				employee.setSalary(98765d);
				//session.update(employee);
				
				session.getTransaction().commit();
			}else {
				System.out.println("Employee Does Not Exist In Database!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session !=null) {
				session.close();
			}
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
    update
        employee_table 
    set
        salary=? 
    where
        employee_id=?
*/	 

