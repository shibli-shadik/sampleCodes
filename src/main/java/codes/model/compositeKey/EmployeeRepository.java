package codes.model.compositeKey;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, EmployeeId> 
{
    List<Employee> findByEmployeeIdDepartmentId(Long departmentId);
}
