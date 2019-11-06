package lk.ijse.dep.sms.business.custom;

import lk.ijse.dep.sms.business.SuperBO;
import lk.ijse.dep.sms.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {

    List<StudentDTO> findAllStudents(String text) throws Exception;

    StudentDTO find(String studentId) throws Exception;

    boolean saveStudent(StudentDTO student) throws Exception;

    boolean updateStudent(StudentDTO student) throws Exception;

    boolean deleteStudent(String studentId) throws Exception;

    String getLastId() throws Exception;
}
