package org.bongiorno.sdrss.repositories.resources

import org.bongiorno.sdrss.domain.resources.Employee
import org.springframework.data.repository.CrudRepository


interface EmployeeRepository : CrudRepository<Employee, Long>
