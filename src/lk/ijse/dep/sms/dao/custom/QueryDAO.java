package lk.ijse.dep.sms.dao.custom;

import lk.ijse.dep.sms.dao.SuperDAO;
import lk.ijse.dep.sms.entity.CustomEntity;

import java.util.List;

public interface QueryDAO extends SuperDAO {

    List<CustomEntity> getAllEmployees(String query) throws Exception;

    CustomEntity findEmployee(String employeeId) throws Exception;

    List<CustomEntity> getEmployeesTeachers() throws Exception;

    List<CustomEntity> getEmployeesNotUsers() throws Exception;

    List<String> getUserRoles(String userId) throws Exception;

    List<CustomEntity> usersList(String query) throws Exception;
}
