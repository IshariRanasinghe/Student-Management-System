package lk.ijse.dep.sms.dao.custom;

import lk.ijse.dep.sms.dao.CrudDAO;
import lk.ijse.dep.sms.dao.CrudUtil;
import lk.ijse.dep.sms.dao.SuperDAO;
import lk.ijse.dep.sms.entity.User;
import lk.ijse.dep.sms.entity.UserRole;
import lk.ijse.dep.sms.entity.UserRolePK;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public interface UserRoleDAO extends CrudDAO<UserRole, UserRolePK> {

}
