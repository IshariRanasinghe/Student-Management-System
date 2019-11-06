package lk.ijse.dep.sms.business;

import lk.ijse.dep.sms.business.custom.impl.EmployeeBOImpl;
import lk.ijse.dep.sms.business.custom.impl.RoleBOImpl;
import lk.ijse.dep.sms.business.custom.impl.StudentBOImpl;
import lk.ijse.dep.sms.business.custom.impl.UserBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance(){
        return (boFactory==null)? (boFactory=new BOFactory()):boFactory;
    }

    public <T extends SuperBO> T getBO(BOTypes botype){
        switch (botype){
            case USER:
                return (T) new UserBOImpl();
            case EMPLOYEE:
                return (T) new EmployeeBOImpl();
            case STUDENT:
                return (T) new StudentBOImpl();
            case ROLE:
                return (T) new RoleBOImpl();
            default:
                return null;
        }
    }
}
