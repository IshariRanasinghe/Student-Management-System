package lk.ijse.dep.sms.dao.custom.impl;


import lk.ijse.dep.sms.dao.custom.EmployeeDAO;
import lk.ijse.dep.sms.entity.Employee;
import lk.ijse.dep.sms.entity.Gender;

class EmployeeDAOImplTest {
    public static void main(String[] args) throws Exception {
        EmployeeDAOImplTest daoImplTest = new EmployeeDAOImplTest();
        daoImplTest.save();
    }

    void save() throws Exception {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        Employee employee= new Employee("E0001","sss", Gender.MALE,"ww","www",1);
        boolean save = employeeDAO.save(employee);
        System.out.println(save);
    }
}