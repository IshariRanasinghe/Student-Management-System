package lk.ijse.dep.sms.dao.custom;

import lk.ijse.dep.sms.dao.CrudDAO;
import lk.ijse.dep.sms.dao.SuperDAO;
import lk.ijse.dep.sms.entity.Employee;

public interface EmployeeDAO extends CrudDAO<Employee,String> {

    String getLastEmployeeId() throws Exception;

    String getEmployeeId(String selectedEmployee) throws Exception;
}
