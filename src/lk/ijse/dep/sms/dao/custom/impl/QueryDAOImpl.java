package lk.ijse.dep.sms.dao.custom.impl;

import lk.ijse.dep.sms.dao.CrudUtil;
import lk.ijse.dep.sms.dao.custom.QueryDAO;
import lk.ijse.dep.sms.entity.CustomEntity;
import lk.ijse.dep.sms.entity.Employee;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public List<CustomEntity> getAllEmployees(String query) throws Exception{
        ResultSet rs = CrudUtil.execute("SELECT e.id,e.name,e.address,e.contact,r.role FROM employee e " +
                "INNER JOIN role r on e.designationId=r.id WHERE (e.id LIKE ? OR e.name LIKE ? OR e.address LIKE ? OR e.contact LIKE ? OR r.role LIKE ?) " +
                "AND r.role != 'Admin';", query, query, query, query, query);
        List<CustomEntity> allEmployees=new ArrayList<>();
        while (rs.next()){
            allEmployees.add(new CustomEntity(rs.getString(1),rs.getString(2),
                    rs.getString(3),rs.getString(4),rs.getString(5)));
        }
        return allEmployees;
    }

    @Override
    public CustomEntity findEmployee(String employeeId) throws Exception {
        ResultSet rs = CrudUtil.execute("SELECT e.id,e.name,e.gender,e.address,e.contact,r.role FROM employee e " +
                "INNER JOIN role r on e.designationId=r.id WHERE e.id=?",employeeId);
        while (rs.next()){
            return new CustomEntity(rs.getString(1),rs.getString(2),
                    rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
        }

        return null;
    }

    @Override
    public List<CustomEntity> getEmployeesTeachers() throws Exception {
        ResultSet rs =CrudUtil.execute("SELECT e.name FROM employee e,role r WHERE e.designationId=r.id AND r.role LIKE 'Teacher' ");
        List<CustomEntity> teachersList = new ArrayList<>();
        while (rs.next()){
            teachersList.add(new CustomEntity(rs.getString(1)));
        }
        return teachersList;
    }

    @Override
    public List<CustomEntity> getEmployeesNotUsers() throws Exception {
        ResultSet rs=CrudUtil.execute("SELECT e.name FROM employee e WHERE e.id NOT IN (SELECT u.empId FROM user u)");
        List<CustomEntity> employeesNotUsers = new ArrayList<>();
        while (rs.next()){
            employeesNotUsers.add(new CustomEntity(rs.getString(1)));
        }
        return employeesNotUsers;


    }

    @Override
    public List<String> getUserRoles(String userId) throws Exception {
        ResultSet rs=CrudUtil.execute("SELECT r.role FROM role r INNER JOIN user_role ur on r.id = ur.roleId " +
                "INNER JOIN user u on ur.userId = u.id WHERE u.id=?",userId);
        List<String> userRoles = new ArrayList<>();
        while (rs.next()){
            userRoles.add(rs.getString("role"));
        }
        return userRoles;
    }

    @Override
    public List<CustomEntity> usersList(String query) throws Exception {
        ResultSet rs = CrudUtil.execute("SELECT u.id,u.username,e.name,r.role FROM user u INNER JOIN employee e on u.empId = e.id " +
                "INNER JOIN user_role ur on u.id = ur.userId INNER JOIN role r on ur.roleId = r.id WHERE " +
                "u.id LIKE ? OR u.username LIKE ? OR e.name LIKE ? OR r.role LIKE ?;",query,query,query,query);
        List<CustomEntity> users = new ArrayList<>();
        while(rs.next()){
            users.add(new CustomEntity(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
        }
        return users;
    }
}
