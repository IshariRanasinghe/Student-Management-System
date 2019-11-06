package lk.ijse.dep.sms.business.custom.impl;

import lk.ijse.dep.sms.business.custom.StudentBO;
import lk.ijse.dep.sms.dao.DAOFactory;
import lk.ijse.dep.sms.dao.DAOTypes;
import lk.ijse.dep.sms.dao.custom.StudentDAO;
import lk.ijse.dep.sms.dto.StudentDTO;
import lk.ijse.dep.sms.dto.StudentDTO;
import lk.ijse.dep.sms.entity.CustomEntity;
import lk.ijse.dep.sms.entity.Student;
import lk.ijse.dep.sms.entity.Gender;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    private StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOTypes.STUDENT);
    
    @Override
    public List<StudentDTO> findAllStudents(String text) throws Exception {
        List<Student> allStudents = studentDAO.findAll(text);
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for(Student student:allStudents){
            studentDTOS.add(new StudentDTO(student.getId(),student.getName(),student.getAddress(),
                    student.getGuardian(),student.getContact()));
        }
        return studentDTOS;
    }

    @Override
    public StudentDTO find(String studentId) throws Exception {
        Student student = studentDAO.find(studentId);
        return new StudentDTO(student.getId(),student.getName(),student.getDobirth(),student.getGender(),student.getAddress(),
                student.getGuardian(), student.getContact());
    }

    @Override
    public boolean saveStudent(StudentDTO student) throws Exception {
        return studentDAO.save(new Student(student.getId(), student.getName(),student.getDobirth(),student.getGender(), student.getAddress(),
                student.getGuardian(), student.getContact()));
    }

    @Override
    public boolean updateStudent(StudentDTO student) throws Exception {
        return studentDAO.update(new Student(student.getId(), student.getName(),student.getDobirth(),student.getGender(), student.getAddress(),
                student.getGuardian(), student.getContact()));
    }

    @Override
    public boolean deleteStudent(String studentId) throws Exception {
        return studentDAO.delete(studentId);
    }

    @Override
    public String getLastId() throws Exception {
        return studentDAO.getLastId();
    }
}
