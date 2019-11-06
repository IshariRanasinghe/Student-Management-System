package lk.ijse.dep.sms.dao.custom.impl;


import lk.ijse.dep.sms.dao.DAOFactory;
import lk.ijse.dep.sms.dao.DAOTypes;
import lk.ijse.dep.sms.dao.custom.UserDAO;
import lk.ijse.dep.sms.entity.User;

class UserDAOImplTest {

    public static void main(String[] args) throws Exception {
        UserDAOImplTest userDAOImplTest = new UserDAOImplTest();
        userDAOImplTest.save();
        userDAOImplTest.isUser();

    }
    void isUser() throws Exception {
        UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOTypes.USER);
        User user = new User("","abc","1234",null);
        String result = userDAO.isUser(user);
        System.out.println("Is user :"+result);

    }


    void save() throws Exception {
        UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOTypes.USER);
        User user = new User("U0001","abc","1234","E0001");
        boolean save = userDAO.save(user);
        System.out.println(save);

    }
}