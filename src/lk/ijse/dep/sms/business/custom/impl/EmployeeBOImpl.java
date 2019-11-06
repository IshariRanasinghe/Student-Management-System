package lk.ijse.dep.sms.business.custom.impl;

import lk.ijse.dep.sms.business.custom.EmployeeBO;
import lk.ijse.dep.sms.dao.DAOFactory;
import lk.ijse.dep.sms.dao.DAOTypes;
import lk.ijse.dep.sms.dao.custom.EmployeeDAO;
import lk.ijse.dep.sms.dao.custom.QueryDAO;
import lk.ijse.dep.sms.dto.EmployeeDTO;
import lk.ijse.dep.sms.entity.CustomEntity;
import lk.ijse.dep.sms.entity.Employee;
import lk.ijse.dep.sms.entity.Gender;

import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {

    private EmployeeDAO employeeDAO = DAOFactory.getInstance().getDAO(DAOTypes.EMPLOYEE);
    private QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOTypes.QUERY);


    @Override
    public List<EmployeeDTO> findAllEmployees(String text) throws Exception {
        List<CustomEntity> allEmployees = queryDAO.getAllEmployees(text);
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for(CustomEntity employee:allEmployees){
            employeeDTOS.add(new EmployeeDTO(employee.getEmpId(),employee.getName(),employee.getAddress(),employee.getContact(),employee.getDesignation()));
        }
        return employeeDTOS;
    }

    @Override
    public EmployeeDTO findEmployee(String employeeId) throws Exception {
        Employee employee = employeeDAO.find(employeeId);
        return new EmployeeDTO(employee.getId(),employee.getName(),employee.getGender(),employee.getAddress(),employee.getContact(),employee.getDesignationId());
    }

    @Override
    public boolean saveEmployee(EmployeeDTO employee) throws Exception {
        return employeeDAO.save(new Employee(employee.getId(),employee.getName(),employee.getGender(),employee.getAddress(),employee.getContact(),employee.getDesignationId()));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employee) throws Exception {
        return employeeDAO.update(new Employee(employee.getId(),employee.getName(),employee.getGender(),employee.getAddress(),employee.getContact(),employee.getDesignationId()));
    }

    @Override
    public boolean deleteEmployee(String employeeId) throws Exception {
        return employeeDAO.delete(employeeId);
    }

    @Override
    public String getLastId() throws Exception {
        return employeeDAO.getLastEmployeeId();
    }

    @Override
    public List<String> employeesNotUsers() throws Exception {
        List<CustomEntity> employeesNotUsers = queryDAO.getEmployeesNotUsers();
        List<String> employees = new ArrayList<>();
        for (CustomEntity user:employeesNotUsers) {
            employees.add(user.getName());
        }
        return employees;
        //employees.add(new EmployeeDTO(employeesNotUsers))
    }

    @Override
    public String getEmployeeId(String selectedEmployee) throws Exception {
        return employeeDAO.getEmployeeId(selectedEmployee);
    }
}
