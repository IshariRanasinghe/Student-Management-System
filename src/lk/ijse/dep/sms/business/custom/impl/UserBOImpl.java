package lk.ijse.dep.sms.business.custom.impl;

import lk.ijse.dep.sms.business.custom.UserBO;
import lk.ijse.dep.sms.dao.DAOFactory;
import lk.ijse.dep.sms.dao.DAOTypes;
import lk.ijse.dep.sms.dao.custom.EmployeeDAO;
import lk.ijse.dep.sms.dao.custom.QueryDAO;
import lk.ijse.dep.sms.dao.custom.UserDAO;
import lk.ijse.dep.sms.dao.custom.UserRoleDAO;
import lk.ijse.dep.sms.db.DBConnection;
import lk.ijse.dep.sms.dto.RoleDTO;
import lk.ijse.dep.sms.dto.UserDTO;
import lk.ijse.dep.sms.dto.UserDTO2;
import lk.ijse.dep.sms.dto.UserDTO3;
import lk.ijse.dep.sms.entity.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOTypes.USER);
    EmployeeDAO employeeDAO = DAOFactory.getInstance().getDAO(DAOTypes.EMPLOYEE);
    UserRoleDAO userRoleDAO = DAOFactory.getInstance().getDAO(DAOTypes.USER_ROLE);
    QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOTypes.QUERY);


    @Override
    public UserDTO find(String userId) throws Exception {
        User user = userDAO.find(userId);
        return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getEmpId());
    }

    @Override
    public boolean saveUser(UserDTO user) throws Exception {
        return userDAO.save(new User(user.getId(), user.getUsername(), user.getPassword(), user.getEmployeeId()));
    }

    @Override
    public boolean updateUser(UserDTO user) throws Exception {
        return userDAO.update(new User(user.getId(), user.getUsername(), user.getPassword(), user.getEmployeeId()));
    }

    @Override
    public boolean deleteUser(String userId) throws Exception {
        return userDAO.delete(userId);
    }

    @Override
    public boolean configureUser(UserDTO user) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            String username = user.getUsername();
            String password = user.getPassword();

            boolean result = employeeDAO.save(new Employee("E0001", username, Gender.NO, "", "", 1));

            if (!result) {
                connection.rollback();
                throw new RuntimeException("Something went wrong");
            }

            result = userDAO.save(new User("U0001", "admin", password, "E0001"));

            if (!result) {
                connection.rollback();
                throw new RuntimeException("Something went wrong");
            }

            result = userRoleDAO.save(new UserRole("U0001", 1));

            if (!result) {
                connection.rollback();
                throw new RuntimeException("Something went wrong");
            }

            connection.commit();
            return true;


        } catch (Throwable e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;

        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String isUser(UserDTO user) throws Exception {
        User user1 = new User(user.getId(), user.getUsername(), user.getPassword(), user.getEmployeeId());
        return userDAO.isUser(user1);

    }

    @Override
    public List<String> getUserRoles(String userId) throws Exception {
        return queryDAO.getUserRoles(userId);
    }

    @Override
    public String getLastId() throws Exception {
        return userDAO.getLastUserId();
    }

    @Override
    public List<UserDTO2> usersList(String text) throws Exception {
        List<CustomEntity> users = queryDAO.usersList(text);
        List<UserDTO2> userDTOS = new ArrayList<>();
        for (CustomEntity user : users) {
            userDTOS.add(new UserDTO2(user.getUserId(), user.getUsername(), user.getEmpName(), user.getRoles()));
        }
        //System.out.println(userDTOS);
        return userDTOS;
    }

    @Override
    public boolean createUserAccount(UserDTO3 userDTO) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        try {

            connection.setAutoCommit(false);
            String id = userDTO.getId();
            String employeeId = userDTO.getEmployeeId();
            String username = userDTO.getUsername();
            String password = userDTO.getPassword();
            List<RoleDTO> roleDTOS = userDTO.getRoleDTOS();

            System.out.println(id+employeeId+username+password);

           boolean result = userDAO.save(new User(id, username, password,employeeId));

            if (!result) {
                connection.rollback();
                throw new RuntimeException("Something went wrong");
            }
            System.out.println("---"+roleDTOS);
            for (RoleDTO role : roleDTOS) {
                System.out.println(id+""+role.getId());
                result = userRoleDAO.save(new UserRole(id, role.getId()));
                System.out.println(result+""+role.getId());
                if (!result) {
                    System.out.println(result+""+role.getId());
                    connection.rollback();
                    throw new RuntimeException("Something went wrong");
                }
            }

            connection.commit();
            return true;
        } catch (Throwable e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;

        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
