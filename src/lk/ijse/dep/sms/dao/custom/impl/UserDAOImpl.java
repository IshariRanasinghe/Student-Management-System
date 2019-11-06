package lk.ijse.dep.sms.dao.custom.impl;

import lk.ijse.dep.sms.dao.CrudUtil;
import lk.ijse.dep.sms.dao.custom.UserDAO;
import lk.ijse.dep.sms.dto.UserDTO;
import lk.ijse.dep.sms.entity.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {


    @Override
    public String getLastUserId() throws Exception {
        ResultSet rs = CrudUtil.execute("SELECT id FROM user ORDER BY id DESC LIMIT 1");
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public String isUser(User user) throws Exception {
        ResultSet rs = CrudUtil.execute("SELECT * FROM user WHERE username=? AND password = md5(?)", user.getUsername(), user.getPassword());
        if(rs.next()){
            return rs.getString("id");
        }else {return null;}
    }

    public List<User> findAll() throws Exception {
        ResultSet rs =CrudUtil.execute("SELECT * FROM user");
        List<User> entitys = new ArrayList<>();
        if(rs.next()){
            /*entitys.add(new User(rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6)));*/
        }
        return entitys;
    }

    public User find(String id) throws Exception {
        ResultSet rs = CrudUtil.execute("SELECT * FROM user WHERE id=?", id);
        if (rs.next()) {
           return new User(rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),rs.getString(4));
        }
        return null;
    }

    public boolean save(User entity) throws Exception {
        return CrudUtil.execute("INSERT INTO User VALUES (?,?,md5(?),?)",
                entity.getId(), entity.getUsername(),entity.getPassword(),entity.getEmpId());
    }

    public boolean update(User entity) throws Exception {
        return CrudUtil.execute("UPDATE User SET id=?, username=?,password=?,empId=?WHERE id=?",
                entity.getId(), entity.getUsername(),entity.getPassword(),entity.getEmpId());
    }

    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM User WHERE id=?", id);
    }
}
