package com.hibernateinfo.client;

import java.util.Date;

import com.hibernateinfo.entities.Employee;
import com.hibernateinfo.service.EmployeeService;
import com.hibernateinfo.service.impl.EmployeeServiceImpl;

/**
 * @author Pasha
 *
 */
public class ClientTest6CreateEmployeeUsingServiceLayer {

	public static void main(String[] args) 
	{
		EmployeeService employeeService = new EmployeeServiceImpl();
		createEmployee(employeeService);
		
	}

	private static void createEmployee(EmployeeService employeeService) {
		employeeService.createEmployee(getEmployee());
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
<property name="hibernate.hbm2ddl.auto">create</property>
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
Employee is created with Id: 29
Hibernate: 
    insert 
    into
        employee_table
        (date_of_join, email, employee_name, salary, employee_id) 
    values
        (?, ?, ?, ?, ?)
*/	