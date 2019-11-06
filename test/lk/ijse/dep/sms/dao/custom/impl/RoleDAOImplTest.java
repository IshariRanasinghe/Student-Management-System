package lk.ijse.dep.sms.dao.custom.impl;


import lk.ijse.dep.sms.dao.custom.RoleDAO;

import java.util.List;

class RoleDAOImplTest {
    public static void main(String[] args) throws Exception {
        RoleDAOImplTest roleDAOImplTest=new RoleDAOImplTest();
        roleDAOImplTest.rolesWithoutAdmin();

    }



    void rolesWithoutAdmin() throws Exception {
        RoleDAO roleDAO = new RoleDAOImpl();
        List<String> strings = roleDAO.rolesWithoutAdmin();
        System.out.println(strings);


    }
}