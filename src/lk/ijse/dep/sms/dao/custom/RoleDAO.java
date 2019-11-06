package lk.ijse.dep.sms.dao.custom;

import lk.ijse.dep.sms.dao.CrudDAO;
import lk.ijse.dep.sms.entity.Role;

import java.util.List;

public interface RoleDAO extends CrudDAO<Role,Integer> {

 List<String> rolesWithoutAdmin() throws Exception;

    Integer getRoleId(String selectedRole) throws Exception;
}
