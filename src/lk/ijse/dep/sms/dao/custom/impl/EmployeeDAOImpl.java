package lk.ijse.dep.sms.dao.custom.impl;

import lk.ijse.dep.sms.dao.CrudUtil;
import lk.ijse.dep.sms.dao.custom.EmployeeDAO;
import lk.ijse.dep.sms.dto.EmployeeDTO;
import lk.ijse.dep.sms.entity.Employee;
import lk.ijse.dep.sms.entity.Gender;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public String getLastEmployeeId() throws Exception {
        ResultSet rs = CrudUtil.execute("SELECT id FROM employee ORDER BY id DESC LIMIT 1");
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public String getEmployeeId(String selectedEmployee) throws Exception {
        ResultSet rs = CrudUtil.execute("SELECT id FROM employee WHERE employee.name=?",selectedEmployee);
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public List<Employee> findAll() throws Exception {
        ResultSet rs =CrudUtil.execute("SELECT * FROM employee");
        List<Employee> employees = new ArrayList<>();
        if(rs.next()){
            employees.add(new Employee(rs.getString(1),
                    rs.getString(2),
                    Gender.valueOf(rs.getString(3)),rs.getString(4),rs.getString(5),rs.getInt(6)));
        }
        return employees;
    }

    @Override
    public Employee find(String s) throws Exception {
        ResultSet rs = CrudUtil.execute("SELECT * FROM employee WHERE id=?", s);
        if (rs.next()) {
            return new Employee(rs.getString(1),
                    rs.getString(2),
                    Gender.valueOf(rs.getString(3)),rs.getString(4),rs.getString(5),rs.getInt(6));
        }
        return null;
    }

    @Override
    public boolean save(Employee entity) throws Exception {
        return CrudUtil.execute("INSERT INTO Employee VALUES (?,?,?,?,?,?)",
                entity.getId(), entity.getName(),entity.getGender().toString(),entity.getAddress(),entity.getContact(),entity.getDesignationId());
    }

    @Override
    public boolean update(Employee entity) throws Exception {
        return CrudUtil.execute("UPDATE Employee SET name=?, gender=?,address=?,contact=?,designationId=? WHERE id=?",
                entity.getName(),entity.getGender().toString(), entity.getAddress(), entity.getContact(),entity.getDesignationId(),entity.getId());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM Employee WHERE id=?", s);
    }
}
