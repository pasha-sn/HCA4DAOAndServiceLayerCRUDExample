package com.hibernateinfo.client;

import com.hibernateinfo.entities.Employee;
import com.hibernateinfo.service.EmployeeService;
import com.hibernateinfo.service.impl.EmployeeServiceImpl;

/**
 * @author Pasha
 *
 */
public class ClientTest7RetrieveEmployeeUsingServiceLayer {

	public static void main(String[] args) 
	{
		EmployeeService employeeService = new EmployeeServiceImpl();
		getEmployeeById(employeeService);
		
	}

	private static void getEmployeeById(EmployeeService employeeService) {
		Employee employee = employeeService.getEmployeeById(29);
		System.out.println(employee);
		}	
}
/*
<property name="hibernate.hbm2ddl.auto">create</property>
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
Employee [employeeId=29, employeeName=Pasha Sadi, email=pasha@hibernate.info, doj=2019-08-16 10:00:00.000, salary=65000.0]
*/	