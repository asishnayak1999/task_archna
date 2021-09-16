package com.fisglobal.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.util.List;
import com.fisglobal.employee.model.Employee;
import com.fisglobal.employee.repository.EmployeeRepository;

@SpringBootApplication
public class EmployeeApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =  SpringApplication.run(EmployeeApplication.class, args);
		EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
		
		Employee emp = new Employee("I01","Asish","Nayak","Asish1999","Bangalore","asish@gmail.com","7077548505","www.linkedin/asish1234");
		employeeRepository.save(emp);
		context.close();
		
		
		
	}

}
