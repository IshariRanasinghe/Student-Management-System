package lk.ijse.dep.sms.business.custom;

import lk.ijse.dep.sms.business.SuperBO;
import lk.ijse.dep.sms.dto.UserDTO;
import lk.ijse.dep.sms.dto.UserDTO2;
import lk.ijse.dep.sms.dto.UserDTO3;

import java.util.List;

public interface UserBO extends SuperBO {

    UserDTO find(String userId) throws Exception;
    boolean saveUser(UserDTO user) throws Exception;
    boolean updateUser(UserDTO user) throws Exception;
    boolean deleteUser(String userId) throws Exception;
    boolean configureUser(UserDTO user) throws Exception;
    String isUser(UserDTO user) throws Exception;
    List<String> getUserRoles(String userId) throws Exception;
    public String getLastId() throws Exception;
    List<UserDTO2> usersList(String text) throws Exception;
    boolean createUserAccount(UserDTO3 userDTO) throws Exception;
}
