package lk.ijse.dep.sms.business.custom;

import lk.ijse.dep.sms.business.SuperBO;
import lk.ijse.dep.sms.dto.RoleDTO;

import java.util.List;

public interface RoleBO extends SuperBO {
    List<RoleDTO> getRoles() throws Exception;
    List<String> roleswithoutAdmin() throws Exception;


    Integer getRoleId(String selectedRole) throws Exception;
}
