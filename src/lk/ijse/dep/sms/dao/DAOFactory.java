package lk.ijse.dep.sms.dao;

import lk.ijse.dep.sms.dao.custom.UserRoleDAO;
import lk.ijse.dep.sms.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance(){
        return (daoFactory==null)?(daoFactory=new DAOFactory()):daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoType){
        switch (daoType){
            case EMPLOYEE:
                return (T) new EmployeeDAOImpl();
            case USER:
                return (T) new UserDAOImpl();
            case USER_ROLE:
                return (T) new UserRoleDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            case STUDENT:
                return (T) new StudentDAOImpl();
            case ROLE:
                return (T) new RoleDAOImpl();
            default:
                return null;
        }
    }
}
