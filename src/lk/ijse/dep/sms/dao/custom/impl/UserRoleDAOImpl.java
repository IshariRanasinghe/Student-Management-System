package lk.ijse.dep.sms.dao.custom.impl;

import lk.ijse.dep.sms.dao.CrudUtil;
import lk.ijse.dep.sms.dao.custom.UserRoleDAO;
import lk.ijse.dep.sms.entity.UserRole;
import lk.ijse.dep.sms.entity.UserRolePK;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDAOImpl implements UserRoleDAO {

    @Override
    public List<UserRole> findAll() throws Exception {
        ResultSet rs = CrudUtil.execute("SELECT * FROM user_role");
        List<UserRole> userRoles =new ArrayList<>();
        while(rs.next()){
            userRoles.add(new UserRole(rs.getString(1),rs.getInt(2)));
        }
        return userRoles;
    }

    @Override
    public UserRole find(UserRolePK userRolePK) throws Exception {
        ResultSet rs = CrudUtil.execute("SELECT * FROM user_role WHERE userId=? AND roleId=?",userRolePK.getUserId(),userRolePK.getRoleId());
        if (rs.next()){
            return new UserRole(rs.getString(1),rs.getInt(2));
        }
        return null;
    }

    @Override
    public boolean save(UserRole entity) throws Exception {
        return CrudUtil.execute("INSERT into user_role VALUES (?,?)",entity.getUserRolePK().getUserId(),entity.getUserRolePK().getRoleId());
    }

    @Override
    public boolean update(UserRole entity) throws Exception {
        return false;
    }


    @Override
    public boolean delete(UserRolePK userRolePK) throws Exception {
        return CrudUtil.execute("DELETE FROM user_role WHERE userId=? AND roleId=?",userRolePK.getUserId(),userRolePK.getRoleId());
    }
}
