package lk.ijse.dep.sms.dao.custom;

import lk.ijse.dep.sms.dao.CrudDAO;
import lk.ijse.dep.sms.dao.SuperDAO;
import lk.ijse.dep.sms.dto.UserDTO;
import lk.ijse.dep.sms.entity.User;

public interface UserDAO extends CrudDAO<User,String> {

    String getLastUserId() throws Exception;

    String isUser(User user) throws Exception;


}
