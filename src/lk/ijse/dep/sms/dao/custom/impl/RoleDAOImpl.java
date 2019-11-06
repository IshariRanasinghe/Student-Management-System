package lk.ijse.dep.sms.dao.custom.impl;

import lk.ijse.dep.sms.dao.CrudUtil;
import lk.ijse.dep.sms.dao.custom.RoleDAO;
import lk.ijse.dep.sms.entity.Role;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {
    @Override
    public List<Role> findAll() throws Exception {
        ResultSet rs = CrudUtil.execute("SELECT * FROM role");
        List<Role> roles = new ArrayList<>();
        while(rs.next()){
            roles.add(new Role(rs.getInt(1),rs.getString(2)));
        }
        return roles;
    }

    @Override
    public Role find(Integer integer) throws Exception {
        return null;
    }

    @Override
    public boolean save(Role entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Role entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        return false;
    }

    @Override
    public List<String> rolesWithoutAdmin() throws Exception {
        ResultSet rs = CrudUtil.execute("SELECT * FROM role WHERE role.role != 'Admin'");
        List<String> roles = new ArrayList<>();
        while(rs.next()){
            roles.add(rs.getString("role"));
        }
        return roles;
    }

    @Override
    public Integer getRoleId(String selectedRole) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT id FROM role WHERE role.role =?", selectedRole);
        if (rst.next()){
            return rst.getInt(1);
        }
        return null;
    }
}
