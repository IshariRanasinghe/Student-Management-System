package lk.ijse.dep.sms.dao.custom.impl;


import lk.ijse.dep.sms.dao.CrudUtil;
import lk.ijse.dep.sms.dao.custom.StudentDAO;
import lk.ijse.dep.sms.entity.Employee;
import lk.ijse.dep.sms.entity.Gender;
import lk.ijse.dep.sms.entity.Student;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public String getLastId() throws Exception {
        ResultSet rs = CrudUtil.execute("SELECT id FROM student ORDER BY id DESC LIMIT 1");
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public List<Student> findAll(String text) throws Exception {
        ResultSet rs =CrudUtil.execute("SELECT * FROM student WHERE id LIKE ? OR name LIKE ? OR " +
                "address LIKE ? OR guardian LIKE ? OR contact LIKE ? GROUP BY id",text,text,text,text,text);
        List<Student> students = new ArrayList<>();

        while(rs.next()){
            students.add(new Student(rs.getString(1),
                    rs.getString(2),rs.getDate(3),
                    Gender.valueOf(rs.getString(4)),rs.getString(5),
                    rs.getString(6),rs.getString(7)));
        }
        return students;
    }

    @Override
    public List<Student> findAll() throws Exception {
        return null;
    }

    @Override
    public Student find(String studentId) throws Exception {
        ResultSet rs = CrudUtil.execute("SELECT * FROM student WHERE id=?", studentId);
        if (rs.next()) {
            return new Student(rs.getString(1),
                    rs.getString(2),rs.getDate(3),
                    Gender.valueOf(rs.getString(4)),rs.getString(5),
                    rs.getString(6),rs.getString(7));
        }
        return null;
    }

    @Override
    public boolean save(Student student) throws Exception {
        return CrudUtil.execute("INSERT INTO student VALUES (?,?,?,?,?,?,?)",student.getId(),
                student.getName(),student.getDobirth(),student.getGender().toString(),student.getAddress(), student.getGuardian(),student.getContact());
    }

    @Override
    public boolean update(Student student) throws Exception {
        return CrudUtil.execute("UPDATE student SET name=?, dobirth=?, gender=?,address=?,guardian=?,contact=? WHERE id=?",
                student.getName(),student.getDobirth(),student.getGender().toString(),student.getAddress(), student.getGuardian(),student.getContact(),student.getId());
    }

    @Override
    public boolean delete(String studentId) throws Exception {
        return CrudUtil.execute("DELETE FROM student WHERE id=?", studentId);
    }
}
