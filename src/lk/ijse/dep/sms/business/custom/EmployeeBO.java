package lk.ijse.dep.sms.business.custom;

import lk.ijse.dep.sms.business.SuperBO;
import lk.ijse.dep.sms.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeBO extends SuperBO {

    List<EmployeeDTO> findAllEmployees(String text) throws Exception;
    EmployeeDTO findEmployee(String employeeId) throws Exception;
    boolean saveEmployee(EmployeeDTO employee) throws Exception;
    boolean updateEmployee(EmployeeDTO employee) throws Exception;
    boolean deleteEmployee(String employeeId) throws Exception;
    String getLastId() throws Exception;
    List<String> employeesNotUsers() throws Exception;

    String getEmployeeId(String selectedEmployee) throws Exception;
}
